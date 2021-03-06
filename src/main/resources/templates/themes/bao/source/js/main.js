"use strict";

function enableLoading(id){
    $("#"+id).find("#loaderId").toggleClass("spinner");
    $("#"+id).attr("disabled", true);
}
  
  
function disableLoading(id){
    $("#"+id).find("#loaderId").removeClass("spinner");
    $("#"+id).attr("disabled", false);
}
  
  
function msg(content, type) {
    switch (type) {
        case "main":
            bs4pop.notice(content, {type: 'primary'});
            break;
        case "success":
            bs4pop.notice(content, {type: 'success'});
            break;
        case "warning":
            bs4pop.notice(content, {type: 'warning'});
            break;
        case "error":
            bs4pop.notice(content, {type: 'danger'});
            break;
        default:
            bs4pop.notice(content, {type: 'secondary'});
            break;
    }
}


function dayNightSwitch() {
  const key = 'nightMode';
  const daySwitch = $("#daySwitch");
  daySwitch.toggleClass("daySwitch");
  $(document.body).toggleClass("night");
  if (document.body.classList.contains('night')) {
    setLocalStorage(key, true);
  } else {
    setLocalStorage(key, false);
  }
}


function autoDayNight() {
  if (window.matchMedia && window.matchMedia(
      '(prefers-color-scheme: dark)').matches) {
    setLocalStorage('nightMode', false);
    checkNightMode()
  }
}

function checkNightMode() {
  const isNight = getLocalStorage('nightMode');
  if (isNight) {
    const daySwitch = $("#daySwitch");
    daySwitch.removeClass("daySwitch");
    $(document.body).addClass("night");
  }
}


function documentClickToc(target) {
  var moonToc = $('#moonToc')
  if (moonToc && moonToc.hasClass('mm-active')) {
    if (target.id && target.id === "moonToc") {
    } else if (target.id && target.id === "moonMenu") {
    } else if (target.id && target.classList.contains('icon-toc')) {
    } else if (target.classList.contains('moon-menu-button')) {
    } else if (target.classList.contains('moon-menu-text')) {
    } else {
      toggleSmallToc()
    }
  }
}


function getHashCode(str, caseSensitive) {
  if (!caseSensitive) {
    str = str.toLowerCase();
  }
  var hash = 1315423911, i, ch;
  for (i = str.length - 1; i >= 0; i--) {
    ch = str.charCodeAt(i);
    hash ^= ((hash << 5) + ch + (hash >> 2));
  }
  return (hash & 0x7FFFFFFF);
}


function dealContentToc() {
  if (getClientWidth() > 1359) {
    initToc();
    scrollTocFixed();
  } else {
    var smallToc = $('.moon-menu-item.icon-toc');
    if (smallToc) {
      smallToc.toggleClass('hidden');
      initMoonToc()
    }

    $(document).click(function (e) {
      var target = e.target;
      documentClickToc(target);
    });
  }
}


function handleNavMenu() {
  if (getClientWidth() <= 800) {
    $('#navHeader .nav').addClass('opacity-100').removeClass('opacity-0')
    return;
  }
  if(hideMenu) {
    document.addEventListener('scroll', handleScrollMenu, false);
  }
}

function handleScrollMenu() {
  
  if (getClientWidth() <= 800) {
    $('#navHeader .nav').addClass('opacity-100').removeClass('opacity-0')
    return;
  }
  var scrollTop = getScrollTop();
  if (scrollTop > 29) {
    $('#navHeader').addClass('nav-bg-fff')
    $('#navHeader .nav').addClass('opacity-100').removeClass('opacity-0')
    $('#navHeader .collapse-nav').hide()
    $('.collapse-burger').removeClass('open');
  } else {
    $('#navHeader').removeClass('nav-bg-fff')
    $('#navHeader .nav').removeClass('opacity-100').addClass('opacity-0')
    $('#navHeader .collapse-nav').show()
  }
}

function collapseNav() {
  var bugger = $('.collapse-burger');
  bugger.toggleClass('open');
  var nav = $('#navHeader .nav');

  nav.toggleClass('opacity-100')
  if (nav.hasClass('opacity-100')) {
    nav.removeClass('slideOut');
    nav.addClass('slideIn');
  } else {
    nav.addClass('slideOut');
    nav.removeClass('slideIn');
  }
}

