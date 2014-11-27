package com.wikifish.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.wikifish.R;
import com.wikifish.persistence.cache.CacheUtil;
import com.wikifish.persistence.cache.MemoryCache;
import com.wikifish.utils.Logger;

import java.io.InputStream;
import java.lang.ref.WeakReference;

public class ImageHandlingTask extends AsyncTask<String, Void, Bitmap> {
    private final WeakReference<ImageView> imageViewReference;
    private Context mContext;
    private String mUrl;
    private int mHeight;
    private int mWidth;
    private final String TAG = getClass().getName();

    public ImageHandlingTask(ImageView imageView, Context context) {
        imageViewReference = new WeakReference<ImageView>(imageView);
        mHeight = imageView.getHeight();
        mWidth = imageView.getWidth();
        mContext = context;
    }

    @Override
    // Actual download method, run in the task thread
    protected Bitmap doInBackground(String... params) {
        Bitmap bitmap = null;
        mUrl = params[0];

        if (mUrl != null && !TextUtils.isEmpty(mUrl)) {
            Logger.log(TAG, mUrl);
            // First, check if it's available in memory cache
            bitmap = MemoryCache.getInstance().getBitmapFromMemCache(mUrl);

            if (bitmap == null) {
                Logger.log(TAG, "bitmap null0");
                // Check against file system cache
                if (CacheUtil.hasCachedFile(mUrl, mContext) != null) {
                    Logger.log(TAG, "Cache not null");
                    InputStream cachedFile = CacheUtil.getCachedFile(mUrl,
                            mContext);
                    bitmap = CacheUtil.decodeImage(cachedFile, mWidth, mHeight);
                }

                // not in cache, download
                if (bitmap == null) {
                    Logger.log(TAG, "bitmap null1");
                    ImageDownloader manager = new ImageDownloader(mUrl);
                    bitmap = manager.downloadBitmap();

                    if (bitmap != null) {
                        Logger.log(TAG, "bitmap not null");
                        // add it to caches
                        MemoryCache.getInstance().addBitmapToMemoryCache(mUrl,
                                bitmap);
                        CacheUtil
                                .createCachedBitmapFile(mUrl, bitmap, mContext);
                    }
                }
            }
        }

        return bitmap;
    }

    @Override
    // Once the image is download, associates it to the imageView
    protected void onPostExecute(Bitmap bitmap) {
        if (isCancelled()) {
            bitmap = null;
        }

        if (imageViewReference != null) {
            ImageView imageView = imageViewReference.get();
            if (imageView != null && imageView.getTag() != null
                    && imageView.getTag().equals(mUrl)) {
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                    imageView.setVisibility(View.VISIBLE);
                } else {
                    imageView.setImageDrawable(imageView.getContext()
                            .getResources()
                            .getDrawable(R.drawable.default_fish));

                }
            }

        }
    }

}
