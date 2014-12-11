
package com.wikifish;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.wikifish.asynctask.GetJSONArrayTask;
import com.wikifish.asynctask.GetJSONArrayTask.GetJSONInterface;
import com.wikifish.listadapter.ListAllAdapter;

import org.json.JSONArray;

public class ListAllActivity extends Activity implements GetJSONInterface {
    ListAllAdapter adapter;
    ProgressDialog dialog;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all);

        final GetJSONArrayTask task = new GetJSONArrayTask(this);
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

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list_all, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        final int id = item.getItemId();
        if (id == R.id.search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
