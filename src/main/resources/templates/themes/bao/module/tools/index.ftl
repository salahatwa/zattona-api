<#include "../macro.ftl">
<#include "kitboxmenu.ftl"/>
<@layout title="Toolkit | ${options.blog_title!} " keywords="${meta_keywords!}" description="${meta_description!}">
    <div class="developer-toolkit-banner">
        <div class="container">
            <div class="row">
                <div class="col-md-4 developer-toolkit-banner-left"></div>
                <div class="col-md-8 text-center align-self-center">
                    <h1>Developer Toolbox</h1>
                    <h3 style="color: #6c757d!important;">Developer Toolkit</h3>
                    <p>Satwa："If a worker wants to do his job well, he must first sharpen his tools."</p>
                    <p>Use tools to optimize your learning and work efficiency, leaving time for better things.</p>
                </div>
            </div>
        </div>
    </div>
    <div class="container" style="padding-top: 50px;">
        <#if KitBoxMenus?? && (KitBoxMenus?size > 0)>
            <#list KitBoxMenus as menu>
                <h4>${menu.title}</h4>
                <div class="row mb-5">
                    <#if menu.links?? && (menu.links?size > 0)>
                        <#list menu.links as link>
                            <div class="col-md-3 mb-2">
                                <div class="card">
                                    <div class="card-body">
                                        <a class="stretched-link" href="${link.href}" style="color: #000000;">
                                            <h6>${link.text}</h6>
                                        </a>
                                        <div style="font-size:12px;color: #6c757d!important;">
                                            ${link.rel!}
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </#list>
                    </#if>
                </div>
            </#list>
        </#if>
    </div>
</@layout>