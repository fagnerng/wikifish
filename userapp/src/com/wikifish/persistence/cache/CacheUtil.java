
package com.wikifish.persistence.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @author vntpamc
 */
public class CacheUtil {

    private static final int defaultBufferSize = 1024;

    private static final String className = CacheUtil.class.getName();

    /**
     * Do-nothing constructor to avoid this class being instanced.
     */
    private CacheUtil() {
    }

    /**
     * Returns an InputStream for the cached file corresponding to the passed
     * URL if it exists or null otherwise.
     * 
     * @param url Remote URL of the file to look for in the cache directory.
     * @param context The current application context.
     * @return InputStream for the cached file corresponding to the passed URL
     *         if it exists or null.
     */
    public static InputStream getCachedFile(String url, Context context) {
        InputStream cachedFileIs = null;
        String hashedUrl = null;

        if (url != null) {
            hashedUrl = hashMD5(url);

            // Check if a cached file for the passed url exists.
            if (hashedUrl != null && hashedUrl.length() > 0) {
                try {

                    hashedUrl = context.getCacheDir().getAbsolutePath() + "/"
                            + hashedUrl;
                    File cachedFile = new File(hashedUrl);
                    cachedFileIs = new FileInputStream(cachedFile);
                } catch (FileNotFoundException e) {
                    Log.e(className,
                            Thread.currentThread().getName()
                                    + "No cache file for passed URL found. Returning null.");
                }
            }
        }

        return cachedFileIs;
    }

    /**
     * Returns the image file path md5 hash corresponding to the passed URL if
     * it exists or null otherwise.
     * 
     * @param url Remote URL of the file to look for in the cache directory.
     * @param context The current application context.
     * @return file path md5 corresponding to the passed URL if it exists or
     *         null.
     */
    public static String hasCachedFile(String url, Context context) {
        String hashedUrl = null;

        if (url != null) {
            hashedUrl = hashMD5(url);

            // Check if a cached file for the passed url exists.
            if (hashedUrl != null && hashedUrl.length() > 0) {
                hashedUrl = context.getCacheDir().getAbsolutePath() + "/"
                        + hashedUrl;
                File cachedFile = new File(hashedUrl);
                if (!cachedFile.exists()) {
                    hashedUrl = null;
                }
            }
        }

        return hashedUrl;
    }

    public static String removeCachedFile(String url, Context context) {
        String cachedFileUrl = null;
        String hashedUrl = hashMD5(url);
        File cacheDir = context.getCacheDir();

        // Create cached file.
        if (cacheDir != null && cacheDir.exists() && hashedUrl != null) {
            cachedFileUrl = cacheDir.getAbsolutePath() + "/" + hashedUrl;
            File cachedFile = new File(cachedFileUrl);

            // If cached file already exists, delete it before creating.
            if (cachedFile.exists()) {
                cachedFile.delete();
            }
        }
        return cachedFileUrl;
    }

    /**
     * Creates a cached file from the passed InputStream. Returns the
     * InputStream to the created cache file if succeeded and null otherwise
     * 
     * @param url The full remote URL from which the file is being downloaded.
     * @param is The InputStream from the remote file to be cached.
     * @param context The current application context.
     * @return String representing the file path from the file that has been
     *         downloaded and created in the file system.
     */
    public static String createCachedFile(String url, InputStream is,
            Context context) {
        String cachedFileUrl = null;
        String hashedUrl = hashMD5(url);
        boolean fileCreated = false;
        FileOutputStream out = null;
        File cacheDir = context.getCacheDir();

        // Create cached file.
        if (cacheDir != null && cacheDir.exists() && hashedUrl != null) {
            cachedFileUrl = cacheDir.getAbsolutePath() + "/" + hashedUrl;
            File cachedFile = new File(cachedFileUrl);

            // If cached file already exists, delete it before creating.
            if (cachedFile.exists()) {
                cachedFile.delete();
            }

            try {
                fileCreated = cachedFile.createNewFile();
                if (fileCreated) {
                    // File was created. Write to it.
                    out = new FileOutputStream(cachedFile);
                    copyStream(is, out);
                } else {
                    // Cached file was not created due to some error.
                    cachedFileUrl = null;
                }
            } catch (IOException e) {
                Log.e(className,
                        "IOException caught creating cache file: "
                                + e.getMessage());
                cachedFileUrl = null;
            } finally {
                // Close cache file OutputStream connection.
                if (out != null) {
                    try {
                        out.close();
                        out = null;
                    } catch (IOException e) {
                        Log.e(className,
                                "IOException closing cache file OutputStream: "
                                        + e.getMessage());
                    }
                }

                // Close remote file InputStream connection.
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }

        return cachedFileUrl;
    }

    /**
     * Returns the total size of the cache files stored.
     * 
     * @param context The current application context.
     * @return the total size of the cache files stored in bytes or -1 if any IO
     *         error occurs while reading file sizes.
     */
    public static int getCacheSize(Context context) {
        int cacheSize = 0;
        File cacheDir = context.getCacheDir();
        File[] cachedFiles = null;
        FileInputStream fileIs = null;

        if (cacheDir != null && cacheDir.exists()) {
            cacheDir.list();
            cachedFiles = cacheDir.listFiles();

            if (cachedFiles != null && cachedFiles.length > 0) {
                for (File cachedFile : cachedFiles) {
                    try {
                        fileIs = new FileInputStream(cachedFile);
                        cacheSize = cacheSize + fileIs.available();
                    } catch (IOException ioe) {
                        Log.e(className,
                                "Unexpected IOException getting file InputStream: "
                                        + ioe.getMessage());
                        cacheSize = -1;
                        break;
                    } finally {
                        try {
                            fileIs.close();
                        } catch (IOException e) {
                            Log.e(className,
                                    "Unexpected IOException closing file InputStream: "
                                            + e.getMessage());
                            cacheSize = -1;
                            break;
                        }
                    }
                }
            }
        }

        return cacheSize;
    }

    /**
     * Deletes cached files until the passed goal size is reached.
     * 
     * @param goalSize The size in bytes the cache must have.
     * @param context The current application context.
     * @return The actual size of cached files after trimming or -1 if some
     *         error occurred.
     */
    public static int trimCache(int goalSize, Context context) {
        int currentCacheSize = 0;
        File cacheDir = context.getCacheDir();
        File[] cachedFiles = null;

        currentCacheSize = getCacheSize(context);

        if (currentCacheSize != -1 && cacheDir != null && cacheDir.exists()) {
            cachedFiles = cacheDir.listFiles();
            if (cachedFiles != null && cachedFiles.length > 0) {

                // Sort the files, oldest first.
                Arrays.sort(cachedFiles, new CacheFileSort());

                for (File cachedFile : cachedFiles) {
                    if (currentCacheSize <= goalSize) {
                        break;
                    }
                    cachedFile.delete();
                    currentCacheSize = getCacheSize(context);
                }
            }
        } else {
            Log.e(className, "Not possible to trim cache due to some IO error.");
            currentCacheSize = -1;
        }

        return currentCacheSize;
    }

    /**
     * Returns the MD5 hash equivalent to the passed String.
     * 
     * @param s The String to be "hashed".
     * @return the MD5 hash equivalent to the passed String.
     */
    private static String hashMD5(String s) {
        String result = null;

        try {
            byte[] input = s.getBytes("UTF-8");
            byte[] output = new byte[16];
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input, 0, input.length);

            int outputLength = md.digest(output, 0, output.length);

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < outputLength; i++) {
                sb.append(Integer.toHexString(output[i] & 0xFF));
            }
            result = sb.toString();
        } catch (UnsupportedEncodingException uee) {
            Log.e(className,
                    "UnsupportedEncodingException caught: " + uee.getMessage());
        } catch (NoSuchAlgorithmException nsae) {
            Log.e(className,
                    "NoSuchAlgorithmException caught: " + nsae.getMessage());
        } catch (DigestException de) {
            Log.e(className, "DigestException caught: " + de.getMessage());
        }

