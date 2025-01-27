package tests;

import io.qameta.allure.*;
import jdk.jfr.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.AllureUtils;

import javax.xml.crypto.Data;

import static utils.AllureUtils.takeScreenshot;

public class LoginTest extends BaseTest {

    @Test(testName = "Проверка авторизации",  retryAnalyzer = Retry.class)
    @Description  ("Проверка авторизации с корректными данными")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Saucedemo-1.0")
    @Feature("auth in saucedemo")
    @Story("Авторизация")
    @TmsLink("http://www.jira.simple/CM-11")
    @Issue("http://www.jira.simple/CM-112")
    @Flaky
    public void checkCorrectLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertEquals(productsPage.getTitle(),
                "Products",
                "Логин не выполнен");
        takeScreenshot(driver);
    }

    @Test(testName = "Проверка авторизации заблокированного пользователя", description = "Проверка авторизации заблокированного пользователя",dependsOnMethods = {"checkCorrectLogin"},   retryAnalyzer = Retry.class, groups = {"smoke", "ui"})
    public void checkUserLocked() {
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");
        Assert.assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Sorry, this user has been locked out.",
                "Сообщение об ошибке не появилось");
        takeScreenshot(driver);
    }

    @Test(priority = 1, testName = "Авторизация без пароля", description = "Авторизация без пароля", enabled = false,dependsOnMethods = {"checkUserLocked"},  retryAnalyzer = Retry.class, groups = {"smoke", "ui"})
    public void checkWithoutPassword() {
        loginPage.open();
        loginPage.login("standart_user", "");
        Assert.assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Password is required",
                "Сообщение об ошибке не появилось");
        takeScreenshot(driver);
    }

    @Test(priority = 2, testName = "Авторизация без логина", description = "Авторизация без логина", dependsOnMethods = {"checkWithoutPassword"},   retryAnalyzer = Retry.class, groups = {"smoke", "ui"})
    public void checkWithoutLogin() {
        loginPage.open();
        loginPage.login("", "secret_sauce");
        Assert.assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Username is required",
                "Сообщение об ошибке не появилось");
        takeScreenshot(driver);
    }

    //270125 update метод для передачи в него различных объектов
    @DataProvider (name = "LoginData")
    public Object[][] loginData(){
        return new Object[][]{
                {"standart_user","udfsfsg","Epic sadface: Username and password do not match any user in this service"},
                {"wrong_user","secret_souce","Epic sadface: Username and password do not match any user in this service"}
        };
    }
    //тоже дописанная строка
    @Test(priority = 3, dataProvider = "LoginData", testName = "Проверка введенных логина и пароля", description = "Универсальный метод проверки, в него можно передать объекты из loginData")
    public void checkInputLoginPassword( String user, String password, String message) {
        loginPage.open();
        loginPage.login(user, password);
        Assert.assertEquals(loginPage.getErrorMessage(),
                message,
                "Логин не выполнен");
        takeScreenshot(driver);
    }
}
