package com.talcrafts.core.util;

import java.io.FileReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.joda.time.LocalDate;
import org.joda.time.Years;

import com.talcrafts.core.domain.User;

public class MortalityRateFinder {

	private static final String MORTALITY_RATE_CSV = "src/main/resources/probabilityOfdying.csv";
	private static final String BMI_CSV = "src/main/resources/BMI.csv";

	public static Double findMortalityRiskFactor(User user) {
		Double probabilityOfDyingRiskFactor = findProbabilityOfDyingRiskFactor(user);
		Double bmiRiskFactor = findBMIRiskFactor(user);
		return findMortalityRiskFactor(probabilityOfDyingRiskFactor, bmiRiskFactor, user);
	}

	private static Double findMortalityRiskFactor(Double probabilityOfDyingRiskFactor, Double bmiRiskFactor,
			User user) {
		BigDecimal mortalityFactor = null;
		if (user.isTobacco()) {
			mortalityFactor = new BigDecimal(probabilityOfDyingRiskFactor + bmiRiskFactor + 20,
					new MathContext(7, RoundingMode.FLOOR));
		} else {
			mortalityFactor = new BigDecimal(probabilityOfDyingRiskFactor + bmiRiskFactor,
					new MathContext(7, RoundingMode.FLOOR));
		}
		return mortalityFactor.doubleValue();
	}

	private static Double findProbabilityOfDyingRiskFactor(User user) {
		Double probabilityOfDyingRiskFactor = null;
		try {
			Reader in = new FileReader(MORTALITY_RATE_CSV);
			Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
			for (CSVRecord csvRecord : records) {
				if (isMatched(user, csvRecord)) {
					Number probabilityOfDyingRiskFactorNumber = DecimalFormat.getInstance()
							.parse(csvRecord.get("Risk Factor"));
					probabilityOfDyingRiskFactor = probabilityOfDyingRiskFactorNumber.doubleValue();
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return probabilityOfDyingRiskFactor;
	}

	private static Double findBMIRiskFactor(User user) {
		Double bmiRiskFactor = null;
		try {
			int bmi = BMICalculator.calculate(user);
			Reader in = new FileReader(BMI_CSV);
			Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
			for (CSVRecord csvRecord : records) {
				String bmiFromCSV = csvRecord.get("BMI");
				if (bmi == Integer.valueOf(bmiFromCSV)) {
					Number bmiRiskFactorRiskFactorNumber = DecimalFormat.getInstance()
							.parse(csvRecord.get("Risk Factor"));
					bmiRiskFactor = bmiRiskFactorRiskFactorNumber.doubleValue();
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null == bmiRiskFactor) {
			bmiRiskFactor = new Double(100);
		}
		return bmiRiskFactor;
	}

	private static boolean isMatched(User user, CSVRecord csvRecord) throws Exception {
		Double minAge = DecimalFormat.getInstance().parse(csvRecord.get("Min Age")).doubleValue();
		Double maxAge = DecimalFormat.getInstance().parse(csvRecord.get("Max Age")).doubleValue();
		String gender = csvRecord.get("Gender");
		int age = findAge(user.getDob());
		return (age >= minAge && age <= maxAge) && (user.getGender().equalsIgnoreCase(gender));
	}

	private static int findAge(Date dob) {
		LocalDate birthdate = new LocalDate(dob);
		LocalDate now = new LocalDate();
		Years ageYears = Years.yearsBetween(birthdate, now);
		return ageYears.getYears();
	}
}
