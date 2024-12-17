package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class ProductsPage {
    WebDriver driver;

    By title = By.cssSelector("[data-test=title]");
    //id кнопки добавить конкретного товара со страницы
    By productAdd = By.cssSelector("[data-test=add-to-cart-sauce-labs-backpack]");
    // id кнопки удалить конкретного товара
    By getProductRemove = By.cssSelector("[data-test=remove-sauce-labs-backpack]");


    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle() {
        return driver.findElement(title).getText();
    }

    public void clickButton() {
        driver.findElement(productAdd).click();
    }

    public String checkButtonRemove() {
        return driver.findElement(getProductRemove).getText();
    }


}
