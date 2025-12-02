package com.itlearn.utility;

import java.io.File;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigDataProvider {

    private Properties pro;

    // Correct constructor (NO 'void')
    public ConfigDataProvider() {
        pro = new Properties();

        try {
            // Adjust folder name EXACTLY as in your project
            File src = new File("./Configration/config.properties");

            FileInputStream fis = new FileInputStream(src);

            pro.load(fis);
            System.out.println("Loaded config file successfully.");

        } catch (Exception e) {
            System.out.println(" Not able to load config file: " + e.getMessage());
        }
    }

    public String getBrowser() {
        return pro.getProperty("browser");
    }

    public String getStagingUrl() {
        return pro.getProperty("testurl");
    }
}
