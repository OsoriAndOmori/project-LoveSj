package com.osori.lovesj.fragment;

import java.time.LocalDate;

import com.osori.lovesj.R;
import com.osori.lovesj.anniversary.BirthDay;
import com.osori.lovesj.anniversary.OsoriAnniversary;
import com.osori.lovesj.utils.BirthDayUtils;
import com.osori.lovesj.utils.DayCounter;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class OsoriFragment extends Fragment {
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_osori, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		ImageView imageView = view.findViewById(R.id.coupleImage);
		GradientDrawable drawable = (GradientDrawable) getContext().getDrawable(R.drawable.background_rounding);
		imageView.setBackground(drawable);
		imageView.setClipToOutline(true);

		TextView weddingDday = view.findViewById(R.id.wedding_dday);
		String weddingText = new StringBuilder().append("결혼한 날 - ")
				.append(OsoriAnniversary.결혼.makeReadableText())
				.append("  D+")
				.append(DayCounter.countDaysIncludeStandardFrom(OsoriAnniversary.결혼.getDate(), LocalDate.now()))
				.append(" days").toString();
		weddingDday.setText(weddingText);

		TextView loveDday = view.findViewById(R.id.love_dday);
		String loveDayText = new StringBuilder().append("연애 시작한 날 - ")
				.append(OsoriAnniversary.연애.makeReadableText())
				.append("  D+")
				.append(DayCounter.countDaysIncludeStandardFrom(OsoriAnniversary.연애.getDate(), LocalDate.now()))
				.append(" Days").toString();
		loveDday.setText(loveDayText);

		TextView 기훈 = view.findViewById(R.id.기훈);
		기훈.setText(BirthDayUtils.buildBirthDayText(BirthDay.기훈));

		TextView 수진 = view.findViewById(R.id.수진);
		수진.setText(BirthDayUtils.buildBirthDayText(BirthDay.수진));

		TextView 나린 = view.findViewById(R.id.나린);
		나린.setText(BirthDayUtils.buildBirthDayText(BirthDay.나린));

		TextView 나율 = view.findViewById(R.id.나율);
		나율.setText(BirthDayUtils.buildBirthDayText(BirthDay.나율));

		TextView 나준 = view.findViewById(R.id.나준);
		나준.setText(BirthDayUtils.buildBirthDayText(BirthDay.나준));
	}
}
