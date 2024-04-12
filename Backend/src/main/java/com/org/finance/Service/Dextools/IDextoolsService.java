package com.org.finance.Service.Dextools;

import com.org.finance.Dto.Dextools.DextoolsInfoDto;
import com.org.finance.Model.Enum.CurrencyType;
import com.org.finance.Model.Main.DextoolsInfo;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;

public interface IDextoolsService {
    DextoolsInfo mapDtoToEntity(DextoolsInfoDto dextoolsInfoDto, CurrencyType currencyType);
    Boolean isGreaterThanSpecificHour(Timestamp objectTs, Integer specificHour);
    DextoolsInfo saveWithoutHoneypot(DextoolsInfo entity) throws IOException;
    HashMap<String, Object> findAll(Integer currentPage, Integer pageSize);
    Page<DextoolsInfo> findAllSolanaAndBase(Integer currentPage, Integer pageSize);
    DextoolsInfo getByContractAddress(String contractAddress);
    DextoolsInfo save(DextoolsInfo entity) throws IOException;
    Timestamp getDateFromDigitTimestamp(Long timestampDigit);
}
