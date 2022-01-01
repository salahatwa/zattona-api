<#if isAutoImport?exists && isAutoImport==true>
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;
</#if>

/**
 * @description ${classInfo.classComment}
 * @author ${authorName}
 * @date ${.now?string('yyyy-MM-dd')}
 */
@Mapper
@Repository
public interface ${classInfo.className}Mapper {

    /**
    * Add
    * @author ${authorName}
    * @date ${.now?string('yyyy/MM/dd')}
    **/
    int insert(${classInfo.className} ${classInfo.className?uncap_first});

    /**
    * Delete
    * @author ${authorName}
    * @date ${.now?string('yyyy/MM/dd')}
    **/
    int delete(int id);

    /**
    * Update
    * @author ${authorName}
    * @date ${.now?string('yyyy/MM/dd')}
    **/
    int update(${classInfo.className} ${classInfo.className?uncap_first});

    /**
    * Query Query based on the primary key id
    * @author ${authorName}
    * @date ${.now?string('yyyy/MM/dd')}
    **/
    ${classInfo.className} load(int id);

    /**
    * Query Paging query
    * @author ${authorName}
    * @date ${.now?string('yyyy/MM/dd')}
    **/
    List<${classInfo.className}> pageList(int offset,int pagesize);

    /**
    * Query Paging query count
    * @author ${authorName}
    * @date ${.now?string('yyyy/MM/dd')}
    **/
    int pageListCount(int offset,int pagesize);

}
