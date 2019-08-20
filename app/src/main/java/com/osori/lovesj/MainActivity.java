package com.osori.lovesj;

import java.time.LocalDate;

import com.osori.lovesj.dday.DayCalculator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TextView weddingDday = findViewById(R.id.wedding_dday);
		weddingDday.setText("Since 2019. 4. 20 - " + DayCalculator.countDaysIncludeStandardFrom(LocalDate.of(2019, 4, 20), LocalDate.now()) + " days");

		TextView loveDday = findViewById(R.id.love_dday);
		loveDday .setText("Since 2014. 4. 19 - " + DayCalculator.countDaysIncludeStandardFrom(LocalDate.of(2014, 4, 19), LocalDate.now()) + " days");
	}
}
