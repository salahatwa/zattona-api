<#include "module/macro.ftl">
<@layout title="${settings.categories_title! 'Categories'} | ${options.blog_title!}" keywords="${options.seo_keywords!}" description="${options.seo_description!}">
    <main class="mx-auto" id="container">
        <header class="bg-cover post-cover">
            <#if settings.categories_patternimg?? && settings.categories_patternimg!=''>
                <div class="cover-bg">
                    <img src="${settings.categories_patternimg!}" class="z-auto"
                         alt="${settings.categories_title! 'Categories'}">
                </div>
            <#else>
                <div class="placeholder-bg">
                </div>
            </#if>
            <div class="cover-content flex justify-center">
                <!-- Cover content -->
                <div class="inner flex flex-col justify-center">
                    <p class="cover-title text-base md:text-4xl lg:text-4xl xl:text-5xl">${settings.categories_title! 'Categories'}</p>
                </div>
            </div>
        </header>
        <div class="container mx-auto px-4 mt-16 max-w-6xl tracking-wider md:leading-relaxed sm:leading-normal ct-container cn-pd content-container">
            <h2 class="w-full m-4 text-3xl">${settings.categories_title! 'Categories'}</h2>
            <div class="flex flex-row flex-wrap grid lg:grid-cols-3 md:grid-cols-2">
                <@categoryTag method="list">
                    <#if categories?? && categories?size gt 0>
                        <#list categories as category>
                            <a class="mt-2 mb-2 mr-2 lk-card-im card-item-vel block" href="${category.fullPath}" 
                               onfocus="this.blur();">
                                <div class="media">
                                    <div class="media-left">
                                        <figure class="image is-64x64">
                                            <#if category.thumbnail?? && category.thumbnail != ''>
                                                <img src="${category.thumbnail}" alt="${category.name}">
                                            <#elseif settings.links_placeholder?? && settings.links_placeholder != ''>
                                                <img src="${settings.links_placeholder}"
                                                     alt="${category.name}">
                                            <#else>
                                                <img src="https://cdn.jsdelivr.net/gh/xzzai/static@v1.0.1/placeholder.jpg"
                                                     alt="${category.name}">
                                            </#if>
                                        </figure>
                                    </div>
                                    <div class="media-content">
                                        <p class="lk-title">${category.name}</p>
                                        <p class="lk-desc">${category.description}</p>
                                    </div>
                                </div>
                            </a>
                        </#list>
                    </#if>
                </@categoryTag>
            </div>

        </div>
    </main>
</@layout>
