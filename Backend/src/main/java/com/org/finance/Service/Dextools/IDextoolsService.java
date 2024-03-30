package com.org.finance.Service.Dextools;

import com.org.finance.Model.Main.DextoolsInfo;

import java.io.IOException;
import java.sql.Timestamp;

public interface IDextoolsService {
    public DextoolsInfo getByContractAddress(String contractAddress);
    public Boolean isGreaterThanSpecificHour(Timestamp objectTs, Integer specificHour);
    public Timestamp getDateFromDigitTimestamp(String timestampDigit);
    public DextoolsInfo save(DextoolsInfo entity) throws IOException;
}
