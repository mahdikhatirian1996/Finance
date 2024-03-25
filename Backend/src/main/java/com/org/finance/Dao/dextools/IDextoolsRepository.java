package com.org.finance.Dao.dextools;

import com.org.finance.Model.main.DextoolsInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDextoolsRepository extends JpaRepository<DextoolsInfo, String> {
    DextoolsInfo findByContractAddress(String contractAddress);
}
