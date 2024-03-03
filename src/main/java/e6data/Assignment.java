package e6data;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Assignment {

    WebDriver driver;
    PageObjectModel POM;

    @BeforeSuite
    public void LaunchBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        POM = new PageObjectModel(driver);
    }

    @AfterSuite
    public void CloseBrowser(){
        driver.quit();
    }
    @Test(priority = 1)
    public void Login() {
        POM.Login();
    }
    @Test(priority = 2)
    public void OptionList() throws IOException {
        List<String> optionNames = POM.LeftPanelOptionsList();
        String filePath = "src/test/java/e6data/e6dataInput.xlsx";
        writeToExcel(optionNames,filePath);
    }

    @Test(priority = 3)
        public void CatalogList(){
            Object[][] Catalogrecords= POM.CatalogRecordsList();
           // writeToExcel(Catalogrecords);
        }

    public void writeToExcel(List<String> OptionName,String filePath) throws IOException {
           // String filePath = "src/test/java/e6data/e6dataInput.xlsx";
            XSSFWorkbook workbook = new XSSFWorkbook();
            FileOutputStream outputFile = new FileOutputStream(filePath);
            XSSFSheet sheet = workbook.createSheet();
            sheet.setColumnWidth(0,5);
            XSSFRow row = sheet.createRow(0);
            XSSFCell cell = row.createCell(0);
            cell.setCellValue("LeftPanelOptionslist");

            for (int j = 0; j < OptionName.size(); j++) {
                row = sheet.createRow(j + 1);
                cell = row.createCell(0);
                cell.setCellValue(OptionName.get(j));
            }

            workbook.write(outputFile);
            outputFile.close();


    }


}

