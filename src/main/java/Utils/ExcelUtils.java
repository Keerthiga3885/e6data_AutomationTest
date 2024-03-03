package Utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ExcelUtils {

    private static final String outputExcelLocation = "test-output/e6dataTestOutput.xlsx";

    public static Object[][] excelReader() {

        Object[][] excelData;

        try {

            InputStream inputStream = Files.newInputStream(Paths.get(outputExcelLocation));
            Workbook workBook = WorkbookFactory.create(inputStream);
            Sheet sheet = workBook.getSheet("NewCluster");

            int rowCount = sheet.getLastRowNum();
            int colCount = sheet.getRow(0).getLastCellNum();
            excelData = new Object[rowCount][colCount];

            for (int i = 1; i <= rowCount; i++) {
                for (int j = 0; j < colCount; j++) {
                    excelData[i-1][j] = sheet.getRow(i).getCell(j);
                }
            }

            workBook.close();
            inputStream.close();

        } catch (IOException e) {
            throw new RuntimeException("Unable to read data from excel " + e.getMessage());
        }

        return excelData;
    }

    public static void excelWriter(String sheetName, String columnName, List<String> data) {

        try {

            InputStream inputStream = Files.newInputStream(Paths.get(outputExcelLocation));
            Workbook workBook = WorkbookFactory.create(inputStream);
            Sheet sheet = workBook.getSheet(sheetName);

            for (int i = 0; i < sheet.getRow(0).getLastCellNum(); i++) {
                if(sheet.getRow(0).getCell(i).getStringCellValue().equalsIgnoreCase(columnName)){
                    for (int j = 0; j < data.size(); j++) {
                        sheet.createRow(j+1);
                        sheet.getRow(j+1).createCell(i).setCellValue(data.get(j));
                    }
                }
            }

            FileOutputStream fileOutputStream = new FileOutputStream(outputExcelLocation);
            workBook.write(fileOutputStream);

            fileOutputStream.flush();
            workBook.close();
            inputStream.close();

        } catch (IOException e) {
            throw new RuntimeException("Unable to write data to excel " + e.getMessage());
        }

    }

}
