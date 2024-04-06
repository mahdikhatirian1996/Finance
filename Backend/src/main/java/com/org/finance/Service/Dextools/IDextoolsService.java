package com.org.finance.Service.Dextools;

import com.org.finance.Model.Main.DextoolsInfo;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;

public interface IDextoolsService {
    DextoolsInfo getByContractAddress(String contractAddress);
    Boolean isGreaterThanSpecificHour(Timestamp objectTs, Integer specificHour);
    Timestamp getDateFromDigitTimestamp(String timestampDigit);
    DextoolsInfo save(DextoolsInfo entity) throws IOException;
    HashMap<String, Object> findAll(Integer currentPage, Integer pageSize);
}
