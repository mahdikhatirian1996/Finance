package com.org.finance.Service.Impl.Dextools;

import com.org.finance.Dao.Dextools.IDextoolsRepository;
import com.org.finance.Model.Main.DextoolsInfo;
import com.org.finance.Service.Dextools.IDextoolsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class DextoolsService implements IDextoolsService {

    @Autowired
    private IDextoolsRepository iDextoolsRepository;

    @Override
    public DextoolsInfo getByContractAddress(String contractAddress) {
        return iDextoolsRepository.findByContractAddress(contractAddress);
    }

    @Override
    public Timestamp getDateFromDigitTimestamp(String timestampDigit) {
        return new Timestamp(Long.parseLong(timestampDigit));
    }

    @Override
    public Boolean isGreaterThanSpecificHour(String timestampDigit, Integer specificHour) {
        Timestamp objectTs = getDateFromDigitTimestamp(timestampDigit + "000");
        Timestamp currentTs = new Timestamp(System.currentTimeMillis());
        if (objectTs.getDate() == currentTs.getDate()) {
            if (currentTs.getHours() - objectTs.getHours() <= specificHour) {
                return true;
            }
        }
        return false;
    }

    @Override
    public DextoolsInfo save(DextoolsInfo entity) {
        if (isGreaterThanSpecificHour(entity.getCreatedDate(), 2)){
            if (iDextoolsRepository.findByContractAddress(entity.getContractAddress()) == null) {
                return iDextoolsRepository.save(entity);
            }
        }
        return null;
    }
}
