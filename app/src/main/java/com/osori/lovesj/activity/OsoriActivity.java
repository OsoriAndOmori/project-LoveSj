package com.osori.lovesj.activity;

import java.time.LocalDate;

import com.osori.lovesj.R;
import com.osori.lovesj.anniversary.OsoriAnniversary;
import com.osori.lovesj.listener.OnSwipeTouchListener;
import com.osori.lovesj.utils.DayCounter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OsoriActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_osori);

		TextView weddingDday = findViewById(R.id.wedding_dday);
		String weddingText = new StringBuilder().append("Since Wedding - ")
				.append(OsoriAnniversary.결혼.makeReadableText())
				.append(" - ")
				.append(DayCounter.countDaysIncludeStandardFrom(OsoriAnniversary.결혼.getDate(), LocalDate.now()))
				.append(" days").toString();
		weddingDday.setText(weddingText);

		TextView loveDday = findViewById(R.id.love_dday);
		String loveDayText = new StringBuilder().append("Since First Love - ")
				.append(OsoriAnniversary.연애.makeReadableText())
				.append(" - ")
				.append(DayCounter.countDaysIncludeStandardFrom(OsoriAnniversary.연애.getDate(), LocalDate.now()))
				.append(" days").toString();
		loveDday.setText(loveDayText);

		LinearLayout linearLayout = findViewById(R.id.wrap_linear_layout);
		OnSwipeTouchListener onSwipeTouchListener = new OnSwipeTouchListener(this) {
			@Override
			public void onSwipeLeft() {
				Intent intent = new Intent(OsoriActivity.this, OsoriWifeFamilyActivity.class);
				startActivity(intent);
			}

			@Override
			public void onSwipeRight() {
				Intent intent = new Intent(OsoriActivity.this, OsoriHusbandFamilyActivity.class);
				startActivity(intent);
			}
		};
		linearLayout.setOnTouchListener(onSwipeTouchListener);
	}
}
