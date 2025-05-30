package com.juaracoding.authentications;

import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.juaracoding.DriverSingleton;
import com.juaracoding.screens.LoginScreen;
import com.juaracoding.screens.ProductListScreen;

import io.appium.java_client.android.AndroidDriver;

public class SignInTest {
    AndroidDriver driver;
    LoginScreen loginScreen;
    ProductListScreen productListScreen;

    @BeforeClass
    public void setup() throws MalformedURLException {
        driver = DriverSingleton.createOrGetDriver();
        loginScreen = new LoginScreen(driver);
        productListScreen = new ProductListScreen(driver);
    }

    @Test
    public void signInShouldBeSuccess() throws InterruptedException {
        loginScreen.login("standard_user", "secret_sauce");
        String actual = productListScreen.getTitle();
        String expected = "PRODUCTS";
        Assert.assertEquals(actual, expected);
    }
}
