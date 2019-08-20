package com.osori.lovesj.activity;

import java.time.LocalDate;

import com.osori.lovesj.R;
import com.osori.lovesj.anniversary.OsoriAnniversary;
import com.osori.lovesj.calculator.DayCalculator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class OsoriActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_osori);

		TextView weddingDday = findViewById(R.id.wedding_dday);
		String weddingText = new StringBuilder().append("Since ")
				.append(OsoriAnniversary.WEDDING.makeReadableText())
				.append(" - ")
				.append(DayCalculator.countDaysIncludeStandardFrom(OsoriAnniversary.WEDDING.getDate(), LocalDate.now()))
				.append(" days").toString();
		weddingDday.setText(weddingText);

		TextView loveDday = findViewById(R.id.love_dday);
		String loveDayText = new StringBuilder().append("Since ")
				.append(OsoriAnniversary.COUPLE.makeReadableText())
				.append(" - ")
				.append(DayCalculator.countDaysIncludeStandardFrom(OsoriAnniversary.COUPLE.getDate(), LocalDate.now()))
				.append(" days").toString();
		loveDday .setText(loveDayText);
	}
}
