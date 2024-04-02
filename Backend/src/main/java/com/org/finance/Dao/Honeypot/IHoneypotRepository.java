package com.org.finance.Dao.Honeypot;

import com.org.finance.Model.Main.HoneypotInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IHoneypotRepository extends
        JpaRepository<HoneypotInfo, String>,
        PagingAndSortingRepository<HoneypotInfo, String> {
    HoneypotInfo findByContractAddress(String contractAddress);

    List<HoneypotInfo> findAllByContractAddress(String contractAddress, Pageable pageable);
}
