
package com.wikifish.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.wikifish.network.HttpClientConnection;
import com.wikifish.utils.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;

import java.io.InputStream;

public class ImageDownloader {

    private final Logger mLogger = new Logger(getClass().getName());

    private final HttpClientConnection mNetworkConn;

    public ImageDownloader(final String url) {
        mNetworkConn = new HttpClientConnection(url);
    }

    /**
     * Will get the image asynchronously and add to image view
     */
    public Bitmap downloadBitmap() {
        Bitmap bitmap = null;

        try {
            final HttpResponse response = mNetworkConn.execute();
            final int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                return null;
            }

            final HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream inputStream = null;
                try {
                    inputStream = entity.getContent();
                    bitmap = BitmapFactory.decodeStream(inputStream);
                    return bitmap;
                } finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    entity.consumeContent();
                }
            }
        } catch (final Exception e) {
            mLogger.error(e.getMessage());
        }

        return bitmap;
    }

}
