/**
 * 
 */

package com.wikifish.adapter;

import java.util.Collections;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.wikifish.MainActivity;
import com.wikifish.R;
import com.wikifish.entity.Comment;
import com.wikifish.utils.WikiFishUtils;

/**
 * @author fagnerng
 */
@SuppressLint("ViewHolder")
public class CommentListAdapter extends BaseAdapter {

	private final Context mContext;
	private final LayoutInflater mLayoutInflater;
	private final List<Comment> comments;

	public CommentListAdapter(final Context context,
			final List<Comment> comments2) {
		mContext = context;
		mLayoutInflater = LayoutInflater.from(mContext);
		this.comments = comments2;

		Collections.sort(comments);
	}

	@Override
	public int getCount() {
		if (comments == null)
			return 0;
		return comments.size();
	}

	@Override
	public Object getItem(final int arg0) {
		return comments.get(arg0);
	}

	@Override
	public long getItemId(final int arg0) {
		return comments.get(arg0).id.hashCode();
	}

	@Override
	public View getView(final int position, final View arg1,
			final ViewGroup arg2) {
		final View view = mLayoutInflater.inflate(R.layout.fragment_comments,
				null);
		final TextView tvOwner = (TextView) view.findViewById(R.id.tv_owner);
		final TextView tvComment = (TextView) view
				.findViewById(R.id.tv_comment);
		final Button numberOflikes = (Button) view
				.findViewById(R.id.bt_number_of_likes);
		final Comment comment = comments.get(position);
		tvOwner.setText(comment.owner);
		tvComment.setText(comment.comment);

		numberOflikes.setText(comment.commentLikes.size() + "");
		if (!comment.commentLikes.contains(MainActivity.session.user.email)) {
			numberOflikes.setBackgroundResource(R.color.backgroundLike);

			numberOflikes.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					comment.like();
					String paramns = "id=" + comment.id + "&user="
							+ MainActivity.session.user.email + "&like="
							+ MainActivity.session.user.email;
					String url = WikiFishUtils.getServerURL()
							+ "/api/comment/like?";
					new SendPost().equals(url + paramns);
					// System.out.println(WikiFishUtils.downloadUrl(url +
					// paramns));

					numberOflikes.setText("+1");
					new Handler().postDelayed(new Runnable() {

						@Override
						public void run() {
							Collections.sort(comments);
							notifyDataSetInvalidated();

						}
					}, 500);

				}

			});
		}
		// TODO mudar esse -1 para o id do usuario logado

		return view;
	}

	private class SendPost extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {
			System.out.println(WikiFishUtils.downloadUrl(params[0]));

			return "";
		}
	}
}
