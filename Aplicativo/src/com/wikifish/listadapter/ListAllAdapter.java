/**
 * 
 */

package com.wikifish.listadapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wikifish.FishDetailsActivity;
import com.wikifish.R;
import com.wikifish.entity.Fish;
import com.wikifish.image.ImageHandlingTask;

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
    private final Drawable defaultAlbumThumbnail;

    public ListAllAdapter(final Context context, final JSONArray json) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        defaultAlbumThumbnail = mContext.getResources()
                .getDrawable(R.drawable.default_fish);

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
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        final Fish actualFish = allFishs.get(position);
        View row = convertView;
        ViewHolder holder;
        if (allFishs.isEmpty()) {
            return isEmptyView();
        }
        else if (row == null) {
            final ViewHolder viewHolder = new ViewHolder();
            row = mLayoutInflater.inflate(R.layout.fragment_fish, null);
            viewHolder.icon = (ImageView) row.findViewById(R.id.iv_picture_fish);
            viewHolder.name = (TextView) row.findViewById(R.id.tv_name_preview);
            row.setTag(viewHolder);
        } else {
            holder = (ViewHolder) row.getTag();
            holder.icon = (ImageView) row.findViewById(R.id.iv_picture_fish);
            holder.icon.clearAnimation();
            holder.icon.destroyDrawingCache();
            holder.icon.setImageDrawable(defaultAlbumThumbnail);
            holder.icon.setTag(null);
        }
        holder = (ViewHolder) row.getTag();
        final String url = actualFish.getUrlPicture();
        if (holder.icon != null && url != null && !url.isEmpty()) {
            holder.icon.setTag(url);
            new ImageHandlingTask(holder.icon, mContext).execute(url);
        }
        holder.name.setText(actualFish.getUsualName());
        row.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(final View v) {
                final Intent i = new Intent(mContext, FishDetailsActivity.class);
                i.putExtra(Fish.KEY, actualFish);
                ((Activity) mContext)
                        .startActivity(i);

            }
        });

        return row;
    }

    private View isEmptyView() {
        final View view = mLayoutInflater.inflate(R.layout.fragment_is_empty, null);

        return view;
    }

    class ViewHolder {
        ImageView icon;
        TextView name;
    }
}