        return result;
    }

    /**
     * Copy data from an input stream to an output stream
     * 
     * @param is Input stream
     * @param os Output stream
     * @return The total number of copied bytes
     * @throws IOException If an I/O exception occurred
     */
    private static int copyStream(InputStream is, OutputStream os)
            throws IOException {
        int totalNrBytes = 0;
        int nrBytes = 0;
        byte[] bytes = new byte[defaultBufferSize];

        while ((nrBytes = is.read(bytes)) > 0) {
            os.write(bytes, 0, nrBytes);
            totalNrBytes += nrBytes;
        }

        return totalNrBytes;
    }

    /**
     * Decode inputStream to bitmap
     * 
     * @param inputStream
     * @param width
     * @param height
     * @return
     */
    public static Bitmap decodeImage(InputStream inputStream, int width, int height) {
        Bitmap bitmap = null;

        if (inputStream != null) {
            BitmapFactory.Options options = new BitmapFactory.Options();

            int scale = calculateInSampleSize(options, width, height);

            // Use default decoding options
            options.inPurgeable = true;
            options.inInputShareable = true;
            options.inSampleSize = scale; /*
                                           * The sample size is the number of
                                           * pixels in either dimension that
                                           * correspond to a single pixel in the
                                           * decoded bitmap. For example,
                                           * inSampleSize == 4 returns an image
                                           * that is 1/4 the width/height of the
                                           * original, and 1/16 the number of
                                           * pixels.
                                           */

            bitmap = BitmapFactory.decodeStream(inputStream, null, options);
        }

        return bitmap;
    }

    public static void createCachedBitmapFile(String url, Bitmap bitmap, Context context) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.PNG, 0 /* ignored for PNG */, bos);
        byte[] bitmapdata = bos.toByteArray();
        ByteArrayInputStream bs = new ByteArrayInputStream(bitmapdata);

        createCachedFile(url, bs, context);
    }

    /**
     * Calculate an inSampleSize for use in a {@link BitmapFactory.Options}
     * object when decoding bitmaps using the decode* methods from
     * {@link BitmapFactory}. This implementation calculates the closest
     * inSampleSize that will result in the final decoded bitmap having a width
     * and height equal to or larger than the requested width and height. This
     * implementation does not ensure a power of 2 is returned for inSampleSize
     * which can be faster when decoding but results in a larger bitmap which
     * isn't as useful for caching purposes.
     * 
     * @param options An options object with out* params already populated (run
     *            through a decode* method with inJustDecodeBounds==true
     * @param reqWidth The requested width of the resulting bitmap
     * @param reqHeight The requested height of the resulting bitmap
     * @return The value to be used for inSampleSize
     */
    private static int calculateInSampleSize(BitmapFactory.Options options,
            int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            // Calculate ratios of height and width to requested height and
            // width
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will
            // guarantee a final image
            // with both dimensions larger than or equal to the requested height
            // and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;

            // This offers some additional logic in case the image has a strange
            // aspect ratio. For example, a panorama may have a much larger
            // width than height. In these cases the total pixels might still
            // end up being too large to fit comfortably in memory, so we should
            // be more aggressive with sample down the image (=larger
            // inSampleSize).

            final float totalPixels = width * height;

            // Anything more than 2x the requested pixels we'll sample down
            // further
            final float totalReqPixelsCap = reqWidth * reqHeight * 2;

            while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
                inSampleSize++;
            }
        }
        return inSampleSize;
    }
}
