package com.org.finance.Dao.Dextools;

import com.org.finance.Model.Main.DextoolsInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDextoolsRepository extends JpaRepository<DextoolsInfo, String> {
    DextoolsInfo findByContractAddress(String contractAddress);
}
