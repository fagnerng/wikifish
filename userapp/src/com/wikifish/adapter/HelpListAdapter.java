/**
 * 
 */

package com.wikifish.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wikifish.R;

/**
 * @author fagnerng
 */
public class HelpListAdapter extends BaseAdapter {

    private Context mContext;
    private final LayoutInflater mLayoutInflater;
    private ArrayList<String> allOptions;
    private ArrayList<Integer> indexOfTitles;
    private Resources mResource;

    private int[] alltitleID = {
            R.string.title_help_feeding_habits,
            R.string.title_help_reproduction,
            R.string.title_help_aquarium_lighting,
            R.string.title_help_temperament,
            R.string.title_help_aquarium_setup,
            R.string.title_help_swimming_habits, R.string.title_help_other
    };

    int[] allDescriptionID = {
            R.array.description_feeding_habits,
            R.array.description_reproduction,
            R.array.description_aquarium_lighting,
            R.array.description_temperament,
            R.array.description_aquarium_setup,
            R.array.description_swimming_habits, R.array.description_other
    };

    public HelpListAdapter(Context context) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(mContext);

        mResource = mContext.getResources();
        populate();
    }

    private void populate() {
        allOptions = new ArrayList<String>();
        indexOfTitles = new ArrayList<Integer>();

        for (int i = 0; i < alltitleID.length; i++)
            populate(i, alltitleID[i], allDescriptionID[i]);
    }

    private void populate(int count, int titleID, int array_descriptionID) {
        String[] description = mResource.getStringArray(array_descriptionID);
        String title = mResource.getString(titleID);
        allOptions.add(title);
        for (String i : description)
            allOptions.add(i);
        indexOfTitles.add(allOptions.indexOf(title));

    }

    @Override
    public int getCount() {
        int value = allOptions.size();
        return value;
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return allOptions.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    @Override
    public View getView(int position, View arg1, ViewGroup arg2) {
        if (indexOfTitles.contains(position)) {
            return getTitleView(position);
        } else {
            return getDescriptionView(position);
        }
    }

    private View getTitleView(int position) {

        View view = mLayoutInflater.inflate(R.layout.view_title_adapter, null);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_title.setText(allOptions.get(position));
        return view;

    }

    private View getDescriptionView(int position) {

        View view = mLayoutInflater.inflate(R.layout.view_description_adapter, null);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_description);
        tv_title.setText(allOptions.get(position));
        ImageView iv_description = (ImageView) view.findViewById(R.id.iv_icon);
        iv_description.setImageResource(R.drawable.forms);
        return view;

    }

}
