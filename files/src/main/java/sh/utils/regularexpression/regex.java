package sh.utils.regularexpression;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class regex {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * @Author SH
	 * @param 데이터
	 * @return 졍규표현식을 제거한 데이터
	 */
	public String removeRegex(String data) {
		logger.info(data);
		String txt;
		try {
			txt = IOUtils.toString(getClass().getResourceAsStream("/regex_remove.txt"), "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
			return data;
		}
		String rgx[] = txt.split("\n");
		for (String rg : rgx) {
			Pattern Script = Pattern.compile(rg);
			Matcher Mat = Script.matcher(data);

			while (Mat.find())
				data = data.replaceAll(Mat.group(), "");
			
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
		String txt;
		try {
			txt = IOUtils.toString(getClass().getResourceAsStream("/regex_print.txt"), "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		String rgx[] = txt.split("\n");
		for (String rg : rgx) {
			Pattern Script = Pattern.compile(rg);
			Matcher Mat = Script.matcher(data);

			while (Mat.find()) {
				list.add(Mat.group());
				logger.info(Mat.group());
			}
		}
		return list;
	}

}
