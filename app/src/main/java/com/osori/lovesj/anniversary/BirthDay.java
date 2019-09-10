package com.osori.lovesj.anniversary;

import java.time.LocalDate;

public enum BirthDay {
	아빠(LocalDate.of(1961, 9, 19), false),
	엄마(LocalDate.of(1962, 12, 21), false),
	문석(LocalDate.of(1990, 6, 23), false),
	은서(LocalDate.of(1993, 5, 20), false),

	이모부(LocalDate.of(1961, 7, 14), true),
	이모(LocalDate.of(1969, 7, 5), true),
	기훈(LocalDate.of(1990, 6, 23), false),
	수진(LocalDate.of(1993, 9, 23), false),
	영진(LocalDate.of(1997, 2, 18), false),
	마루(LocalDate.of(2006, 11, 17), false);

	private LocalDate birth;
	private boolean lunarBirthDay;

	BirthDay(LocalDate birth, boolean lunarBirthDay) {
		this.birth = birth;
		this.lunarBirthDay = lunarBirthDay;
	}

	public LocalDate getBirth() {
		return birth;
	}

	public boolean isLunarBirthDay() {
		return lunarBirthDay;
	}
}
