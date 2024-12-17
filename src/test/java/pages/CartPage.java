package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    WebDriver driver;

    By cartLink = By.cssSelector("[data-test=shopping-cart-link]");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://www.saucedemo.com/cart.html");
    }

    public void clickToCartLink() {
        driver.findElement(cartLink).click();
    }

}
