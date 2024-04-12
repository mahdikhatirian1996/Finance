package com.org.finance.Dao.Dextools;

import com.org.finance.Model.Main.DextoolsInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IDextoolsRepository extends
        JpaRepository<DextoolsInfo, String>,
        PagingAndSortingRepository<DextoolsInfo, String> {
    DextoolsInfo findByContractAddress(String contractAddress);

    @Query(value = "SELECT * FROM DEXTOOLS_INFO e WHERE e.CURRENCY_TYPE IN (:currencyTypes)", nativeQuery = true)
    Page<DextoolsInfo> findAllBycByCurrencyTypeIndex(@Param("currencyTypes") List<Integer> currencyTypes, Pageable pageable);
}
