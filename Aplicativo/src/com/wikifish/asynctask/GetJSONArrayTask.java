
package com.wikifish.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import com.wikifish.utils.Logger;
import com.wikifish.utils.WikiFishUtils;

import org.json.JSONArray;
import org.json.JSONException;

public class GetJSONArrayTask extends AsyncTask<String, Void, String> {

    private final Logger mLogger = new Logger(getClass().getName());
    private final Context mContext;

    public GetJSONArrayTask(final Context context) {
        mContext = context;
    }

    @Override
    protected String doInBackground(final String... urls) {
        // params comes from the execute() call: params[0] is the url.

        int trys = 10;
        String result = null;

        while (trys > 0) {
            trys--;

            mLogger.info("GET: " + urls[0]);

            result = WikiFishUtils.downloadUrl(urls[0]);

            if (result == null) {

                try {
                    Thread.sleep(1000);
                } catch (final InterruptedException e) {
                    mLogger.error(e.getMessage());
                }
            } else {
                break;
            }

        }

        return result;
    }

    // onPostExecute displays the results of the AsyncTask.
    @Override
    protected void onPostExecute(final String result) {
        JSONArray mainObject;

        try {
            if (result != null) {
                mainObject = new JSONArray(result);
            } else {
                mainObject = new JSONArray("[{'status': 'failed'}]");
            }

            mLogger.debug(mainObject.toString());
            ((GetJSONInterface) mContext).callbackGetJSON(mainObject);

        } catch (final JSONException e) {
            mLogger.debug(result);
            mLogger.error(e.getMessage());

        }
    }

    public interface GetJSONInterface {
        public void callbackGetJSON(final JSONArray json);
    }
}
