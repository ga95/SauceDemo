package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {
    WebDriver driver;
    By searchLink = By.cssSelector("[data-test=inventory-item-name]");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToInventoryLink(){
        driver.findElement(searchLink).click();
    }

    public String getNameProduct(){
        return driver.findElement(searchLink).getText();
    }
}
