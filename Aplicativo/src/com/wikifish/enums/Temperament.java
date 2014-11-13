package com.wikifish.enums;

import com.wikifish.R;

public enum Temperament {

	Peaceful(R.drawable.ic_launcher),
	NotForBegginers(R.drawable.ic_launcher);
	
	private long rID;

	private Temperament(final long rID) {
		this.rID = rID;

	}

	public long getRID() {
		return rID;
	}
}
