export class NewPairModel {
  contractAddress?: string;
  // --- HoneypotInfo.java ---
  nameHI?: string;
  holdersHI?: string;
  liquidityHI?: string;
  createdDateHI?: string;
  createdDateHITimestamp?: string;
  averageTax?: string;
  averageGas?: string;
  buyTax?: string;
  buyGas?: string;
  sellTax?: string;
  sellGas?: string;
  transferTax?: string;
  honeypotReason?: string;
  currencyTypeName?: string;
  isHoneypot?: boolean;
  isOpensource?: boolean;
  currencyTypeIndex?: number;
  // --- DextoolsInfo.java ---
  nameDI?: string;
  holdersDI?: string;
  liquidityDI?: string;
  createdDateDI?: number;
  createdDateDITimestamp?: string;
  symbol?: string;
  updatedDate?: number;
  insertedDate?: number;
  insertedDateTimestamp?: string;
}
