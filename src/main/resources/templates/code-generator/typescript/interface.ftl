/**
 * @description ${classInfo.classComment}
 * @author ${authorName}
 * @date ${.now?string('yyyy-MM-dd')}
 */
export interface ${classInfo.className} {

<#if classInfo.fieldList?exists && classInfo.fieldList?size gt 0>

<#list classInfo.fieldList as fieldItem >

    <#if isComment?exists && isComment==true>
   /**
    * ${fieldItem.fieldComment}
    */</#if>
    <#if isComment?exists && isComment==true>@Column(name="${fieldItem.columnName}")</#if>
    ${fieldItem.fieldName}: ${fieldItem.fieldClass};

</#list>

 
</#if>

}
