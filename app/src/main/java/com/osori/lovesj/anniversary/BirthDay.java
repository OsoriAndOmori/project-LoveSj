package com.osori.lovesj.anniversary;

import java.time.LocalDate;

public enum BirthDay {
	기훈(LocalDate.of(1990, 6, 23), false),
	수진(LocalDate.of(1993, 9, 23), false),
	아버지(LocalDate.of(1961, 9, 19), false),
	어머니(LocalDate.of(1962, 12, 21), false),
	예지(LocalDate.of(1993, 5, 20), false),
	아버님(LocalDate.of(1961, 7, 14), true),
	어머님(LocalDate.of(1969, 7, 5), true),
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
