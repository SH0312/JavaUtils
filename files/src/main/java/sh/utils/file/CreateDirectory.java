package sh.utils.file;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateDirectory {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * @Author SH
	 * @param directoryPath
	 * @return boolean
	 */
	public boolean createDirectory(String path) {
		// 경로가 파일까지 존재시 제거
		if (path.contains(".")) {
			path = (path.contains("/")) ? path.substring(0, path.lastIndexOf("/"))
					: path.substring(0, path.lastIndexOf(File.separator));
		}
		File createDirectory = new File(path);
		if (!createDirectory.exists()) {
			createDirectory.mkdirs();
			logger.info("Directory create succeeded");
			return true;
		} else {
			logger.info("Directory already exists");
			return false;
		}
	}
}
