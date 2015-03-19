package com.wikifish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wikifish.entity.Comment;
import com.wikifish.utils.WikiFishUtils;

public class CommentHelper {

	private static final String TAG = "CommentHelper";

	public static void loadComments(String token, CommentHelper.Callback callback, String parent) {
		new CommentAsyncTask().execute(token,parent, callback);
	}

	public static interface Callback {
		public void call(List<Comment> comments);
	}

	private static class CommentAsyncTask extends
			AsyncTask<Object, Integer, String> {

		private CommentHelper.Callback callback;

		@Override
		protected String doInBackground(Object... params) {
			this.callback = (CommentHelper.Callback) params[2];
			return getData((String) params[0], (String) params[1]);
		}

		@SuppressWarnings("unchecked")
		protected void onPostExecute(String result) {
			Log.d(TAG, result);
			Type listType = new TypeToken<List<Comment>>() {
			}.getType();
			try {
				callback.call((List<Comment>) new Gson()
						.fromJson(result, listType));
			} catch (Exception e) {
				e.printStackTrace();
				callback.call(new ArrayList<Comment>());
			}
		}
	}

	private static String getData(String token, String parent) {
		System.out.println(WikiFishUtils.getServerURL()
				+ "/api/comment/?parent="+parent);
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(WikiFishUtils.getServerURL()
				+ "/api/comment/?parent="+parent);

		try {
			if(token== null)token = "";
			httpget.addHeader("Cookie", "ua_session_token=" + token + ";");
			HttpResponse response = httpclient.execute(httpget);
			return getStringFromInputStream(response.getEntity().getContent());
		} catch (ClientProtocolException e) {

		} catch (IOException e) {

		}

		return "";
	}

	/** Convert InputStream to String */
	private static String getStringFromInputStream(InputStream is) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {
			br = new BufferedReader(new InputStreamReader(is));

			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();
	}

}
