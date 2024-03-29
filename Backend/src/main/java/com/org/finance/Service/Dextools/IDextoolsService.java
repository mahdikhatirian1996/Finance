package com.org.finance.Service.Dextools;

import com.org.finance.Model.Main.DextoolsInfo;

import java.sql.Timestamp;

public interface IDextoolsService {
    public DextoolsInfo getByContractAddress(String contractAddress);
    public Boolean isGreaterThanSpecificHour(String timestampDigit, Integer specificHour);
    public Timestamp getDateFromDigitTimestamp(String timestampDigit);
    public DextoolsInfo save(DextoolsInfo entity);
}