// ??????
function loadGallery() {
  if (
      typeof Viewer !== "undefined" &&
      document.getElementById("gallery-content")
  ) {
    new Viewer(document.getElementById("gallery-content"), {
      toolbar: true
    });
  }

  gallery()
}


function scrollTocFixed() {
  document.addEventListener("scroll", tocScroll, false);
}

function removeScrollTocFixed() {
  document.removeEventListener('scroll', tocScroll, false);
}

function loadCodeLineNumber() {
  if (enableLineNumber) {
    $('.md-content  pre>code[class*="language-"]').each(function (i, block) {
      lineNumbersBlock(block);
    });
  }
}

function addCopyButton() {
	    $('.md-content  pre>code').each(function (i, block) {
	      var button = document.createElement("span")
	      button.className = "copy-code-btn iconfont icon-editor-preview"
	      block.parentNode.insertBefore(button, block)
	      button.addEventListener('click', function(e){
	        e.preventDefault()
	        copy(block)
	      });
	      
	    });
}

function copy(target) {
    var textArea = document.createElement('textarea');
    textArea.setAttribute('style','width:1px;border:0;opacity:0;');
    document.body.appendChild(textArea)
    textArea.value = target.innerText;
    textArea.select();
    document.execCommand('copy');
    document.body.removeChild(textArea);
    msg("copied success", "success");
}


function getScrollTop() {
  return document.documentElement.scrollTop || document.body.scrollTop;
}

function tocScroll(event) {
  var tocId = "#toc";
  var Obj = $("#tocFlag");

  //????????????YesNo??????
  if (Obj.length !== 1) {
    return false;
  }

  var tocFixed = $(tocId);
  var ObjTop = Obj.offset().top - $(window).height() * 0.5;

  // ?????????????????????????????????
  var scrollTop = getScrollTop();
  var postHeaderHeight = $("#postHeader").height();
  if (scrollTop > postHeaderHeight / 2) {
    tocFixed.show();
  } else {
    tocFixed.hide();
  }

  var tocEle = document.querySelector(tocId);
  if (!tocEle || !tocEle.getBoundingClientRect()) {
    return;
  }
  var tocHeight = tocEle.getBoundingClientRect().height;
  if (scrollTop > ObjTop - tocHeight * 0.5) {
    tocFixed.addClass("toc-right-fixed");
  } else {
    tocFixed.removeClass("toc-right-fixed");
  }

  // ????????????right
  tocEleRight();

  event.preventDefault();
}

function getClientWidth() {
  return document.body.clientWidth;
}

function initToc() {
  var headerEl = "h1,h2,h3,h4,h5,h6", //headers
      content = ".md-content"; //????????????
  tocbot.init({
    tocSelector: "#toc",
    contentSelector: content,
    headingSelector: headerEl,
    scrollSmooth: true,
    headingsOffset: 0 - $("#postHeader").height(),
    // scrollSmoothOffset: -80, // ?????????????????????????????????header
    hasInnerContainers: false,
  });

  $(".toc-link").each(function () {
    var linkContent = $(this).html();
    $(this).html('<span class="toc-link-dot"></span>' + linkContent);
  });
  // ????????????right
  tocEleRight();
}

function tocEleRight() {
  var screenWidth = getClientWidth();
  var tocEle = document.getElementById("toc");
  if (tocEle) {
    tocEle.style.left = (screenWidth - 800) / 2 + 820 + "px";
  }
}

function toggleAliPay() {
  $(".qrcode-wechat").addClass("hidden");
  $(".qrcode-alipay").toggleClass("hidden");
  $("#wechat i").removeClass("active-bg");
  $("#alipay i").toggleClass("active-bg");
}

function toggleWeChat() {
  $(".qrcode-alipay").addClass("hidden");
  $(".qrcode-wechat").toggleClass("hidden");
  $("#alipay i").removeClass("active-bg");
  $("#wechat i").toggleClass("active-bg");
}

function scollTo() {
  var postHeight = $("#homeHeader").height();
  window.scroll({top: postHeight, behavior: "smooth"});
}

function generateId() {
  const chars = `ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz`;
  let id = ``;
  for (let i = 0; i < 8; i++) {
    id += chars[Math.floor(Math.random() * chars.length)];
  }
  return id;
}

