package e6dataTest;

import Pages.LeftNavigation;
import Utils.DriverUtils;
import Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class E6dataTest {

    @Test
    public void loginTest() {

        WebDriver loginDriver = DriverUtils.initDriver("chrome");
        LoginPage loginPage = new LoginPage(loginDriver);
        loginPage.login();
        DriverUtils.tearDown(loginDriver);

    }@Test
    public void leftNavigationTest() {

        // Login to e6Data
        WebDriver loginDriver = DriverUtils.initDriver("chrome");
        LoginPage loginPage = new LoginPage(loginDriver);
        loginPage.login();

        // Fetch left panel options
        LeftNavigation leftNavigation = new LeftNavigation((loginDriver));
        leftNavigation.LeftPanelOptionsList();

        // Closing browser driver
        DriverUtils.tearDown(loginDriver);

    }

}
