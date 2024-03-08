package com.osori.lovesj.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
        TextView 일산할아버지 = view.findViewById(R.id.일산할아버지);
        일산할아버지.setText(BirthDayUtils.buildBirthDayText(BirthDay.일산할아버지));

        TextView 일산할머니 = view.findViewById(R.id.일산할머니);
        일산할머니.setText(BirthDayUtils.buildBirthDayText(BirthDay.일산할머니));

        TextView 성길 = view.findViewById(R.id.성길);
        성길.setText(BirthDayUtils.buildBirthDayText(BirthDay.성길));

        TextView 예지 = view.findViewById(R.id.예지);
        예지.setText(BirthDayUtils.buildBirthDayText(BirthDay.예지));

        TextView 은하 = view.findViewById(R.id.은하);
        은하.setText(BirthDayUtils.buildBirthDayText(BirthDay.은하));

        TextView 은비 = view.findViewById(R.id.은비);
        은비.setText(BirthDayUtils.buildBirthDayText(BirthDay.은비));
    }
}
