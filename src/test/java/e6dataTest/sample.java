package e6dataTest;

import Utils.ExcelUtils;

import java.util.Arrays;

public class sample {

    public static void main(String[] args) {

        ExcelUtils excelutils = new ExcelUtils();
        System.out.println(Arrays.deepToString(excelutils.excelReader()));
    }

}
