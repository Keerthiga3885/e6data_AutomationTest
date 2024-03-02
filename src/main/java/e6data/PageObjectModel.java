package e6data;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class PageObjectModel {

    WebDriver driver;


    @FindBy(xpath = "//*[@id='root']/div[1]/div[2]/div[3]/form/div[1]/div/input")
    WebElement Email;

    @FindBy(xpath = "//*[@id='root']/div[1]/div[2]/div[3]/form/div[2]/div/input")
    WebElement Password;

    @FindBy(xpath = "//*[@id='root']/div[1]/div[2]/div[3]/form/div[4]/button")
    WebElement Sign_in;

    @FindBy(xpath = "//*[@id='ws']/div[2]/table/tbody/tr/td/a")
    WebElement Record;

    @FindBy(xpath = "//*[@id=\"root\"]/div[1]/div[1]/div/div[2]/ul/div[1]/li[7]/div/div")
    WebElement ConnectivityOption;

    @FindAll(@FindBy(xpath = "//span[@class='name-label ']"))
    List<WebElement> LeftPanelOptions;

    @FindBy(xpath = "//a[@href='/catalog?sid=s55el54cur']")
    WebElement Catalog;
    @FindBy(xpath = "//*[@id=\"total-records\"]/span")
    WebElement CatalogRecordsCount;
    @FindBy(xpath = "//*[@id='ct']/div[1]/div/div/div/button[@aria-label='Next Page']")
    WebElement NextPageButton;

    @FindBy(id = "settings")
    WebElement SettingsButton;
    @FindBy(id = "100")
    WebElement PageSizeAs100;

    @FindBy(xpath = "//*[@aria-label='Close']")
    WebElement SettingsCloseButton;

    @FindBy(xpath = "//a[@href='/cluster?sid=s55el54cur']")
    WebElement Cluster;


    PageObjectModel(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public void Login() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get("https://friends1.e6xlabs.cloud/login");

        wait.until(ExpectedConditions.elementToBeClickable(Email));
        Email.sendKeys("task@e6x.io");
        Password.sendKeys("Abcd@123");

        wait.until(ExpectedConditions.elementToBeClickable(Sign_in));
        Sign_in.click();


    }

    public List<String> LeftPanelOptionsList() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        wait.until(ExpectedConditions.elementToBeClickable(Record)).click();
        wait.until(ExpectedConditions.elementToBeClickable(ConnectivityOption)).click();

        wait.until((ExpectedConditions.visibilityOfAllElements(LeftPanelOptions)));
        List<String> LeftPanelOptionNames = new ArrayList<>();

        for (WebElement LeftPanelOption : LeftPanelOptions) {

            //System.out.println(LeftPanelOption.getText());
            LeftPanelOptionNames.add(LeftPanelOption.getText());

        }
        return LeftPanelOptionNames;
    }

    public Object[][] CatalogRecordsList() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(Catalog)).click();
        String CatalogRecords = wait.until(ExpectedConditions.elementToBeClickable(CatalogRecordsCount)).getText();
        int TotalCatalogRecords = Integer.parseInt(CatalogRecords);
        wait.until(ExpectedConditions.elementToBeClickable(SettingsButton)).click();
        PageSizeAs100.click();
        SettingsCloseButton.click();
        double n = TotalCatalogRecords / 100.00;
        double CatalogPageCount = Math.ceil(n);
        System.out.println(CatalogPageCount);
        int CatalogRecordsPerPage = Math.min(TotalCatalogRecords, 100);
        Object[][] data = new Object[TotalCatalogRecords + 1][3];
        while (CatalogPageCount >= 0) {

            for (int i = 1; i <= CatalogRecordsPerPage; i++) {
                for (int j = 0; j < 3; j++) {
                    if (j == 0) {
                        //name
                        WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='p-datatable-table p-datatable-resizable-table']/tbody/tr[" + i + "]/td[1]/a")));
                        data[i][j] = name.getText();
                    }
                    if (j == 1) {

                        //createdBy
                        WebElement createdBy = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='p-datatable-table p-datatable-resizable-table']/tbody/tr[" + i + "]/td[4]")));
                        data[i][j] = createdBy.getText();
                    }
                    if (j == 2) {

                        //Status
                        WebElement status = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='p-datatable-table p-datatable-resizable-table']/tbody/tr[" + i + "]/td[5]/span")));
                        data[i][j] = status.getText();
                    }

                    System.out.println(data[i][j]);
                }
            }
            CatalogPageCount--;
            if(CatalogPageCount>0){
            NextPageButton.click();
            }
        }
    return data;
    }


}
