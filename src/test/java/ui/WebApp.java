package ui;

import org.openqa.selenium.WebDriver;

public class WebApp {
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private ItemPage itemPage;

    public WebApp(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage loginPage(){
        if (loginPage == null){
            loginPage = new LoginPage(driver);
        }
        return loginPage;
    }

    public HomePage homePage(){
        if (homePage == null){
            homePage = new HomePage(driver);
        }
        return homePage;
    }


    public ItemPage itemPage(){
        if (itemPage == null){
            itemPage = new ItemPage(driver);
        }
        return itemPage;
    }





}
