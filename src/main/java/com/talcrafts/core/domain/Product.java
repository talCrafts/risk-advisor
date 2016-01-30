package com.talcrafts.core.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Product {
	private String code;
	private String name;
	private String carrierName;
	private String premiumPerUnit;
	private String minCoverage;
	private String maxCoverage;
	private String claimSettlementrating;
	private String risk;
	private boolean cashValue;
	private String minTenureYears;
	private String maxTenureYears;
	private String smoker;
	private String benefits;
	private String riders;
	private String maxAge;

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

	public String getPremiumPerUnit() {
		return premiumPerUnit;
	}

	public void setPremiumPerUnit(String premiumPerUnit) {
		this.premiumPerUnit = premiumPerUnit;
	}

	public String getMinCoverage() {
		return minCoverage;
	}

	public void setMinCoverage(String minCoverage) {
		this.minCoverage = minCoverage;
	}

	public String getMaxCoverage() {
		return maxCoverage;
	}

	public void setMaxCoverage(String maxCoverage) {
		this.maxCoverage = maxCoverage;
	}

	public String getClaimSettlementrating() {
		return claimSettlementrating;
	}

	public void setClaimSettlementrating(String claimSettlementrating) {
		this.claimSettlementrating = claimSettlementrating;
	}

	public String getRisk() {
		return risk;
	}

	public void setRisk(String risk) {
		this.risk = risk;
	}

	public boolean isCashValue() {
		return cashValue;
	}

	public void setCashValue(boolean cashValue) {
		this.cashValue = cashValue;
	}

	public String getMinTenureYears() {
		return minTenureYears;
	}

	public void setMinTenureYears(String minTenureYears) {
		this.minTenureYears = minTenureYears;
	}

	public String getMaxTenureYears() {
		return maxTenureYears;
	}

	public void setMaxTenureYears(String maxTenureYears) {
		this.maxTenureYears = maxTenureYears;
	}

	public String getSmoker() {
		return smoker;
	}

	public void setSmoker(String smoker) {
		this.smoker = smoker;
	}

	public String getBenefits() {
		return benefits;
	}

	public void setBenefits(String benefits) {
		this.benefits = benefits;
	}

	public String getRiders() {
		return riders;
	}

	public void setRiders(String riders) {
		this.riders = riders;
	}

	public String getMaxAge()
	{
		return maxAge;
	}

	public void setMaxAge(String maxAge)
	{
		this.maxAge = maxAge;
	}
}