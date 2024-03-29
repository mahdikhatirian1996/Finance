package com.org.finance.Model.Main;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "DEXTOOLS_INFO")
public class DextoolsInfo {

    @Id
    @Column(name = "CONTRACT_ADDRESS")
    private String contractAddress;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SYMBOL")
    private String symbol;

    @Column(name = "HOLDERS")
    private String holders;

    @Column(name = "LIQUIDITY")
    private String liquidity;

//    @Column(name = "CREATED_DATE")
    private String createdDate;

//    @Column(name = "UPDATED_DATE")
    private String updatedDate;

//    @Column(name = "CREATED_DATE")
    private Timestamp createdDateTemp;

//    @Column(name = "UPDATED_DATE")
    private Timestamp updatedDateTemp;

    public DextoolsInfo() {}

    public DextoolsInfo(
        String contractAddress, String name, String symbol, String holders,
        String liquidity, String createdDate, String updatedDate
    ) {
        this.contractAddress = contractAddress;
        this.name = name;
        this.symbol = symbol;
        this.holders = holders;
        this.liquidity = liquidity;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
