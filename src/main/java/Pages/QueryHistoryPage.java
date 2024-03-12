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
import java.util.List;

public class QueryHistoryPage extends Base {

    WebDriver driver;

    @FindBy(id = "Connectivity")
    WebElement btnConnectivity;

    @FindBy(xpath = "//span[@class='name-label ' and text()='Query history']")
    WebElement btnQueryHistory;

    @FindBy(xpath = "//input[@class='input-label w-100 form-control form-control-sm']")
    WebElement btnFilter;

    @FindBy(xpath = "//div[text()='Last 7 days']")
    WebElement ddLast7Days;

    @FindBy(xpath = "//input[@id='multi-select']")
    WebElement btnEntityType;

    @FindBy(xpath = "//div[text()='Status']")
    WebElement ddStatus;

    @FindBy(xpath = "//div[text()='Cluster name']")
    WebElement ddClusterName;

    @FindBy(id = "formBasicPassword")
    WebElement txtEntityName;

    @FindBy(xpath = "//button[text()='Add filter']")
    WebElement btnAddFilter;

    @FindBy(id = "settings")
    WebElement btnSettings;

    @FindBy(xpath = "//span[@id='total-records']/following::button[@aria-label='Next Page'][1]")
    WebElement btnNextPage;

    @FindBy(xpath = "//*[@id='total-records']/span")
    WebElement lblTotalRecords;

    @FindBy(id = "100")
    WebElement rb100;

    @FindBy(xpath = "//*[@aria-label='Close']")
    WebElement btnClose;

    @FindAll(@FindBy(xpath = "//table/tbody/tr/td[1]/span"))
    List<WebElement> tblQueryId;

    @FindAll(@FindBy(xpath = "//table/tbody/tr/td[2]/span[2]"))
    List<WebElement> tblQueryHash;

    @FindAll(@FindBy(xpath = "//table/tbody/tr/td[3]/span"))
    List<WebElement> tblStatus;

    @FindAll(@FindBy(xpath = "//table/tbody/tr/td[4]"))
    List<WebElement> tblCachedResult;

    @FindAll(@FindBy(xpath = "//table/tbody/tr/td[5]"))
    List<WebElement> tblPlanningTime;

    @FindAll(@FindBy(xpath = "//table/tbody/tr/td[6]"))
    List<WebElement> tblQueueTime;

    @FindAll(@FindBy(xpath = "//table/tbody/tr/td[7]"))
    List<WebElement> tblExecutionTime;

    @FindAll(@FindBy(xpath = "//table/tbody/tr/td[8]"))
    List<WebElement> tblStartTime;

    @FindAll(@FindBy(xpath = "//table/tbody/tr/td[9]"))
    List<WebElement> tblEndTime;

    @FindAll(@FindBy(xpath = "//table/tbody/tr/td[10]"))
    List<WebElement> tblCluster;

    @FindAll(@FindBy(xpath = "//table/tbody/tr/td[11]"))
    List<WebElement> tblRunBy;

    public QueryHistoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void queryHistory() {

        List<String> queryIdList = new ArrayList<>();
        List<String> queryHashList = new ArrayList<>();
        List<String> statusList = new ArrayList<>();
        List<String> cachedResultList = new ArrayList<>();
        List<String> planningTimeList = new ArrayList<>();
        List<String> queueTimeList = new ArrayList<>();
        List<String> executionList = new ArrayList<>();
        List<String> startTimeList = new ArrayList<>();
        List<String> endTimeList = new ArrayList<>();
        List<String> clusterList = new ArrayList<>();
        List<String> runByList = new ArrayList<>();

        // Navigate to query history page and filter
        waitToClick(driver, 10, btnConnectivity);
        btnConnectivity.click();

        waitToClick(driver, 10, btnQueryHistory);
        btnQueryHistory.click();

        waitToClick(driver, 10, btnFilter);
        btnFilter.click();
        ddLast7Days.click();

        waitToClick(driver, 10, btnEntityType);
        btnEntityType.click();
        ddClusterName.click();

        txtEntityName.sendKeys("basic");
        btnAddFilter.click();

        new Actions(driver).pause(Duration.ofSeconds(5)).perform();

        waitToClick(driver, 10, btnSettings);
        btnSettings.click();

        waitToClick(driver, 10, rb100);
        rb100.click();
        btnClose.click();

        double noOfPages = Math.ceil(Integer.parseInt(lblTotalRecords.getText()) / 100.00);

        // Fetch and store results from ui
        while (noOfPages >= 1) {

            for (WebElement queryId : tblQueryId) {
                queryIdList.add(queryId.getText());
            }

            for (WebElement queryHash : tblQueryHash) {
                queryHashList.add(queryHash.getText());
            }

            for (WebElement status : tblStatus) {
                statusList.add(status.getText());
            }

            for (WebElement cachedResult : tblCachedResult) {
                cachedResultList.add(cachedResult.getText());
            }

            waitToVisibleAllElements(driver,15,tblPlanningTime);
            for (WebElement planningTime : tblPlanningTime) {
                planningTimeList.add(planningTime.getText());
            }

            for (WebElement queueTime : tblQueueTime) {
                queueTimeList.add(queueTime.getText());
            }

            for (WebElement execution : tblExecutionTime) {
                executionList.add(execution.getText());
            }

            for (WebElement startTime : tblStartTime) {
                startTimeList.add(startTime.getText());
            }

            for (WebElement endTime : tblEndTime) {
                endTimeList.add(endTime.getText());
            }

            for (WebElement cluster : tblCluster) {
                clusterList.add(cluster.getText());
            }

            for (WebElement runBy : tblRunBy) {
                runByList.add(runBy.getText());
            }

            if (noOfPages > 1) {
                btnNextPage.click();
            }

            noOfPages--;

        }
        // Update results in excel
        ExcelUtils.excelWriter("QueryHistory", "QueryID", queryIdList);
        ExcelUtils.excelWriter("QueryHistory", "QueryHash", queryHashList);
        ExcelUtils.excelWriter("QueryHistory", "Status", statusList);
        ExcelUtils.excelWriter("QueryHistory", "CachedResult", cachedResultList);
        ExcelUtils.excelWriter("QueryHistory", "PlanningTime", planningTimeList);
        ExcelUtils.excelWriter("QueryHistory", "QueueTime", queueTimeList);
        ExcelUtils.excelWriter("QueryHistory", "ExecutionTime", executionList);
        ExcelUtils.excelWriter("QueryHistory", "StartTime", startTimeList);
        ExcelUtils.excelWriter("QueryHistory", "EndTime", endTimeList);
        ExcelUtils.excelWriter("QueryHistory", "Cluster", clusterList);
        ExcelUtils.excelWriter("QueryHistory", "RunBy", runByList);

    }

}