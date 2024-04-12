package com.org.finance.Dto.Newpairs;


import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
public class NewpairDto {
    public String contractAddress;
    // --- DextoolsInfo ---
    public String nameDI;
    public String holdersDI;
    public String liquidityDI;
    public String totalSupply;
    public Long createdDateDI;
    public String symbol;
    public Long updatedDate;
    public Long insertedDate;
    // --- honeypotInfo ---
    public String nameHI;
    public String holdersHI;
    public String liquidityHI;
    public Long createdDateHI;
    public String averageTax;
    public String averageGas;
    public String buyTax;
    public String buyGas;
    public String sellTax;
    public String sellGas;
    public String transferTax;
    public String honeypotReason;
    public String currencyTypeName;
    public Boolean isHoneypot;
    public Boolean isOpensource;
    public Integer currencyTypeIndex;

}