function getBilibili(width, height, id) {
  return `<iframe width="${width}" height="${height}" src="//player.bilibili.com/player.html?aid=${id}" scrolling="no" border="0" frameborder="no" framespacing="0" allowfullscreen="true"> </iframe>`
}

function getWangYiMusic(id) {
  return `<iframe frameborder="no" border="0" marginwidth="0" marginheight="0" width=330 height=110 src="//music.163.com/outchain/player?type=0&id=${id}&auto=1&height=90"></iframe>`
}

const wangyi = /\[music:\s*\d+\s*\]/g;

var bilibili = /\[bilibili:\s*\d+\s*,\s*\d+\s*,\s*\d+\s*\]/g

/**
 * ??????????????? markdown
 */
function formatContent() {

  var mdContent = document.getElementById("original");
  const persentContent = $("#write");
  if (!mdContent || !persentContent) {
    return;
  }
  // ????????????html
  let originalContent = mdContent.innerHTML;
  if (typeof originalContent === "undefined") {
    return false;
  }
  // ???????????????markdown??????
  originalContent = HTMLDecode(originalContent);

  persentContent.empty();
  persentContent.addClass("loading");

  const renderer = new marked.Renderer();

  renderer.heading = function (text, level, raw, slugger) {
    return `<h${level} id=${generateId()}>${text}</h${level}>`;
  };

  renderer.paragraph = function (text) {
    // ?????????????????????
    var musics = text.match(wangyi);
    if (musics && musics.length > 0) {
      for (var i = 0; i < musics.length; i++) {
        var wangyiMusic = musics[i].match(/\d+/);
        if (wangyiMusic && wangyiMusic.length > 0) {
          var id = wangyiMusic[0];
          text = text.replace(musics[i], getWangYiMusic(id));
        }
      }
    }

    // ??????bilibili??????
    var videos = text.match(bilibili);
    if (videos && videos.length > 0) {
      for (var j = 0; j < videos.length; j++) {
        var video = videos[j].match(/\d+/g);
        if (video && video.length > 0 && video.length === 3) {
          var aid = video[0], width = video[1], height = video[2];
          text = text.replace(videos[j], getBilibili(width, height, aid));
        }
      }
    }
    return `<p>${text}</p>`
  };

  renderer.link = function (href, title, text) {
    if (href && href.startsWith('#')) {
      return `<a href="${href}" rel="noopener noreferrer">${text}</a>`;
    }
    return `<a href="${href}" target="_blank" rel="noopener noreferrer">${text}</a>`;
  };

  renderer.image = function (href, title, text) {
    const reg = /([^]*)\[([^]*)\]\(([^]*)\)/;
    const isContainUrl = reg.test(text);
    const imgHtml = `<img class="lazyload" src=${loading} data-src=${href} alt=${text}>`;
    return `<span style="text-align: center;">
              ${
        isContainUrl
            ? getImgWithUrlHtml(text.match(reg), href)
            : imgHtml
    }
            </span>`;
  };

  function getImgWithUrlHtml(textArr, href) {
    return `<img class="lazyload" src=${loading} data-src=${href} alt=${textArr[2]}></a>`;
  }

  renderer.listitem = function (text, task) {
    if (task) {
      return `<li style="list-style: none;">${text}</li>`;
    }
    return `<li>${text}</li>`;
  };

  renderer.blockquote = function (text) {
    text = text.trim();
    // ???????????????
    text = text.replace(/[\r\n]/g, "<br/>");
    text = text.replace(/<p>/g, "");
    text = text.replace(/<\/p>/g, "<br>");
    const textArr = text.split("<br>");
    const context = [];
    for (let i = 0; i < textArr.length; i++) {
      if (textArr[i].trim().length === 0) {
        continue;
      }
      let txt = textArr[i].replace(/<br\/>/g, '')
      txt = txt.replace(/<br>/g, '')
      context.push(`<p>${txt}</p>`);
    }
    return `<blockquote>${context.join("")}</blockquote>`;
  };

  renderer.table = function (header, body) {
    if (body) {
      body = "<tbody>" + body + "</tbody>";
    }

    return (
        '<div class="md-table"><table>\n' +
        "<thead>\n" +
        header +
        "</thead>\n" +
        body +
        "</table></div>\n"
    );
  };

  marked.setOptions({
    renderer: renderer,
    highlight: function (code, language) {
      if (enableCodeHighlight) {
        const validLanguage = hljs.getLanguage(language)
            ? language
            : "plaintext";
        return hljs.highlight(validLanguage, code).value;
      }
      return code;
    },
    pedantic: false,
    gfm: true,
    breaks: false,
    sanitize: false,
    smartLists: true,
    smartypants: false,
    xhtml: false,
  });

  persentContent.empty();
  persentContent.removeClass("loading");
  persentContent.html(marked(originalContent.trim()));

  mdContent.remove();
  mdContent = null;
  
  addCopyButton();
  // ????????????
  loadCodeLineNumber();

  // ??????
  loadGallery()

  // ????????????
  renderMath()

  // ???????????????
  lazyloadImg()
  return true;
}

