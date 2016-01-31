package com.talcrafts;

import java.util.ArrayList;
import java.util.List;

import com.talcrafts.watson.domain.Column;
import com.talcrafts.watson.domain.Problem;
import com.talcrafts.watson.service.TradeoffAnalyticsService.OBJECTIVE;

public enum RISK_CATEGORY {
	HEALTH("Health_Insurance"), LIFE("Life_Insurance");

	private static final String NUMERIC = "numeric";
	public static final String CLAIM_SETTLEMENT_RATIO = "claimSettlementRatio";
	public static final String MAXIMUM_TERM = "maximumTerm";
	public static final String CASH_VALUE = "cashValue";
	public static final String COVERAGE = "coverage";
	public static final String PREMIUM = "premium";
	private static final String MAX = "max";
	private static final String MIN = "min";
	private static final List<OBJECTIVE> NO_OBJECTIVES = new ArrayList<>(0);

	private RISK_CATEGORY(String subject) {
		this.subject = subject;
	}

	private Problem problem = new Problem();

	private String subject = null;

	private RISK_CATEGORY() {
	}

	public Problem getProblem(List<OBJECTIVE> objectives) {
		List<Column> columns = new ArrayList<>();
		if (objectives == null) {
			objectives = NO_OBJECTIVES;
		}
		switch (this) {
		case HEALTH:
			if (objectives.contains(OBJECTIVE.ALL) || objectives.contains(OBJECTIVE.PREMIUM)) {
				columns.add(getPremiumColumn());
			}
			if (objectives.contains(OBJECTIVE.ALL) || objectives.contains(OBJECTIVE.COVERAGE)) {
				columns.add(getCoverageColumn());
			}
			if (objectives.contains(OBJECTIVE.ALL) || objectives.contains(OBJECTIVE.CSR)) {
				columns.add(getClaimSettlementRatio());
			}
			if (objectives.contains(OBJECTIVE.ALL) || objectives.contains(OBJECTIVE.TERM)) {
				columns.add(getTermColumn());
			}
			break;
		case LIFE:
			break;
		default:
		}
		problem.setColumns(columns.toArray(new Column[columns.size()]));
		problem.setSubject(getSubject());
		return problem;
	}

	public String getSubject() {
		return subject;
	}

	private Column getPremiumColumn() {
		Column premiumColumn = new Column();
		premiumColumn.setType(NUMERIC);
		premiumColumn.setKey(PREMIUM);
		premiumColumn.setFullName("Premium(\u20B9 per lac per month)");
		premiumColumn.setIsObjective(true);
		premiumColumn.setGoal(MIN);
		premiumColumn.setFormat("####0");
		return premiumColumn;
	}

	private Column getCoverageColumn() {
		Column coverageColumn = new Column();
		coverageColumn.setType(NUMERIC);
		coverageColumn.setKey(COVERAGE);
		coverageColumn.setFullName("Coverage(\u20B9)");
		coverageColumn.setIsObjective(true);
		coverageColumn.setGoal(MAX);
		coverageColumn.setFormat("####0");
		return coverageColumn;
	}

	private Column getTermColumn() {
		Column termColumn = new Column();
		termColumn.setType(NUMERIC);
		termColumn.setKey(MAXIMUM_TERM);
		termColumn.setFullName("Maximum Term(Years)");
		termColumn.setIsObjective(true);
		termColumn.setGoal(MAX);
		return termColumn;
	}

	private Column getClaimSettlementRatio() {
		Column claimSettlementRatioColumn = new Column();
		claimSettlementRatioColumn.setType(NUMERIC);
		claimSettlementRatioColumn.setKey(CLAIM_SETTLEMENT_RATIO);
		claimSettlementRatioColumn.setFullName("Claim Settlement Ratio(%)");
		claimSettlementRatioColumn.setIsObjective(true);
		claimSettlementRatioColumn.setGoal(MAX);
		return claimSettlementRatioColumn;
	}

}
