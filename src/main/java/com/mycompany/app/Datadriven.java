package com.mycompany.app;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Datadriven {

	public ArrayList<String> getdata(String testcasename) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<String> a = new ArrayList<String>();

		FileInputStream fis = new FileInputStream("/Users/chrishleysekar/Downloads/PracticeFiles/Data Driven.xlsx"); 
		// To access any files
		
		
		@SuppressWarnings("resource")
		XSSFWorkbook w = new XSSFWorkbook(fis); // XSSFWorkbook is used to access excel. So w object will be able to
												// access
												// excel file
		int scount = w.getNumberOfSheets(); // Getting the total number of sheets in the excel

		for (int i = 0; i < scount; i++) {
			if (w.getSheetName(i).equalsIgnoreCase("Gmail")) // Check all the sheets name
			{
				XSSFSheet sname = w.getSheetAt(i); // Store the Sheet index in sname

				// Now we area into the desired sheet in the excel

				Iterator<Row> row = sname.iterator(); // Used Iterator from collections of java to iterate the rows in
														// the sheet (Sheet is a collection of rows)
				Row frow = row.next(); // Only after next function, the control moves to the next row (downwards)

				// Now we are in the first row, have to find the cell with the name 'Testcase'

				Iterator<Cell> ce = frow.cellIterator(); // Same iterator conept used over cells (Rows are collection of
															// cells)

				int k = 0, column = 0;

				while (ce.hasNext()) // Checks if the next cell has some value by 'hasnext' function
				{
					Cell cellvalue = ce.next(); // Moves to the next cell (to the left)

					if (cellvalue.getStringCellValue().equalsIgnoreCase("Testcases")) { // Check the value of the cell
						column = k; // Finds in which column the Testcase is found
					}
				}
				k++;
			

				// Now the Testcase column is identified, have to access the specific test in
				// that column
				while (row.hasNext()) // row object which is already used to itterate between rows is used
				{
					Row r = row.next();
					if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testcasename)) {
						Iterator<Cell> cell = r.cellIterator();
						while (cell.hasNext()) {
							Cell c = cell.next();
							if (c.getCellType() == CellType.STRING) {

								a.add(c.getStringCellValue());
							} else {

								a.add(NumberToTextConverter.toText(c.getNumericCellValue()));

							}
						}
					}
				}
			}
		}
		return a;
}
}
