package com.wikifish;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class SearchActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		findViewById(R.id.ib_search).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				search();
				
			}

	
		});
	}

	private void search() {
		
		
	}	
}
