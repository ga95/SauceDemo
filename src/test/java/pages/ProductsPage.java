package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class ProductsPage {
    WebDriver driver;

    By title = By.cssSelector("[data-test=title]");
    By readMore = By.xpath("/html/body/div/main/section[2]/div/div/div[1]/div/div[2]/div/a");
    //id кнопки добавить конкретного товара со страницы
    By productAdd = By.cssSelector("[data-test=add-to-cart-sauce-labs-backpack]");
    // id кнопки удалить конкретного товара
    By getProductRemove = By.cssSelector("[data-test=remove-sauce-labs-backpack]");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Получение заголовка")
    public String getTitle() {
        return driver.findElement(title).getText();
    }
    @Step("Клик по кнопке добавить в корзину")
    public void clickButton() {
        driver.findElement(productAdd).click();
    }
    @Step("Клик по кнопке удалить продукт")
    public String checkButtonRemove() {
        return driver.findElement(getProductRemove).getText();
    }


}
