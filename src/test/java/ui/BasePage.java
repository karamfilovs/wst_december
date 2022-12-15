package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {
    private static String BASE_URL = System.getProperty("baseUrl", "https://st2016.inv.bg");
    private WebDriver driver;


    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected void type(By by, String text) {
        WebElement element = driver.findElement(by);
        element.clear();
        element.sendKeys(text);
    }

    protected void click(By by) {
        WebElement element = driver.findElement(by);
        element.click();
    }

    protected String getText(By by) {
        WebElement element = driver.findElement(by);
        return element.getText();
    }

    protected void navigate(String urlSuffix) {
        driver.get(BASE_URL + urlSuffix);
    }

}
