
package com.wikifish;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.wikifish.listadapter.ListAllAdapter;

public class ListAllActivity extends Activity {
    ListAllAdapter adapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all);

        final ListView lvAll = (ListView) findViewById(R.id.lv_list_all);
        adapter = new ListAllAdapter(this);
        lvAll.setAdapter(adapter);
    }
}
