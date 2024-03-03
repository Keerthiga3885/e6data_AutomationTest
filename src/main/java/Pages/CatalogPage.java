package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CatalogPage {

    WebDriver driver;



    public CatalogPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);

    }

}
