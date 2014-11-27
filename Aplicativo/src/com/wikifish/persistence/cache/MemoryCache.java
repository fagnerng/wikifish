
package com.wikifish.persistence.cache;

import android.graphics.Bitmap;
import android.util.LruCache;

public class MemoryCache {

    private LruCache<String, Bitmap> mMemoryCache;

    private static MemoryCache sInstance;

    /**
     * The unique class instance
     */
    static {
        sInstance = new MemoryCache();
    }

    /**
     * Returns the class instance.
     * 
     * @return instance the only instance of this class
     */
    public static MemoryCache getInstance() {
        return sInstance;
    }

    private MemoryCache() {
        // Get max available VM memory, exceeding this amount will throw an
        // OutOfMemory exception. Stored in kilobytes as LruCache takes an
        // int in its constructor.
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        // Use 1/8th of the available memory for this memory cache.
        final int cacheSize = maxMemory / 8;

        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // The cache size will be measured in kilobytes rather than
                // number of items.
                return bitmap.getByteCount() / 1024;
            }
        };
    }

    /**
     * Add a bitmap to memory cache
     * 
     * @param key
     * @param bitmap
     */
    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    /**
     * Get a bitmap from cache
     * 
     * @param key
     * @return
     */
    public Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }

    public void clearCache() {
        mMemoryCache.evictAll();
    }
}
