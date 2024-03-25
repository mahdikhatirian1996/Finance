package com.org.finance.Dao.honeypot;

import com.org.finance.Model.main.HoneypotInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHoneypotRepository extends JpaRepository<HoneypotInfo, String> {
    HoneypotInfo findByContractAddress(String contractAddress);
}
