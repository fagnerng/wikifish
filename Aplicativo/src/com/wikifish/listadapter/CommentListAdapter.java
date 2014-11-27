/**
 * 
 */

package com.wikifish.listadapter;

import java.util.ArrayList;
import java.util.Collections;

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

/**
 * @author fagnerng
 */
@SuppressLint("ViewHolder")
public class CommentListAdapter extends BaseAdapter {

    private Context mContext;
    private final LayoutInflater mLayoutInflater;
    public static ArrayList<Comment> comments;

    public CommentListAdapter(Context context) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(mContext);
        comments = new ArrayList<Comment>();
        for (int i = 0; i < 5; i++) {
            comments.add(new Comment(i, "Usuario " + i, "Comentario " + i, i,
                    i % 2 != 0));
        }
        Collections.sort(comments);
    }

    @Override
    public int getCount() {
        return comments.size();
    }

    @Override
    public Object getItem(int arg0) {
        return comments.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return comments.get(arg0).getId();
    }

    @Override
    public View getView(int position, View arg1, ViewGroup arg2) {
        View view = mLayoutInflater.inflate(R.layout.fragment_comments, null);
        TextView tvOwner = (TextView) view.findViewById(R.id.tv_owner);
        TextView tvComment = (TextView) view.findViewById(R.id.tv_comment);
        TextView numberOflikes = (TextView) view
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
            public void onClick(View v) {
                comment.like();
                Collections.sort(comments);
                notifyDataSetChanged();
            }
        });
        dislike.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                comment.dislike();
                Collections.sort(comments);
                notifyDataSetChanged();

            }
        });
        return view;
    }
}
