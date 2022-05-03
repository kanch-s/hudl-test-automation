package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.HomePage;
import pages.LoginPage;

public class LoginTest {
    LoginPage loginPage;
    HomePage homePage;
    BasePage basePage;
    String correctUsername;
    String correctPassword;
    String wrongPassword;
    String wrongUsername;
    String homePageLoginBtn;

    @BeforeMethod
    public void setUp(){
        basePage = new BasePage();
        loginPage = basePage.setUpBrowser();
        correctUsername = basePage.getPropertyDetails("username");
        correctPassword = basePage.getPropertyDetails("password");
        wrongUsername = basePage.getPropertyDetails("wrongUsername");
        wrongPassword = basePage.getPropertyDetails("wrongPassword");
        homePageLoginBtn = "Log in";
    }

    @Test(priority = 1)
    public void invalidUserName(){
        loginPage.enterInvalidCredentials(wrongUsername,correctPassword);
        Assert.assertEquals(loginPage.errorMessage(),("We didn't recognize that email and/or password.Need help?"));
    }

    @Test (priority = 2)
    public void invalidPassword(){
        loginPage.enterInvalidCredentials(correctUsername,wrongPassword);
        Assert.assertEquals(loginPage.errorMessage(),("We didn't recognize that email and/or password.Need help?"));
    }

    @Test (priority = 3)
    public void emptyUserName(){
        loginPage.enterInvalidCredentials("",correctPassword);
        Assert.assertEquals(loginPage.errorMessage(),("We didn't recognize that email and/or password.Need help?"));
    }

    @Test (priority = 4)
    public void loginToTheSystem(){
        homePage = loginPage.LoginToTheSystem(correctUsername,correctPassword);
        Assert.assertEquals(homePage.getLoggedInUser(),(correctUsername));
    }

    @Test (priority = 5)
    public void rememberMe(){
        loginPage.rememberMe(correctUsername,correctPassword);
        Assert.assertEquals(loginPage.checkLoggedInUser(),(correctUsername));
    }

    @Test (priority = 6)
    public void logoutFromTheSystem(){
        homePage = loginPage.LogoutFromTheSystem(correctUsername,correctPassword);
        Assert.assertEquals(homePage.pageStatus(),(homePageLoginBtn));
    }

    @AfterMethod
    public void close(){
        basePage.closeBrowser();
    }

}