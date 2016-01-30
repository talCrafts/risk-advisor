package com.talcrafts.core.domain;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ibm.watson.developer_cloud.tradeoff_analytics.v1.model.Option;
import com.talcrafts.RISK_CATEGORY;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
	private static final String CARRIER = "Carrier";

	private String code;
	private String name;
	private String carrierName;
	private String premiumPerLac;
	private String minCoverage;
	private String maxCoverage;
	private String claimSettlementrating;
	private String risk;
	private int cashValue = 0;
	private String minTenureYears;
	private String maxTenureYears;
	private String smoker;
	private String benefits;
	private String riders;
	private String descriptionHtml;
	private String maxAge;

	public String findRisk(Double mortalityRiskFactor) {
		if (mortalityRiskFactor <= 20) {
			return "Low";
		} else if (mortalityRiskFactor > 20 && mortalityRiskFactor <= 50) {
			return "Moderate";
		} else {
			return "High";
		}
	}

	public Product() {

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

	public String getPremiumPerLac() {
		return premiumPerLac;
	}

	public void setPremiumPerLac(String premiumPerLac) {
		this.premiumPerLac = premiumPerLac;
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

	public int getCashValue() {
		return cashValue;
	}

	public void setCashValue(int cashValue) {
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
		values.put(RISK_CATEGORY.PREMIUM, Double.parseDouble(getPremiumPerLac()));
		values.put(RISK_CATEGORY.COVERAGE, Double.parseDouble(getMaxCoverage()));
		values.put(RISK_CATEGORY.MAXIMUM_TERM, Double.parseDouble(getMaxTenureYears()));
		values.put(RISK_CATEGORY.CLAIM_SETTLEMENT_RATIO, Double.parseDouble(getClaimSettlementrating()));
		values.put(RISK_CATEGORY.CASH_VALUE, getCashValue());
		values.put(CARRIER, getCarrierName());
		option.setValues(values);
		return option;
	}

	public String getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(String maxAge) {
		this.maxAge = maxAge;
	}

}