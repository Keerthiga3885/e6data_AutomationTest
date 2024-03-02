package e6dataTest;

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

    }

}
