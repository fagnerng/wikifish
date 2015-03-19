
package com.wikifish.enums;

import com.wikifish.R;

public enum AquariumLighting {

    BrightWithSunlight(R.drawable.ic_launcher),
    BrightNoSunlight(R.drawable.ic_launcher),
    Dark(R.drawable.ic_launcher);

    private long rID;

    private AquariumLighting(final long rID) {
        this.rID = rID;

    }

    public long getRID() {
        return rID;
    }
}
