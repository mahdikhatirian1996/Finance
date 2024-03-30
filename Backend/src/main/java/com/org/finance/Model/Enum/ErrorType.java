package com.org.finance.Model.Enum;

import lombok.Getter;

import java.util.HashMap;

@Getter
public enum ErrorType {
    REPEATED(1, "تکراری", "Repeated Contract Address"),
    OUT_OF_TIME(2, "خارج از بازه زمانی", "Out Of Time");

    private final Integer index;
    private final String persianTitle;
    private final String englishTitle;

    ErrorType(Integer index, String persianTitle, String englishTitle) {
        this.index = index;
        this.persianTitle = persianTitle;
        this.englishTitle = englishTitle;
    }


}
