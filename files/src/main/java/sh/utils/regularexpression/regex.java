package sh.utils.regularexpression;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class regex {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private final List<regexModel> regexPrintList = new ArrayList<regexModel>();
	private final List<regexModel> regexRemoveList = new ArrayList<regexModel>();

	/**
	 * @Author SH
	 * @param
	 * @return 졍규표현식 사전 로드
	 */
	public regex() {
		try {
			File printFile = null;
			URL printResource = regex.class.getResource("/regex_print.txt");
			printFile = Paths.get(printResource.toURI()).toFile();
			List<String> printList = FileUtils.readLines(printFile, "UTF-8");
			for (String reg : printList) {
				Pattern pattern = Pattern.compile(reg);
				regexPrintList.add(new regexModel(pattern));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {

			File removeFile = null;
			URL removeResource = regex.class.getResource("/regex_remove.txt");
				removeFile = Paths.get(removeResource.toURI()).toFile();
			List<String> removeLlist = FileUtils.readLines(removeFile, "UTF-8");
			for (String reg : removeLlist) {
				Pattern pattern = Pattern.compile(reg);
				regexRemoveList.add(new regexModel(pattern));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @Author SH
	 * @param 데이터
	 * @return 졍규표현식을 제거한 데이터
	 */
	public String removeRegex(String data) {
		logger.info(data);

		for(regexModel regex : regexPrintList) {
			Matcher matcher = regex.getPattern().matcher(data);
			while (matcher.find())
				data = data.replaceAll(matcher.group(), "");
			
			logger.info(data);
		}
		
		return data;
	}

	/**
	 * @Author SH
	 * @param 데이터
	 * @return 졍규표현식에 해당하는 데이터 리스트
	 */
	public List<String> printRegex(String data) {
		List<String> list = new ArrayList<String>();
		logger.info(data);
		
		for(regexModel regex : regexPrintList) {
			Matcher matcher = regex.getPattern().matcher(data);
			while (matcher.find()) {
				list.add(matcher.group());
				logger.info(matcher.group());
			}
		}
		
		return list;
	}

}
