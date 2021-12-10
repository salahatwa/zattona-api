(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-f04cc1dc"],{"86db":function(e,t,a){"use strict";var s=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("a-drawer",{attrs:{title:"Article settings",width:e.isMobile()?"100%":"480",placement:"right",closable:"",destroyOnClose:"",visible:e.visible,afterVisibleChange:e.handleAfterVisibleChanged},on:{close:e.onClose}},[a("div",{staticClass:"post-setting-drawer-content"},[a("div",{staticClass:"mb-4"},[a("h3",{staticClass:"post-setting-drawer-title"},[e._v("basic settings")]),a("div",{staticClass:"post-setting-drawer-item"},[a("a-form",{attrs:{layout:"vertical"}},[e.needTitle?a("a-form-item",{attrs:{label:"Article title："}},[a("a-input",{model:{value:e.selectedPost.title,callback:function(t){e.$set(e.selectedPost,"title",t)},expression:"selectedPost.title"}})],1):e._e(),a("a-form-item",{attrs:{label:"Article alias：",help:e.fullPath}},[a("a-input",{model:{value:e.selectedPost.slug,callback:function(t){e.$set(e.selectedPost,"slug",t)},expression:"selectedPost.slug"}})],1),a("a-form-item",{attrs:{label:"issuing time："}},[a("a-date-picker",{attrs:{showTime:"",defaultValue:e.pickerDefaultValue,format:"YYYY-MM-DD HH:mm:ss",placeholder:"Select article publication time"},on:{change:e.onPostDateChange,ok:e.onPostDateOk}})],1),a("a-form-item",{attrs:{label:"Open comment："}},[a("a-radio-group",{attrs:{defaultValue:!1},model:{value:e.selectedPost.disallowComment,callback:function(t){e.$set(e.selectedPost,"disallowComment",t)},expression:"selectedPost.disallowComment"}},[a("a-radio",{attrs:{value:!1}},[e._v("Turn on")]),a("a-radio",{attrs:{value:!0}},[e._v("shut down")])],1)],1),a("a-form-item",{attrs:{label:"Whether to top："}},[a("a-radio-group",{attrs:{defaultValue:0},model:{value:e.selectedPost.topPriority,callback:function(t){e.$set(e.selectedPost,"topPriority",t)},expression:"selectedPost.topPriority"}},[a("a-radio",{attrs:{value:1}},[e._v("Yes")]),a("a-radio",{attrs:{value:0}},[e._v("No")])],1)],1),e.customTpls.length>0?a("a-form-item",{attrs:{label:"Custom template："}},[a("a-select",{model:{value:e.selectedPost.template,callback:function(t){e.$set(e.selectedPost,"template",t)},expression:"selectedPost.template"}},[a("a-select-option",{key:"",attrs:{value:""}},[e._v("No")]),e._l(e.customTpls,(function(t){return a("a-select-option",{key:t,attrs:{value:t}},[e._v(e._s(t))])}))],2)],1):e._e()],1)],1)]),a("a-divider"),a("div",{staticClass:"mb-4"},[a("h3",{staticClass:"post-setting-drawer-title"},[e._v("Categories")]),a("div",{staticClass:"post-setting-drawer-item"},[a("a-form",{attrs:{layout:"vertical"}},[a("a-form-item",[a("category-tree",{attrs:{categories:e.categories},model:{value:e.selectedCategoryIds,callback:function(t){e.selectedCategoryIds=t},expression:"selectedCategoryIds"}})],1),e.categoryFormVisible?a("a-form-item",[a("category-select-tree",{attrs:{categories:e.categories},model:{value:e.categoryToCreate.parentId,callback:function(t){e.$set(e.categoryToCreate,"parentId",t)},expression:"categoryToCreate.parentId"}})],1):e._e(),e.categoryFormVisible?a("a-form-item",[a("a-input",{attrs:{placeholder:"Category Name"},model:{value:e.categoryToCreate.name,callback:function(t){e.$set(e.categoryToCreate,"name",t)},expression:"categoryToCreate.name"}})],1):e._e(),e.categoryFormVisible?a("a-form-item",[a("a-input",{attrs:{placeholder:"Category path"},model:{value:e.categoryToCreate.slug,callback:function(t){e.$set(e.categoryToCreate,"slug",t)},expression:"categoryToCreate.slug"}})],1):e._e(),a("a-form-item",[a("a-space",[e.categoryFormVisible?a("a-button",{attrs:{type:"primary"},on:{click:e.handlerCreateCategory}},[e._v("Save")]):e._e(),e.categoryFormVisible?e._e():a("a-button",{attrs:{type:"dashed"},on:{click:function(t){e.categoryFormVisible=!0}}},[e._v("Add")]),e.categoryFormVisible?a("a-button",{on:{click:function(t){e.categoryFormVisible=!1}}},[e._v("Cancel")]):e._e()],1)],1)],1)],1)]),a("a-divider"),a("div",{staticClass:"mb-4"},[a("h3",{staticClass:"post-setting-drawer-title"},[e._v("Tag")]),a("div",{staticClass:"post-setting-drawer-item"},[a("a-form",{attrs:{layout:"vertical"}},[a("a-form-item",[a("TagSelect",{model:{value:e.selectedTagIds,callback:function(t){e.selectedTagIds=t},expression:"selectedTagIds"}})],1)],1)],1)]),a("a-divider"),a("div",{staticClass:"mb-4"},[a("h3",{staticClass:"post-setting-drawer-title"},[e._v("Summary")]),a("div",{staticClass:"post-setting-drawer-item"},[a("a-form",{attrs:{layout:"vertical"}},[a("a-form-item",[a("a-input",{attrs:{type:"textarea",autoSize:{minRows:5},placeholder:"If you do not fill in, it will be automatically intercepted from the article"},model:{value:e.selectedPost.summary,callback:function(t){e.$set(e.selectedPost,"summary",t)},expression:"selectedPost.summary"}})],1)],1)],1)]),a("a-divider"),a("div",{staticClass:"mb-4"},[a("h3",{staticClass:"post-setting-drawer-title"},[e._v("cover picture")]),a("div",{staticClass:"post-setting-drawer-item"},[a("div",{staticClass:"post-thumb"},[a("img",{staticClass:"img",attrs:{src:e.selectedPost.thumbnail||"/images/placeholder.jpg"},on:{click:function(t){e.thumbDrawerVisible=!0}}}),a("a-form",{attrs:{layout:"vertial"}},[a("a-form-item",[a("a-input",{attrs:{placeholder:"Click on the cover image to select a picture, or enter an external link"},model:{value:e.selectedPost.thumbnail,callback:function(t){e.$set(e.selectedPost,"thumbnail",t)},expression:"selectedPost.thumbnail"}})],1)],1),a("a-button",{staticClass:"post-thumb-remove",attrs:{type:"dashed"},on:{click:function(t){e.selectedPost.thumbnail=null}}},[e._v("Remove")])],1)])]),a("a-divider",{staticClass:"divider-transparent"})],1),a("AttachmentSelectDrawer",{attrs:{drawerWidth:480},on:{listenToSelect:e.handleSelectPostThumb},model:{value:e.thumbDrawerVisible,callback:function(t){e.thumbDrawerVisible=t},expression:"thumbDrawerVisible"}}),a("a-drawer",{attrs:{title:"advanced settings",width:e.isMobile()?"100%":"480",placement:"right",closable:"",destroyOnClose:"",visible:e.advancedVisible},on:{close:function(t){e.advancedVisible=!1}}},[a("div",{staticClass:"post-setting-drawer-content"},[a("div",{staticClass:"mb-4"},[a("h3",{staticClass:"post-setting-drawer-title"},[e._v("Encryption settings")]),a("div",{staticClass:"post-setting-drawer-item"},[a("a-form",{attrs:{layout:"vertical"}},[a("a-form-item",{attrs:{label:"Access password："}},[a("a-input-password",{attrs:{autocomplete:"new-password"},model:{value:e.selectedPost.password,callback:function(t){e.$set(e.selectedPost,"password",t)},expression:"selectedPost.password"}})],1)],1)],1)]),a("a-divider"),a("div",{staticClass:"mb-4"},[a("h3",{staticClass:"post-setting-drawer-title"},[e._v("SEO setup")]),a("div",{staticClass:"post-setting-drawer-item"},[a("a-form",{attrs:{layout:"vertical"}},[a("a-form-item",{attrs:{label:"Custom keywords："}},[a("a-input",{attrs:{placeholder:"Multiple keywords are separated by English commas. If you don’t fill in, tags will be automatically used as keywords"},model:{value:e.selectedPost.metaKeywords,callback:function(t){e.$set(e.selectedPost,"metaKeywords",t)},expression:"selectedPost.metaKeywords"}})],1),a("a-form-item",{attrs:{label:"Custom description："}},[a("a-input",{attrs:{type:"textarea",autoSize:{minRows:5},placeholder:"If you do not fill in, it will be automatically intercepted from the article"},model:{value:e.selectedPost.metaDescription,callback:function(t){e.$set(e.selectedPost,"metaDescription",t)},expression:"selectedPost.metaDescription"}})],1)],1)],1)]),a("a-divider"),a("div",{staticClass:"mb-4"},[a("h3",{staticClass:"post-setting-drawer-title"},[e._v("Metadata")]),a("a-form",{attrs:{layout:"vertical"}},[e._l(e.selectedMetas,(function(t,s){return a("a-form-item",{key:s,attrs:{prop:"metas."+s+".value"}},[a("a-row",{attrs:{gutter:5}},[a("a-col",{attrs:{span:12}},[a("a-input",{model:{value:t.key,callback:function(a){e.$set(t,"key",a)},expression:"meta.key"}},[a("i",{attrs:{slot:"addonBefore"},slot:"addonBefore"},[e._v("K")])])],1),a("a-col",{attrs:{span:12}},[a("a-input",{model:{value:t.value,callback:function(a){e.$set(t,"value",a)},expression:"meta.value"}},[a("i",{attrs:{slot:"addonBefore"},slot:"addonBefore"},[e._v("V")]),a("a",{attrs:{slot:"addonAfter",href:"javascript:void(0);"},on:{click:function(a){return a.preventDefault(),e.handleRemovePostMeta(t)}},slot:"addonAfter"},[a("a-icon",{attrs:{type:"close"}})],1)])],1)],1)],1)})),a("a-form-item",[a("a-button",{attrs:{type:"dashed"},on:{click:e.handleInsertPostMeta}},[e._v("Add")])],1)],2)],1),a("a-divider",{staticClass:"divider-transparent"})],1)]),a("div",{staticClass:"bottom-control"},[a("a-space",[a("a-button",{attrs:{type:"dashed"},on:{click:function(t){e.advancedVisible=!0}}},[e._v("Advanced")]),e.saveDraftButton?a("ReactiveButton",{attrs:{type:"danger",loading:e.draftSaving,errored:e.draftSavedErrored,text:"save draft",loadedText:"Saved successfully",erroredText:"Save failed"},on:{click:e.handleDraftClick,callback:e.handleSavedCallback}}):e._e(),a("ReactiveButton",{attrs:{loading:e.saving,errored:e.savedErrored,text:e.selectedPost.id?"Save":"Publish",loadedText:(e.selectedPost.id?"Save":"Publish")+" success",erroredText:(e.selectedPost.id?"Save":"Publish")+" failure"},on:{click:function(t){return e.handlePublishClick()},callback:e.handleSavedCallback}})],1)],1)],1)},r=[],o=(a("8e6e"),a("456d"),a("ac6a"),a("7f7f"),a("ade3")),i=a("eda3"),c=a("fa25"),n=a("edf6"),l=a("ac0d"),d=a("9158"),u=a("5974"),h=a.n(u),m=a("2f62"),f=a("c405"),p=a("caf6"),g=a("12de");function v(e,t){var a=Object.keys(e);if(Object.getOwnPropertySymbols){var s=Object.getOwnPropertySymbols(e);t&&(s=s.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),a.push.apply(a,s)}return a}function b(e){for(var t=1;t<arguments.length;t++){var a=null!=arguments[t]?arguments[t]:{};t%2?v(Object(a),!0).forEach((function(t){Object(o["a"])(e,t,a[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(a)):v(Object(a)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(a,t))}))}return e}var y={name:"PostSettingDrawer",mixins:[l["a"],l["b"]],components:{CategoryTree:i["a"],CategorySelectTree:c["a"],TagSelect:n["a"]},data:function(){return{thumbDrawerVisible:!1,categoryFormVisible:!1,advancedVisible:!1,selectedPost:this.post,selectedTagIds:this.tagIds,selectedCategoryIds:this.categoryIds,categories:[],categoryToCreate:{},customTpls:[],saving:!1,savedErrored:!1,draftSaving:!1,draftSavedErrored:!1}},props:{post:{type:Object,required:!0},tagIds:{type:Array,required:!0},categoryIds:{type:Array,required:!0},metas:{type:Array,required:!0},needTitle:{type:Boolean,required:!1,default:!1},saveDraftButton:{type:Boolean,required:!1,default:!0},visible:{type:Boolean,required:!1,default:!0}},watch:{post:function(e){this.selectedPost=e},selectedPost:function(e){this.$emit("onRefreshPost",e)},tagIds:function(e){this.selectedTagIds=e},selectedTagIds:function(e){this.$emit("onRefreshTagIds",e)},categoryIds:function(e){this.selectedCategoryIds=e},selectedCategoryIds:function(e){this.$emit("onRefreshCategoryIds",e)},selectedMetas:function(e){this.$emit("onRefreshPostMetas",e)}},computed:b(b({},Object(m["c"])(["options"])),{},{selectedMetas:function(){return this.metas},pickerDefaultValue:function(){if(this.selectedPost.createTime){var e=new Date(this.selectedPost.createTime);return Object(d["a"])(e,"YYYY-MM-DD HH:mm:ss")}return Object(d["a"])(new Date,"YYYY-MM-DD HH:mm:ss")},fullPath:function(){var e=this.options.post_permalink_type,t=this.options.blog_url,a=this.options.archives_prefix,s=this.options.path_suffix?this.options.path_suffix:"";switch(e){case"DEFAULT":return"".concat(t,"/").concat(a,"/").concat(this.selectedPost.slug?this.selectedPost.slug:"{slug}").concat(s);case"YEAR":return"".concat(t).concat(Object(d["a"])(this.selectedPost.createTime?this.selectedPost.createTime:new Date,"/YYYY/")).concat(this.selectedPost.slug?this.selectedPost.slug:"{slug}").concat(s);case"DATE":return"".concat(t).concat(Object(d["a"])(this.selectedPost.createTime?this.selectedPost.createTime:new Date,"/YYYY/MM/")).concat(this.selectedPost.slug?this.selectedPost.slug:"{slug}").concat(s);case"DAY":return"".concat(t).concat(Object(d["a"])(this.selectedPost.createTime?this.selectedPost.createTime:new Date,"/YYYY/MM/DD/")).concat(this.selectedPost.slug?this.selectedPost.slug:"{slug}").concat(s);case"ID":return"".concat(t,"/?p=").concat(this.selectedPost.id?this.selectedPost.id:"{id}");case"ID_SLUG":return"".concat(t,"/").concat(a,"/").concat(this.selectedPost.id?this.selectedPost.id:"{id}").concat(s);default:return""}}}),methods:{handleAfterVisibleChanged:function(e){e&&(this.handleListCategories(),this.handleListPresetMetasField(),this.handleListCustomTpls(),this.handleSetPinyinSlug())},handleListCategories:function(){var e=this;f["a"].listAll().then((function(t){e.categories=t.data.data}))},handleListPresetMetasField:function(){var e=this;this.metas.length<=0&&g["a"].getActivatedTheme().then((function(t){var a=t.data.data.postMetaField;if(a&&a.length>0)for(var s=0,r=a.length;s<r;s++)e.selectedMetas.push({value:"",key:a[s]})}))},handleListCustomTpls:function(){var e=this;g["a"].customPostTpls().then((function(t){e.customTpls=t.data.data}))},handleSelectPostThumb:function(e){this.selectedPost.thumbnail=encodeURI(e.path),this.thumbDrawerVisible=!1},handlerCreateCategory:function(){var e=this;this.categoryToCreate.name?f["a"].create(this.categoryToCreate).then((function(t){e.categoryToCreate={},e.categoryFormVisible=!1})).finally((function(){e.handleListCategories()})):this.$notification["error"]({message:"prompt",description:"Category name cannot be empty！"})},handleDraftClick:function(){this.selectedPost.status="DRAFT",this.createOrUpdatePost()},handlePublishClick:function(){this.selectedPost.status="PUBLISHED",this.createOrUpdatePost()},createOrUpdatePost:function(){var e=this;this.selectedPost.title?(this.selectedPost.categoryIds=this.selectedCategoryIds,this.selectedPost.tagIds=this.selectedTagIds,this.selectedPost.metas=this.selectedMetas,"DRAFT"===this.selectedPost.status?this.draftSaving=!0:this.saving=!0,this.selectedPost.id?p["a"].update(this.selectedPost.id,this.selectedPost,!1).catch((function(){"DRAFT"===e.selectedPost.status?e.draftSavedErrored=!0:e.savedErrored=!0})).finally((function(){setTimeout((function(){e.saving=!1,e.draftSaving=!1}),400)})):p["a"].create(this.selectedPost,!1).catch((function(){"DRAFT"===e.selectedPost.status?e.draftSavedErrored=!0:e.savedErrored=!0})).then((function(t){e.selectedPost=t.data.data})).finally((function(){setTimeout((function(){e.saving=!1,e.draftSaving=!1}),400)}))):this.$notification["error"]({message:"prompt",description:"Article title cannot be empty！"})},handleSavedCallback:function(){this.draftSavedErrored||this.savedErrored?(this.draftSavedErrored=!1,this.savedErrored=!1):(this.$emit("onSaved",!0),this.$router.push({name:"PostList"}))},onClose:function(){this.$emit("close",!1)},onPostDateChange:function(e,t){this.selectedPost.createTime=e.valueOf()},onPostDateOk:function(e){this.selectedPost.createTime=e.valueOf()},handleRemovePostMeta:function(e){var t=this.selectedMetas.indexOf(e);-1!==t&&this.selectedMetas.splice(t,1)},handleInsertPostMeta:function(){this.selectedMetas.push({value:"",key:""})},handleSetPinyinSlug:function(){if(this.selectedPost.title&&""!==this.selectedPost.title&&!this.selectedPost.id&&h.a.isSupported()){var e,t="",a=h.a.parse(this.selectedPost.title);a.forEach((function(a,s){if(2===a.type){var r=a.target?a.target.toLowerCase():"";t+=t&&!/\n|\s/.test(e.target)?"-"+r:r}else t+=(e&&2===e.type?"-":"")+a.target;e=a})),this.$set(this.selectedPost,"slug",t)}}}},P=y,C=a("2877"),T=Object(C["a"])(P,s,r,!1,null,null,null);t["a"]=T.exports},c405:function(e,t,a){"use strict";a("7f7f"),a("ac6a");var s=a("9efd"),r="/api/admin/categories",o={};function i(e,t){t.forEach((function(t){e.key===t.parentId&&(e.children||(e.children=[]),e.children.push({key:t.id,title:t.name,isLeaf:!1}))})),e.children?e.children.forEach((function(e){return i(e,t)})):e.isLeaf=!0}o.listAll=function(){var e=arguments.length>0&&void 0!==arguments[0]&&arguments[0];return Object(s["a"])({url:"".concat(r),params:{more:e},method:"get"})},o.listTree=function(){return Object(s["a"])({url:"".concat(r,"/tree_view"),method:"get"})},o.create=function(e){return Object(s["a"])({url:r,data:e,method:"post"})},o.delete=function(e){return Object(s["a"])({url:"".concat(r,"/").concat(e),method:"delete"})},o.get=function(e){return Object(s["a"])({url:"".concat(r,"/").concat(e),method:"get"})},o.update=function(e,t){return Object(s["a"])({url:"".concat(r,"/").concat(e),data:t,method:"put"})},o.concreteTree=function(e){var t={key:0,title:"top",children:[]};return i(t,e),t.children},t["a"]=o},caf6:function(e,t,a){"use strict";var s=a("9efd"),r="/api/admin/posts",o={listLatest:function(e){return Object(s["a"])({url:"".concat(r,"/latest"),params:{top:e},method:"get"})},query:function(e){return Object(s["a"])({url:r,params:e,method:"get"})},get:function(e){return Object(s["a"])({url:"".concat(r,"/").concat(e),method:"get"})},create:function(e,t){return Object(s["a"])({url:r,method:"post",data:e,params:{autoSave:t}})},update:function(e,t,a){return Object(s["a"])({url:"".concat(r,"/").concat(e),method:"put",data:t,params:{autoSave:a}})},updateDraft:function(e,t){return Object(s["a"])({url:"".concat(r,"/").concat(e,"/status/draft/content"),method:"put",data:{content:t}})},updateStatus:function(e,t){return Object(s["a"])({url:"".concat(r,"/").concat(e,"/status/").concat(t),method:"put"})},updateStatusInBatch:function(e,t){return Object(s["a"])({url:"".concat(r,"/status/").concat(t),data:e,method:"put"})},delete:function(e){return Object(s["a"])({url:"".concat(r,"/").concat(e),method:"delete"})},deleteInBatch:function(e){return Object(s["a"])({url:"".concat(r),data:e,method:"delete"})},preview:function(e){return Object(s["a"])({url:"".concat(r,"/preview/").concat(e),method:"get"})},postStatus:{PUBLISHED:{value:"PUBLISHED",color:"green",status:"success",text:"PUB"},DRAFT:{value:"DRAFT",color:"yellow",status:"warning",text:"DR"},RECYCLE:{value:"RECYCLE",color:"red",status:"error",text:"Rec"},INTIMATE:{value:"INTIMATE",color:"blue",status:"success",text:"PRV"}},permalinkType:{DEFAULT:{type:"DEFAULT",text:"default"},YEAR:{type:"YEAR",text:"year type"},DATE:{type:"DATE",text:"year and month type"},DAY:{type:"DAY",text:"year, month and day"},ID:{type:"ID",text:"ID type"},ID_SLUG:{type:"ID_SLUG",text:"ID alias type"}}};t["a"]=o},d28d:function(e,t,a){"use strict";var s=a("9efd"),r="/api/admin/tags",o={listAll:function(){var e=arguments.length>0&&void 0!==arguments[0]&&arguments[0];return Object(s["a"])({url:r,params:{more:e},method:"get"})},createWithName:function(e){return Object(s["a"])({url:r,data:{name:e},method:"post"})},create:function(e){return Object(s["a"])({url:r,data:e,method:"post"})},update:function(e,t){return Object(s["a"])({url:"".concat(r,"/").concat(e),data:t,method:"put"})},delete:function(e){return Object(s["a"])({url:"".concat(r,"/").concat(e),method:"delete"})}};t["a"]=o},eda3:function(e,t,a){"use strict";var s=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("a-tree",{attrs:{checkable:"",treeData:e.categoryTree,defaultExpandAll:!0,checkedKeys:e.categoryIds},on:{check:e.onCheck}})},r=[],o=a("c405"),i={name:"CategoryTree",model:{prop:"categoryIds",event:"check"},props:{categoryIds:{type:Array,required:!1,default:function(){return[]}},categories:{type:Array,required:!1,default:function(){return[]}}},computed:{categoryTree:function(){return o["a"].concreteTree(this.categories)}},methods:{onCheck:function(e,t){this.$log.debug("Chekced keys",e),this.$log.debug("e",t);var a=t.checkedNodes.filter((function(e){return e.data.props.isLeaf})).map((function(e){return e.key}));this.$log.debug("Effectively selected category ids",a),this.$emit("check",a)}}},c=i,n=a("2877"),l=Object(n["a"])(c,s,r,!1,null,null,null);t["a"]=l.exports},edf6:function(e,t,a){"use strict";var s=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("a-select",{staticClass:"w-full",attrs:{allowClear:"",mode:"tags",placeholder:"Select or enter a Tag"},on:{change:e.handleChange},model:{value:e.selectedTagNames,callback:function(t){e.selectedTagNames=t},expression:"selectedTagNames"}},e._l(e.tags,(function(t){return a("a-select-option",{key:t.id,attrs:{value:t.name}},[e._v(e._s(t.name))])})),1)],1)},r=[],o=(a("ac6a"),a("7f7f"),a("d28d")),i=a("bc3a"),c=a.n(i),n={name:"TagSelect",model:{prop:"tagIds",event:"change"},props:{tagIds:{type:Array,required:!1,default:function(){return[]}}},data:function(){return{tags:[],selectedTagNames:[]}},created:function(){this.handleListTags()},watch:{tags:function(e,t){var a=this;e&&(this.selectedTagNames=this.tagIds.map((function(e){return a.tagIdMap[e].name})))}},computed:{tagIdMap:function(){var e={};return this.tags.forEach((function(t){e[t.id]=t})),e},tagNameMap:function(){var e={};return this.tags.forEach((function(t){e[t.name]=t})),e}},methods:{handleListTags:function(e){var t=this;o["a"].listAll(!0).then((function(a){t.tags=a.data.data,e&&e()}))},handleChange:function(){var e=this;this.$log.debug("Changed");var t=this.selectedTagNames.filter((function(t){return!e.tagNameMap[t]}));if(this.$log.debug("Tag names to create",t),t!==[]){var a=t.map((function(e){return o["a"].createWithName(e)}));c.a.all(a).then(c.a.spread((function(){e.handleListTags((function(){e.$log.debug("Tag name map",e.tagNameMap);var t=e.selectedTagNames.map((function(t){return e.tagNameMap[t].id}));e.$emit("change",t)}))})))}else{var s=this.selectedTagNames.map((function(t){return e.tagNameMap[t].id}));this.$emit("change",s)}}}},l=n,d=a("2877"),u=Object(d["a"])(l,s,r,!1,null,null,null);t["a"]=u.exports},fa25:function(e,t,a){"use strict";var s=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("a-tree-select",{attrs:{treeData:e.categoryTreeData,placeholder:"Please select the superior directory, the default is the top directory",treeDefaultExpandAll:"",treeDataSimpleMode:!0,allowClear:!0,value:e.categoryIdString},on:{change:e.handleSelectionChange}})},r=[],o=(a("6b54"),a("7f7f"),a("c5f6"),{name:"CategorySelectTree",model:{prop:"categoryId",event:"change"},props:{categoryId:{type:Number,required:!0,default:0},categories:{type:Array,required:!1,default:function(){return[]}}},computed:{categoryTreeData:function(){return this.categories.map((function(e){return{id:e.id,title:e.name,value:e.id.toString(),pId:e.parentId}}))},categoryIdString:function(){return this.categoryId.toString()}},methods:{handleSelectionChange:function(e,t,a){this.$log.debug("value: ",e),this.$log.debug("label: ",t),this.$log.debug("extra: ",a),this.$emit("change",e?parseInt(e):0)}}}),i=o,c=a("2877"),n=Object(c["a"])(i,s,r,!1,null,null,null);t["a"]=n.exports}}]);