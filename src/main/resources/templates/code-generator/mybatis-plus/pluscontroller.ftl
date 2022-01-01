<#if isWithPackage?exists && isWithPackage==true>package ${packageName}.controller;</#if>
<#if isAutoImport?exists && isAutoImport==true>
import com.alibaba.fastjson.JSON;
import ${packageName}.entity.${classInfo.className};
import ${packageName}.mapper.${classInfo.className}Mapper;
import ${packageName}.util.ReturnT;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.Map;
</#if>
/**
* @description ${classInfo.classComment}Controller
* @author ${authorName}
* @date ${.now?string('yyyy-MM-dd')}
*/
@Slf4j
@RestController
@RequestMapping("/${classInfo.className?uncap_first}")
public class ${classInfo.className}Controller {

    @Autowired
    private ${classInfo.className}Mapper ${classInfo.className?uncap_first}Mapper;

    /**
    * Add Or edit
    */
    @PostMapping("/save")
    public Object save(@RequestBody ${classInfo.className} ${classInfo.className?uncap_first}){
        log.info("${classInfo.className?uncap_first}:"+JSON.toJSONString(${classInfo.className?uncap_first}));
        ${classInfo.className} old${classInfo.className} = ${classInfo.className?uncap_first}Mapper.selectOne(new QueryWrapper<${classInfo.className}>().eq("${classInfo.className?uncap_first}_id",${classInfo.className?uncap_first}.get${classInfo.className}Id()));
        ${classInfo.className?uncap_first}.setUpdateTime(new Date());
        if(old${classInfo.className}!=null){
            ${classInfo.className?uncap_first}Mapper.updateById(${classInfo.className?uncap_first});
        }else{
        if(${classInfo.className?uncap_first}Mapper.selectOne(new QueryWrapper<${classInfo.className}>().eq("${classInfo.className?uncap_first}_name",${classInfo.className?uncap_first}.get${classInfo.className}Name()))!=null){
            return ${returnUtilFailure}("Save failed, duplicate name");
        }
        ${classInfo.className?uncap_first}.setCreateTime(new Date());
        ${classInfo.className?uncap_first}Mapper.insert(${classInfo.className?uncap_first});
        }
        return ${returnUtilSuccess}("Saved successfully");
    }

    /**
    * Delete
    */
    @PostMapping("/delete")
    public Object delete(int id){
    ${classInfo.className} ${classInfo.className?uncap_first} = ${classInfo.className?uncap_first}Mapper.selectOne(new QueryWrapper<${classInfo.className}>().eq("${classInfo.className?uncap_first}_id",id));
        if(${classInfo.className?uncap_first}!=null){
            ${classInfo.className?uncap_first}Mapper.deleteById(id);
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
    ${classInfo.className} ${classInfo.className?uncap_first} = ${classInfo.className?uncap_first}Mapper.selectOne(new QueryWrapper<${classInfo.className}>().eq("${classInfo.className?uncap_first}_id",id));
        if(${classInfo.className?uncap_first}!=null){
            return ${returnUtilSuccess}(${classInfo.className?uncap_first});
        }else{
            return ${returnUtilFailure}("The object was not found");
        }
    }

    /**
    * Automatic pagination query
    */
    @PostMapping("/list")
    public Object list(String searchParams,
    @RequestParam(required = false, defaultValue = "0") int page,
    @RequestParam(required = false, defaultValue = "10") int limit) {
        log.info("page:"+page+"-limit:"+limit+"-json:"+ JSON.toJSONString(searchParams));
        //Page builder
        Page<${classInfo.className}> buildPage = new Page<${classInfo.className}>(page,limit);
        //Conditional Constructor
        QueryWrapper<${classInfo.className}> queryWrapper = new QueryWrapper<${classInfo.className}>();
        if(StringUtils.isNotEmpty(searchParams)&&JSON.isValid(searchParams)) {
            ${classInfo.className} ${classInfo.className?uncap_first} = JSON.parseObject(searchParams, ${classInfo.className}.class);
            queryWrapper.eq(StringUtils.isNoneEmpty(${classInfo.className?uncap_first}.get${classInfo.className}Name()), "${classInfo.className?uncap_first}_name", ${classInfo.className?uncap_first}.get${classInfo.className}Name());
        }
        //Perform paging
        IPage<${classInfo.className}> pageList = ${classInfo.className?uncap_first}Mapper.selectPage(buildPage, queryWrapper);
        //Return result
        return ${returnUtil}.PAGE(pageList.getRecords(),pageList.getTotal());
    }
    /**
    * Manual paging query (use on demand)
    */
    /*@PostMapping("/list2")
    public Object list2(String searchParams,
    @RequestParam(required = false, defaultValue = "0") int page,
    @RequestParam(required = false, defaultValue = "10") int limit) {
        log.info("searchParams:"+ JSON.toJSONString(searchParams));
        //General mode
        ${classInfo.className} queryParamDTO = JSON.parseObject(searchParams, ${classInfo.className}.class);
        //Dedicated DTO mode
        //QueryParamDTO queryParamDTO = JSON.parseObject(searchParams, QueryParamDTO.class);
        //queryParamDTO.setPage((page - 1)* limit);
        //queryParamDTO.setLimit(limit);
        //(page - 1) * limit, limit
        List<${classInfo.className}> itemList = ${classInfo.className?uncap_first}Mapper.pageAll(queryParamDTO,(page - 1)* limit,limit);
        Integer itemCount = ${classInfo.className?uncap_first}Mapper.countAll(queryParamDTO);
        //Return result
        return ${returnUtilSuccess}.PAGE(itemList,itemCount);
    }*/
    @GetMapping("/list")
    public ModelAndView listPage(){
        return new ModelAndView("${classInfo.className?uncap_first}-list");
    }

    @GetMapping("/edit")
    public ModelAndView editPage(int id){
        ${classInfo.className} ${classInfo.className?uncap_first} = ${classInfo.className?uncap_first}Mapper.selectOne(new QueryWrapper<${classInfo.className}>().eq("${classInfo.className?uncap_first}_id",id));
        return new ModelAndView("${classInfo.className?uncap_first}-edit","${classInfo.className?uncap_first}",${classInfo.className?uncap_first});
    }

    /**
    * Publish/Pause (please block if not needed)
    */
    @PostMapping("/publish")
    public Object publish(int id,Integer status){
        ${classInfo.className} ${classInfo.className?uncap_first} = ${classInfo.className?uncap_first}Mapper.selectOne(new QueryWrapper<${classInfo.className}>().eq("${classInfo.className?uncap_first}_id",id));
        if(${classInfo.className?uncap_first}!=null){
            ${classInfo.className?uncap_first}.setUpdateTime(new Date());
            ${classInfo.className?uncap_first}.setStatus(status);
            ${classInfo.className?uncap_first}Mapper.updateById(${classInfo.className?uncap_first});
            return ${returnUtilSuccess}((status==1)?"Published":"Suspended");
        }else if(status.equals(${classInfo.className?uncap_first}.getStatus())){
            return ${returnUtilFailure}("Incorrect status");
        }else{
            return ${returnUtilFailure}();
        }
    }

    /**
    * Execute (if not required, please block)
    */
    @PostMapping("/execute")
    public Object execute(){
        return ${returnUtilSuccess};
    }
}
}



