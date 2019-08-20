package com.osori.lovesj.anniversary;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public enum BirthDay {
    굴훈(LocalDate.of(1990, 6, 23), false),
    굴진(LocalDate.of(1993, 9, 23), false),
    아버지(LocalDate.of(1961, 9, 19), false),
    어머니(LocalDate.of(1962, 12, 12), false),
    동생(LocalDate.of(1993, 5, 20), false),
    장인어른(LocalDate.of(1961, 7, 14), true),
    장모님(LocalDate.of(1969, 7, 5), true),
    처남(LocalDate.of(1997, 6, 23), false),
    마루(LocalDate.of(2006, 6, 23), false);

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

    public String makeReadableText(){
        return this.birth.format(DateTimeFormatter.ofPattern("yyyy. MM. dd."));
    }
}
