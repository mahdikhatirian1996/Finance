package com.org.finance.Model.main;

import com.org.finance.Model.Enum.CurrencyType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "HONEYPOT_INFO")
public class HoneypotInfo {

    @Id
    @Column(name = "CONTRACT_ADDRESS")
    private String contractAddress;

    @Column(name = "HOLDERS")
    private String holders;

    @Column(name = "AVERAGE_TAX")
    private String averageTax;

    @Column(name = "AVERAGE_GAS")
    private String averageGas;

    @Column(name = "SELL_GAS")
    private String sellGas;

    @Column(name = "BUY_GAS")
    private String buyGas;

    @Column(name = "SELL_TAX")
    private String sellTax;

    @Column(name = "BUY_TAX")
    private String buyTax;

    @Column(name = "TRANSFER_TAX")
    private String transferTax;

    @Column(name = "IS_HONEYPOT")
    private Boolean isHoneypot;

    @Column(name = "HONEYPOT_REASON")
    private String honeypotReason;

    @Column(name = "LIQUIDITY")
    private String liquidity;

    @Column(name = "CREATED_DATE")
    private String createdDate;

    @Column(name = "CURRENCY_TYPE")
    @Enumerated(EnumType.ORDINAL)
    private CurrencyType currencyType;

    @Column(name = "IS_OPENSOURCE")
    private Boolean isOpensource;


    public HoneypotInfo() {
    }

    public HoneypotInfo(
        String contractAddress, String holders, String averageTax, String averageGas, String sellGas,
        String buyGas, String sellTax, String buyTax, String transferTax, Boolean isHoneypot,
        String honeypotReason, String liquidity, CurrencyType currencyType, Boolean isOpensource
    ) {
        this.contractAddress = contractAddress;
        this.holders = holders;
        this.averageTax = averageTax;
        this.averageGas = averageGas;
        this.sellGas = sellGas;
        this.buyGas = buyGas;
        this.sellTax = sellTax;
        this.buyTax = buyTax;
        this.transferTax = transferTax;
        this.isHoneypot = isHoneypot;
        this.honeypotReason = honeypotReason;
        this.liquidity = liquidity;
        this.currencyType = currencyType;
        this.isOpensource = isOpensource;
    }
}
