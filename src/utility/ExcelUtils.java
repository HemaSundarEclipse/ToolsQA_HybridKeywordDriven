package utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;

import config.Constants;
import executionEngine.DriverScript;

public class ExcelUtils {
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static org.apache.poi.ss.usermodel.Cell Cell;
	private static XSSFRow Row;
/**
 * 
 * @param Path
 * @throws Exception
 * 
 * Setting the Excel file path & Workbook
 */
	public static void setExcelFile(String Path) throws Exception {
		try {
			FileInputStream ExcelFile = new FileInputStream(Path);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
		} catch (Exception e) {
			Log.error("Class Utils | Method setExcelFile | Exception desc : "
					+ e.getMessage());
			DriverScript.bResult = false;
		}
	}
	/**
	 * 
	 * @param RowNum
	 * @param ColNum
	 * @param SheetName
	 * @return
	 * @throws Exception
	 * 
	 * To get cell data from given RowNumber, ColumnNumber & SheetName 
	 */

	public static String getCellData(int RowNum, int ColNum, String SheetName)
			throws Exception {
		try {
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			Log.error("Class Utils | Method getCellData | Exception desc : "
					+ e.getMessage());
			DriverScript.bResult = false;
			return "";
		}
	}
/**
 * @param SheetName
 * @return
 * 
 * To get the total number of rows in given Sheet 
 */
	public static int getRowCount(String SheetName) {
		int iNumber = 0;
		try {
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			iNumber = ExcelWSheet.getLastRowNum() + 1;
		} catch (Exception e) {
			Log.error("Class Utils | Method getRowCount | Exception desc : "
					+ e.getMessage());
			DriverScript.bResult = false;
		}
		return iNumber;
	}
	/**
	 * @param sTestCaseName
	 * @param colNum
	 * @param SheetName
	 * @return
	 * @throws Exception
	 * 
	 * To get the first row of the Test step for a particular Test Case
	 */
	public static int getRowContains(String sTestCaseName, int colNum,
			String SheetName) throws Exception {
		int iRowNum = 0;
		try {
			// ExcelWSheet = ExcelWBook.getSheet(SheetName);
			int rowCount = ExcelUtils.getRowCount(SheetName);
			for (; iRowNum < rowCount; iRowNum++) {
				if (ExcelUtils.getCellData(iRowNum, colNum, SheetName)
						.equalsIgnoreCase(sTestCaseName)) {
					break;
				}
			}
		} catch (Exception e) {
			Log.error("Class Utils | Method getRowContains | Exception desc : "
					+ e.getMessage());
			DriverScript.bResult = false;
		}
		return iRowNum;
	}
	/**
	 * @param SheetName
	 * @param sTestCaseID
	 * @param iTestCaseStart
	 * @return
	 * @throws Exception
	 * 
	 * To get the last row of the Test step for a particular Test Case
	 */
	public static int getTestStepsCount(String SheetName, String sTestCaseID,
			int iTestCaseStart) throws Exception {
		try {
			for (int i = iTestCaseStart; i <= ExcelUtils.getRowCount(SheetName); i++) {
				if (!sTestCaseID.equals(ExcelUtils.getCellData(i,
						Constants.Col_TestCaseID, SheetName))) {
					int number = i;
					return number;
				}
			}
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			int number = ExcelWSheet.getLastRowNum() + 1;
			return number;
		} catch (Exception e) {
			Log.error("Class Utils | Method getRowContains | Exception desc : "
					+ e.getMessage());
			DriverScript.bResult = false;
			return 0;
		}
	}
	/**
	 * @param Result
	 * @param RowNum
	 * @param ColNum
	 * @param SheetName
	 * @throws Exception
	 * 
	 * To write data into Excel for given row, cell & sheet
	 * Writing result of the test step to the result column for the particular test step
	 */
	@SuppressWarnings("static-access")
	// This method is use to write value in the excel sheet
	// This method accepts four arguments (Result, Row Number, Column Number &
	// Sheet Name)
	public static void setCellData(String Result, int RowNum, int ColNum,
			String SheetName) throws Exception {
		try {

			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			Row = ExcelWSheet.getRow(RowNum);
			Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
			if (Cell == null) {
				Cell = Row.createCell(ColNum);
				Cell.setCellValue(Result);
			} else {
				Cell.setCellValue(Result);
			}
			// Constant variables Test Data path and Test Data file name
			FileOutputStream fileOut = new FileOutputStream(
					Constants.Path_TestData);
			ExcelWBook.write(fileOut);
			// fileOut.flush();
			fileOut.close();
			ExcelWBook = new XSSFWorkbook(new FileInputStream(
					Constants.Path_TestData));
		} catch (Exception e) {
			DriverScript.bResult = false;
		}
	}
}