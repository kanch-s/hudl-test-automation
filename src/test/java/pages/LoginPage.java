package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {
    WebDriver driver;
    public LoginPage(WebDriver driver){
        this.driver = driver;
    }
    String userName;
    By lblNavBarAccountName = By.xpath("//div[@class='hui-globaluseritem__display-name']");
    By lblNavBarUserName = By.xpath("//div[@class='hui-globaluseritem__email']");
    By lblNavBarLogOut = By.xpath("//a[@data-qa-id='webnav-usermenu-logout']");
    By lblHomeLabel = By.xpath("//a[@class='mainnav__btn mainnav__btn--primary']");
    By txtUsername = By.id("email");
    By txtPassword = By.id("password");
    By btnLogin = By.id("logIn");
    By checkBoxRememberMe = By.xpath("//div[@class='uni-form__check-item']");
    By lblErrorMessage = By.xpath("//p[@data-qa-id='error-display']");


    private void typeUsername(String userName){
        try {
            driver.findElement(txtUsername).clear();
            driver.findElement(txtUsername).sendKeys(userName);
        }catch (NoSuchElementException e){
            throw new RuntimeException("Element not found within the given time" + btnLogin);
        }
    }
    private void typePassword(String password){
        try {
            driver.findElement(txtPassword).clear();
            driver.findElement(txtPassword).sendKeys(password);
        }catch (NoSuchElementException e){
            throw new RuntimeException("Element not found within the given time" + btnLogin);
        }
    }
    private void clickRememberMe(){
        try{
            driver.findElement(checkBoxRememberMe).click();
        }catch (NoSuchElementException e){
            throw new RuntimeException("Element not found within the given time" + btnLogin);
        }
    }
    private void clickSubmit(){
        try{
            driver.findElement(btnLogin).click();
        }catch (NoSuchElementException e){
            throw new RuntimeException("Element not found within the given time" + btnLogin);
        }
    }
    public String getLoggedInUser(){
        return this.userName;
    }
    public String errorMessage(){
        WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(lblErrorMessage));
        return driver.findElement(lblErrorMessage).getText().replace("\n", "").replace("\r", "");
    }

    public void enterInvalidCredentials(String userName, String password){
        this.typeUsername(userName);
        this.typePassword(password);
        this.clickSubmit();
    }
    public String checkLoggedInUser() {
        WebElement accountName = driver.findElement(lblNavBarAccountName);
        Actions actions = new Actions(driver);
        actions.moveToElement(accountName).perform();
        WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(lblNavBarUserName));

        HomePage homePage = new HomePage(driver);
        return driver.findElement(lblNavBarUserName).getText().replace("\n", "").replace("\r", "");
    }
    public HomePage LoginToTheSystem(String userName, String password){
        this.typeUsername(userName);
        this.typePassword(password);
        this.clickSubmit();
        WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='hui-globaluseritem__display-name']")));
        this.takeSnapShot(driver,"Login");

        WebElement accountName = driver.findElement(lblNavBarAccountName);
        Actions actions = new Actions(driver);
        actions.moveToElement(accountName).perform();
        firstResult = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(lblNavBarUserName));

        HomePage homePage = new HomePage(driver);
        homePage.userName = driver.findElement(lblNavBarUserName).getText().replace("\n", "").replace("\r", "");
        return homePage;
    }

    public HomePage rememberMe(String userName, String password){
        this.typeUsername(userName);
        this.typePassword(password);
        this.clickRememberMe();
        this.clickSubmit();
        WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='hui-globaluseritem__display-name']")));
        this.takeSnapShot(driver,"Login");

        WebElement accountName = driver.findElement(lblNavBarAccountName);
        Actions actions = new Actions(driver);
        actions.moveToElement(accountName).perform();
        firstResult = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(lblNavBarUserName));

        HomePage homePage = new HomePage(driver);
        homePage.userName = driver.findElement(lblNavBarUserName).getText().replace("\n", "").replace("\r", "");
        driver.get(this.getPropertyDetails("test_url_index"));

        firstResult = new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='hui-globaluseritem__display-name']")));

        return homePage;
    }
    public HomePage LogoutFromTheSystem(String userName, String password){
        this.typeUsername(userName);
        this.typePassword(password);
        this.clickSubmit();

        WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='hui-globaluseritem__display-name']")));

        WebElement accountName = driver.findElement(lblNavBarAccountName);
        Actions actions = new Actions(driver);
        actions.moveToElement(accountName).perform();

        try{
            driver.findElement(lblNavBarLogOut).click();
        }catch (NoSuchElementException e){
            throw new RuntimeException("Element not found within the given time" + btnLogin);
        }

        firstResult = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(lblHomeLabel));

        HomePage homePage = new HomePage(driver);
        homePage.pageStatus = driver.findElement(lblHomeLabel).getText().replace("\n", "").replace("\r", "");
        this.takeSnapShot(driver,"Logout-Menu");
        return homePage;

    }
}