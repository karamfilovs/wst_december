import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@Feature("Login feature")
@Link("https://pragmaticbg.atlassian.net/browse/IN-77")
public class DemoTest {



    @Test
    @Disabled
    @Issue("Issue with search")
    @DisplayName("Test with bug")
    @Severity(SeverityLevel.TRIVIAL)
    public void failingTest() {
        Assertions.assertEquals(1, 3);
    }

    @Test
    @DisplayName("Can create item")
    @Severity(SeverityLevel.BLOCKER)
    public void canCreateItem() {
        //Login.login("username", "password");
        //ItemPage.navigateTo();
        //ItemPage.createItem("beer", 20.20);
        //TestRail
        //Navigate to Login page https://myloginpage.com
        //Enter username and password username/password and click Login button
        //...
    }

    @Test
    @DisplayName("Can login with valid credentials")
    @Description("To make sure users can login with valid accounts")
    @Severity(SeverityLevel.CRITICAL)
    public void canLoginWithValidCredentials() {
        navigateToLoginPage();
        loginWith("admin", "admin");
        checkUserLoggedIn();
        takeScreenshot();
    }

    @Step("Navigate to Login page")
    private void navigateToLoginPage() {
    }

    @Step("Enter username and password and click Login button")
    private void loginWith(String username, String password) {
    }

    @Step("Check that the login was successful")
    private void checkUserLoggedIn() {
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public String takeScreenshot() {
        return "Something";
    }

    @Test
    @DisplayName("Can update item")
    public void canUpdateItem() {

    }

    @Test
    @DisplayName("Can download user guide")
    public void canDownloadUserGuide() {
        //PDF
    }
}
