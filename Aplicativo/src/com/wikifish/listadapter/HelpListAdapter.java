/**
 * 
 */
package com.wikifish.listadapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.wikifish.R;

/**
 * @author fagnerng
 * 
 */
public class HelpListAdapter extends BaseAdapter {

	private Context mContext;
	private final LayoutInflater mLayoutInflater;
	private HashMap<String, String[]> allOptions;
	private ArrayList<Integer> indexOfTitles;
	private Resources mResource;
	
	private int[] alltitleID = { R.string.title_help_feeding_habits,
			R.string.title_help_reproduction,
			R.string.title_help_aquarium_lighting,
			R.string.title_help_temperament,
			R.string.title_help_aquarium_setup,
			R.string.title_help_swimming_habits, R.string.title_help_other };
	
	int[] allDescriptionID = { R.array.description_feeding_habits,
			R.array.description_reproduction,
			R.array.description_aquarium_lighting,
			R.array.description_temperament,
			R.array.description_aquarium_setup,
			R.array.description_swimming_habits, R.array.description_other };

	public HelpListAdapter(Context context) {
		this.mContext = context;
		this.mLayoutInflater = LayoutInflater.from(mContext);

		mResource = mContext.getResources();
		populate();
	}

	private void populate() {
		allOptions = new HashMap<String, String[]>();
		indexOfTitles = new ArrayList<Integer>();

		for (int i = 0; i < alltitleID.length; i++)
			populate(i, alltitleID[i], allDescriptionID[i]);
	}

	private void populate(int count, int titleID, int array_descriptionID) {
		String[] description = mResource.getStringArray(array_descriptionID);
		String title = mResource.getString(titleID);
		indexOfTitles.add(getNextIndex(count));
		allOptions.put(title, description);

	}

	private int getNextIndex(int count) {
		if(indexOfTitles.isEmpty())
			return 0;
		else{
			return 1;
		}
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		View view = mLayoutInflater.inflate(R.layout.activity_main, null);

		return view;
	}

}
