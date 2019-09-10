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

public class OsoriWifeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_osori_wife, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView fatherInLaw = view.findViewById(R.id.father_in_law);
        fatherInLaw.setText(BirthDayUtils.buildBirthDayText(BirthDay.이모부));

        TextView motherInLaw = view.findViewById(R.id.mother_in_law);
        motherInLaw.setText(BirthDayUtils.buildBirthDayText(BirthDay.이모));

        TextView kh = view.findViewById(R.id.kh);
        kh.setText(BirthDayUtils.buildBirthDayText(BirthDay.기훈));

        TextView sj = view.findViewById(R.id.sj);
        sj.setText(BirthDayUtils.buildBirthDayText(BirthDay.수진));

        TextView yj = view.findViewById(R.id.yj);
        yj.setText(BirthDayUtils.buildBirthDayText(BirthDay.영진));

        TextView maroo = view.findViewById(R.id.maroo);
        maroo.setText(BirthDayUtils.buildBirthDayText(BirthDay.마루));
    }
}
