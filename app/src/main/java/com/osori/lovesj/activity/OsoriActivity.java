package com.osori.lovesj.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.osori.lovesj.R;
import com.osori.lovesj.listener.SwipeAdapter;

public class OsoriActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_osori);

		ViewPager viewPager = findViewById(R.id.view_page);
		viewPager.setOffscreenPageLimit(0);
		SwipeAdapter swipeAdapter = new SwipeAdapter(getSupportFragmentManager());
		viewPager.setAdapter(swipeAdapter);
		viewPager.setCurrentItem(0);
	}
}
