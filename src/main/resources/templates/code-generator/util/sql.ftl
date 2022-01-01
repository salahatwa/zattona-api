
<#if classInfo.fieldList?exists && classInfo.fieldList?size gt 0>
 #SQL select
    SELECT <#list classInfo.fieldList as fieldItem >t.${fieldItem.columnName}<#if fieldItem_has_next>,</#if></#list>
    FROM ${classInfo.tableName} t;

 #CSV fields name
    <#list classInfo.fieldList as fieldItem >${fieldItem.columnName}<#if fieldItem_has_next>,</#if></#list>
</#if>

<#if classInfo.fieldList?exists && classInfo.fieldList?size gt 0>
#LEFT JOIN
    SELECT
        *
    FROM
    ${classInfo.tableName} a
    LEFT JOIN ${classInfo.tableName} b
    ON a.${classInfo.tableName}_id=b.${classInfo.tableName}_id
    WHERE 1=1;
</#if>

<#if classInfo.fieldList?exists && classInfo.fieldList?size gt 0>
#INSERT INTO
    INSERT INTO ${classInfo.tableName} ( <#list classInfo.fieldList as fieldItem >${fieldItem.columnName}<#if fieldItem_has_next>,</#if></#list> )
    VALUES
    (
    <#list classInfo.fieldList as fieldItem >''<#if fieldItem_has_next>,</#if></#list>
    );
</#if>


<#if classInfo.fieldList?exists && classInfo.fieldList?size gt 0>
#Association update
    UPDATE ${classInfo.tableName} a
    JOIN ${classInfo.tableName}_join b ON a.${classInfo.tableName}_id = b.${classInfo.tableName}_id
    SET <#list classInfo.fieldList as fieldItem > a.${fieldItem.columnName} = b.${fieldItem.columnName}<#if fieldItem_has_next>,</#if> </#list>
    WHERE
    b.${classInfo.tableName}_id IS NOT NULL;

    UPDATE ${classInfo.tableName} a,${classInfo.tableName}_join b
    SET <#list classInfo.fieldList as fieldItem > a.${fieldItem.columnName} = b.${fieldItem.columnName}<#if fieldItem_has_next>,</#if> </#list>
    WHERE a.${classInfo.tableName}_id = b.${classInfo.tableName}_id;

#Ordinary update
    UPDATE ${classInfo.tableName}
    SET
    <#list classInfo.fieldList as fieldItem >
        ${fieldItem.columnName} = ''<#if fieldItem_has_next>,</#if>
    </#list>
    WHERE
    <#list classInfo.fieldList as fieldItem >
        ${fieldItem.columnName} = ''<#if fieldItem_has_next>,</#if>
    </#list>;
</#if>



<#if classInfo.fieldList?exists && classInfo.fieldList?size gt 0>
#Association delete
    delete a from ${classInfo.tableName}_del as a inner join ${classInfo.tableName} as b
    where a.${classInfo.tableName}_id=b.${classInfo.tableName}_id;

#Normal delete
    DELETE
    FROM
    ${classInfo.tableName}
    WHERE
    <#list classInfo.fieldList as fieldItem >
        ${fieldItem.columnName} = ''<#if fieldItem_has_next>,</#if>
    </#list>;

</#if>


