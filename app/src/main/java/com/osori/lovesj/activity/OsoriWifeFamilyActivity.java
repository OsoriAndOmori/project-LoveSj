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

public class OsoriWifeFamilyActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_osori_wife);

		TextView fatherInLaw = findViewById(R.id.father_in_law);
		fatherInLaw.setText(BirthDayUtils.buildBirthDayText(BirthDay.아버님));

		TextView motherInLaw = findViewById(R.id.mother_in_law);
		motherInLaw.setText(BirthDayUtils.buildBirthDayText(BirthDay.어머님));

		TextView sj = findViewById(R.id.sj);
		sj.setText(BirthDayUtils.buildBirthDayText(BirthDay.수진));

		TextView yj = findViewById(R.id.yj);
		yj.setText(BirthDayUtils.buildBirthDayText(BirthDay.영진));

		TextView maroo = findViewById(R.id.maroo);
		maroo.setText(BirthDayUtils.buildBirthDayText(BirthDay.마루));

		LinearLayout linearLayout = findViewById(R.id.wrap_linear_layout);
		OnSwipeTouchListener onSwipeTouchListener = new OnSwipeTouchListener(this) {
			@Override
			public void onSwipeRight() {
				Intent intent = new Intent(OsoriWifeFamilyActivity.this, OsoriActivity.class);
				startActivity(intent);
			}
		};
		linearLayout.setOnTouchListener(onSwipeTouchListener);
	}
}
