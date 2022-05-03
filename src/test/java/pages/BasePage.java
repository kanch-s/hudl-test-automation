package pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.*;
import java.util.Properties;

public class BasePage {
    WebDriver driver;

    public LoginPage setUpBrowser(){
        String browserName = this.getPropertyDetails("browser");

        if (browserName.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.get(this.getPropertyDetails("test_url_login"));
        }
        else if (browserName.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.get(this.getPropertyDetails("test_url_login"));
        }
        driver.manage().window().maximize();
        return new LoginPage(driver);
    }
    public String getPropertyDetails(String propName){
        String filePath = System.getProperty("user.dir");
        Properties properties  = new Properties();
        try {
            InputStream inputStream = new FileInputStream(filePath +"/src/test/java/utils/data.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(propName);
    }

    public void takeSnapShot(WebDriver driver,String imageName) {
        String filePath = System.getProperty("user.dir");
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File(filePath +"/src/test/java/Screenshots/"
                    + imageName+".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public void closeBrowser(){
        driver.close();
    }
}
