package sh.utils.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileListing {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * @Author SH
	 * @param path
	 * @param format
	 * @return List - String = filePath
	 */
	public List<String> directoryFileRead(String path, String format) {

		File dir = new File(path);
		List<String> filePath = new ArrayList<String>();

		// param(dirm TrueFileFilter.INSTANCE, FalseFileFilter.INSTANCE - 해당 경로 만 검색
		// param(dirm TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE - 경로 내의 디렉토리까지 검색
		List<File> files = (List<File>) FileUtils.listFiles(dir, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);

		for (File file : files) {
			if (file.getName().substring(file.getName().lastIndexOf(".") + 1).equalsIgnoreCase(format)) {
				filePath.add(file.getAbsolutePath());
				logger.info("file : " + file.getAbsolutePath());
			}
		}
		return filePath;
	}
}