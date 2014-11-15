package com.wikifish.enums;

import com.wikifish.R;

public enum FeedingHabit {

	DryPackaged(R.drawable.ic_launcher),
	LiveWorms(R.drawable.ic_launcher), 
	LiveFish(R.drawable.ic_launcher), 
	Vegetarian(R.drawable.ic_launcher);
	private long rID;

	private FeedingHabit(final long rID) {
		this.rID = rID;

	}

	public long getRID() {
		return rID;
	}
}
