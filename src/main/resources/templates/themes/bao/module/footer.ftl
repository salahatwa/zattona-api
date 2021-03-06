<footer class="footer mt-8">
    <div class="inner container mx-auto flex flex-row lg:justify-between  md:justify-center items-center grid lg:grid-cols-2 md:grid-cols-1">
        <#-- Social information begin -->
        <div class="offsite-links flex flex-row justify-center flex-wrap">
            <#if settings.sina??>
                <a href="${settings.sina!}" class="circle pad-10" target="_blank" rel="noopener">
                    <span class="iconfont icon-weibo"> </span>
                </a>
            </#if>
            <#if settings.qq??>
                <a href="//wpa.qq.com/msgrd?v=3&uin=${settings.qq!}&site=qq&menu=yes" class="circle pad-10"
                   target="_blank" rel="noopener" title="QQ Chat">
                    <span class="iconfont icon-tencentqq"> </span>
                </a>
            </#if>
            <#if settings.telegram??>
                <a href="https://t.me/${settings.telegram!}" class="circle pad-10" target="_blank" rel="noopener"
                   title="Telegram Chat">
                    <span class="iconfont icon-telegram"> </span>
                </a>
            </#if>
            <#if settings.twitter??>
                <a href="${settings.twitter!}" class="circle pad-10" target="_blank" rel="noopener" title="Twitter">
                    <span class="iconfont icon-twitter"> </span>
                </a>
            </#if>
            <#if settings.github??>
                <a href="${settings.github!}" class="circle pad-10" target="_blank" rel="noopener" title="Github">
                    <span class="iconfont icon-github"> </span>
                </a>
            </#if>
            <#if settings.zhihu??>
                <a href="${settings.zhihu!}" class="circle pad-10" target="_blank" rel="noopener" title="More">
                    <span class="iconfont icon-hot"> </span>
                </a>
            </#if>
            <#if settings.mail??>
                <a href="mailto:${settings.mail!}" class="circle pad-10" target="_blank" rel="noopener" title="Email">
                    <span class="iconfont icon-email"> </span>
                </a>
            </#if>
            <#if settings.rss!true>
                <a href="${atom_url!}" class="circle pad-10" target="_blank" rel="noopener" title="RSS">
                    <span class="iconfont icon-rss"> </span>
                </a>
            </#if>
        </div>
        <#-- Social information end -->

        <div class="site-info flex flex-col justify-center">
            <@global.footer />
            <#if settings.Icp??>
                <p><a href="http://beian.miit.gov.cn" target="_blank" class="text-xs">${settings.Icp!}</a></p>
            </#if>
            <#if settings.PublicSecurityRecord??>
                <p style="display: inline-block;background-image: url(${theme_base!}/assets/images/beian.png);background-repeat: no-repeat;background-position: top left;background-size: contain;padding-left: 20px;">
                    <a href="http://www.beian.gov.cn" target="_blank"
                       class="text-xs">${settings.PublicSecurityRecord!}</a>
                </p>
            </#if>
            <!-- Please respect the author, please be sure to keep the copyright -->
            <div class="badges">
                <div class="github-badge">
                    <a style="color: #fff" rel="license" href="http://genhub.online" target="_blank">
                        <span class="badge-subject">Powered</span>
                        <span class="badge-value bg-blue">Genhub</span>
                    </a>
                </div>
                <!--
                <div class="github-badge">
                    <a style="color: #fff" rel="license" href="http://genhub.online"
                       target="_blank">
                        <span class="badge-subject">Licence</span>
                        <span class="badge-value bg-red">MIT</span>
                    </a>
                </div>
                -->

                <#if settings.TimeStatistics??>
                    <div class="github-badge">
                        <a style="color: #fff" rel="license" href="javascript:void(0)">
                            <span class="badge-subject">run</span>
                            <span class="badge-value bg-orange" id="span_dt_dt"></span>
                        </a>
                    </div>
                </#if>
                <#if settings.visit_statistics!false>
                    <div class="github-badge">
                        <a style="color: #fff" rel="license" href="javascript:void(0)">
                            <span class="badge-subject">user</span>
                            <span id="busuanzi_container_site_uv"><span class="badge-value bg-brightgreen" id="busuanzi_value_site_uv"></span></span>
                        </a>
                    </div>
                    <div class="github-badge">
                        <a style="color: #fff" rel="license" href="javascript:void(0)">
                            <span class="badge-subject">access</span>
                            <span id="busuanzi_container_site_pv"><span class="badge-value bg-blueviolet" id="busuanzi_value_site_pv"></span></span>
                        </a>
                    </div>
                </#if>
            </div>
        </div>
    </div>
</footer>

<#if settings.right_corner_circle!true>
    <#include "menu.ftl"/>
</#if>
<#if settings.Aplayer?? && settings.Aplayer != ''>
    <meting-js
        server="netease"
        type="playlist"
        fixed="true"
        id="${settings.Aplayer}">
    </meting-js>
</#if>

<#-- ?????? -->
<form method="get" action="/search">
    <div class="hidden search-box" id="searchBox">
        <div class="search-ipt ipt ipt-w">
            <div class="ipt-ct ct-bg search-container" id="searchInput">
                <label class="sh-lab">
                    <input class="sh-ipt" maxlength="30" name="keyword" id="searchBoxInput" spellcheck="false" placeholder="Search" autofocus="autofocus" autocomplete="off">
                    <i class="sh-icon iconfont icon-search"></i>
                </label>
                <a class="sh-exit iconfont icon-exit" onClick="toggleSearchBox()"></a>
            </div>
        </div>
    </div>
</form>
