package config;

public class Constants {

	// List of System Variables
	public static final String URL = "http://www.store.demoqa.com";
	public static final String Path_TestData = "D:\\workspace\\ToolsQA_KeywordDriven\\src\\dataEngine\\DataEngine.xlsx";
	public static final String Path_OR = "D:\\workspace\\ToolsQA_KeywordDriven\\src\\config\\OR.txt";
	public static final String File_TestData = "DataEngine.xlsx";

	// List of Data Sheet Column Numbers
	public static final int Col_TestCaseID = 0;
	public static final int Col_TestScenarioID = 1;
	public static final int Col_PageObject = 3;
	public static final int Col_ActionKeyword = 4;
	public static final int Col_Result = 3;
	public static final int Col_TestStepResult = 6;
	public static final int Col_DataSet = 5;

	// New entry in Constant variable
	public static final int Col_RunMode = 2;
	public static final String KEYWORD_FAIL = "FAIL";
	public static final String KEYWORD_PASS = "PASS";

	// List of Data Engine Excel sheets
	public static final String Sheet_TestSteps = "Test Steps";
	// New entry in Constant variable
	public static final String Sheet_TestCases = "Test Cases";

	// List of Test Data
	public static final String UserName = "testuser_3";
	public static final String Password = "Test@123";

}