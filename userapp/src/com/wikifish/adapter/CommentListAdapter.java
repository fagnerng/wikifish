/**
 * 
 */

package com.wikifish.adapter;

import java.util.Collections;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wikifish.R;
import com.wikifish.entity.Comment;

/**
 * @author fagnerng
 */
@SuppressLint("ViewHolder")
public class CommentListAdapter extends BaseAdapter {

    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private final List<Comment> comments;

    public CommentListAdapter(final Context context, final List<Comment> comments2) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        this.comments = comments2;
        
        Collections.sort(comments2);
    }

    @Override
    public int getCount() {
    	if(comments == null) return 0;
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
    public View getView(final int position, final View arg1, final ViewGroup arg2) {
        final View view = mLayoutInflater.inflate(R.layout.fragment_comments, null);
        final TextView tvOwner = (TextView) view.findViewById(R.id.tv_owner);
        final TextView tvComment = (TextView) view.findViewById(R.id.tv_comment);
        final Button numberOflikes = (Button) view
                .findViewById(R.id.bt_number_of_likes);
        final Comment comment = comments.get(position);
        tvOwner.setText(comment.owner);
        tvComment.setText(comment.comment);

        numberOflikes.setText(comment.commentLikes.size() + "");
        if(comment.commentLikes.size()>1){
        	numberOflikes.setBackgroundResource(R.color.backgroundGreen);
        }
        numberOflikes.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				comment.like();
				numberOflikes.setText(comment.commentLikes.size() + "");
		        if(comment.commentLikes.size()>1){
		        	numberOflikes.setBackgroundResource(R.color.backgroundGreen);	
		        }
			}
		});
        
        // TODO mudar esse -1 para o id do usuario logado
        
        return view;
    }
}
