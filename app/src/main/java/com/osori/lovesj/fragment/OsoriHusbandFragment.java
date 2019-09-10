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
import com.osori.lovesj.anniversary.BirthDay;
import com.osori.lovesj.utils.BirthDayUtils;

public class OsoriHusbandFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_osori_husband, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView father = view.findViewById(R.id.father);
        father.setText(BirthDayUtils.buildBirthDayText(BirthDay.아빠));

        TextView mother = view.findViewById(R.id.mother);
        mother.setText(BirthDayUtils.buildBirthDayText(BirthDay.엄마));

        TextView me = view.findViewById(R.id.me);
        me.setText(BirthDayUtils.buildBirthDayText(BirthDay.문석));

        TextView sister = view.findViewById(R.id.sister);
        sister.setText(BirthDayUtils.buildBirthDayText(BirthDay.은서));
    }
}
