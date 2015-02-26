
package com.wikifish;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.wikifish.asynctask.GetJSONArrayTask;
import com.wikifish.asynctask.GetJSONArrayTask.GetJSONInterface;
import com.wikifish.entity.Fish;
import com.wikifish.listadapter.ListAllAdapter;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class ListAllActivity extends Activity implements GetJSONInterface {
    private ListAllAdapter adapter;
    private ProgressDialog dialog;
    private ArrayList<Fish> allFishs;
    private EditText searchText;
    private Button searchButton;
    private OnClickListener searchListener;
    private OnClickListener clearListener;
    private final String TAG = getClass().getName();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all);

        final GetJSONArrayTask task = new GetJSONArrayTask(this);
        task.execute("http://wikifish.herokuapp.com/fish");
        dialog = new ProgressDialog(this);
        dialog.setIndeterminate(true);
        dialog.show();
        searchText = (EditText) findViewById(R.id.et_search);
        searchButton = (Button) findViewById(R.id.bt_search);

        searchListener = new OnClickListener() {

            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(final View v) {
                final String seach = searchText.getText().toString().toLowerCase();
                Log.d(TAG, seach);
                if (seach.isEmpty()) {
                    Log.d(TAG, "empty");
                    return;
                }
                else {
                    Log.d(TAG, "no empty");
                    if (adapter.fishsToDisplay != null) {

                        if (allFishs == null) {
                            Log.d(TAG, "first use");
                            allFishs = adapter.fishsToDisplay;
                        }
                        final ArrayList<Fish> temp = new ArrayList<Fish>();
                        for (final Fish fish : allFishs) {
                            if (fish.getUsualName().toLowerCase().indexOf(seach) >= 0
                                    || fish.getScientificName().toLowerCase().indexOf(seach) >= 0) {
                                temp.add(fish);
                                Log.d(TAG, fish.getUsualName());
                            }
                        }
                        Log.d(TAG, temp.size() + "");
                        adapter.setFishToDisplay(temp);
                        searchButton.setText(R.string.button_clear);
                        searchButton.setOnClickListener(clearListener);
                    }
                }

            }
        };

        clearListener = new OnClickListener() {

            @Override
            public void onClick(final View v) {
                adapter.setFishToDisplay(allFishs);
                searchButton.setText(R.string.button_search);
                searchText.setText("");
                searchButton.setOnClickListener(searchListener);

            }
        };

        searchButton.setOnClickListener(searchListener);
    }

    private void getAllFishs(final JSONArray json) {
        allFishs = new ArrayList<Fish>();
        for (int i = 0; i < json.length(); i++) {
            try {
                allFishs.add(new Fish(json.getJSONObject(i)));
            } catch (final JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

    @Override
    public void callbackGetJSON(final JSONArray json) {
        if (json.length() > 0) {

        }
        final ListView lvAll = (ListView) findViewById(R.id.lv_list_all);
        getAllFishs(json);
        adapter = new ListAllAdapter(this);
        adapter.setFishToDisplay(allFishs);
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
