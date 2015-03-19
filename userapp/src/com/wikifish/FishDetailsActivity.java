
package com.wikifish;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wikifish.adapter.CommentListAdapter;
import com.wikifish.entity.Comment;
import com.wikifish.entity.Fish;
import com.wikifish.image.ImageHandlingTask;

public class FishDetailsActivity extends Activity {
	
    CommentListAdapter adapter;
    Button bt_add_comment;
    private Fish mFish;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish_details);
        mFish = (Fish) getIntent().getSerializableExtra(Fish.KEY);
        if (mFish != null && MainActivity.session != null) {
            init();
        }
    }

    private void init() {
        setHelpButton();
        setTextInfo();
        setComments();
        setPicture();

    }
    
    
    private void setPicture() {
        final ImageView iv_fish = (ImageView) findViewById(R.id.iv_picture_fish);
        iv_fish.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(final View v) {
                 DialogImageZoom mDialog = new DialogImageZoom(FishDetailsActivity.this, iv_fish.getDrawable());
                 mDialog.show();

            }
        });
        if (!mFish.urlPicture.isEmpty()) {
            iv_fish.setTag(mFish.urlPicture);
            final ImageHandlingTask iHT_downloader = new ImageHandlingTask(iv_fish, this);
            iHT_downloader
                    .execute(mFish.urlPicture);
        }
    }

    private void setHelpButton() {
        final ImageView iv_help = (ImageView) findViewById(R.id.iv_help);
        final Dialog mDialog = new DialogHelp(this);
        iv_help.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(final View v) {
                mDialog.show();

            }
        });
    }

    private void setComments() {
    	System.out.println(MainActivity.session.token);
    	System.out.println(mFish.id);
    	
    	
    	CommentHelper.loadComments(MainActivity.session.token, new CommentHelper.Callback() {
			
			@Override
			public void call(List<Comment> comments) {
				mFish.comments = new ArrayList<Comment>();
				mFish.comments.addAll(comments);
					
		        final ListView lv_comments = (ListView) findViewById(R.id.lv_comments);
		        adapter = new CommentListAdapter(FishDetailsActivity.this, mFish.comments);
		        lv_comments.setAdapter(adapter);
		        bt_add_comment = (Button) findViewById(R.id.bt_add_comment);
		        bt_add_comment.setOnClickListener(new OnClickListener() {

		            @Override
		            public void onClick(final View v) {
		                // TODO adiciona um novo comentario

		            }

		        });
			}
		}, mFish.id);
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	

    }

    private void setTextInfo() {
        final TextView tv_name = (TextView) findViewById(R.id.tv_usual_name);
        tv_name.setText(mFish.usualName);
        final TextView tv_scientific = (TextView) findViewById(R.id.tv_scientific_name);
        tv_scientific.setText(mFish.cientificName);
    }

}
