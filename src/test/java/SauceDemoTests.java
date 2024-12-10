import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class SauceDemoTests {

    WebDriver driver;

    @BeforeMethod
    public void setup(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("incognito");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
    }
    @Test
    public void PositiveTest() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String pageUrl = driver.getCurrentUrl();
        Assert.assertEquals(pageUrl, "https://www.saucedemo.com/inventory.html");
    }
    @Test
    public void NegativeTest1() {
        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String errorMessage = driver.findElement(By.xpath("//div[@class='error-message-container error']/h3")).getText();
        Assert.assertEquals(errorMessage, "Epic sadface: Sorry, this user has been locked out.");
    }
    @Test
    public void NegativeTest2() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("login-button")).click();
        String errorMessage = driver.findElement(By.xpath("//div[@class='error-message-container error']/h3")).getText();
        Assert.assertEquals(errorMessage, "Epic sadface: Password is required");
    }
    @Test
    public void NegativeTest3() {
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String errorMessage = driver.findElement(By.xpath("//div[@class='error-message-container error']/h3")).getText();
        Assert.assertEquals(errorMessage, "Epic sadface: Username is required");
    }
    @AfterMethod(alwaysRun = true)
    public void quit(){
        driver.quit();
    }
}