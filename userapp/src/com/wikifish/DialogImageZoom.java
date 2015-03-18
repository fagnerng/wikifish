
package com.wikifish;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Window;
import android.widget.ImageView;

public class DialogImageZoom extends Dialog {

    public DialogImageZoom(final Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
        setTitle("");
        setContentView(R.layout.image_zoom);

    }

    public DialogImageZoom(final Context context, final Drawable image) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
        setTitle("");
        setContentView(R.layout.image_zoom);
        ((ImageView) findViewById(R.id.iv_zoom)).setImageDrawable(image);
    }
}
