package com.org.finance.Service.Impl.Dextools;

import com.org.finance.Dao.Dextools.IDextoolsRepository;
import com.org.finance.Dto.Dextools.DextoolsInfoDto;
import com.org.finance.Model.Enum.CurrencyType;
import com.org.finance.Model.Enum.ErrorType;
import com.org.finance.Model.Main.DextoolsInfo;
import com.org.finance.Model.Main.HoneypotInfo;
import com.org.finance.Service.Dextools.IDextoolsService;
import com.org.finance.Service.Honeypot.IHoneypotService;
import org.json.JSONObject;
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
    private final DextoolsInfo littleHoldersObject = new DextoolsInfo(ErrorType.LITTLE_HOLDERS);
    private final DextoolsInfo unknownObject = new DextoolsInfo(ErrorType.UNKNOWN);

    @Autowired
    private IDextoolsRepository iDextoolsRepository;

    @Autowired
    private IHoneypotService iHoneypotService;

    @Override
    public DextoolsInfo getByContractAddress(String contractAddress) {
        return iDextoolsRepository.findByContractAddress(contractAddress);
    }

    @Override
    public Timestamp getDateFromDigitTimestamp(Long timestampDigit) {
        return new Timestamp(timestampDigit);
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
        /* if (
                objectTs.getYear() == currentTs.getYear() &&
                objectTs.getMonth() == currentTs.getMonth() &&
                objectTs.getDate() == currentTs.getDate()
        ) {
            return currentTs.getHours() - objectTs.getHours() <= specificHour;
        }
        return false; */
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
                entity.setCurrencyType(honeypotInfo.getCurrencyType());
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
        List<Integer> integerList = new ArrayList<>();
        integerList.add(0);
        integerList.add(1);
        Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by("CREATED_DATE").descending());
        Page<DextoolsInfo> dextoolsInfos = iDextoolsRepository.findAllBycByCurrencyTypeIndex(integerList, pageable);
        params.put("list", dextoolsInfos.getContent());
        params.put("count", dextoolsInfos.getTotalElements());
        return params;
    }

    @Override
    public Page<DextoolsInfo> findAllSolanaAndBase(Integer currentPage, Integer pageSize) {
        HashMap<String, Object> params = new HashMap<>();
        List<Integer> integerList = new ArrayList<>();
        integerList.add(2);
        integerList.add(3);
        Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by("CREATED_DATE").descending());
        return iDextoolsRepository.findAllBycByCurrencyTypeIndex(integerList, pageable);
    }

    @Override
    public DextoolsInfo mapDtoToEntity(DextoolsInfoDto dto, CurrencyType currencyType) {
        JSONObject jsonObject = new JSONObject(dto.getInfo());
        DextoolsInfo entity = new DextoolsInfo();

        if (currencyType.equals(CurrencyType.BASE)) {
            entity.setCreatedDate(dto.getCreatedAtTimestamp());
            entity.setCurrencyType(currencyType);
        } else if (currencyType.equals(CurrencyType.SOLANA)) {
            entity.setCreatedDate(dto.getCreatedAt());
            entity.setCurrencyType(currencyType);
        }
        if (jsonObject.has("name")) {
            entity.setName(jsonObject.getString("name"));
        }
        if (jsonObject.has("symbol")) {
            entity.setSymbol(jsonObject.getString("symbol"));
        }
        if (jsonObject.has("totalSupply")) {
            entity.setTotalSupply(jsonObject.getString("totalSupply"));
        }

        entity.setContractAddress(jsonObject.getString("address"));
        entity.setHolders(String.valueOf(jsonObject.getLong("holders")));
        entity.setUpdatedDate(this.getDateFromDigitTimestamp(dto.getUpdatedAt() * 1000L));
        entity.setLiquidity(String.valueOf(dto.getLiquidity()));
        return entity;
    }

    @Override
    public DextoolsInfo saveWithoutHoneypot(DextoolsInfo entity) throws IOException {
        if (
                isGreaterThanSpecificHour(entity.getCreatedDate(), 1) &&
                isHolderMoreSpecificCount(entity, 100L)
        ) {
            if (iDextoolsRepository.findByContractAddress(entity.getContractAddress()) == null) {
                entity.setInsertedDate(new Timestamp(System.currentTimeMillis()));
                return iDextoolsRepository.save(entity);
            } else {
                return repeatedObject;
            }
        } else {
            if (!isGreaterThanSpecificHour(entity.getCreatedDate(), 1))
                return outOfTimeObject;
            else if (!isHolderMoreSpecificCount(entity, 100L))
                return littleHoldersObject;
            return unknownObject;
        }
    }

    @Override
    public Boolean isHolderMoreSpecificCount(DextoolsInfo entity, Long specificHolder) {
        return entity.getHolders() != null && Long.parseLong(entity.getHolders()) > specificHolder;
    }
}
