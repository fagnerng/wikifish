/**
 * 
 */

package com.wikifish.listadapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wikifish.FishDetailsActivity;
import com.wikifish.R;
import com.wikifish.entity.Fish;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * @author fagnerng
 */
@SuppressLint("ViewHolder")
public class ListAllAdapter extends BaseAdapter {

    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    public static ArrayList<Fish> allFishs;

    public ListAllAdapter(final Context context, final JSONArray json) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
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
    public int getCount() {
        if (allFishs.isEmpty()) {
            return 1;
        }
        return allFishs.size();
    }

    @Override
    public Object getItem(final int arg0) {
        return allFishs.get(arg0);
    }

    @Override
    public long getItemId(final int arg0) {
        return arg0;
    }

    @Override
    public View getView(final int position, final View arg1, final ViewGroup arg2) {
        if (allFishs.isEmpty()) {
            return isEmptyView();
        }
        final View view = mLayoutInflater.inflate(R.layout.fragment_fish, null);
        final TextView tvnamePreview = (TextView) view.findViewById(R.id.tv_name_preview);
        tvnamePreview.setText(allFishs.get(position).getUsualName());
        view.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(final View v) {
                ((Activity) mContext)
                        .startActivity(new Intent(mContext, FishDetailsActivity.class));

            }
        });
        // final ImageView iv_fish = (ImageView)
        // view.findViewById(R.id.iv_picture_fish);
        // iv_fish.setTag("http://www.tonyhakim.com.au/wp-content/uploads/2014/09/Purple-fish.jpg");
        // final ImageHandlingTask iHT_downloader = new
        // ImageHandlingTask(iv_fish, mContext);
        // iHT_downloader
        // .execute("http://www.tonyhakim.com.au/wp-content/uploads/2014/09/Purple-fish.jpg");
        return view;
    }

    private View isEmptyView() {
        final View view = mLayoutInflater.inflate(R.layout.fragment_is_empty, null);

        return view;
    }

}
