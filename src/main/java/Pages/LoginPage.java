package Pages;

import Utils.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoginPage extends Base {

    WebDriver driver;

    @FindBy(xpath = "//input[@placeholder='Email']")
    WebElement txtEmail;

    @FindBy(xpath = "//input[@placeholder='Password']")
    WebElement txtPassword;

    @FindBy(xpath = "//button[text()='Sign in']")
    WebElement btnSignIn;

    @FindBy(xpath="//tbody/tr/td[5]/span[text()='Active']/ancestor::tr/td[1]/a")
    WebElement lnkActiveWorkspace;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void login() {

        Properties properties = null;

        // Load property files
        try (FileInputStream file = new FileInputStream("src/main/resources/Configuration.properties")) {
            properties = new Properties();
            properties.load(file);
        } catch (IOException e) {
            System.out.println(" !!!! Failed Loading Property Utils !!!! ");
        }

        // Load the url
        assert properties != null;
        driver.get(properties.getProperty("Url"));

        // Entering email & pwd
        waitToClick(driver, 30, txtEmail);
        txtEmail.sendKeys(properties.getProperty("Email"));
        txtPassword.sendKeys(properties.getProperty("Password"));

        // Click sign in btn
        waitToClick(driver, 20, btnSignIn);
        btnSignIn.click();

        waitToClick(driver,30,lnkActiveWorkspace);
        lnkActiveWorkspace.click();

    }

}
