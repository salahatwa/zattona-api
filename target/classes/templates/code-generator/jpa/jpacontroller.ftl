<#if isWithPackage?exists && isWithPackage==true>package ${packageName}.controller;</#if>
<#if isAutoImport?exists && isAutoImport==true>
import ${packageName}.entity.${classInfo.className};
import ${packageName}.repository.${classInfo.className}Repository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    private ${classInfo.className}Repository ${classInfo.className?uncap_first}Repository;

    /**
    * Add or edit
    */
    @PostMapping("/save")
    public Object save(${classInfo.className} ${classInfo.className?uncap_first}){
        return ${classInfo.className?uncap_first}Repository.save(${classInfo.className?uncap_first});
    }

    /**
    * delete
    */
    @PostMapping("/delete")
    public Object delete(int id){
        Optional<${classInfo.className}> ${classInfo.className?uncap_first}=${classInfo.className?uncap_first}Repository.findById(id);
        if(${classInfo.className?uncap_first}.isPresent()){
            ${classInfo.className?uncap_first}Repository.deleteById(id);
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
        Optional<${classInfo.className}> ${classInfo.className?uncap_first}=${classInfo.className?uncap_first}Repository.findById(id);
        if(${classInfo.className?uncap_first}.isPresent()){
            return ${returnUtilSuccess}(${classInfo.className?uncap_first}.get());
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

            //To create a matcher, if you need query conditions, please modify the code here
            ExampleMatcher matcher = ExampleMatcher.matchingAll();

            //Create instance
            Example<${classInfo.className}> example = Example.of(${classInfo.className?uncap_first}, matcher);
            //Paging structure
            Pageable pageable = PageRequest.of(pageNumber,pageSize);

            return ${classInfo.className?uncap_first}Repository.findAll(example, pageable);
    }

}
