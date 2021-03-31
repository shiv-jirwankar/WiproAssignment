package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelData {

	DataFormatter formatter = new DataFormatter(Locale.US);

	/**reads the desired test data from the excel sheet based on the parameters passed
	 * 
	 * @param sheetName
	 * @param testName
	 * @return String data value from the excel sheet
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public String readDataFromExcel(String sheetName, String testName) throws IOException

	{

		String filepath = new File("").getAbsolutePath() + File.separator + "TestData.xlsx";
		// File file = new File("C://Users//com//Documents//Test_Data_CoinZoom.xlsx");
		FileInputStream inputStream = new FileInputStream(new File(filepath));

		// FileInputStream inputStream = new FileInputStream(file);

		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

		Sheet sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

		for (int i = 0; i < rowCount + 1; i++) {
			Row row = sheet.getRow(i);
			// Create a loop to print cell values in a row
			if (row.getCell(0).getStringCellValue().equalsIgnoreCase(testName)) {
				String valueToEnter = formatter.formatCellValue(row.getCell(1));
				return valueToEnter;
			}

		}
		return null;

	}

}
