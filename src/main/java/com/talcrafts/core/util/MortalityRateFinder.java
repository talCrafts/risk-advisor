package com.talcrafts.core.util;

import java.io.FileReader;
import java.io.Reader;
import java.text.DecimalFormat;
import java.util.Date;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.joda.time.LocalDate;
import org.joda.time.Years;

import com.talcrafts.core.domain.User;

public class MortalityRateFinder {

	private static final String MORTALITY_RATE_CSV = "src/main/resources/buildchart.csv";

	public Double findMortalityRate(User user) {
		Double mortalityRate = null;
		try {
			Reader in = new FileReader(MORTALITY_RATE_CSV);
			Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
			for (CSVRecord csvRecord : records) {
				if (isMatched(user, csvRecord)) {
					Number parse = DecimalFormat.getInstance().parse(csvRecord.get("Total Risk"));
					mortalityRate = parse.doubleValue();
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mortalityRate;
	}

	private boolean isMatched(User user, CSVRecord csvRecord) throws Exception {
		Double minAge = DecimalFormat.getInstance().parse(csvRecord.get("Min Age")).doubleValue();
		Double maxAge = DecimalFormat.getInstance().parse(csvRecord.get("Max Age")).doubleValue();
		String gender = csvRecord.get("Gender");
		String smoker = csvRecord.get("Smoker");
		String smokerStatusFromUser = user.isTobacco() ? "Y" : "N";
		int age = findAge(user.getDob());
		return (age >= minAge && age <= maxAge) && (user.getGender().equalsIgnoreCase(gender))
				&& (smokerStatusFromUser.equalsIgnoreCase(smoker));
	}

	private int findAge(Date dob) {
		LocalDate birthdate = new LocalDate(dob);
		LocalDate now = new LocalDate();
		Years ageYears = Years.yearsBetween(birthdate, now);
		return ageYears.getYears();
	}
}
