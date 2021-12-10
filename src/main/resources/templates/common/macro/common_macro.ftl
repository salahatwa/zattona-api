<#ftl strip_whitespace=true>

<#-- 统计代码 -->
<#macro statistics>
    ${options.blog_statistics_code!}
</#macro>

<#-- 页脚信息 -->
<#macro footer_info>
    ${options.blog_footer_info!}
</#macro>

<#macro custom_head>
    ${options.blog_custom_head!}
</#macro>

<#-- favicon -->
<#macro favicon>
    <#if options.blog_favicon?? && options.blog_favicon!=''>
        <link rel="shortcut icon" type="images/x-icon" href="${options.blog_favicon!}">
    </#if>
</#macro>

<#-- 站点验证代码，已废弃 -->
<#macro verification>

</#macro>

<#-- Time formatting a few... ago -->
<#macro timeline datetime=.now>
    <#assign ct = (.now?long-datetime?long)/1000>
    <#if ct gte 31104000>${(ct/31104000)?int} Years ago
        <#t><#elseif ct gte 2592000>${(ct/2592000)?int} Months ago
        <#t><#elseif ct gte 86400*2>${(ct/86400)?int} Days ago
        <#t><#elseif ct gte 86400>yesterday
        <#t><#elseif ct gte 3600>${(ct/3600)?int} Hour ago
        <#t><#elseif ct gte 60>${(ct/60)?int} Minutes ago
        <#t><#elseif ct gt 0>${ct?int} Seconds ago
        <#t><#else>Just
    </#if>
</#macro>

<#macro globalHeader>
    <#if options.seo_spider_disabled!false>
        <meta name="robots" content="none">
    </#if>
    <meta name="generator" content="Genhub ${version!}"/>
    <@custom_head />
    <@verification />
    <@favicon />
</#macro>

<#macro globalFooter>
    <@footer_info />
    <@statistics />
</#macro>