import api.API;
import api.LoginAPI;
import dto.Item;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ui.WebApp;

import java.time.Duration;

public class HybridTest {
    private WebDriver driver;
    private static String token;
    private WebApp webApp;
    private API api;

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup(); //Download web driver and setup property
        token = LoginAPI.getToken();
    }


    @BeforeEach
    public void beforeEach() {
        driver = new ChromeDriver(); //Create new chrome browser instance
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        webApp = new WebApp(driver);
        api = new API(token);

    }

    @Test
    @DisplayName("Test with UI and API steps")
    @Description("This is just a dummy test example")
    public void hybridTestExample(){
        api.itemAPI().deleteAllItems();
        webApp.loginPage().navigateTo();
        webApp.loginPage().loginWith("karamfilovs@gmail.com", "111111");
        webApp.itemPage().navigateTo();
        String expectedMsg = "Не са намерени артикули, отговарящи на зададените критерии.";
        Assertions.assertEquals(expectedMsg, webApp.itemPage().emptyListMsg());
    }
}
