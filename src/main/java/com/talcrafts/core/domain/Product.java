package com.talcrafts.core.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Product {

	private String code;
	private String name;
	private String carrierName;
	private BigDecimal premiumPerUnit;
	private Integer unit;
	private BigDecimal minCoverage;
	private BigDecimal maxCoverage;
	private BigDecimal claimSettlementrating;
	private BigDecimal minMortality;
	private BigDecimal maxMortality;
	private boolean cashValue;
	private Date productLaunchDate;
	private int minTenureYears;
	private int maxTenureYears;

	
	public Product(String code, String name, String carrierName) {
		this.carrierName = carrierName;
		this.code = code;
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCarrierName() {
		return carrierName;
	}
	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}
	public BigDecimal getPremiumPerUnit() {
		return premiumPerUnit;
	}
	public void setPremiumPerUnit(BigDecimal premiumPerUnit) {
		this.premiumPerUnit = premiumPerUnit;
	}
	public Integer getUnit() {
		return unit;
	}
	public void setUnit(Integer unit) {
		this.unit = unit;
	}
	public BigDecimal getMinCoverage() {
		return minCoverage;
	}
	public void setMinCoverage(BigDecimal minCoverage) {
		this.minCoverage = minCoverage;
	}
	public BigDecimal getMaxCoverage() {
		return maxCoverage;
	}
	public void setMaxCoverage(BigDecimal maxCoverage) {
		this.maxCoverage = maxCoverage;
	}
	public BigDecimal getClaimSettlementrating() {
		return claimSettlementrating;
	}
	public void setClaimSettlementrating(BigDecimal claimSettlementrating) {
		this.claimSettlementrating = claimSettlementrating;
	}
	public BigDecimal getMinMortality() {
		return minMortality;
	}
	public void setMinMortality(BigDecimal minMortality) {
		this.minMortality = minMortality;
	}
	public BigDecimal getMaxMortality() {
		return maxMortality;
	}
	public void setMaxMortality(BigDecimal maxMortality) {
		this.maxMortality = maxMortality;
	}
	public boolean isCashValue() {
		return cashValue;
	}
	public void setCashValue(boolean cashValue) {
		this.cashValue = cashValue;
	}
	public Date getProductLaunchDate() {
		return productLaunchDate;
	}
	public void setProductLaunchDate(Date productLaunchDate) {
		this.productLaunchDate = productLaunchDate;
	}
	public int getMinTenureYears() {
		return minTenureYears;
	}
	public void setMinTenureYears(int minTenureYears) {
		this.minTenureYears = minTenureYears;
	}
	public int getMaxTenureYears() {
		return maxTenureYears;
	}
	public void setMaxTenureYears(int maxTenureYears) {
		this.maxTenureYears = maxTenureYears;
	}

}
