package com.org.finance.Dao.Honeypot;

import com.org.finance.Model.Main.HoneypotInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHoneypotRepository extends JpaRepository<HoneypotInfo, String> {
    HoneypotInfo findByContractAddress(String contractAddress);
}
