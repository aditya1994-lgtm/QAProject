package com.itlearn.TestCases;

import java.io.File;

import org.testng.annotations.Test;

import com.itlearn.pages.BaseTest;
import com.itlearn.pages.DashboardPage;
import com.itlearn.pages.LoginPage;
import com.itlearn.pages.paymentPage;
import com.itlearn.utility.ReadExcelFile;

public class paymentTestCase extends BaseTest{ 
    @Test(priority = 1)
    public void testCourses() throws InterruptedException {

        // Use the project-relative path correctly
        String fileName = System.getProperty("user.dir") + File.separator + "Testdata" + File.separator + "TestInfo.xlsx";

        // IMPORTANT: do NOT declare a local WebDriver here. Use 'driver' inherited from BaseTest.
        LoginPage lp = new LoginPage(driver);

        // Read username/password from the correct sheet & cells
        String username = ReadExcelFile.getCellValue(fileName, "LoginData", 1, 0); // row 1, col 0
        String password = ReadExcelFile.getCellValue(fileName, "LoginData", 1, 1); // row 1, col 1

        System.out.println("Using credentials -> user: '" + username + "' pass: '" + password + "'");

        lp.loginPortal(username, password);

        DashboardPage dp = new DashboardPage(driver);
        Thread.sleep(5000);
        dp.dashboardClick();
        
        paymentPage pay = new paymentPage(driver);
        String cardnum = ReadExcelFile.getCellValue(fileName, "carddetails", 0, 0); // row 1, col 0
        String expdate = ReadExcelFile.getCellValue(fileName, "carddetails", 0, 1); // row 1, col 1
        String cvvnum = ReadExcelFile.getCellValue(fileName, "carddetails", 0, 2); // row 1, col 0
        
        
        pay.paymentOption(cardnum,expdate,cvvnum);        
        
        
        
    }
}
