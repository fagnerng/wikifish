package com.wikifish.enums;

import com.wikifish.R;

public enum Reproduction {

    Egglayer(R.drawable.ic_launcher), Livebearer(R.drawable.ic_launcher), Mounthbrooder(
            R.drawable.ic_launcher);

    private long rID;

    private Reproduction(final long rID) {
        this.rID = rID;

    }

    public long getRID() {
        return rID;
    }
}
