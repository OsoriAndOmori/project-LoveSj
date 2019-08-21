package com.osori.lovesj.activity;

import com.osori.lovesj.R;
import com.osori.lovesj.anniversary.BirthDay;
import com.osori.lovesj.listener.OnSwipeTouchListener;
import com.osori.lovesj.utils.BirthDayUtils;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OsoriHusbandFamilyActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_osori_husband);

		TextView father = findViewById(R.id.father);
		father.setText(BirthDayUtils.buildBirthDayText(BirthDay.아버지));

		TextView mother = findViewById(R.id.mother);
		mother.setText(BirthDayUtils.buildBirthDayText(BirthDay.어머니));

		TextView me = findViewById(R.id.me);
		me.setText(BirthDayUtils.buildBirthDayText(BirthDay.기훈));

		TextView sister = findViewById(R.id.sister);
		sister.setText(BirthDayUtils.buildBirthDayText(BirthDay.예지));

		LinearLayout linearLayout = findViewById(R.id.wrap_linear_layout);
		OnSwipeTouchListener onSwipeTouchListener = new OnSwipeTouchListener(this) {
			@Override
			public void onSwipeLeft() {
				Intent intent = new Intent(OsoriHusbandFamilyActivity.this, OsoriActivity.class);
				startActivity(intent);
			}
		};
		linearLayout.setOnTouchListener(onSwipeTouchListener);
	}
}
