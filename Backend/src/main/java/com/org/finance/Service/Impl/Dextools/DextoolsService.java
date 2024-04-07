package com.org.finance.Service.Impl.Dextools;

import com.org.finance.Dao.Dextools.IDextoolsRepository;
import com.org.finance.Model.Enum.ErrorType;
import com.org.finance.Model.Main.DextoolsInfo;
import com.org.finance.Model.Main.HoneypotInfo;
import com.org.finance.Service.Dextools.IDextoolsService;
import com.org.finance.Service.Honeypot.IHoneypotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class DextoolsService implements IDextoolsService {

    private final DextoolsInfo repeatedObject = new DextoolsInfo(ErrorType.REPEATED);
    private final DextoolsInfo outOfTimeObject = new DextoolsInfo(ErrorType.OUT_OF_TIME);

    @Autowired
    private IDextoolsRepository iDextoolsRepository;

    @Autowired
    private IHoneypotService iHoneypotService;

    @Override
    public DextoolsInfo getByContractAddress(String contractAddress) {
        return iDextoolsRepository.findByContractAddress(contractAddress);
    }

    @Override
    public Timestamp getDateFromDigitTimestamp(String timestampDigit) {
        return new Timestamp(Long.parseLong(timestampDigit));
    }

    @Override
    public Boolean isGreaterThanSpecificHour(Timestamp objectTs, Integer specificHour) {
        Timestamp currentTs = new Timestamp(System.currentTimeMillis());
        if (objectTs.getDate() == currentTs.getDate()) {
            if (currentTs.getHours() - objectTs.getHours() <= specificHour) {
                return true;
            }
        }
        return false;
    }

    @Override
    public DextoolsInfo save(DextoolsInfo entity) throws IOException {
        if (isGreaterThanSpecificHour(entity.getCreatedDate(), 1)) {
            if (
                    iDextoolsRepository.findByContractAddress(entity.getContractAddress()) == null &&
                    iHoneypotService.findByContractAddress(entity.getContractAddress()) == null
            ) {
                HoneypotInfo honeypotInfo = iHoneypotService.save(entity.getContractAddress());
                if (entity.getName() == null) {
                    entity.setName(honeypotInfo.getName());
                }
                entity.setInsertedDate(new Timestamp(System.currentTimeMillis()));
                return iDextoolsRepository.save(entity);
            } else {
                return repeatedObject;
            }
        } else {
            return outOfTimeObject;
        }
    }

    @Override
    public HashMap<String, Object> findAll(Integer currentPage, Integer pageSize) {
        HashMap<String, Object> params = new HashMap<>();
        Pageable pageable =
                PageRequest.of(currentPage, pageSize, Sort.by("createdDate").descending());
        Page<DextoolsInfo> dextoolsInfos = iDextoolsRepository.findAll(pageable);
        params.put("list", dextoolsInfos.getContent());
        params.put("count", dextoolsInfos.getTotalElements());
        return params;
    }
}
