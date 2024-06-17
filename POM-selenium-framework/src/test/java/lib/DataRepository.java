package lib;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class DataRepository {

	private Sheet sheet;
	private Workbook  workbook;

	public DataRepository(String filePath, String sheetName) throws FileNotFoundException {
		try {
			File file = new File(filePath);
			FileInputStream inputStream = new FileInputStream(file);
			workbook = WorkbookFactory.create(inputStream);
			sheet = workbook.getSheet(sheetName);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public String getStringCellValue(String cellContent) {
		String cellValue = "";
		for (Row row : sheet) {
			for (Cell cell : row) {
				if (cell.getCellType() == CellType.STRING) {
					if (cell.getRichStringCellValue().getString().trim().equals(cellContent)) {
						cellValue = sheet.getRow(row.getRowNum()).getCell(cell.getColumnIndex() + 1)
								.getStringCellValue().toString().trim();
					}
				}
			}
		}
		return cellValue;
	}
}
