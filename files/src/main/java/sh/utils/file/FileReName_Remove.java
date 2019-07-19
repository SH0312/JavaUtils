package sh.utils.file;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileReName_Remove {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	
	/**
	 * @Author SH
	 * @param fileName
	 * @param newFileName
	 * @return boolean
	 */
	public boolean fileReName(String fileName, String newFileName) {
		File file = new File(fileName);
		File reName = new File(newFileName);

		return file.renameTo(reName);
	}

	
	/**
	 * @Author SH
	 * @param fileName
	 * @return boolean
	 */
	public boolean fileRemove(String path) {

		File file = new File(path);

		if (file.exists()) {
			if (file.delete())
				return true;
			return false;

		} else {
			logger.info("File does not exist");
			return false;
		}
	}


}
