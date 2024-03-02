package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverUtils {

    public static WebDriver initDriver(String browserType) {

        WebDriver driver;

        // Driver selection
        if (browserType.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else {
            driver = new EdgeDriver();
        }

        driver.manage().window().maximize();
        return driver;

    }

}