/**
 * ????????? HTML
 * @param text
 * @returns {*}
 * @constructor
 */
function HTMLDecode(text) {
  var arrEntities = {lt: "<", gt: ">", nbsp: " ", amp: "&", quot: '"'};
  return text.replace(/&(lt|gt|nbsp|amp|quot);/gi, function (all, t) {
    return arrEntities[t];
  });
}

/*******************************
 * ?????????????????????
 */
function smoothBack2Top() {
  window.scroll({top: 0, behavior: 'smooth'});
}

function smoothBack2Bottom() {
  const offsetHeight = document.documentElement.offsetHeight;
  const scrollHeight = document.documentElement.scrollHeight;
  window.scroll({top: scrollHeight - offsetHeight, behavior: 'smooth'});
}

function ckBack2Top() {
  $('#moonToc').removeClass('mm-active');
  smoothBack2Top();
}

function ckBack2Bottom() {
  $('#moonToc').removeClass('mm-active');
  smoothBack2Bottom();
}

function ckShowContent() {
  toggleSmallToc()

  // ??????????????????
  $('.moon-menu-button').trigger("click");
}

function toggleSmallToc() {
  var moonContent = $('#moonToc')
  moonContent.toggleClass('mm-active');

  if (moonContent.hasClass('mm-active')) {
    moonContent.show();
  } else {
    moonContent.hide()
  }
}

function initMoonToc() {
  var headerEl = 'h1,h2,h3,h4,h5,h6',  //headers
      content = '.md-content';//????????????
  tocbot.init({
    tocSelector: '#moonToc',
    contentSelector: content,
    headingSelector: headerEl,
    scrollSmooth: true,
    isCollapsedClass: '',
    headingsOffset: 0 - ($('#postHeader').height() + 58),
    scrollSmoothOffset: -60,
    hasInnerContainers: false,
  });

  var moonToc = $('#moonToc');
  // ??????????????????
  if (moonToc && moonToc.children().length === 0) {
    $('.icon-toc').addClass('hidden');
  }
}

function toggleSearchBox() {
  $('#searchBox').toggleClass('hidden');
}

function toggleCircle() {
  var $moonDot = $('g.moon-dot');
  var firstCircle = $moonDot.children('circle:first');
  var lastCircle = $moonDot.children('circle:last');
  var cy = $(firstCircle).attr('cy');
  if (cy === '0') {
    $(firstCircle).attr('cx', '0');
    $(firstCircle).attr('cy', '-.8rem');
    $(lastCircle).attr('cx', '0');
    $(lastCircle).attr('cy', '.8rem');
  } else {
    $(firstCircle).attr('cx', '-.8rem');
    $(firstCircle).attr('cy', '0');
    $(lastCircle).attr('cx', '.8rem');
    $(lastCircle).attr('cy', '0');
  }
}

function ckMoonButton() {
  // ??????????????????
  toggleCircle();
  $('.moon-menu-items').toggleClass('item-ani');
}

/**
 * ????????????Pagination??????
 * @param  e
 */
