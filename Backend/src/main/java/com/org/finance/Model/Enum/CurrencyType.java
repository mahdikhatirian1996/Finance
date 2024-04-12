package com.org.finance.Model.Enum;

import lombok.Getter;

import java.util.HashMap;

@Getter
public enum CurrencyType {
    ETHEREUM(1, "اتریوم", "ETH"),
    BNB_CHAIN(2, "بی ان بی", "BNB"),
    SOLANA(3, "سولانا", "SOLANA"),
    BASE(4, "بیس", "BASE");

    private final Integer index;
    private final String persianTitle;
    private final String honeypotTitle; // READ THIS FIELD FROM {CHAIN : CURRENCY} JSON

    CurrencyType(Integer index, String persianTitle, String honeypotTitle) {
        this.index = index;
        this.persianTitle = persianTitle;
        this.honeypotTitle = honeypotTitle;
    }

    public static String getEnumNameFromTitle(String honeypotTitle) {
        for (CurrencyType currency : CurrencyType.values()) {
            if (currency.getHoneypotTitle().equals(honeypotTitle)) {
                return currency.name();
            }
        }
        return null;
    }

    public static CurrencyType getValueOfHoneypotTitle(String honeypotTitle) {
        for (CurrencyType currency : CurrencyType.values()) {
            if (currency.getHoneypotTitle().equals(honeypotTitle)) {
                return currency;
            }
        }
        return null;
    }

}
