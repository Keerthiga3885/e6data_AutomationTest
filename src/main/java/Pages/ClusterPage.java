package Pages;

import Utils.Base;
import Utils.ExcelUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClusterPage extends Base {

    WebDriver driver;

    @FindBy(xpath = "//a[@href='/cluster?sid=s55el54cur']")
    WebElement btnCluster;

    @FindBy(xpath = "//span[@id='total-records']/following::button[@aria-label='Next Page'][1]")
    WebElement btnNextPage;

    @FindBy(xpath = "//*[@id='total-records']/span")
    WebElement lblTotalRecords;

    @FindBy(id = "settings")
    WebElement btnSettings;

    @FindBy(id = "100")
    WebElement rb100;

    @FindBy(xpath = "//*[@aria-label='Close']")
    WebElement btnClose;

    @FindAll(@FindBy(xpath = "//table[@class='p-datatable-table p-datatable-resizable-table']/tbody/tr/td[1]/a"))
    List<WebElement> tblNames;

    @FindAll(@FindBy(xpath = "//table[@class='p-datatable-table p-datatable-resizable-table']/tbody/tr/td[5]"))
    List<WebElement> tblCreatedBy;

    @FindAll(@FindBy(xpath = "//table[@class='p-datatable-table p-datatable-resizable-table']/tbody/tr/td[6]/span"))
    List<WebElement> tblStatus;

    public ClusterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clusterOptions() {

        List<String> nameList = new ArrayList<>();
        List<String> createdByList = new ArrayList<>();
        List<String> statusList = new ArrayList<>();

        // Navigate to cluster page and filter
        waitToClick(driver, 10, btnCluster);
        btnCluster.click();

        waitToClick(driver, 10, btnSettings);
        btnSettings.click();

        waitToClick(driver, 10, rb100);
        rb100.click();
        btnClose.click();

        double noOfPages = Math.ceil(Integer.parseInt(lblTotalRecords.getText()) / 100.00);

        new Actions(driver).pause(Duration.ofSeconds(5)).perform();

        // Iterate rows to get text
        while (noOfPages >= 1) {

            for (WebElement name : tblNames) {
                nameList.add(name.getText());
            }

            for (WebElement createdBy : tblCreatedBy) {
                createdByList.add(createdBy.getText());
            }

            for (WebElement status : tblStatus) {
                statusList.add(status.getText());
            }

            if (noOfPages > 1) {
                btnNextPage.click();
            }

            noOfPages--;
        }

        // Update results in excel
        ExcelUtils.excelWriter("ClusterOptions", "Name", nameList);
        ExcelUtils.excelWriter("ClusterOptions", "Created By", createdByList);
        ExcelUtils.excelWriter("ClusterOptions", "Status", statusList);
        ExcelUtils.excelWriter("ClusterOptions", "Total Records", Collections.singletonList(lblTotalRecords.getText()));

    }

}
