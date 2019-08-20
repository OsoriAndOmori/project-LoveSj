package com.osori.lovesj.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.osori.lovesj.R;
import com.osori.lovesj.anniversary.BirthDay;
import com.osori.lovesj.anniversary.OsoriAnniversary;
import com.osori.lovesj.utils.BirthDayUtils;
import com.osori.lovesj.utils.DayCounter;

import java.time.LocalDate;

public class OsoriWifeFamilyActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_osori_wife);

		TextView fatherInLaw = findViewById(R.id.father_in_law);
		fatherInLaw.setText(BirthDayUtils.buildBirthDayText(BirthDay.장인어른));

		TextView motherInLaw = findViewById(R.id.mother_in_law);
		motherInLaw.setText(BirthDayUtils.buildBirthDayText(BirthDay.장모님));

		TextView sj = findViewById(R.id.sj);
		sj.setText(BirthDayUtils.buildBirthDayText(BirthDay.굴진));

		TextView yj = findViewById(R.id.yj);
		yj.setText(BirthDayUtils.buildBirthDayText(BirthDay.영진));

		TextView maroo = findViewById(R.id.maroo);
		maroo.setText(BirthDayUtils.buildBirthDayText(BirthDay.마루));
	}
}
