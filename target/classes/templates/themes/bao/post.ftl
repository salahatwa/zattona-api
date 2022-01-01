<#include "module/macro.ftl">
<@layout title="${post.title!} | ${options.blog_title!} " keywords="${meta_keywords!}" description="${meta_description!}">
    <main class="mx-auto" id="container">
        <header class="bg-cover post-cover" id="postHeader">
            <#if post.thumbnail?? && post.thumbnail!=''>
                <div class="cover-bg">
                    <img src="${post.thumbnail!}" class="z-auto" alt="${post.title!}">
                </div>
            <#elseif settings.card_random_cover_list?? && settings.card_random_cover_list != ''>
                <div class="cover-bg">
                    <img src="${theme_base!}/source/images/loading.svg" class="z-auto lazyload img-random"  alt="${post.title}"/>
                </div>
            <#else>
                <div class="placeholder-bg">
                </div>
            </#if>
            <div class="cover-content post-cover-content flex justify-center">
                <!-- Cover content -->
                <div class="inner flex flex-col justify-center">
                    <p class="post-title cover-title text-base lg:text-4xl xl:text-5xl md:text-3xl">${post.title!}</p>
                    <div class="post-meta">
                        <div class="post-meta-wrap">
                            <img class="author-avatar"
                                 src="${user.avatar!}" alt="author-avatar"/>
                            <span class="post-author">${post.visits} Visits</span>
                            <time class="published"
                                  datetime="${post.createTime?string("yyyy-MM-dd")}">
                                Published: ${post.createTime?string("yyyy-MM-dd")}</time>
                            <#if "${post.createTime}" != "${post.editTime}">
                                <time class="published"
                                      datetime="${post.editTime?string("yyyy-MM-dd")}">
                                    Updated: ${post.editTime?string("yyyy-MM-dd")}</time>
                            </#if>
                        </div>
                        <div class="text-center post-categories">
                            <#if post.categories?? && post.categories?size gt 0>
                                <span class="iconfont icon-folder"> </span>
                            </#if>
                            <#if post.categories?? && post.categories?size gt 0>

                                <#list post.categories as category>
                                    <a href="${category.fullPath!}" class="post-category">
                                        · ${category.name!}
                                    </a>
                                </#list>
                            </#if>
                        </div>
                    </div>
                </div>
            </div>
        </header>
        <div class="article-content content-container" id="gallery-content">


            <div id="original" style="display: block">${post.formatContent}</div>
            <div class="container mx-auto px-4 md-content mt-8 max-w-6xl tracking-wider md:leading-relaxed sm:leading-normal cn-pd ct-container loading"
                 id="write" >
            </div>
            <div id="tocFlag"></div>
            <#if settings.post_toc!true>
                <aside id="toc" class="toc"></aside>
            </#if>
        </div>

        <div class="container mx-auto px-4 mt-8 max-w-6xl tracking-wider md:leading-relaxed sm:leading-normal  cn-pd ct-container coffee-tags">
                 <div class="column is-4-tablet is-1-desktop <#if is_sheet??>is-hidden-desktop</#if> is-1-widescreen is-1-fullhd  has-order-1 column-left is-hidden-touch <%= sticky_class(left) %>">
            <div class="level" style="margin: auto;width: fit-content;padding: 2px;">
                <#if settings.share_type?? && settings.share_type!=''>
                    <#include "module/share/${settings.share_type}.ftl">
                </#if>
            </div>
        </div>
            <#include "module/widget/sponsor.ftl">
        </div>


        <div class="container mx-auto px-4 mt-8 pb-4 max-w-6xl tracking-wider md:leading-relaxed sm:leading-normal cn-pd coffee-tags ct-container">
            <p class="flex flex-row justify-start flex-wrap">
                <#if post.tags?? && post.tags?size gt 0>
                    <#list post.tags as tag>
                        <a href="${tag.fullPath!}" class="post-tag mt-2 mb-2 mr-2">
                            #&nbsp;${tag.name!}
                        </a>
                    </#list>
                </#if>
            </p>
            <hr class="mt-4" style="background-color: rgba(96, 125, 139, .05); size: 2px;">
        </div>

        <!-- Previous and Next -->
        <#if settings.post_nepre!true>
            <#include "module/widget/prev_next_page.ftl">
        </#if>
        
        <div class="container mx-auto px-4 mt-16 max-w-6xl tracking-wider md:leading-relaxed sm:leading-normal ct-container cn-pd">
            <#include "module/comment.ftl">
            <#if is_post??>
                <@comment post,"post" />
            <#elseif is_sheet??>
                <@comment sheet,"sheet" />
            </#if>
        </div>
    </main>
    
</@layout>



