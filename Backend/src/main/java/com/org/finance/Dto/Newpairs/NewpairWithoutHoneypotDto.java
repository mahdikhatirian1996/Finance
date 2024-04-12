package com.org.finance.Dto.Newpairs;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NewpairWithoutHoneypotDto {
    public String contractAddress;
    // --- DextoolsInfo ---
    public String name;
    public String holders;
    public String liquidity;
    public String symbol;
    public String totalSupply;
    public Long createdDate;
    public Long updatedDate;
    public Long insertedDate;
    public String currencyTypeName;
    public Integer currencyTypeIndex;

}
