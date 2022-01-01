<#if isAutoImport?exists && isAutoImport==true>
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
</#if>
/**
 * @description ${classInfo.classComment}
 * @author ${authorName}
 * @date ${.now?string('yyyy-MM-dd')}
 */
@RestController
@RequestMapping("/${classInfo.className?uncap_first}")
public class ${classInfo.className}Controller {

    @Autowired
    private SQLManager sqlManager;

    /**
    * Add or edit
    */
    @PostMapping("/save")
    public Object save(${classInfo.className} ${classInfo.className?uncap_first}){
        ${classInfo.className} ${classInfo.className?uncap_first}=sqlManager.unique(${classInfo.className}.class,${classInfo.className?uncap_first}.getId());
        if(${classInfo.className?uncap_first}!=null){
            sqlManager.updateById(${classInfo.className?uncap_first});
            return ${returnUtilSuccess}("Edit successfully");
        }else{
            sqlManager.insert(${classInfo.className?uncap_first});
            return ${returnUtilFailure}("Saved successfully");
        }
    }

    /**
    * delete
    */
    @PostMapping("/delete")
    public Object delete(int id){
        ${classInfo.className} ${classInfo.className?uncap_first}=sqlManager.unique(${classInfo.className}.class,id);
        if(${classInfo.className?uncap_first}!=null){
            sqlManager.deleteById(id);
            return ${returnUtilSuccess}("successfully deleted");
        }else{
            return ${returnUtilFailure}("The object was not found");
        }
    }

    /**
    * Inquire
    */
    @PostMapping("/find")
    public Object find(int id){
        ${classInfo.className} ${classInfo.className?uncap_first}=sqlManager.unique(${classInfo.className}.class,id);
        if(${classInfo.className?uncap_first}!=null){
            return ${returnUtilSuccess}(${classInfo.className?uncap_first});
        }else{
            return ${returnUtilFailure}("The object was not found");
        }
    }

    /**
    * Paging query
    */
    @PostMapping("/list")
    public Object list(${classInfo.className} ${classInfo.className?uncap_first},
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {
            List<${classInfo.className}> list = sqlManager.query(${classInfo.className}.class).select();
            return ${returnUtilSuccess}(list);
    }

}
