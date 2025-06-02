package com.juaracoding.screens;

import java.time.Duration;
import java.time.chrono.ThaiBuddhistEra;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductListScreen {
    private int customSubDrag = 45;
    private AndroidDriver driver;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"]")
    private List<WebElement> cartButtons;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"PRODUCTS\"]")
    private WebElement title;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"][1]")
    private WebElement cartButton1;

    @AndroidFindBy(className = "android.widget.TextView")
    private WebElement cartCount;

    @AndroidFindBy(xpath = "//android.widget.ScrollView[@content-desc=\"test-PRODUCTS\"]")
    private WebElement scrollView;

    @AndroidFindBy(xpath = "(//android.view.ViewGroup[@content-desc=\"test-Drag Handle\"])")
    private List<WebElement> dragButtons;

    public ProductListScreen(AndroidDriver androidDriver) {
        driver = androidDriver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public String getTitle() {
        return title.getText();
    }

    public String getCartCount() {
        return cartCount.getText();
    }

    public void addToCartProduct1() {
        cartButton1.click();
    }

    public void scroll() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("elementId", scrollView);
        params.put("direction", "down");
        params.put("percent", 0.7);
        params.put("speed", 1000);

        js.executeScript("mobile: scrollGesture", params);
    }

    public void dragAndDrop(WebElement drag) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("elementId", drag);
        params.put("endX", scrollView.getLocation().getX());
        params.put("endY", scrollView.getLocation().getY() - customSubDrag);
        params.put("speed", 5000);

        js.executeScript("mobile: dragGesture", params);
    }

    public void addToCartProducts() throws InterruptedException {
        for (WebElement cartButton : cartButtons) {
            cartButton.click();
            Thread.sleep(1000);
        }
    }

    public void dragToCarts() throws InterruptedException {
        for (WebElement dragButton : dragButtons) {
            dragAndDrop(dragButton);
            Thread.sleep(1000);
        }
    }

    public void addToCartAndScroll(int limit) throws InterruptedException {
        for (int i = 1; i <= limit; i++) {
            addToCartProducts();
            Thread.sleep(1000);
            scroll();
            Thread.sleep(1000);
        }
    }

    public void dragToCartAndScroll(int limit) throws InterruptedException {
        for (int i = 1; i <= limit; i++) {
            dragToCarts();
            Thread.sleep(1000);
            scroll();
            Thread.sleep(1000);
        }
    }
}
