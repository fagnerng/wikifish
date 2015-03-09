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
import com.wikifish.entity.Fish;

public class FishHelper {
	private static final String TAG = "ArticleHelper";
	public static void loadArticles(String token, FishHelper.Callback callback) {
		new FishAsyncTask().execute(token, callback);
	}
	
	public static interface Callback {
		public void call(List<Fish> articles);
	}
	
	private static class FishAsyncTask extends AsyncTask<Object, Integer, String>{
		
		private FishHelper.Callback callback;
		
		@Override
		protected String doInBackground(Object... params) {
			this.callback = (FishHelper.Callback) params[1];
			return getData((String) params[0]);
		}
		
		@SuppressWarnings("unchecked")
		protected void onPostExecute(String result) {
			Log.d(TAG, result);
			Type listType = new TypeToken<List<Fish>>() {}.getType();
			try{
			callback.call((List<Fish>) new Gson().fromJson(result, listType));
			}catch(Exception e){
				e.printStackTrace();
				callback.call(new ArrayList<Fish>());
			}
		}
	}
	
	private static String getData(String token) {
		HttpClient httpclient = new DefaultHttpClient();
		//HttpContext ctx = new BasicHttpContext();
		HttpGet httpget = new HttpGet("http://wikifish.herokuapp.com/fish");
		
		try {
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