package run.halo.app.generator.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import run.halo.app.generator.entity.ClassInfo;
import run.halo.app.generator.entity.FieldInfo;
import run.halo.app.generator.entity.ParamInfo;

/**
 * Generator Util
 *
 * @author ssatwa
 */
public class TableParseUtil {

	/**
	 * Parse JSON to generate class information
	 *
	 * @param paramInfo
	 * @return
	 */
	public static List<ClassInfo> processJsonToClassInfo(ParamInfo paramInfo) {

		List<ClassInfo> results = new ArrayList<ClassInfo>();
		ClassInfo codeJavaInfo = new ClassInfo();
		codeJavaInfo.setTableName("JsonDto");
		codeJavaInfo.setClassName("JsonDto");
		codeJavaInfo.setClassComment("JsonDto");

		// support children json if forget to add '{' in front of json
		if (paramInfo.getInput().trim().startsWith("\"")) {
			paramInfo.setInput("{" + paramInfo.getInput());
		}

		if (JSON.isValid(paramInfo.getInput())) {
			if (paramInfo.getInput().trim().startsWith("{")) {
				JSONObject jsonObject = JSONObject.parseObject(paramInfo.getInput().trim());

				results = processJson(jsonObject);

				return results;
			} else if (paramInfo.getInput().trim().startsWith("[")) {
				JSONArray jsonArray = JSONArray.parseArray(paramInfo.getInput().trim());
				results = processJson(jsonArray.getJSONObject(0));
				return results;
			}
		} else {
			System.out.println("NOT VALID..");
			return results;
		}
		return results;
	}

	public static List<FieldInfo> processJsonObjectToFieldList(JSONObject jsonObject, List<ClassInfo> allClassess) {
		// field List
		List<FieldInfo> fieldList = new ArrayList<FieldInfo>();
		jsonObject.keySet().stream().forEach(jsonField -> {
			Object fieldObj = jsonObject.get(jsonField);
			if (fieldObj instanceof JSONArray) {
				JSONArray arr = jsonObject.getJSONArray(jsonField);
				for (int i = 0; i < arr.size(); i++) {
					Object arrayObject = arr.get(i);

					if (arrayObject instanceof JSONObject) {
						ClassInfo cls = processJsonObjectToClass2(jsonField, (JSONObject) arrayObject, allClassess);
						allClassess.add(cls);

						FieldInfo fieldInfo = new FieldInfo();
						fieldInfo.setFieldName(cls.getClassName());
						fieldInfo.setColumnName(jsonField);
						fieldInfo.setFieldClass(List.class.getSimpleName() + "<" + cls.getClassName() + ">");
						fieldInfo.setFieldComment("List<Parent>:" + jsonField);
						fieldList.add(fieldInfo);
						break;
					} else if (arrayObject instanceof JSONArray) {
						processJsonObjectToClass(jsonField, (JSONObject) arrayObject, allClassess);
					} else {
						FieldInfo fieldInfo2 = new FieldInfo();
						fieldInfo2.setFieldName(jsonField);
						fieldInfo2.setColumnName(jsonField);
						if (Objects.isNull(fieldObj))
							fieldInfo2.setFieldClass(List.class.getSimpleName());
						else
							fieldInfo2.setFieldClass(
									List.class.getSimpleName() + "<" + arrayObject.getClass().getSimpleName() + ">");
						fieldInfo2.setFieldComment("children:" + jsonField);
						fieldList.add(fieldInfo2);
						break;
					}

				}
				;
			} else if (fieldObj instanceof JSONObject) {
				ClassInfo cls = processJsonObjectToClass(jsonField, jsonObject, allClassess);
				allClassess.add(cls);

				FieldInfo fieldInfo = new FieldInfo();
				fieldInfo.setFieldName(cls.getClassName());
				fieldInfo.setColumnName(jsonField);
				fieldInfo.setFieldClass(cls.getClassName());
				fieldInfo.setFieldComment("Parent:" + jsonField);
				fieldList.add(fieldInfo);
			}

			else {

				FieldInfo fieldInfo = new FieldInfo();
				fieldInfo.setFieldName(jsonField);
				fieldInfo.setColumnName(jsonField);
				if (Objects.isNull(fieldObj))
					fieldInfo.setFieldClass(String.class.getSimpleName());
				else
					fieldInfo.setFieldClass(fieldObj.getClass().getSimpleName());
				fieldInfo.setFieldComment("Field:" + jsonField);
				fieldList.add(fieldInfo);
			}
		});
		if (fieldList.size() < 1) {
			throw new CodeGenerateException("JSON解析失败");
		}
		return fieldList;
	}

	private static List<ClassInfo> processJson(JSONObject jsonObject) {
		List<ClassInfo> allClassess = new ArrayList<ClassInfo>();
		ClassInfo codeJavaInfo = new ClassInfo();
		codeJavaInfo.setTableName("JsonDto");
		codeJavaInfo.setClassName("JsonDto");
		codeJavaInfo.setClassComment("JsonDto");
		codeJavaInfo.setFieldList(processJsonObjectToFieldList(jsonObject, allClassess));
		allClassess.add(codeJavaInfo);
		return allClassess;
	}

	private static ClassInfo processJsonObjectToClass2(String jsonField, JSONObject jsonObject,
			List<ClassInfo> allClassess) {
		ClassInfo codeJavaInfo = new ClassInfo();
		codeJavaInfo.setTableName(jsonField);
		codeJavaInfo.setClassName(jsonField);
		codeJavaInfo.setClassComment(jsonField);
		codeJavaInfo.setFieldList(processJsonObjectToFieldList(jsonObject, allClassess));
		return codeJavaInfo;
	}

	private static ClassInfo processJsonObjectToClass(String jsonField, JSONObject jsonObject,
			List<ClassInfo> allClassess) {
		ClassInfo codeJavaInfo = new ClassInfo();
		codeJavaInfo.setTableName(jsonField);
		codeJavaInfo.setClassName(jsonField);
		codeJavaInfo.setClassComment(jsonField);
		codeJavaInfo.setFieldList(processJsonObjectToFieldList((JSONObject) jsonObject.get(jsonField), allClassess));
		return codeJavaInfo;
	}

}
