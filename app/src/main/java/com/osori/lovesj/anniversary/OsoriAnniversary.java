package com.osori.lovesj.anniversary;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
		return this.date.format(DateTimeFormatter.ofPattern("yyyy. MM. dd."));
	}
}
