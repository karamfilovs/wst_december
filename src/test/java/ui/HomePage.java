package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePage extends BasePage {
    private static Logger logger = LoggerFactory.getLogger("HomePage.class");
    private static final String URL = "/";
    private By userPanelHeaderLocator = By.cssSelector("div.userpanel-header");
    private By logoutLinkLocator = By.cssSelector("a.selenium-button-logout");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Retrieving text from user panel section
     * @return text
     */
    public String getLoggerUser(){
        logger.debug("Retrieving text from user panel");
        return getText(userPanelHeaderLocator);
    }

    /**
     * Logout from the system
     */
    public void logout(){
        logger.debug("Logging out from the system!");
        click(userPanelHeaderLocator);
        click(logoutLinkLocator);
    }
}
