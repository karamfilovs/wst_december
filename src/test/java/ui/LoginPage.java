package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage extends BasePage {
    private static Logger logger = LoggerFactory.getLogger("Login.class");
    private static final String URL = "/login";
    private By emailFieldLocator = By.id("loginusername");
    private By passwordFieldLocator = By.id("loginpassword");
    private By loginBtnLocator = By.id("loginsubmit");
    private By companyLocator = By.xpath("//div[@id='wellcome']/h2");
    private By successLogoutMsgLocator = By.id("okmsg");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void loginWith(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

    public void enterEmail(String email) {
        logger.debug("Enter email:" + email);
        type(emailFieldLocator, email);
    }

    public void enterPassword(String password) {
        logger.debug("Enter password: " + password);
        type(passwordFieldLocator, password);
    }

    public void clickLoginButton() {
        logger.debug("Clicking login button");
        click(loginBtnLocator);
    }

    public String getCompanyName() {
        logger.debug("Retrieving company name");
        return getText(companyLocator);
    }

    public String successLogoutMsg() {
        logger.debug("Retrieving logout success message");
        return getText(successLogoutMsgLocator);
    }

    public void navigateTo(){
        logger.debug("Navigating to Login page");
        navigate(URL);
    }
}
