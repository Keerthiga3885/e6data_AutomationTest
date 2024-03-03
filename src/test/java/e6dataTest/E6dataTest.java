package e6dataTest;

import Pages.*;
import Utils.DriverUtils;
import Utils.ExcelUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class E6dataTest {

    @BeforeSuite
    public void setup() throws IOException {

        // Clear old results
        FileUtils.deleteDirectory(new File("test-output"));

        // Create Test-Output directory to store results
        FileUtils.forceMkdir(new File("test-output"));

        // Copy test data file to write test results
        FileUtils.copyFile(new File("src/main/resources/e6dataTestInput.xlsx"), new File("test-output/e6dataTestOutput.xlsx"));

    }

    @Test
    public void loginTest() {

        WebDriver loginDriver = DriverUtils.initDriver("chrome");
        LoginPage loginPage = new LoginPage(loginDriver);
        loginPage.login();
        DriverUtils.tearDown(loginDriver);

    }

    @Test
    public void leftNavigationTest() {

        // Login to e6Data
        WebDriver leftNavigationDriver = DriverUtils.initDriver("chrome");
        LoginPage loginPage = new LoginPage(leftNavigationDriver);
        loginPage.login();

        // Fetch left panel menus
        PltInfraPage leftNavigation = new PltInfraPage((leftNavigationDriver));
        leftNavigation.leftNavigationMenuList();

        // Closing browser driver
        DriverUtils.tearDown(leftNavigationDriver);

    }

    @Test
    public void catalogOptionTest() {

        // Login to e6Data
        WebDriver catalogOptionDriver = DriverUtils.initDriver("chrome");
        LoginPage loginPage = new LoginPage(catalogOptionDriver);
        loginPage.login();

        // Fetch catalog options
        CatalogPage catalogPage = new CatalogPage(catalogOptionDriver);
        catalogPage.catalogOptions();

        // Closing browser driver
        DriverUtils.tearDown(catalogOptionDriver);

    }

    @Test
    public void clusterOptionTest() {

        // Login to e6Data
        WebDriver clusterOptionDriver = DriverUtils.initDriver("chrome");
        LoginPage loginPage = new LoginPage(clusterOptionDriver);
        loginPage.login();

        // Fetch cluster options
        ClusterPage clusterPage = new ClusterPage(clusterOptionDriver);
        clusterPage.clusterOptions();

        // Closing browser driver
        DriverUtils.tearDown(clusterOptionDriver);

    }

    @Test(dataProvider = "E6TestData", dataProviderClass = ExcelUtils.class)
    public void createAndDeleteClusterTest(String Name, String Catalog, String AutoSuspensionTime) {

        // Login to e6Data
        WebDriver createAndDeleteClusterDriver = DriverUtils.initDriver("chrome");
        LoginPage loginPage = new LoginPage(createAndDeleteClusterDriver);
        loginPage.login();

        // Fetch cluster options
        ClusterPage clusterPage = new ClusterPage(createAndDeleteClusterDriver);
        clusterPage.createNewCluster(Name,Catalog,AutoSuspensionTime);
        clusterPage.deleteCluster(Name);

        // Closing browser driver
        DriverUtils.tearDown(createAndDeleteClusterDriver);

    }

    @Test
    public void queryHistoryTest() {

        // Login to e6Data
        WebDriver queryHistoryDriver = DriverUtils.initDriver("chrome");
        LoginPage loginPage = new LoginPage(queryHistoryDriver);
        loginPage.login();

        // Fetch query history
        QueryHistoryPage queryHistoryPage = new QueryHistoryPage(queryHistoryDriver);
        queryHistoryPage.queryHistory();

        // Closing browser driver
        DriverUtils.tearDown(queryHistoryDriver);

    }


}

