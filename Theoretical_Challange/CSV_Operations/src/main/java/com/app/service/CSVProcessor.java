package com.app.service;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class CSVProcessor {

	public void processCsv(File inputFile, File outputFile) throws Exception {
		if (!inputFile.exists()) {
			throw new FileNotFoundException("Input file not found: " + inputFile.getPath());
		}

		try (FileReader fileReader = new FileReader(inputFile);
				CSVReader csvReader = new CSVReader(fileReader);
				FileWriter fileWriter = new FileWriter(outputFile);
				CSVWriter csvWriter = new CSVWriter(fileWriter);
				Workbook workbook = new HSSFWorkbook();) {
			String[] nextLine;
			Sheet sheet = workbook.createSheet("Sheet1");
			FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

			int rowNum = 0;
			while ((nextLine = csvReader.readNext()) != null) {

				if (nextLine.length == 0) {
					throw new IllegalArgumentException("Empty row found at line " + rowNum);
				}
				Row row = sheet.createRow(rowNum);
				String[] outputRow = new String[nextLine.length];

				for (int colNum = 0; colNum < nextLine.length; colNum++) {
					Cell cell = row.createCell(colNum);
					String cellValue = nextLine[colNum];

					if (cellValue == null || cellValue.trim().isEmpty()) {
						throw new IllegalArgumentException(
								"Null or empty cell value at row " + rowNum + ", column " + colNum);
					}

					try {
						if (isFormula(cellValue)) {
							cell.setCellFormula(cellValue.substring(1)); // Remove '='
							CellValue evaluatedValue = evaluator.evaluate(cell);

							if (evaluatedValue != null && evaluatedValue.getCellType() == CellType.NUMERIC) {
								outputRow[colNum] = String.valueOf(evaluatedValue.getNumberValue());
							} else {
								throw new RuntimeException(
										"Unable to evaluate formula in cell at row " + rowNum + ", column " + colNum);
							}

						} else {
							// Handle regular numeric values
							double number = Double.parseDouble(cellValue);
							cell.setCellValue(number);
							outputRow[colNum] = cellValue;
						}
					} catch (NumberFormatException e) {
						throw new NumberFormatException(
								"Invalid number format at row " + rowNum + ", column " + colNum + ": " + cellValue);
					} catch (IllegalArgumentException e) {
						throw new IllegalArgumentException(
								"Invalid formula at row " + rowNum + ", column " + colNum + ": " + cellValue);
					}
				}

				csvWriter.writeNext(outputRow);
				rowNum++;
			}
			workbook.write(new FileOutputStream(outputFile)); // Save the workbook contents to output file
		} catch (IOException e) {
			throw new IOException("Error reading/writing file: " + e.getMessage());
		} catch (Exception e) {
			throw new Exception("An unexpected error occurred while processing the CSV: " + e.getMessage());
		}
	}

	// Check if it's a formula (starts with '=')
	private boolean isFormula(String value) {
		return value != null && value.startsWith("=");
	}
}
