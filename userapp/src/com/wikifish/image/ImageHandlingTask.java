
package com.wikifish.image;

import java.io.InputStream;
import java.lang.ref.WeakReference;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.wikifish.R;
import com.wikifish.persistence.cache.CacheUtil;
import com.wikifish.persistence.cache.MemoryCache;

public class ImageHandlingTask extends AsyncTask<String, Void, Bitmap> {

   // private final Logger mLogger = new Logger(getClass().getName());

    private final WeakReference<ImageView> imageViewReference;
    private final Context mContext;
    private String mUrl;
    private final int mHeight;
    private final int mWidth;

    public ImageHandlingTask(final ImageView imageView, final Context context) {
        imageViewReference = new WeakReference<ImageView>(imageView);
        mHeight = imageView.getHeight();
        mWidth = imageView.getWidth();
        mContext = context;
    }

    @Override
    // Actual download method, run in the task thread
    protected Bitmap doInBackground(final String... params) {
        Bitmap bitmap = null;
        mUrl = params[0];

        if (mUrl != null && !TextUtils.isEmpty(mUrl)) {
           // mLogger.debug(mUrl);
            // First, check if it's available in memory cache
            bitmap = MemoryCache.getInstance().getBitmapFromMemCache(mUrl);

            if (bitmap == null) {
               // mLogger.debug("bitmap null0");
                // Check against file system cache
                if (CacheUtil.hasCachedFile(mUrl, mContext) != null) {
                    //mLogger.debug("Cache not null");
                    final InputStream cachedFile = CacheUtil.getCachedFile(mUrl, mContext);
                    bitmap = CacheUtil.decodeImage(cachedFile, mWidth, mHeight);
                }

                // not in cache, download
                if (bitmap == null) {
                   // mLogger.debug("bitmap null1");
                    final ImageDownloader manager = new ImageDownloader(mUrl);
                    bitmap = manager.downloadBitmap();

                    if (bitmap != null) {
                       // mLogger.debug("bitmap not null");
                        // add it to caches
                        MemoryCache.getInstance().addBitmapToMemoryCache(mUrl, bitmap);
                        CacheUtil.createCachedBitmapFile(mUrl, bitmap, mContext);
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
            final ImageView imageView = imageViewReference.get();
            if (imageView != null && imageView.getTag() != null && imageView.getTag().equals(mUrl)) {
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                    imageView.setVisibility(View.VISIBLE);
                } else {
                    imageView.setImageDrawable(imageView.getContext().getResources()
                            .getDrawable(R.drawable.default_fish));

                }
            }

        }
    }

}
