package run.halo.app.generator.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class GeneratedCode {

	private String tableName;
	private String originTableName;
	private String className;
	private String classComment;
	private List<TmpData> results=new ArrayList<GeneratedCode.TmpData>();
	
	@Data
	public static class TmpData{
		private String tmpName;
		private String tmpCode;
	}

}
