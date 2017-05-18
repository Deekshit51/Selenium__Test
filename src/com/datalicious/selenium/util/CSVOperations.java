package com.datalicious.selenium.util;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.supercsv.cellprocessor.constraint.StrMinMax;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import com.datalicious.selenium.beans.CheckDetails;

public class CSVOperations {

	public static final CellProcessor[] readDataValidationOutDetails_CellProcessor = new CellProcessor[] {
			new StrMinMax(0, 100), new StrMinMax(0, 100) };

	public static List<CheckDetails> readDataValidationOutDetails() {
		String fileName = "LogDetails.csv";
		ICsvBeanReader inFile = null;
		List<CheckDetails> CheckDetailsList = new ArrayList<CheckDetails>();
		CheckDetails CheckDetails;

		try {

			// Creating BeanReader object by passing csv file name and

			inFile = new CsvBeanReader(new FileReader(fileName),
					CsvPreference.EXCEL_PREFERENCE);

			// reading header
			final String[] header = inFile.getCSVHeader(true);

			// reading the beans and adding to list
			while ((CheckDetails = inFile.read(CheckDetails.class, header,
					readDataValidationOutDetails_CellProcessor)) != null) {
				CheckDetailsList.add(CheckDetails);
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				inFile.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return CheckDetailsList;

	}

	public static void writeDataValidationResults(
			List<CheckDetails> CheckDetailsList) {
		String fileName = "LogDetails.csv";
		ICsvBeanReader inFile = null;
		CsvBeanWriter outFile = null;
		try {

			// Creating BeanReader object by passing csv file name and
			inFile = new CsvBeanReader(new FileReader(fileName),

			CsvPreference.EXCEL_PREFERENCE);

			final String[] header = inFile.getCSVHeader(true);

			outFile = new CsvBeanWriter(new FileWriter(fileName),

			CsvPreference.EXCEL_PREFERENCE);

			outFile.writeHeader(header);

			for (CheckDetails CheckDetails : CheckDetailsList) {
				outFile.write(CheckDetails, header,
						readDataValidationOutDetails_CellProcessor);
			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			try {

				inFile.close();
				outFile.close();

			} catch (Exception e) {

				e.printStackTrace();

			}

		}

	}

	public static void writeTestResults(CheckDetails checkDetails) {
		String fileName = "LogDetails.csv";
		ICsvBeanReader inFile = null;
		CsvBeanWriter outFile = null;

		List<CheckDetails> checkDetailsList = CSVOperations
				.readDataValidationOutDetails();
		if (checkDetailsList != null && checkDetailsList.size() > 0) {
			checkDetailsList.add(checkDetails);
		} else {
			checkDetailsList = new ArrayList<CheckDetails>();
			checkDetailsList.add(checkDetails);
		}

		try {

			// Creating BeanReader object by passing csv file name and
			inFile = new CsvBeanReader(new FileReader(fileName),

			CsvPreference.EXCEL_PREFERENCE);

			final String[] header = inFile.getCSVHeader(true);

			outFile = new CsvBeanWriter(new FileWriter(fileName),

			CsvPreference.EXCEL_PREFERENCE);

			outFile.writeHeader(header);

			for (CheckDetails checkDetails1 : checkDetailsList) {
				outFile.write(checkDetails1, header,
						readDataValidationOutDetails_CellProcessor);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				inFile.close();
				outFile.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
