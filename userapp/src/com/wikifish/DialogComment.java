package com.wikifish;

import java.io.IOException;

import com.wikifish.utils.WikiFishUtils;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class DialogComment extends Dialog {
	public String _id;
	public DialogComment(Context context) {
		super(context);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
        setTitle("");
        setContentView(R.layout.dialog_comment);
        setCanceledOnTouchOutside(false);
        final Button makecomment = (Button)findViewById(R.id.bt_comment);
        final Button cancel = (Button)findViewById(R.id.bt_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dismiss();
				
			}
		});
        final EditText commentText = (EditText)findViewById(R.id.et_comment);
        makecomment.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				System.out.println(commentText.getText());
				String Url = WikiFishUtils.getServerURL()+"/api/comment/";
				String paramns = "text=" +commentText.getText()+
						"&user=" + MainActivity.session.user.email+
						"&parent="+_id;
				new SendPost().execute(Url,paramns);
				dismiss();
				
			}
		});
	}

	private class SendPost extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {

			try {
				System.out
						.println(WikiFishUtils.sendPOST(params[0], params[1]));

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return "";
		}
	}
}
