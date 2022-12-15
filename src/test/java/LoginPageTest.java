import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.HomePage;
import ui.LoginPage;

import java.time.Duration;

@Feature("Login")
@Epic("UI")
public class LoginPageTest {
    private WebDriver driver;

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup(); //Download web driver and setup property
    }


    @BeforeEach
    public void beforeEach() {
        driver = new ChromeDriver(); //Create new chrome browser instance
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(""))));
    }

    @AfterEach
    public void afterEach() {
        if (driver != null) {
            driver.quit(); //Kills chrome browser instance
        }
    }


    @Test
    @DisplayName("Can login with valid credentials")
    @Description("To check that users can login")
    public void canLoginWithValidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        Assertions.assertEquals("QA Ground", loginPage.getCompanyName());
        loginPage.loginWith("karamfilovs@gmail.com", "111111");
        HomePage homePage = new HomePage(driver);
        String actualUser = homePage.getLoggerUser();
        Assertions.assertEquals("karamfilovs@gmail.com", actualUser);
    }

    @Test
    @DisplayName("Can logout from the system")
    @Description("To check that users can logout")
    public void canLogout() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        Assertions.assertEquals("QA Ground", loginPage.getCompanyName());
        loginPage.loginWith("karamfilovs@gmail.com", "111111");
        HomePage homePage = new HomePage(driver);
        String actualUser = homePage.getLoggerUser();
        Assertions.assertEquals("karamfilovs@gmail.com", actualUser);
        //Logout
        homePage.logout();
        //Check that user is redirected to login page / success message
        String successLogoutMsg = loginPage.successLogoutMsg();
        Assertions.assertEquals("Вие излязохте от акаунта си. ", successLogoutMsg);
        Assertions.assertEquals("QA Ground", loginPage.getCompanyName());
    }
}
