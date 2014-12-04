
package com.wikifish;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class DialogImageZoom extends Dialog {

    public DialogImageZoom(final Context context) {
        super(context);
        setContentView(R.layout.image_zoom);
        setTitle(context.getResources().getString(R.string.title_image_preview));

    }

    public DialogImageZoom(final Context context, final Drawable image) {
        super(context);
        setContentView(R.layout.image_zoom);
        setTitle(context.getResources().getString(R.string.title_image_preview));
        ((ImageView) findViewById(R.id.iv_zoom)).setBackground(image);
    }
}
