
package com.wikifish.network;

import com.wikifish.utils.Logger;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

public class HttpClientConnection {

    private final Logger mLogger = new Logger(getClass().getName());

    private final HttpClient mHttpClient;
    private final HttpGet mGet;

    /**
     * Default HttpClientConnections constructor
     *
     * @param url URL to be accessed
     * @param method Which method should be used
     */
    public HttpClientConnection(final String url) {
        mHttpClient = new DefaultHttpClient();
        mGet = new HttpGet(url);
    }

    /**
     * Execute HttpClient connection
     *
     * @return Returns the current server response
     */
    public HttpResponse execute() {
        try {
            if (mGet != null) {
                return mHttpClient.execute(mGet);
            }
        } catch (final ClientProtocolException e) {
            mLogger.error("ClientProtocolException", e);
            return null;
        } catch (final IOException e) {
            mLogger.error("IOException", e);
            return null;
        }
        return null;
    }
}
