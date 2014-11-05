package com.wikifish;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.wikifish.image.ImageHandlingTask;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ImageView iv_fish = (ImageView) findViewById(R.id.iv_sample);
		iv_fish.setTag("http://www.tonyhakim.com.au/wp-content/uploads/2014/09/Purple-fish.jpg");
		ImageHandlingTask  iHT_downloader = new ImageHandlingTask(iv_fish, this);
		iHT_downloader.execute("http://www.tonyhakim.com.au/wp-content/uploads/2014/09/Purple-fish.jpg");
	}
}
