package com.wikifish.enums;

import com.wikifish.R;

public enum AquariumSetUp {

    DenselyPlanted(R.drawable.ic_launcher), RocksNoPlants(
            R.drawable.ic_launcher), OnlyGravel(R.drawable.ic_launcher), RockAndPlants(
            R.drawable.ic_launcher);
    private long rID;

    private AquariumSetUp(final long rID) {
        this.rID = rID;

    }

    public long getRID() {
        return rID;
    }
}
