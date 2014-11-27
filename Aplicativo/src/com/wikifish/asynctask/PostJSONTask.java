
package com.wikifish.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import com.wikifish.utils.Logger;
import com.wikifish.utils.WikiFishUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class PostJSONTask extends AsyncTask<String, Void, String> {

    private final Logger mLogger = new Logger(getClass().getName());

    private final Context mContext;

    public PostJSONTask(final Context context) {
        mContext = context;
    }

    @Override
    protected String doInBackground(final String... paramns) {

        int trys = 10;
        String result = null;

        while (trys > 0) {
            trys--;

            try {

                mLogger.info("POST: " + paramns[0] + " - " + paramns[1]);

                result = WikiFishUtils.sendPOST(paramns[0], paramns[1]);

                if (result != null) {
                    break;
                }

            } catch (final IOException e) {
                mLogger.debug("Unable to retrieve web page. URL may be invalid: "
                        + WikiFishUtils.getServerURL() + " - " + paramns[0]);
            }

            try {
                Thread.sleep(1000);
            } catch (final InterruptedException e) {
                mLogger.error(e.getMessage());
            }
        }

        return result;
    }

    @Override
    protected void onPostExecute(final String msg) {
        try {
            ((PostJSONInterface) mContext).callbackPostJSON(new JSONObject(msg));
        } catch (final JSONException e) {
            mLogger.error("Error when creating JSON on PostJSONTask", e);
        }

    }

    public interface PostJSONInterface {
        public void callbackPostJSON(JSONObject json);
    }
}
