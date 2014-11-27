
package com.wikifish;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.wikifish.entity.Comment;
import com.wikifish.image.ImageHandlingTask;
import com.wikifish.listadapter.CommentListAdapter;

public class MainActivity extends Activity {

    CommentListAdapter adapter;
    EditText et_comment;
    Button bt_add_comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView iv_fish = (ImageView) findViewById(R.id.iv_picture_fish);
        iv_fish.setTag("http://www.tonyhakim.com.au/wp-content/uploads/2014/09/Purple-fish.jpg");
        ImageHandlingTask iHT_downloader = new ImageHandlingTask(iv_fish, this);
        iHT_downloader
                .execute("http://www.tonyhakim.com.au/wp-content/uploads/2014/09/Purple-fish.jpg");
        ImageView iv_help = (ImageView) findViewById(R.id.iv_help);
        final Dialog mDialog = new DialogHelp(this);
        et_comment = (EditText) findViewById(R.id.et_comment);
        et_comment.setSelected(false);

        et_comment.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                et_comment.setEnabled(true);

                bt_add_comment.setEnabled(true);

            }
        });
        iv_help.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mDialog.show();

            }
        });

        ListView lv_comments = (ListView) findViewById(R.id.lv_comments);
        adapter = new CommentListAdapter(this);
        lv_comments.setAdapter(adapter);
        bt_add_comment = (Button) findViewById(R.id.bt_add_comment);
        bt_add_comment.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                addNewComment();
                et_comment.setText("");
                bt_add_comment.setEnabled(false);

            }

        });

    }

    private void addNewComment() {

        String comment = et_comment.getText().toString();
        if (comment.trim().isEmpty()) {
            Toast.makeText(this, "comment not be empty", Toast.LENGTH_SHORT)
                    .show();
        } else {
            if (CommentListAdapter.comments != null) {
                CommentListAdapter.comments.add(new Comment(-1, "my user",
                        comment.trim(), 0, false));
                adapter.notifyDataSetChanged();
            }
        }

    }
}
