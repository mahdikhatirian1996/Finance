package com.org.finance.Service.Newpairs;

import com.org.finance.Dto.Newpairs.NewpairDto;
import com.org.finance.Model.Main.DextoolsInfo;
import com.org.finance.Model.Main.HoneypotInfo;

import java.util.HashMap;

public interface INewPairsService {
    HashMap<String, Object> getAll(Integer currentPage, Integer pageSize);
    HashMap<String, Object> setDIOnDtos(Integer currentPage, Integer pageSize);
    HashMap<String, Object> setDIAndHiOnDtos(Integer currentPage, Integer pageSize);
    HashMap<String, Object> getAllWithoutHoneypot(Integer currentPage, Integer pageSize);
    NewpairDto mapDiAndHiListToEntity(NewpairDto dto, DextoolsInfo dextoolsInfo, HoneypotInfo honeypotInfo);
    NewpairDto mapDiListToEntity(NewpairDto dto, DextoolsInfo dextoolsInfo);
}
