package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverUtils {

    public static WebDriver initDriver(String browserType) {

        WebDriver driver;

        // Driver selection
        if (browserType.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else {
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        return driver;

    }

    public static void tearDown(WebDriver driver){

        driver.quit();

    }

}
