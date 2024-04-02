package com.org.finance.Service.Impl.Newpairs;

import com.org.finance.Dto.Newpairs.NewpairDto;
import com.org.finance.Model.Main.DextoolsInfo;
import com.org.finance.Model.Main.HoneypotInfo;
import com.org.finance.Service.Dextools.IDextoolsService;
import com.org.finance.Service.Honeypot.IHoneypotService;
import com.org.finance.Service.Newpairs.INewPairsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewPairsService implements INewPairsService {

    @Autowired
    private IDextoolsService iDextoolsService;

    @Autowired
    private IHoneypotService iHoneypotService;

    @Override
    public List<NewpairDto> getAll(Integer currentPage, Integer pageSize) {
        return setDIAndHiOnDtos(currentPage, pageSize);
    }

    @Override
    public List<NewpairDto> setDIAndHiOnDtos(Integer currentPage, Integer pageSize) {
        List<NewpairDto> newpairDtos = new ArrayList<>();
        List<DextoolsInfo> dInfos = iDextoolsService.findAll(currentPage, pageSize);
        List<HoneypotInfo> hInfos = iHoneypotService.getListByDextoolsContractAddress(dInfos);
        for (int i = 0; i < dInfos.size(); i++) {
            newpairDtos.add(this.mapObjectListToEntity(
                    new NewpairDto(),
                    dInfos.get(i),
                    hInfos.get(i)
            ));
        }
        return newpairDtos;
    }

    @Override
    public NewpairDto mapObjectListToEntity(NewpairDto dto, DextoolsInfo dextoolsInfo, HoneypotInfo honeypotInfo) {
        dto.setContractAddress(dextoolsInfo.getContractAddress());
        dto.setNameDI(dextoolsInfo.getName());
        dto.setHoldersDI(dextoolsInfo.getHolders());
        dto.setLiquidityDI(dextoolsInfo.getLiquidity());
        dto.setCreatedDateDI(dextoolsInfo.getCreatedDate().getTime());
        dto.setSymbol(dextoolsInfo.getSymbol());
        dto.setUpdatedDate(dextoolsInfo.getUpdatedDate().getTime());
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
}
