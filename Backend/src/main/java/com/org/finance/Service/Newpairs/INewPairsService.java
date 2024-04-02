package com.org.finance.Service.Newpairs;

import com.org.finance.Dto.Newpairs.NewpairDto;
import com.org.finance.Model.Main.DextoolsInfo;
import com.org.finance.Model.Main.HoneypotInfo;

import java.util.List;

public interface INewPairsService {
    List<NewpairDto> getAll(Integer currentPage, Integer pageSize);
    List<NewpairDto> setDIAndHiOnDtos(Integer currentPage, Integer pageSize);
    NewpairDto mapObjectListToEntity(NewpairDto dto, DextoolsInfo dextoolsInfo, HoneypotInfo honeypotInfo);
}
