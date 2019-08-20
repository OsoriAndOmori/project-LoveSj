package com.osori.lovesj.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.osori.lovesj.R;
import com.osori.lovesj.anniversary.OsoriAnniversary;
import com.osori.lovesj.utils.DayCounter;

import java.time.LocalDate;

public class OsoriActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_osori);

        TextView weddingDday = findViewById(R.id.wedding_dday);
        String weddingText = new StringBuilder().append("Since Wedding - ")
                .append(OsoriAnniversary.WEDDING.makeReadableText())
                .append(" - ")
                .append(DayCounter.countDaysIncludeStandardFrom(OsoriAnniversary.COUPLE.getDate(), LocalDate.now()))
                .append(" days").toString();
        weddingDday.setText(weddingText);

        TextView loveDday = findViewById(R.id.love_dday);
        String loveDayText = new StringBuilder().append("Since First Love - ")
                .append(OsoriAnniversary.COUPLE.makeReadableText())
                .append(" - ")
                .append(DayCounter.countDaysIncludeStandardFrom(OsoriAnniversary.WEDDING.getDate(), LocalDate.now()))
                .append(" days").toString();
        loveDday.setText(loveDayText);

        Button khFamilyButton = findViewById(R.id.kh_family);
        khFamilyButton.setOnClickListener(view -> {
            Intent intent = new Intent(OsoriActivity.this, OsoriHusbandFamilyActivity.class);
            startActivity(intent);
        });

        Button sjFamilyButton = findViewById(R.id.sj_family);
        sjFamilyButton.setOnClickListener(view -> {
            Intent intent = new Intent(OsoriActivity.this, OsoriWifeFamilyActivity.class);
            startActivity(intent);
        });
    }
}