function getData(e) {
  const path = $(e).attr("path");
  var pageContainer = "#pageContainer";
  $.ajax({
    type: "GET",
    url: path,
    beforeSend: function () {
      $(pageContainer).empty();
      $(pageContainer).addClass("loading");
    },
    success: function (data) {
      $(pageContainer).removeClass("loading");
      $(pageContainer).empty();
      let result = $(data).find(pageContainer);
      $(pageContainer).append(result.children());
      let page = "#pagination";
      let pagination = $(data).find(page);
      $(page).empty();
      $(page).append(pagination.children());
      lazyloadImg();
      if ($(data).find(".ziyan")) {
        // ????????????
        setTimeAgo();

        // ??????????????????
        hljsZiYanCode()

      }
    },
    error: function () {
      $(pageContainer).empty();
      $(pageContainer).addClass("loading");
    },
  });
}

/**
 * ??????????????????
 * @param e
 */
function getMore(e) {
  const path = $(e).attr("path");
  var moreContainer = "#moreContainer";
  var moreLoading = '#pagination';
  $.ajax({
    type: "GET",
    url: path,
    beforeSend: function () {
      $(moreLoading).empty();
      $(moreLoading).addClass("loading");
    },
    success: function (data) {
      $(moreLoading).removeClass("loading");
      $(moreLoading).empty();
      let result = $(data).find(moreContainer);
      $(moreContainer).append(result.children());
      let page = "#pagination";
      let pagination = $(data).find(page);
      $(page).empty();
      $(page).append(pagination.children());
      if ($(data).find(".ziyan")) {
        // ????????????
        setTimeAgo();

        // ??????????????????
        hljsZiYanCode()
      }
    },
  });
}

/**
 * ??????????????????
 */
function renderMath() {
  if (openKatex && renderMathInElement && typeof renderMathInElement
      !== 'undefined') {
    if (document.getElementById('write')) {
      renderMathInElement(document.getElementById('write'), katex_config)
    } else if (document.getElementById('ziyan')) {
      renderMathInElement(document.getElementById('ziyan'), katex_config)
    }
  }
}

/**
 * ???????????????
 */
function lazyloadImg() {
  var imgs = document.querySelectorAll('img.lazyload');

  //????????????bound.top<=clientHeight????????????????????????bool???
  function isIn(el) {
    const bound = el.getBoundingClientRect();
    const clientHeight = window.innerHeight;
    return bound.top <= clientHeight;
  }

  //????????????YesNo??????????????????????????????????????????
  function check() {
    Array.from(imgs).forEach(function (el) {
      if (isIn(el)) {
        loadImg(el);
      }
    })
  }

  function loadImg(el) {
    const loaded = el.getAttribute('data-loaded')
    if (!loaded) {
      var index = el.getAttribute('index');
      var imgIndex = !index ? new Date().getSeconds() : index;
      if (el.classList.contains('img-random') && typeof photos !== 'undefined' && photos.length > 0) {
        el.src = photos[imgIndex % photos.length];
      } else {
        el.src = el.dataset.src;
      }
      el.setAttribute('data-loaded', true)
    }
  }

  window.onload = window.onscroll = function () { //onscroll()?????????????????????????????????
    check();
  }
}

/**
 * ????????????
 * @param time
 * @returns {string|*}
 */
function timeAgo(time) {
  var currentTime = new Date().getTime()
  var between = currentTime - time
  var days = Math.floor(between / (24 * 3600 * 1000))
  if (days === 0) {
    var leave1 = between % (24 * 3600 * 1000)
    var hours = Math.floor(leave1 / (3600 * 1000))
    if (hours === 0) {
      var leave2 = leave1 % (3600 * 1000)
      var minutes = Math.floor(leave2 / (60 * 1000))
      if (minutes === 0) {
        var leave3 = leave2 % (60 * 1000)
        var seconds = Math.round(leave3 / 1000)
        return seconds + ' seconds ago'
      }
      return minutes + ' minutes ago'
    }
    return hours + ' hour ago'
  }
  if (days < 0) {
    return 'just'
  }
  if (days < 1) {
    return days + ' days ago'
  } else {
    return formatDate(time, 'yyyy/MM/dd hh:mm');
  }
}

/**
 * ???????????????
 * @param date
 * @param fmt
 * @returns {*}
 */
