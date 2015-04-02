package executionEngine;

import java.lang.reflect.Method;

import config.ActionKeywords;
import config.Constants;
import utility.ExcelUtils;

public class DriverScript1 {
	public static ActionKeywords actionKeywords;
	public static String sActionKeyword;
	public static Method method[];
	public static boolean bResult;

	public DriverScript1() throws NoSuchMethodException, SecurityException {
		actionKeywords = new ActionKeywords();
		method = actionKeywords.getClass().getMethods();
	}

	public static void main(String[] args) throws Exception {

		// Instead of hard coded Excel path, a Constant Variable is used
		String sPath = Constants.Path_TestData;

		// Here we are passing the Excel path and SheetName to connect with
		// Excel file
		// Again a Constant Variable is used in place of Excel Sheet Name
		ExcelUtils.setExcelFile(sPath);

		// Hard coded values are used for Excel row & columns for now
		// In later chapters we will use these hard coded value much efficiently
		// This is the loop for reading the values of the column 3 (Action
		// Keyword) row by row
		for (int iRow = 1; iRow <= 9; iRow++) {
			// Constant Variable is used in place of Column number
			sActionKeyword = ExcelUtils.getCellData(iRow,
					Constants.Col_ActionKeyword, Constants.Sheet_TestSteps);
			execute_Actions();
		}
	}

	private static void execute_Actions() throws Exception {
		for (int i = 0; i < method.length; i++) {
			if (method[i].getName().equals(sActionKeyword)) {
				method[i].invoke(actionKeywords);
				break;
			}
		}
	}
}