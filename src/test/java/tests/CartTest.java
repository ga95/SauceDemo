package tests;

import io.qameta.allure.*;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import static utils.AllureUtils.takeScreenshot;

public class CartTest extends BaseTest{
    @Test(priority = 1,testName = "Проверка добавления товара в корзину")
    @Description("Проверка добавления товара в корзину")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Saucedemo-1.0")
    @Feature("add to cart in saucedemo")
    @Story("Добавление в корзину")
    @TmsLink("http://www.jira.simple/CM-84")
    @Issue("http://www.jira.simple/CM-145")
    @Flaky
    public void checkAddProductToCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertEquals(productsPage.getTitle(),
                "Products",
                "Логин не выполнен");
        productsPage.clickButton();
        Assert.assertEquals(productsPage.checkButtonRemove(),
                "Remove",
                "Кнопка Удалить не появилась");
        cartPage.clickToCartLink();
        //проверяем, что мы на странице с title = You cart
        Assert.assertEquals(cartPage.getTitle(),
                "Your Cart",
                "Переход в корзину не удался");
        //проверить, что кнопка удалить именно от добавленного товара присутствует на странице корзины
        Assert.assertEquals(productsPage.checkButtonRemove(),
                "Remove",
                "Кнопка Удалить не появилась");
        inventoryPage.clickToInventoryLink();
        // проверяем, что открыта страница товара
        Assert.assertEquals(inventoryPage.getNameProduct(),
                "Sauce Labs Backpack",
                "Открыт не тот товар");
        takeScreenshot(driver);
    }
}
