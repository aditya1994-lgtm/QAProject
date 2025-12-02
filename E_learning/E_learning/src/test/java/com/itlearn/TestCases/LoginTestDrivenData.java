package com.itlearn.TestCases;


import java.io.IOException;

import java.util.*;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;

import org.testng.annotations.Test;

import com.itlearn.pages.BaseTest;
import com.itlearn.pages.LoginPage;
import com.itlearn.utility.ReadExcelFile;

public class LoginTestDrivenData extends BaseTest {
	String fileName = System.getProperty("user.dir") + "/Testdata/TestInfo.xlsx";

	
	@Test(priority=1,dataProvider="LoginDataProvider")
	
		void verifyLogin(String username,String password) throws IOException, InterruptedException {
//		System.out.println(username +"1"+password+ "2");
			
			LoginPage lp=new LoginPage(driver);
//			    String username="Demo12";
//				String password="Test123456$";
				lp.loginPortal("Demo12","Test123456$");
				Thread.sleep(5000);
				lp.logout();
				
				if(username.equals("Demo12")&& password.equals("Test123456$")) {
					System.out.println("Test pass");
					captureScreenShot(driver,"verifyLogin");
					Assert.assertTrue(true);
					Thread.sleep(3000);
					lp.logout();
				}
				else {
					captureScreenShot(driver,"verifyLogin");
					Assert.assertTrue(false);
					
				}
		}
	
	 
	@DataProvider(name="LoginDataProvider")
	public String[][] LoginDataProvider() {
	    System.out.println("Using file: " + fileName);
	    int ttlRows = ReadExcelFile.getRowCount(fileName, "LoginData");
	    int ttlColumns = ReadExcelFile.getColCount(fileName, "LoginData");
	    System.out.println("Rows: " + ttlRows + ", Cols: " + ttlColumns);

	    if (ttlRows <= 1 || ttlColumns <= 0) {
	        System.out.println("No data rows found - returning empty dataset");
	        return new String[0][0];
	    }

	    List<String[]> rows = new ArrayList();
	    for (int i = 1; i < ttlRows; i++) { // skip header
	        String user = ReadExcelFile.getCellValue(fileName, "LoginData", i, 0);
	        String pass = ReadExcelFile.getCellValue(fileName, "LoginData", i, 1);

	        System.out.println("Row " + i + " -> user:'" + user + "' pass:'" + pass + "'");

	        // add row even if empty so you can see it; you can change to skip blanks below
	        rows.add(new String[] { user == null ? "" : user, pass == null ? "" : pass });
	    }

	    // convert list -> array
	    String[][] data = new String[rows.size()][ttlColumns];
	    for (int i = 0; i < rows.size(); i++) data[i] = rows.get(i);
	    return data;
	}

}
