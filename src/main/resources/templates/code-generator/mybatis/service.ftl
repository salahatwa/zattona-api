<#if isAutoImport?exists && isAutoImport==true>
import java.util.Map;
</#if>
/**
 * @description ${classInfo.classComment}
 * @author ${authorName}
 * @date ${.now?string('yyyy-MM-dd')}
 */
public interface ${classInfo.className}Service {

    /**
    * Add
    */
    public Object insert(${classInfo.className} ${classInfo.className?uncap_first});

    /**
    * Delete
    */
    public Object delete(int id);

    /**
    * Update
    */
    public Object update(${classInfo.className} ${classInfo.className?uncap_first});

    /**
    * Query based on the primary key id
    */
    public ${classInfo.className} load(int id);

    /**
    * Paging query
    */
    public Map<String,Object> pageList(int offset, int pagesize);

}
