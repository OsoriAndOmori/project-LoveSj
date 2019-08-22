package com.osori.lovesj.anniversary;

import com.osori.lovesj.utils.BirthDayUtils;

import java.time.LocalDate;

public enum OsoriAnniversary {
	연애(LocalDate.of(2014, 4, 19)),
	결혼(LocalDate.of(2019, 4, 20));

	private LocalDate date;

	OsoriAnniversary(LocalDate date) {
		this.date = date;
	}

	public LocalDate getDate() {
		return this.date;
	}

	public String makeReadableText() {
		return BirthDayUtils.localDateToReadableText(this.date);
	}
}
