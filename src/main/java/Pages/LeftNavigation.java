package Pages;

import Utils.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class LeftNavigation extends Base {
    WebDriver driver;

    @FindBy(xpath="//a[@href='/workspace-details/s55el54cur']")
    WebElement hyperlinkRecord;

    @FindBy(id="Connectivity")
    WebElement optionConnectivity;

    @FindAll(@FindBy(xpath = "//span[@class='name-label ']"))
    List<WebElement> optionsInLeftPanel;

    public LeftNavigation(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void LeftPanelOptionsList(){

        waitToClick(driver,30,hyperlinkRecord);
        hyperlinkRecord.click();

        waitToClick(driver,30,optionConnectivity);
        optionConnectivity.click();

        waitToVisibleAllElements(driver,30,optionsInLeftPanel);
        List<String> leftPanelOptionNames = new ArrayList<>();

        for (WebElement leftPanelOption : optionsInLeftPanel) {

            //System.out.println(leftPanelOption.getText());
            leftPanelOptionNames.add(leftPanelOption.getText());

        }

    }

}
