
<#if is_categories?? || is_tags ??>
    <#--标签云-->
    <script src="https://cdn.jsdelivr.net/gh/xzzai/static@master/js/jqcloud-1.0.4.min.js"></script>
    <script type='text/javascript'>
      $(function () {
        renderTagCloud();
        renderCategoryCloud();
      });
    </script>
</#if>

<#if settings.Aplayer?? && settings.Aplayer != ''>
    <script src="https://cdn.jsdelivr.net/npm/aplayer@1.10.1/dist/APlayer.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/meting@2/dist/Meting.min.js"></script>
</#if>

<#if settings.enabled_mathjax!true>
    <script defer src="https://cdn.jsdelivr.net/npm/katex@0.12.0/dist/katex.min.js"
            integrity="sha384-g7c+Jr9ZivxKLnZTDUhnkOnsh30B4H0rpLUpJ4jAIKs4fnJI+sEnkvrMWph2EDg4"
            crossorigin="anonymous"></script>
    <script defer src="https://cdn.jsdelivr.net/npm/katex@0.12.0/dist/contrib/auto-render.min.js"
            integrity="sha384-mll67QQFJfxn0IYznZYonOWZ644AWYC+Pt2cHqMaRhXVrursRwvLnLaebdGIlYNa"
            crossorigin="anonymous"></script>
</#if>


<script type="text/javascript">
  // console.clear();
  console.log("%c Some friends come from afar, not to mention.", "background:#24272A; color:#ffffff", "");
  <#if settings.github??>
    console.log("%c Github %c", "background:#24272A; color:#ffffff", "", "${settings.github!}");
  </#if>
</script>

<script type="text/javascript">
  ${settings.common_js!''}

  const loading = "${theme_base!}/source/images/loading-45.svg";
  var photoList = `${settings.card_random_cover_list!''}`;
  var photos = []
  if (photoList && photoList.trim() !== '') {
    if(photoList.endsWith(';')) {
      photoList = photoList.substring(0, photoList.length - 1);
    }
     photos = photoList.split(";");
  }
  // 默认收缩菜单
  var hideMenu = false;
  <#if settings.default_hide_menu!false> 
      hideMenu = true;
  </#if>

  var autoNightMode = false
  <#if settings.auto_night_mode!true>
    autoNightMode = true;
  </#if>
</script>

<#if settings.TimeStatistics??>
    <script type="text/javascript">
      // 建站时间统计
      function show_date_time() {
        if ($("#span_dt_dt").length > 0) {
          BirthDay = new Date("${settings.TimeStatistics!}");
          today = new Date();
          timeold = (today.getTime() - BirthDay.getTime());
          sectimeold = timeold / 1000;
          secondsold = Math.floor(sectimeold);
          msPerDay = 24 * 60 * 60 * 1000;
          e_daysold = timeold / msPerDay;
          daysold = Math.floor(e_daysold);
          e_hrsold = (e_daysold - daysold) * 24;
          hrsold = Math.floor(e_hrsold);
          e_minsold = (e_hrsold - hrsold) * 60;
          minsold = Math.floor((e_hrsold - hrsold) * 60);
          seconds = Math.floor((e_minsold - minsold) * 60);
          span_dt_dt.innerHTML = daysold + "Day" + hrsold + "Hour" + minsold + "Minute" + seconds + "Second";
        }
      }

      show_date_time();
    </script>
</#if>


<#-- Pjax 相关代码 -->
<#include "pjax.ftl">