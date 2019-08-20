package com.osori.lovesj.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.osori.lovesj.R;
import com.osori.lovesj.anniversary.BirthDay;
import com.osori.lovesj.utils.DayCounter;

import java.time.LocalDate;

public class OsoriHusbandFamilyActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_osori_husband);

		TextView father = findViewById(R.id.wedding_dday);
		String fatherBirthDayText = new StringBuilder().append("Since ")
				.append(BirthDay.아버지.makeReadableText())
				.append(" - ")
				.append(DayCounter.countDaysIncludeStandardFrom(BirthDay.아버지.getBirth(), LocalDate.now()))
				.append(" days").toString();
		father.setText(fatherBirthDayText);
	}
}
