package sh.utils.excel;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelWrite {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	static XSSFRow row = null;
	static XSSFCell cell = null;
	static XSSFSheet sheet = null;
	static XSSFWorkbook workbook = null;
	static XSSFCellStyle cs = null;

	/**
	 * Excel 생성
	 * 
	 * @Author SH
	 * @return boolean
	 * 
	 */
	public boolean excelwrite() {
		// create workbook
		workbook = new XSSFWorkbook();
		ExcelWrite ew = new ExcelWrite();

		if (ew.excelFirstLine("Sheet1") && ew.excelDataLine()) {
			// excel save
			FileOutputStream excelOutput = null;
			String path = "C:\\Users\\user\\Desktop\\test.xlsx";
			try {
				excelOutput = new FileOutputStream(path);
				workbook.write(excelOutput);
				excelOutput.close();
				logger.info("====END====");
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		logger.info("Excel 생성 실패");
		return false;
	}

	/**
	 * 
	 * Excel Title bar 생성
	 * 
	 * @Author SH
	 * @param sheetName
	 * @return boolean;
	 */
	public boolean excelFirstLine(String sheetNm) {
		sheet = workbook.createSheet(sheetNm);

		// 틀 고정
		sheet.createFreezePane(0, 2);

		// 셀 가운데 정렬
		cs = workbook.createCellStyle();
		cs.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);

		// 폰트 설정
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short) 10);
		font.setFontName("맑은 고딕");
		cs.setFont(font);

		// 셀 병합
		// (start Low, endLow, start column, end column)
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, 2));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 3, 6));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 7, 8));

		String tab[] = { "No.", "Title", "Data", "Date", "Author" };
		int tabsSlect[] = { 0, 1, 3, 7, 9 };
		// 셀 크기 - 병합하지 않은 전체 cell 수만 큼 크기 지정
		int tabSize[] = { 1500, 1500, 1500, 1500, 1500, 1500, 1500, 1500, 1500 };
		// 너비 적용
		for (int i = 0; i < tabSize.length; i++) {
			sheet.setColumnWidth(i, tabSize[i]);
		}

		// crate 0 Low
		row = sheet.createRow((short) 0);
		for (int i = 0; i < tab.length; i++) {
			// 사용 할 셀 선택
			cell = row.createCell(tabsSlect[i]);
			// 셀 데이터 적용
			cell.setCellValue(tab[i]);
			// style 적용
			cell.setCellStyle(cs);
		}
		return true;
	}

	/**
	 * 
	 * Excel 데이터 생성
	 * 
	 * @Author SH
	 * @return boolean
	 * 
	 */
	public boolean excelDataLine() {
		try {
			// excel 데이터 추가
			for (int i = 2; i < 12; i++) {
				row = sheet.createRow((short) i);
				sheet.addMergedRegion(new CellRangeAddress(i, i, 1, 2));
				sheet.addMergedRegion(new CellRangeAddress(i, i, 3, 6));
				sheet.addMergedRegion(new CellRangeAddress(i, i, 7, 8));
				cell = row.createCell(0);
				cell.setCellStyle(cs);
				cell.setCellValue(i - 1);
				cell = row.createCell(1);
				cell.setCellStyle(cs);
				cell.setCellValue("title" + (i - 1));
				cell = row.createCell(3);
				cell.setCellStyle(cs);
				cell.setCellValue("Data" + (i - 1));
				cell = row.createCell(7);
				cell.setCellStyle(cs);
				cell.setCellValue("Date" + (i - 1));
				cs.setWrapText(true);
				cell.setCellStyle(cs);
				cell = row.createCell(9);
				cell.setCellValue("Author" + (i - 1));
				cs.setWrapText(true);
				cell.setCellStyle(cs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
