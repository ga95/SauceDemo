package tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(LoginTest.class);

    @Test
    public void checkCorrectLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertEquals(productsPage.getTitle(),
                "Products",
                "Логин не выполнен");
    }

    @Test
    public void checkUserLocked() {
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");
        Assert.assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Sorry, this user has been locked out.",
                "Сообщение об ошибке не появилось");

    }

    @Test
    public void checkWithoutPassword() {
        loginPage.open();
        loginPage.login("standart_user", "");
        Assert.assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Password is required",
                "Сообщение об ошибке не появилось");
    }

    @Test
    public void checkWithoutLogin() {
        loginPage.open();
        loginPage.login("", "secret_sauce");
        Assert.assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Username is required",
                "Сообщение об ошибке не появилось");
    }

    @Test
    public void checkAddProductToCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertEquals(productsPage.getTitle(),
                "Products",
                "Логин не выполнен");
        productsPage.clickButton();
        //проверка, что именно у этого товара появилась кнопка удалить
        //(id кнопок товаров уникальны. id кнопки добавить и удалить отличаются)
        Assert.assertEquals(productsPage.checkButtonRemove(),
                "Remove",
                "Кнопка Удалить не появилась");
        cartPage.clickToCartLink();
        //проверить, что кнопка удалить именно от добавленного товара присутствует на странице корзины
        Assert.assertEquals(productsPage.checkButtonRemove(),
                "Remove",
                "Кнопка Удалить не появилась");

    }

}
