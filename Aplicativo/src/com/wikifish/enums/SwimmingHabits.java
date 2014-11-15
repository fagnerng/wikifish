package com.wikifish.enums;

import com.wikifish.R;

public enum SwimmingHabits {

	Bottom(R.drawable.ic_launcher),
	NoSpecial(R.drawable.ic_launcher), 
	Middle(R.drawable.ic_launcher), 
	Top(R.drawable.ic_launcher);
	private long rID;

	private SwimmingHabits(final long rID) {
		this.rID = rID;

	}

	public long getRID() {
		return rID;
	}
}
