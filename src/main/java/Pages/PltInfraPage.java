package Pages;

import Utils.Base;
import Utils.ExcelUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class PltInfraPage extends Base {

    WebDriver driver;

    @FindBy(id = "Connectivity")
    WebElement btnConnectivity;

    @FindAll(@FindBy(xpath = "//span[@class='name-label ']"))
    List<WebElement> leftNavigationMenu;

    public PltInfraPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void leftNavigationMenuList() {

        waitToClick(driver, 30, btnConnectivity);
        btnConnectivity.click();

        waitToVisibleAllElements(driver, 20, leftNavigationMenu);
        List<String> leftNavigationMenuList = new ArrayList<>();

        for (WebElement leftPanelOption : leftNavigationMenu) {
            leftNavigationMenuList.add(leftPanelOption.getText());
        }

        // Update results to excel
        ExcelUtils.excelWriter("LeftNavigationMenu", "Menu List", leftNavigationMenuList);

    }

}
