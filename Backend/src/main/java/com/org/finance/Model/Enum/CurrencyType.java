package com.org.finance.Model.Enum;

import lombok.Getter;

import java.util.HashMap;

@Getter
public enum CurrencyType {
    ETHEREUM(1, "اتریوم", "ETH"),
    BNB_CHAIN(2, "بی ان بی", "BNB");

    private final Integer index;
    private final String persianTitle;
    private final String honeypotTitle; // READ THIS FIELD FROM {CHAIN : CURRENCY} JSON

    CurrencyType(Integer index, String persianTitle, String englishTitle) {
        this.index = index;
        this.persianTitle = persianTitle;
        this.honeypotTitle = englishTitle;
    }

    public static HashMap<String, Object> getJsonValueOfIndex(Integer index) {
        for (CurrencyType currency : CurrencyType.values()) {
            if (currency.getIndex().equals(index)) {
                HashMap<String, Object> mainEnum = new HashMap<>();
                HashMap<String, Object> detailEnum = new HashMap<>();
                detailEnum.put("index", currency.getIndex());
                detailEnum.put("persianTitle", currency.getPersianTitle());
                detailEnum.put("honeypotTitle", currency.getHoneypotTitle());
                mainEnum.put(currency.name(), detailEnum);
                return mainEnum;
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
