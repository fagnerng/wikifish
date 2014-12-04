/**
 * 
 */

package com.wikifish.listadapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wikifish.R;
import com.wikifish.entity.Comment;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author fagnerng
 */
@SuppressLint("ViewHolder")
public class CommentListAdapter extends BaseAdapter {

    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private final ArrayList<Comment> comments;

    public CommentListAdapter(final Context context, final ArrayList<Comment> comments) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        this.comments = comments;

        Collections.sort(comments);
    }

    @Override
    public int getCount() {
        return comments.size();
    }

    @Override
    public Object getItem(final int arg0) {
        return comments.get(arg0);
    }

    @Override
    public long getItemId(final int arg0) {
        return comments.get(arg0).getId();
    }

    @Override
    public View getView(final int position, final View arg1, final ViewGroup arg2) {
        final View view = mLayoutInflater.inflate(R.layout.fragment_comments, null);
        final TextView tvOwner = (TextView) view.findViewById(R.id.tv_owner);
        final TextView tvComment = (TextView) view.findViewById(R.id.tv_comment);
        final TextView numberOflikes = (TextView) view
                .findViewById(R.id.tv_number_of_likes);
        final Comment comment = comments.get(position);
        tvOwner.setText(comment.getOwner());
        tvComment.setText(comment.getComment());

        numberOflikes.setText(comment.getNumberOfLike() + "");

        ImageButton like, dislike;
        like = (ImageButton) view.findViewById(R.id.ib_like);
        dislike = (ImageButton) view.findViewById(R.id.ib_dislike);
        like.setEnabled(!comment.isLiked());
        dislike.setEnabled(comment.isLiked());
        like.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(final View v) {
                comment.like();
                Collections.sort(comments);
                notifyDataSetChanged();
            }
        });
        dislike.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(final View v) {
                comment.dislike();
                Collections.sort(comments);
                notifyDataSetChanged();

            }
        });
        return view;
    }
}
