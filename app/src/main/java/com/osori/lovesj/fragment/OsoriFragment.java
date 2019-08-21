package com.osori.lovesj.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.osori.lovesj.R;
import com.osori.lovesj.anniversary.OsoriAnniversary;
import com.osori.lovesj.utils.DayCounter;

import java.time.LocalDate;

public class OsoriFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_osori, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView weddingDday = view.findViewById(R.id.wedding_dday);
        String weddingText = new StringBuilder().append("Since Wedding - ")
                .append(OsoriAnniversary.결혼.makeReadableText())
                .append(" - ")
                .append(DayCounter.countDaysIncludeStandardFrom(OsoriAnniversary.결혼.getDate(), LocalDate.now()))
                .append(" days").toString();
        weddingDday.setText(weddingText);

        TextView loveDday = view.findViewById(R.id.love_dday);
        String loveDayText = new StringBuilder().append("Since First Love - ")
                .append(OsoriAnniversary.연애.makeReadableText())
                .append(" - ")
                .append(DayCounter.countDaysIncludeStandardFrom(OsoriAnniversary.연애.getDate(), LocalDate.now()))
                .append(" days").toString();
        loveDday.setText(loveDayText);
    }
}
