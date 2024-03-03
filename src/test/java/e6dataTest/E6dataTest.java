package e6dataTest;

import Pages.PltInfraPage;
import Utils.DriverUtils;
import Pages.LoginPage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class E6dataTest {

    @BeforeSuite
    public void setup() throws IOException {

        // Clear old results
        FileUtils.deleteDirectory(new File("test-output"));

        // Create Test-Output directory to store results
        FileUtils.forceMkdir(new File("test-output"));

        // Copy test data file to write test results
        FileUtils.copyFile(new File("src/main/resources/e6dataTestInput.xlsx"), new File("test-output/e6dataTestOutput.xlsx"));

    }

    //@Test
    public void loginTest() {

        WebDriver loginDriver = DriverUtils.initDriver("chrome");
        LoginPage loginPage = new LoginPage(loginDriver);
        loginPage.login();
        DriverUtils.tearDown(loginDriver);

    }

    @Test
    public void leftNavigationTest() {

        // Login to e6Data
        WebDriver leftNavigationDriver = DriverUtils.initDriver("chrome");
        LoginPage loginPage = new LoginPage(leftNavigationDriver);
        loginPage.login();

        // Fetch left panel menus
        PltInfraPage leftNavigation = new PltInfraPage((leftNavigationDriver));
        leftNavigation.leftNavigationMenuList();

        // Closing browser driver
        DriverUtils.tearDown(leftNavigationDriver);

    }

}

