package Pages;

import Utils.Base;
import Utils.ExcelUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

    @FindBy(xpath = "//button[text()='Create']")
    WebElement btnCreateCluster;

    @FindBy(xpath = "//input[@placeholder='Enter cluster name']")
    WebElement txtClusterName;

    @FindBy(xpath = "//input[@id='multi-select']/parent::div")
    WebElement ddCatalog;

    @FindBy(xpath = "//input[@id='cacheEnabled']")
    WebElement sldResultCache;

    @FindBy(xpath = "//span[text()='Advanced Settings']")
    WebElement hdrAdvancedSettings;

    @FindBy(xpath = "//input[@id='susTime']")
    WebElement txtSuspensionTime;

    @FindBy(xpath = "//input[@id='defaultQuery']")
    WebElement sldQueryTimeOut;

    @FindBy(xpath = "//div[text()='Create Cluster']/following::button[text()='Create']")
    WebElement btnCreateNewCluster;

    @FindBy(xpath = "//span[text()='Delete']")
    WebElement btnDelete;

    @FindBy(id = "formBasicPassword")
    WebElement txtDeleteAlert;

    @FindBy(xpath = "//button[text()='Confirm']")
    WebElement btnDeleteConfirm;


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

    public void createNewCluster(String name, String catalog, String suspensionTime) {

        // Navigate to cluster page
        waitToClick(driver, 10, btnCluster);
        btnCluster.click();

        waitToClick(driver, 15, btnCreateCluster);
        btnCreateCluster.click();

        waitToClick(driver, 10, txtClusterName);
        txtClusterName.sendKeys(name);
        ddCatalog.click();

        new Actions(driver).pause(Duration.ofSeconds(3)).perform();
        driver.findElement(By.xpath("//div[text()='" + catalog + "']")).click();

        new Actions(driver).click(ddCatalog).sendKeys(Keys.TAB).pause(Duration.ofSeconds(1)).perform();

        waitToClick(driver, 10, sldResultCache);
        sldResultCache.click();
        hdrAdvancedSettings.click();

        waitToClick(driver, 10, sldQueryTimeOut);
        sldQueryTimeOut.click();
        txtSuspensionTime.clear();
        txtSuspensionTime.sendKeys(suspensionTime);
        btnCreateNewCluster.click();

    }

    public void deleteCluster(String name) {

        // Navigate to cluster page
        waitToClick(driver, 10, btnCluster);
        btnCluster.click();

        waitToClick(driver, 30, btnSettings);
        btnSettings.click();

        waitToClick(driver, 10, rb100);
        rb100.click();
        btnClose.click();

        // Calculate number of pages
        int totalRecords = Integer.parseInt(lblTotalRecords.getText());
        int pageSize = 100;
        int totalPages = (int) Math.ceil((double) totalRecords / pageSize);

        // Iterate through pages
        for (int page = 1; page <= totalPages; page++) {

            // Iterate through rows on the current page
            for (WebElement row : tblNames) {

                if (row.getText().contains(name)) {

                    // Found the row containing the name
                    String status = row.findElement(By.xpath("//td[6]/span")).getText();

                    if (status.equalsIgnoreCase("Active")) {

                        // Click on dropdown menu and delete
                        WebElement dropdownBtn = row.findElement(By.xpath("//td//button[@id='dropdown-autoclose-true']"));
                        dropdownBtn.click();

                        waitToClick(driver, 10, btnDelete);
                        btnDelete.click();

                        waitToClick(driver, 10, txtDeleteAlert);
                        txtDeleteAlert.sendKeys("Delete");

                        waitToClick(driver, 10, btnDeleteConfirm);
                        btnDeleteConfirm.click();

                    }
                    return;
                }

            }

            // Click next page
            if (page < totalPages) {
                btnNextPage.click();
            }

        }

    }

}



