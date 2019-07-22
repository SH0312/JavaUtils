package sh.utils.restApi;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

public class RestApiCall {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		RestApiCall api = new RestApiCall();
		String getUrl = "";
		String postUrl = "";

		Map<String, String> param = new HashMap<String, String>();
		param.put("k1", "v1");
		param.put("k2", "v2");

		api.getFormat(getUrl);
		api.postFormat(postUrl, param);

	}

	public void postFormat(String postUrl, Map<String, String> param) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(postUrl);
		httpPost.setHeader("Content-Type", "application/json");

		Gson gson = new Gson();
		String jsonValue = gson.toJson(param);

		try {
			httpPost.setEntity(new StringEntity(jsonValue, "UTF-8"));
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity respEntity = response.getEntity();

			if (respEntity != null) {
				String returnData = EntityUtils.toString(respEntity);
				logger.info(returnData);
			}

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void getFormat(String getUrl) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(getUrl);
		httpGet.setHeader("Content-Type", "application/json");

		try {
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity respEntity = response.getEntity();

			if (respEntity != null) {
				String returnData = EntityUtils.toString(respEntity);
				logger.info(returnData);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}