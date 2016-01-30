package com.talcrafts.core.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

import org.joda.time.DateTime;

import com.ibm.watson.developer_cloud.tradeoff_analytics.v1.model.Option;
import com.talcrafts.RISK_CATEGORY;

public class Product {
	private static final String CARRIER = "Carrier";
	private static final String PRODUCT_FIRST_AVAILABLE_IN = "Product First Available:";

	private String code;
	private String name;
	private String carrierName;
	private BigDecimal premiumPerUnit;
	private Integer unit;
	private BigDecimal minCoverage;
	private BigDecimal maxCoverage;
	private BigDecimal claimSettlementrating;
	private String risk;
	private int cashValue = 0;
	private Date productLaunchDate;
	private int minTenureYears;
	private int maxTenureYears;
	private boolean smoker;
	private String benefits;
	private String riders;
	private String descriptionHtml;

	public String findRisk(Double mortalityRiskFactor) {
		if (mortalityRiskFactor <= 20) {
			return "Low";
		} else if (mortalityRiskFactor > 20 && mortalityRiskFactor <= 50) {
			return "Moderate";
		} else {
			return "High";
		}
	}

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

	public String getRisk() {
		return risk;
	}

	public void setRisk(String risk) {
		this.risk = risk;
	}

	public void setRisk(Double mortalityRiskFactor) {
		this.risk = findRisk(mortalityRiskFactor);
	}

	public int getCashValue() {
		return cashValue;
	}

	public void setCashValue(int cashValue) {
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

	public boolean isSmoker() {
		return smoker;
	}

	public void setSmoker(boolean smoker) {
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

	public String getDescriptionHtml() {
		return descriptionHtml;
	}

	public void setDescriptionHtml(String descriptionHtml) {
		this.descriptionHtml = descriptionHtml;
	}

	public Option toOption() {
		Option option = new Option();
		option.setKey(getCode());
		option.setName(getName());
		option.setDescriptionHtml(getDescriptionHtml());
		HashMap<String, Object> values = new HashMap<>();
		values.put(RISK_CATEGORY.PREMIUM, getPremiumPerUnit());
		values.put(RISK_CATEGORY.COVERAGE, getMaxCoverage());
		values.put(RISK_CATEGORY.MAXIMUM_TERM, getMaxTenureYears());
		values.put(RISK_CATEGORY.CLAIM_SETTLEMENT_RATIO, getClaimSettlementrating());
		values.put(RISK_CATEGORY.CASH_VALUE, getCashValue());
		values.put(CARRIER, getCarrierName());
		values.put(PRODUCT_FIRST_AVAILABLE_IN, new DateTime(getProductLaunchDate()).getYear());
		option.setValues(values);
		return option;
	}
}