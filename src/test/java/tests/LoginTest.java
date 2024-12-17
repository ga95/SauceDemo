package tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

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
}
