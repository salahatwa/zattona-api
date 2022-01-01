package run.halo.app.generator.entity;

import lombok.Data;

import java.util.List;

/**
 * class info
 *
 * @author ssatwa
 */
@Data
public class ClassInfo {

    private String tableName;
    private String originTableName;
    private String className;
    private String classComment;
    private List<FieldInfo> fieldList;
    
    

}