function formatDate(date, fmt) {
  date = new Date(date);
  if (/(y+)/.test(fmt)) {
    fmt = fmt.replace(RegExp.$1,
        (date.getFullYear() + '').substr(4 - RegExp.$1.length));
  }
  let o = {
    'M+': date.getMonth() + 1,
    'd+': date.getDate(),
    'h+': date.getHours(),
    'm+': date.getMinutes(),
    's+': date.getSeconds()
  };
  for (let k in o) {
    if (new RegExp(`(${k})`).test(fmt)) {
      let str = o[k] + '';
      fmt = fmt.replace(RegExp.$1,
          (RegExp.$1.length === 1) ? str : padLeftZero(str));
    }
  }
  return fmt;
}

/**
 *
 * @param str
 * @returns {string}
 */
function padLeftZero(str) {
  return ('00' + str).substr(str.length);
}

/**
 * ????????????
 */
function setTimeAgo() {
  $('.time-ago').each(function () {
    const time = $(this).attr('time');
    const timeStr = timeAgo(new Date(time));
    $(this).html(timeStr);
  });
}

/**
 * ??????????????????
 */
function hljsZiYanCode() {
  $('.ziyan .md-content pre code').each(function () {
    hljs.highlightBlock(this);
  });
}

/**
 * ????????????
 * @param key
 * @returns {null|*}
 */
function getLocalStorage(key) {
  var exp = 60 * 60 * 1000; // ?????????????????????
  if (localStorage.getItem(key)) {
    var vals = localStorage.getItem(key); // ????????????????????????
    var dataObj = JSON.parse(vals); // ?????????????????????JSON??????
    // ??????(???????????? - ?????????????????????????????????????????????) > ????????????
    var isTimed = (new Date().getTime() - dataObj.timer) > exp;
    if (isTimed) {
      console.log("Storage expired");
      localStorage.removeItem(key);
      return null;
    } else {
      var newValue = dataObj.val;
    }
    return newValue;
  } else {
    return null;
  }
}

function isQuotaExceeded(e) {
  var quotaExceeded = false;
  if (e) {
    if (e.code) {
      switch (e.code) {
        case 22:
          quotaExceeded = true;
          break;
        case 1014: // Firefox
          if (e.name === 'NS_ERROR_DOM_QUOTA_REACHED') {
            quotaExceeded = true;
          }
          break;
      }
    } else if (e.number === -2147024882) { // IE8
      quotaExceeded = true;
    }
  }
  return quotaExceeded;

}

function setLocalStorage(key, value) {
  var curtime = new Date().getTime(); // ?????????????????? ????????????JSON???????????????
  var valueDate = JSON.stringify({
    val: value,
    timer: curtime
  });
  try {
    localStorage.removeItem(key)
    localStorage.setItem(key, valueDate);
  } catch (e) {
    // ???????????????
    if (isQuotaExceeded(e)) {
      console.log("Error: Local storage exceeds limit");
      localStorage.clear();
    } else {
      console.log("Error: Failed to save to local storage");
    }
  }
}

// ????????????
function gallery() {

  var $photoPage = $(".photos-page");
  // ????????????YesNo???????????????
  if ($photoPage.length < 1) {
    return;
  }
  // ??????????????????
  var $masonrys = $(".masonry-gallery.gallery");

  var option = {
    masonry: {
      gutter: 10,
    },
    // layoutMode: 'masonry',
    // sortBy: 'name',
    // percentPosition: true,
    itemSelector: ".gallery-item",
  }
  $masonrys.find("img.lazyload").on('load', function () {
    $masonrys.isotope(option);
  })

  $("#gallery-filter li a").on("click", function () {
    $("#gallery-filter li a").removeClass("active");
    $(this).addClass("active");
    var dataFilter = $(this).data("filter");
    $masonrys.isotope({
      filter: dataFilter,
    });
    return false;
  });
}

$(function () {
  checkNightMode()

  // ????????????????????????
  if (autoNightMode) {
    autoDayNight();
  }

  // ??????????????????
  handleNavMenu();

  // ?????????markdown??????
  const format = formatContent();

  // ????????????
  if (typeof tocbot !== "undefined" && document.getElementById("toc")) {
    dealContentToc();
  }

  if ($('#container').find('.md-content').length > 0 && format) {
    return;
  }

  // ??????
  loadGallery();
  // ???????????????
  lazyloadImg();

  if ($('#container').find('.ziyan').length > 0) {
    // ????????????
    setTimeAgo();

    // ??????????????????
    hljsZiYanCode()

  }
});