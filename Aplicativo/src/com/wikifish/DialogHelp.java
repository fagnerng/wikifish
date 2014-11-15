package com.wikifish;

import com.wikifish.listadapter.HelpListAdapter;

import android.app.Dialog;
import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class DialogHelp extends Dialog {

	public DialogHelp(Context context) {
		super(context);
		setTitle(R.string.app_name);
		setContentView(R.layout.dialog_help);
		setCanceledOnTouchOutside(true);
		BaseAdapter adapter = new HelpListAdapter(context);
		ListView lv_help = (ListView) findViewById(R.id.lv_help);
		lv_help.setDividerHeight(0);
		lv_help.setAdapter(adapter);
	}

}
