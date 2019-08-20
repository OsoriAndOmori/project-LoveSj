package com.osori.lovesj.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.osori.lovesj.R;
import com.osori.lovesj.anniversary.BirthDay;
import com.osori.lovesj.utils.BirthDayUtils;
import com.osori.lovesj.utils.DayCounter;

import java.time.LocalDate;

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
        me.setText(BirthDayUtils.buildBirthDayText(BirthDay.굴훈));

        TextView sister = findViewById(R.id.sister);
        sister.setText(BirthDayUtils.buildBirthDayText(BirthDay.예지));
    }
}
