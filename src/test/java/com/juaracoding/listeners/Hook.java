package com.juaracoding.listeners;

import java.net.MalformedURLException;
import java.time.Duration;
import org.testng.IExecutionListener;
import com.juaracoding.DriverSingleton;
import com.juaracoding.utill.ConfigReader;
import com.juaracoding.utill.EmailSender;

import io.appium.java_client.android.AndroidDriver;

public class Hook implements IExecutionListener {
    public static AndroidDriver driver;

    @Override
    public void onExecutionStart() {
        ConfigReader.loadProperties("config.properties");
        System.err.println("TestNG is commencing execution");
        try {
            driver = DriverSingleton.createOrGetDriver();

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.hideKeyboard();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onExecutionFinish() {
        System.err.println("TestNG is finished execution");
        DriverSingleton.quitDriver();

        if (ConfigReader.get("automation.email.activate").equals("1")) {
            System.err.println("Start sending email.");
            EmailSender.send();
        }

    }
}
