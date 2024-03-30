package com.org.finance.Model.Main;

import com.org.finance.Model.Enum.ErrorType;
import jakarta.persistence.*;
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

    @Column(name = "CREATED_DATE")
    private Timestamp createdDate;

    @Column(name = "UPDATED_DATE")
    private Timestamp updatedDate;

    @Transient
    private ErrorType errorType;

    public DextoolsInfo() {}

    public DextoolsInfo(ErrorType errorType){
        this.setErrorType(errorType);
    }

    public DextoolsInfo(
        String contractAddress, String name, String symbol, String holders,
        String liquidity, Timestamp createdDate, Timestamp updatedDate, ErrorType errorType
    ) {
        this.contractAddress = contractAddress;
        this.name = name;
        this.symbol = symbol;
        this.holders = holders;
        this.liquidity = liquidity;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.errorType = errorType;
    }
}
