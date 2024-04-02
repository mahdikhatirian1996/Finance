package com.org.finance.Dao.Dextools;

import com.org.finance.Model.Main.DextoolsInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IDextoolsRepository extends
        JpaRepository<DextoolsInfo, String>,
        PagingAndSortingRepository<DextoolsInfo, String> {
    DextoolsInfo findByContractAddress(String contractAddress);

    List<DextoolsInfo> findAllByContractAddress(String contractAddress, Pageable pageable);

}
