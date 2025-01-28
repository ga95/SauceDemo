package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {
    WebDriver driver;
    By searchLink = By.cssSelector("[data-test=inventory-item-name]");
    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Клик по ссылке и инвентарь")
    public void clickToInventoryLink(){
        driver.findElement(searchLink).click();
    }
    @Step("Получение имени продукта")
    public String getNameProduct(){
        return driver.findElement(searchLink).getText();
    }
}
