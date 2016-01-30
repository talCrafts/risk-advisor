package com.talcrafts;

import java.util.ArrayList;
import java.util.List;

import com.ibm.watson.developer_cloud.tradeoff_analytics.v1.model.Problem;
import com.ibm.watson.developer_cloud.tradeoff_analytics.v1.model.column.Column;
import com.ibm.watson.developer_cloud.tradeoff_analytics.v1.model.column.Column.Goal;
import com.ibm.watson.developer_cloud.tradeoff_analytics.v1.model.column.NumericColumn;

public enum RISK_CATEGORY {
	HEALTH, 
	LIFE;

	public static final String CLAIM_SETTLEMENT_RATIO = "claimSettlementRatio";
	public static final String MAXIMUM_TERM = "maximumTerm";
	public static final String CASH_VALUE = "cashValue";
	public static final String COVERAGE = "coverage";
	public static final String PREMIUM = "premium";
	private Problem problem = new Problem();

	private RISK_CATEGORY() {
		problem.setSubject(name());
	}

	public Problem getProblem() {
		List<Column> columns = new ArrayList<>();
		switch (this) {
		case HEALTH:
			columns.add(getPremiumColumn());
			columns.add(getCoverageColumn());
			columns.add(getTermColumn());
			columns.add(getClaimSettlementRatio());
			columns.add(getCashValueColumn());
			break;
		case LIFE:
			break;
		default:
		}
		problem.setColumns(columns);
		return problem;
	}

	private Column getPremiumColumn() {
		Column premiumColumn = new NumericColumn();
		premiumColumn.setKey(PREMIUM);
		premiumColumn.setFullName("Premium");
		premiumColumn.setObjective(true);
		premiumColumn.setGoal(Goal.MIN);
		premiumColumn.setFormat("\u20B9####0.00");
		return premiumColumn;
	}

	private Column getCoverageColumn() {
		Column coverageColumn = new NumericColumn();
		coverageColumn.setKey(COVERAGE);
		coverageColumn.setFullName("Coverage");
		coverageColumn.setObjective(true);
		coverageColumn.setGoal(Goal.MAX);
		coverageColumn.setFormat("\u20B9####0.00");
		return coverageColumn;
	}

	private Column getCashValueColumn() {
		Column coverageColumn = new NumericColumn();
		coverageColumn.setKey(CASH_VALUE);
		coverageColumn.setFullName("Cash Value");
		coverageColumn.setObjective(true);
		coverageColumn.setGoal(Goal.MAX);
		coverageColumn.setFormat("\u20B9####0.00");
		return coverageColumn;
	}

	private Column getTermColumn() {
		Column termColumn = new NumericColumn();
		termColumn.setKey(MAXIMUM_TERM);
		termColumn.setFullName("Maximum Term");
		termColumn.setObjective(true);
		termColumn.setGoal(Goal.MAX);
		return termColumn;
	}

	private Column getClaimSettlementRatio() {
		Column claimSettlementRatioColumn = new NumericColumn();
		claimSettlementRatioColumn.setKey(CLAIM_SETTLEMENT_RATIO);
		claimSettlementRatioColumn.setFullName("Claim Settlement Ratio");
		claimSettlementRatioColumn.setObjective(true);
		claimSettlementRatioColumn.setGoal(Goal.MAX);
		claimSettlementRatioColumn.setFormat("####0.0%");
		return claimSettlementRatioColumn;
	}

}
