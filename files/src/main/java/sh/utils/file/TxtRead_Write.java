package sh.utils.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TxtRead_Write {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * @ @Author SH
	 * @param filePath
	 * @return txt 데이터
	 */
	public String textRead(String path) {
		File file = new File(path);
		
		//file check
		if (!file.isFile()) {
			logger.info("File does not exist");
			return null;
		}
		StringBuffer sb = new StringBuffer();
		FileReader filereader;
		try {
			filereader = new FileReader(file);
			BufferedReader bufReader = new BufferedReader(filereader);
			String line = "";
			while ((line = bufReader.readLine()) != null) {
				System.out.println(line);
				sb.append(line);
			}
			bufReader.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	/**
	 * @ @Author SH
	 * @param file Path<br>save Data
	 * @return boolean
	 */
	public boolean textWrite(String path, List<String> data) {
		  try{
	            //Directory Path Check
	            CreateDirectory cd = new CreateDirectory();
	            cd.createDirectory(path);
			  
	            //파일 객체 생성
	            File file = new File(path);
	            FileWriter fw = new FileWriter(file);
	            BufferedWriter bw = new BufferedWriter(fw);
	            
	            if(file.isFile() && file.canWrite()){
	            	for(String write : data) {
	            		bw.write(write);
	            		bw.newLine();
	            	}
	            	bw.close();
	            }
	            
	        }catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
		return true;
	}
}
