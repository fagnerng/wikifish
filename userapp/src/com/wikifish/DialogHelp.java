
package com.wikifish;

import android.app.Dialog;
import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.wikifish.adapter.HelpListAdapter;

public class DialogHelp extends Dialog {

    public DialogHelp(final Context context) {
        super(context);
        setTitle(R.string.app_name);
        setContentView(R.layout.dialog_help);
        setCanceledOnTouchOutside(true);
        final BaseAdapter adapter = new HelpListAdapter(context);
        final ListView lv_help = (ListView) findViewById(R.id.lv_help);
        lv_help.setDividerHeight(0);
        lv_help.setAdapter(adapter);
    }

}
