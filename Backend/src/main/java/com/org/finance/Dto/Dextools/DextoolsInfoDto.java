package com.org.finance.Dto.Dextools;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class DextoolsInfoDto {
    private String info;
    private String tempInfo;
    private Long updatedAt;
    private Timestamp createdAt;
    private Timestamp createdAtTimestamp;
    private Double liquidity;
}
