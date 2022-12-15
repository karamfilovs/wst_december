package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ItemPage extends BasePage {
    private static Logger logger = LoggerFactory.getLogger("ItemPage.class");
    private static final String URL = "/objects";
    private By emptyListMsgLocator = By.id("emptylist");

    public ItemPage(WebDriver driver) {
        super(driver);
    }

    public void navigateTo() {
        logger.debug("Navigating to Item page");
        navigate(URL);
    }

    public String emptyListMsg(){
        logger.debug("Retrieving empty list message");
        return getText(emptyListMsgLocator);
    }
}
