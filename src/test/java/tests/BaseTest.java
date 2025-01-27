package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;
import pages.ProductsPage;

import java.time.Duration;

@Listeners(TestListener.class)
public class BaseTest {
    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;
    InventoryPage inventoryPage;

    @Parameters({"browser"})
    @BeforeMethod
    public void setup(@Optional("chrome") String browser) {
        //добавлена кроссбраузерность
        if(browser.equalsIgnoreCase("chrome")){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("incognito");
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
        } else if(browser.equalsIgnoreCase("firefox")){
            driver = new FirefoxDriver();
        }
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        inventoryPage = new InventoryPage(driver);

    }

    @AfterMethod(alwaysRun = true)
    public void quit() {
        driver.quit();
    }
}
