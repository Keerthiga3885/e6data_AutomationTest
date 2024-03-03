# e6data_AutomationTest
This project is built using **Selenium with java**.
It follows **Page Object Model** & **TestNG** approach.

## Prerequisites

Ensure that your system has the following software installed:

- Java (version 21)
- Maven - (version 3.x)

## Add Dependencies in (POM.xml)

Add the following dependencies to your `pom.xml` file:

        <!-- https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java -->
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>4.17.0</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.testng/testng -->
        <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>7.8.0</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.apache.poi/poi -->
        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi</artifactId>
            <version>5.2.5</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml -->
        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi-ooxml</artifactId>
            <version>5.2.5</version>
        </dependency>

## Test Cases Covered

- `loginTest` - Validate application through the provided credentials.
- `leftNavigationTest` - List available options in the left navigation.
- `catalogOptionTest && clusterOptionTest` - List Name, created by, status and total count of the records in
  each option.
- `createAndDeleteClusterTest` - Create and delete the cluster.
- `queryHistoryTest` - Get records for the last 7 days by
  applying filters.

## Project Structure

The project follows standard Maven directory structure:

- `src/main/java/pages`: LoginPage, PltInfraPage, ClusterPage, CatalogPage, QueryHistoryPage
- `src/main/java/utils`: Base, DriverUtils, ExcelUtils
- `src/main/java/resources`: Configuration.properties, e6dataTestInput.xlsx
- `src/test/java/e6dataTest`: E6dataTest
- `test-output`: e6dataTestOutput.xlsx
- `./`: README.md, TestNG.xml, pom.xml

## Running the Test Suite

- Tests Can be able to execute tests by **pom.xml** file using **mvn clean test** command
- Run the `TestNG.xml` file, Six Test will execute in both sequential & parallel manner