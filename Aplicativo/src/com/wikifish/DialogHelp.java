package com.wikifish;

import android.app.Dialog;
import android.content.Context;

public class DialogHelp extends Dialog {

	public DialogHelp(Context context) {
		super(context);
		setTitle(R.string.app_name);
		setContentView(R.layout.activity_help);
		setCanceledOnTouchOutside(true);

	}

}
