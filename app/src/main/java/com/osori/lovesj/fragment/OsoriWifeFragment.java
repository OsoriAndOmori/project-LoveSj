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

public class OsoriWifeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_osori_wife, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView 멋쟁이할아버지 = view.findViewById(R.id.멋쟁이할아버지);
        멋쟁이할아버지.setText(BirthDayUtils.buildBirthDayText(BirthDay.멋쟁이할아버지));

        TextView 콩콩콩할머니 = view.findViewById(R.id.콩콩콩할머니);
        콩콩콩할머니.setText(BirthDayUtils.buildBirthDayText(BirthDay.콩콩콩할머니));

        TextView 영진 = view.findViewById(R.id.영진);
        영진.setText(BirthDayUtils.buildBirthDayText(BirthDay.영진));

        TextView 마루 = view.findViewById(R.id.마루);
        마루.setText(BirthDayUtils.buildBirthDayText(BirthDay.마루));
    }
}
