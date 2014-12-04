
package com.wikifish;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListView;

import com.wikifish.asynctask.GetJSONTask;
import com.wikifish.asynctask.GetJSONTask.GetJSONInterface;
import com.wikifish.listadapter.ListAllAdapter;

import org.json.JSONArray;

public class ListAllActivity extends Activity implements GetJSONInterface {
    ListAllAdapter adapter;
    ProgressDialog dialog;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all);

        final GetJSONTask task = new GetJSONTask(this);
        task.execute("http://wikifish.herokuapp.com/fish");
        dialog = new ProgressDialog(this);
        dialog.setIndeterminate(true);
        dialog.show();
    }

    @Override
    public void callbackGetJSON(final JSONArray json) {
        if (json.length() > 0) {

        }
        final ListView lvAll = (ListView) findViewById(R.id.lv_list_all);
        adapter = new ListAllAdapter(this, json);
        lvAll.setAdapter(adapter);

        dialog.dismiss();
    }
}
