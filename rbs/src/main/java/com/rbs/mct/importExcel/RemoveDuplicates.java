package com.rbs.mct.importExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class RemoveDuplicates {
	static Connection connection = null;
	static String query = "INSERT INTO rbs_cpts(cpt_id,cpt_cde, cpt_desc,cpt_category,cpt_fee) VALUES (?,?,?,?,?)";
	static java.sql.PreparedStatement stmt = null;

	public static void writeDB(Map<String, RecordSet> map)
			throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/rbsprod", "root", "raj24");
			stmt = connection.prepareStatement(query);
			Set<String> keyset = map.keySet();
			int rownum = 0;
			for (String key : keyset) {
				RecordSet objArr = map.get(key);
				stmt.setInt(1, rownum);
				stmt.setString(2, objArr.getCpt());
				stmt.setString(3, objArr.getDescrption());
				stmt.setString(4, objArr.getCategory());
				String fee = "0";
				if (null != objArr.getFee()) {
					fee = objArr.getFee().replace("$", "");
				}

				stmt.setFloat(5, Float.parseFloat(fee));
				stmt.executeUpdate();
				rownum++;
			}

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		}
	}

	public static void writeData(Map<String, RecordSet> map) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Sample sheet");

		Set<String> keyset = map.keySet();
		int rownum = 0;
		for (String key : keyset) {
			Row row = sheet.createRow(rownum++);
			RecordSet objArr = map.get(key);
			row.createCell(0).setCellValue(objArr.getCategory());
			row.createCell(1).setCellValue(objArr.getDescrption());
			row.createCell(2).setCellValue(objArr.getCpt());
			row.createCell(3).setCellValue(objArr.getFee());
		}

		try {
			FileOutputStream out = new FileOutputStream(new File(
					"C:\\Ashish\\new.xls"));
			workbook.write(out);
			out.close();
			System.out.println("Excel written successfully..");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws ClassNotFoundException {

		try {

			FileInputStream file = new FileInputStream(new File(
					"/home/rajendra/RP/Handson/MAster File.xls"));

			// Get the workbook instance for XLS file
			HSSFWorkbook workbook = new HSSFWorkbook(file);

			// Get first sheet from the workbook
			HSSFSheet sheet = workbook.getSheetAt(0);

			// Iterate through each rows from first sheet
			Iterator<Row> rowIterator = sheet.iterator();
			Map<String, RecordSet> recordMap = new HashMap<String, RecordSet>();
			Map<String, RecordSet> duplicateMap = new HashMap<String, RecordSet>();
			List<String> dupCount = new ArrayList<String>();
			if (rowIterator.hasNext())
				rowIterator.next();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				RecordSet record = new RecordSet();
				// For each row, iterate through each columns
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {

					Cell cell = cellIterator.next();
					int ind = cell.getColumnIndex();
					if (ind == 0) {
						record.setCategory(cell.getStringCellValue());
					} else if (ind == 1) {
						record.setDescrption(cell.getStringCellValue());
					} else if (ind == 2) {
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							record.setCpt(cell.getNumericCellValue() + "");
							break;
						case Cell.CELL_TYPE_STRING:
							record.setCpt(cell.getStringCellValue());
							break;

						}
					} else {
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							record.setFee(cell.getNumericCellValue() + "");
							break;
						case Cell.CELL_TYPE_STRING:
							record.setFee(cell.getStringCellValue() + "");
							break;
						}

					}

				}
				if (recordMap.containsKey(record.getCpt() + " "
						+ record.getDescrption().toLowerCase())) {
					duplicateMap.put(record.getCpt() + " "
							+ record.getDescrption().toLowerCase(), record);
					System.out.println(record.getCpt());
					dupCount.add("");
				} else {
					recordMap.put(record.getCpt() + " "
							+ record.getDescrption().toLowerCase(), record);
				}
			}
			System.out.println(recordMap.size());
			System.out.println(dupCount.size() + "\n" + duplicateMap.size());
			file.close();
			FileOutputStream out = new FileOutputStream(new File(
					"C:\\Ashish\\test.xls"));
			workbook.write(out);
			out.close();

			// writeData(recordMap);
			writeDB(recordMap);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
