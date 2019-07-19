package sh.utils.excel;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelRead {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 
	 * 수정 필요
	 * 
	 * @Author SH
	 * @param fileName
	 * @return boolean
	 * 
	 */
	public boolean exceRead(String file) {
		logger.info("file = "+ file);
		
		XSSFWorkbook readbook = null;
		XSSFSheet readsheet = null;
		XSSFRow readrow = null;
		try {
			FileInputStream files = new FileInputStream(file);
			readbook = new XSSFWorkbook(files);
			//row cell sheet 0번부터 사용
			
			//0번째 시트
			readsheet = readbook.getSheetAt(0);

			logger.info("Read Row Data Line = "+readsheet.getPhysicalNumberOfRows());
			for (int i = 1; i < readsheet.getPhysicalNumberOfRows(); i++) { 
				readrow = readsheet.getRow(i);
				XSSFCell cell = readrow.getCell(1);
				
				if(cell != null)
					logger.info(i+" == "+ cell.toString());
			}
			
		}catch (Exception e) {
			logger.info("해당 row cell 데이터 null");
			return false;
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		ExcelRead e = new ExcelRead();
		e.exceRead("C:\\Users\\user\\Desktop\\2019년 1월 연락망.xlsx");
	}

}
