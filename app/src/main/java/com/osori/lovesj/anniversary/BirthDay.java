package com.osori.lovesj.anniversary;

import java.time.LocalDate;

public enum BirthDay {
	기훈(LocalDate.of(1990, 6, 23), false),
	수진(LocalDate.of(1993, 9, 23), false),
	나린(LocalDate.of(2020, 6, 9), false),
	나율(LocalDate.of(2022, 4, 15), false),
	나준(LocalDate.of(2023, 12, 20), false),
	일산할아버지(LocalDate.of(1961, 9, 19), false),
	일산할머니(LocalDate.of(1962, 12, 21), false),
	성길(LocalDate.of(1991, 5, 14), false),
	예지(LocalDate.of(1993, 5, 20), false),
	은하(LocalDate.of(2021, 1, 8), false),
	은비(LocalDate.of(2022, 12, 12), false),
	멋쟁이할아버지(LocalDate.of(1961, 7, 14), true),
	콩콩콩할머니(LocalDate.of(1969, 7, 5), true),
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
