package com.org.finance.Service.Impl.Newpairs;

import com.org.finance.Dto.Newpairs.NewpairDto;
import com.org.finance.Model.Main.DextoolsInfo;
import com.org.finance.Model.Main.HoneypotInfo;
import com.org.finance.Service.Dextools.IDextoolsService;
import com.org.finance.Service.Honeypot.IHoneypotService;
import com.org.finance.Service.Newpairs.INewPairsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class NewPairsService implements INewPairsService {

    @Autowired
    private IDextoolsService iDextoolsService;

    @Autowired
    private IHoneypotService iHoneypotService;

    @Override
    public HashMap<String, Object> getAllWithoutHoneypot(Integer currentPage, Integer pageSize) {
        return setDIOnDtos(currentPage, pageSize);
    }

    @Override
    public HashMap<String, Object> getAll(Integer currentPage, Integer pageSize) {
        return setDIAndHiOnDtos(currentPage, pageSize);
    }

    @Override
    public HashMap<String, Object> setDIOnDtos(Integer currentPage, Integer pageSize) {
        List<NewpairDto> newpairDtos = new ArrayList<>();
        HashMap<String, Object> params = new HashMap<>();
        Page<DextoolsInfo> dextoolsInfos = iDextoolsService.findAllSolanaAndBase(currentPage, pageSize);

        for (DextoolsInfo object : dextoolsInfos.getContent()) {
            newpairDtos.add(this.mapDiListToEntity(
                    new NewpairDto(),
                    object
            ));
        }
        params.put("list", newpairDtos);
        params.put("count", dextoolsInfos.getTotalElements());
        return params;
    }

    @Override
    public HashMap<String, Object> setDIAndHiOnDtos(Integer currentPage, Integer pageSize) {
        List<NewpairDto> newpairDtos = new ArrayList<>();
        HashMap<String, Object> params = iDextoolsService.findAll(currentPage, pageSize);
        List<DextoolsInfo> dextoolsInfos = (List<DextoolsInfo>) params.get("list");
        List<HoneypotInfo> hInfos = iHoneypotService.getListByDextoolsContractAddress(dextoolsInfos);
        for (int i = 0; i < dextoolsInfos.size(); i++) {
            newpairDtos.add(this.mapDiAndHiListToEntity(
                    new NewpairDto(),
                    dextoolsInfos.get(i),
                    hInfos.get(i)
            ));
        }
        params.put("list", newpairDtos);
        return params;
    }

    @Override
    public NewpairDto mapDiAndHiListToEntity(NewpairDto dto, DextoolsInfo dextoolsInfo, HoneypotInfo honeypotInfo) {
        dto.setContractAddress(dextoolsInfo.getContractAddress());
        dto.setNameDI(dextoolsInfo.getName());
        dto.setHoldersDI(dextoolsInfo.getHolders());
        dto.setLiquidityDI(dextoolsInfo.getLiquidity());
        dto.setCreatedDateDI(dextoolsInfo.getCreatedDate().getTime());
        dto.setSymbol(dextoolsInfo.getSymbol());
        dto.setUpdatedDate(dextoolsInfo.getUpdatedDate().getTime());
        dto.setInsertedDate(dextoolsInfo.getInsertedDate().getTime());
        dto.setNameHI(honeypotInfo.getName());
        dto.setHoldersHI(honeypotInfo.getHolders());
        dto.setLiquidityHI(honeypotInfo.getLiquidity());
        dto.setCreatedDateHI(Long.valueOf(honeypotInfo.getCreatedDate()));
        dto.setAverageTax(honeypotInfo.getAverageTax());
        dto.setAverageGas(honeypotInfo.getAverageGas());
        dto.setBuyTax(honeypotInfo.getBuyTax());
        dto.setBuyGas(honeypotInfo.getBuyGas());
        dto.setSellTax(honeypotInfo.getSellTax());
        dto.setSellGas(honeypotInfo.getSellGas());
        dto.setTransferTax(honeypotInfo.getTransferTax());
        dto.setHoneypotReason(honeypotInfo.getHoneypotReason());
        dto.setCurrencyTypeName(honeypotInfo.getCurrencyType().name());
        dto.setIsHoneypot(honeypotInfo.getIsHoneypot());
        dto.setIsOpensource(honeypotInfo.getIsOpensource());
        dto.setCurrencyTypeIndex(honeypotInfo.getCurrencyType().getIndex());
        return dto;
    }

    @Override
    public NewpairDto mapDiListToEntity(NewpairDto dto, DextoolsInfo dextoolsInfo) {
        dto.setContractAddress(dextoolsInfo.getContractAddress());
        dto.setNameDI(dextoolsInfo.getName());
        dto.setHoldersDI(dextoolsInfo.getHolders());
        dto.setLiquidityDI(dextoolsInfo.getLiquidity());
        dto.setCreatedDateDI(dextoolsInfo.getCreatedDate().getTime());
        dto.setSymbol(dextoolsInfo.getSymbol());
        dto.setUpdatedDate(dextoolsInfo.getUpdatedDate().getTime());
        dto.setInsertedDate(dextoolsInfo.getInsertedDate().getTime());
        dto.setCurrencyTypeName(dextoolsInfo.getCurrencyType().getHoneypotTitle());
        dto.setTotalSupply(dextoolsInfo.getTotalSupply());
        return dto;
    }

}
