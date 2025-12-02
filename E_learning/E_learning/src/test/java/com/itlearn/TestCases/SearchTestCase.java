package com.itlearn.TestCases;

import org.testng.annotations.Test;

import com.itlearn.pages.BaseTest;
import com.itlearn.pages.LoginPage;
import com.itlearn.pages.searchPage;
import com.itlearn.utility.ReadExcelFile;


public class SearchTestCase extends BaseTest {
	String fileName = System.getProperty("user.dir") + "/Testdata/TestInfo.xlsx";
	 @Test(priority=1)
	void searchCourseTest() throws InterruptedException {
		LoginPage lp=new LoginPage(driver);
		String username=ReadExcelFile.getCellValue(fileName,"Demo12",1,0);
		String password=ReadExcelFile.getCellValue(fileName,"Test123456$",1,1);
		lp.loginPortal(username,password);
		
		searchPage sp=new searchPage(driver);
		String searchCourse=ReadExcelFile.getCellValue(fileName,"HTML For Beginner",1,0);
		sp.searchCourse(searchCourse);
		
	}

}
