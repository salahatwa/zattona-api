package run.halo.app.service.core;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class RestUtil {

	public static String getValueByKey(String json, String key) {
		DocumentContext jsonContext = JsonPath.parse(json);
		Object value = jsonContext.read(key);
		return value.toString();
	}
}
