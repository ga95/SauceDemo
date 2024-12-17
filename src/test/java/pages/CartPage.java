package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

public class CartPage {
    WebDriver driver;
    By title = By.cssSelector("[data-test=title]");
    By cartLink = By.cssSelector("[data-test=shopping-cart-link]");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle() {
        return driver.findElement(title).getText();
    }

    public void clickToCartLink() {
        driver.findElement(cartLink).click();
    }
}
