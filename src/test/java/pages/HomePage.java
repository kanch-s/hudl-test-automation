package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
public class HomePage extends BasePage{
    WebDriver driver;
    String pageStatus;
    By lblHomeLabel = By.xpath("//a[@class='mainnav__btn mainnav__btn--primary']");
    public HomePage(WebDriver driver){
        this.driver = this.driver;
    }
    public String homePage(){
        WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(lblHomeLabel));
        return driver.findElement(lblHomeLabel).getText().replace("\n", "").replace("\r", "");
    }
    public String pageStatus(){
        return this.pageStatus;
    }
    String userName;

    By btnLogin = By.id("logIn");
    By lblNavBarAccountName = By.xpath("//div[@class='hui-globaluseritem__display-name']");
    By lblNavBarUserName = By.xpath("//div[@class='hui-globaluseritem__email']");
    By lblNavBarLogOut = By.xpath("//a[@data-qa-id='webnav-usermenu-logout']");

    private void clickLogout(){
        this.takeSnapShot(driver,"HomePage");
        driver.findElement(lblNavBarLogOut).click();
    }
    public String getLoggedInUser(){
        return this.userName;
    }
}