(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-73e506da"],{"7fb0":function(e,t,a){"use strict";a.r(t);var o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("a-row",{attrs:{gutter:12,type:"flex",align:"middle"}},[a("a-col",{staticClass:"pb-3",attrs:{span:24}},[a("a-card",{attrs:{bordered:!1,bodyStyle:{padding:"16px"}}},[a("div",{staticClass:"table-page-search-wrapper"},[a("a-form",{attrs:{layout:"inline"}},[a("a-row",{attrs:{gutter:48}},[a("a-col",{attrs:{md:6,sm:24}},[a("a-form-item",{attrs:{label:"Keywords："}},[a("a-input",{model:{value:e.list.queryParam.keyword,callback:function(t){e.$set(e.list.queryParam,"keyword",t)},expression:"list.queryParam.keyword"}})],1)],1),a("a-col",{attrs:{md:6,sm:24}},[a("a-form-item",{attrs:{label:"Grouping："}},[a("a-select",{on:{change:function(t){return e.handleQuery()}},model:{value:e.list.queryParam.team,callback:function(t){e.$set(e.list.queryParam,"team",t)},expression:"list.queryParam.team"}},e._l(e.computedTeams,(function(t,o){return a("a-select-option",{key:o,attrs:{value:t}},[e._v(e._s(t))])})),1)],1)],1),a("a-col",{attrs:{md:6,sm:24}},[a("span",{staticClass:"table-page-search-submitButtons"},[a("a-space",[a("a-button",{attrs:{type:"primary"},on:{click:function(t){return e.handleQuery()}}},[e._v("Search")]),a("a-button",{on:{click:function(t){return e.handleResetParam()}}},[e._v("Reset")])],1)],1)])],1)],1)],1),a("div",{staticClass:"table-operator mb-0"},[a("a-button",{attrs:{type:"primary",icon:"plus"},on:{click:function(t){e.form.visible=!0}}},[e._v("Add to")])],1)])],1),a("a-col",{attrs:{span:24}},[a("a-list",{attrs:{grid:{gutter:12,xs:2,sm:2,md:4,lg:6,xl:6,xxl:6},dataSource:e.list.data,loading:e.list.loading},scopedSlots:e._u([{key:"renderItem",fn:function(t,o){return a("a-list-item",{key:o},[a("a-card",{attrs:{bodyStyle:{padding:0},hoverable:""},on:{click:function(a){return e.handleOpenEditForm(t)}}},[a("div",{staticClass:"photo-thumb"},[a("img",{attrs:{src:t.thumbnail,loading:"lazy"}})]),a("a-card-meta",{staticClass:"p-3"},[a("ellipsis",{attrs:{slot:"description",length:e.isMobile()?12:16,tooltip:""},slot:"description"},[e._v(e._s(t.name))])],1)],1)],1)}}])})],1)],1),a("div",{staticClass:"page-wrapper"},[a("a-pagination",{attrs:{current:e.list.pagination.page,total:e.list.pagination.total,defaultPageSize:e.list.pagination.size,pageSizeOptions:["18","36","54","72","90","108"],showSizeChanger:"",showLessItems:""},on:{change:e.handlePaginationChange,showSizeChange:e.handlePaginationChange}})],1),a("div",{staticStyle:{position:"fixed",bottom:"30px",right:"30px"}},[a("a-button",{attrs:{type:"primary",shape:"circle",icon:"setting",size:"large"},on:{click:function(t){e.optionFormVisible=!0}}})],1),a("a-modal",{attrs:{title:"Page setup",afterClose:function(){return e.optionFormVisible=!1}},model:{value:e.optionFormVisible,callback:function(t){e.optionFormVisible=t},expression:"optionFormVisible"}},[a("template",{slot:"footer"},[a("a-button",{key:"submit",attrs:{type:"primary"},on:{click:function(t){return e.handleSaveOptions()}}},[e._v("Save")])],1),a("a-form",{attrs:{layout:"vertical"}},[a("a-form-item",{attrs:{label:"page title：",help:"* Need to adapt the theme"}},[a("a-input",{model:{value:e.options.photos_title,callback:function(t){e.$set(e.options,"photos_title",t)},expression:"options.photos_title"}})],1),a("a-form-item",{attrs:{label:"Number of items displayed per page："}},[a("a-input-number",{staticStyle:{width:"100%"},model:{value:e.options.photos_page_size,callback:function(t){e.$set(e.options,"photos_page_size",t)},expression:"options.photos_page_size"}})],1)],1)],2),a("a-drawer",{attrs:{title:"image"+(e.form.model.id?"Modify ":"Add to"),width:e.isMobile()?"100%":"480",closable:"",visible:e.form.visible,destroyOnClose:""},on:{close:e.onDrawerClose}},[a("a-form-model",{ref:"photoForm",attrs:{model:e.form.model,rules:e.form.rules,layout:"vertical"}},[a("a-form-model-item",{attrs:{prop:"url",label:"image address："}},[a("div",{staticClass:"pb-2"},[a("img",{staticClass:"w-full cursor-pointer",staticStyle:{"border-radius":"4px"},attrs:{src:e.form.model.url||"/images/placeholder.jpg"},on:{click:function(t){e.attachmentSelectDrawer.visible=!0}}})]),a("a-input",{attrs:{placeholder:"Click on the cover image to select an image, or enter an external link"},model:{value:e.form.model.url,callback:function(t){e.$set(e.form.model,"url",t)},expression:"form.model.url"}})],1),a("a-form-model-item",{attrs:{prop:"thumbnail",label:"Thumbnail address："}},[a("a-input",{model:{value:e.form.model.thumbnail,callback:function(t){e.$set(e.form.model,"thumbnail",t)},expression:"form.model.thumbnail"}})],1),a("a-form-model-item",{attrs:{prop:"name",label:"image name："}},[a("a-input",{model:{value:e.form.model.name,callback:function(t){e.$set(e.form.model,"name",t)},expression:"form.model.name"}})],1),a("a-form-model-item",{attrs:{prop:"takeTime",label:"Shooting date："}},[a("a-date-picker",{staticStyle:{width:"100%"},attrs:{showTime:"",defaultValue:e.takeTimeDefaultValue,format:"YYYY-MM-DD HH:mm:ss"},on:{change:e.onTakeTimeChange,ok:e.onTakeTimeSelect}})],1),a("a-form-model-item",{attrs:{prop:"location",label:"Filming locations："}},[a("a-input",{model:{value:e.form.model.location,callback:function(t){e.$set(e.form.model,"location",t)},expression:"form.model.location"}})],1),a("a-form-model-item",{attrs:{prop:"team",label:"Grouping："}},[a("a-auto-complete",{staticStyle:{width:"100%"},attrs:{dataSource:e.computedTeams,allowClear:""},model:{value:e.form.model.team,callback:function(t){e.$set(e.form.model,"team",t)},expression:"form.model.team"}})],1),a("a-form-model-item",{attrs:{prop:"description",label:"description："}},[a("a-input",{attrs:{type:"textarea",autoSize:{minRows:5}},model:{value:e.form.model.description,callback:function(t){e.$set(e.form.model,"description",t)},expression:"form.model.description"}})],1)],1),a("a-divider",{staticClass:"divider-transparent"}),a("div",{staticClass:"bottom-control"},[a("a-space",[a("ReactiveButton",{attrs:{loading:e.form.saving,errored:e.form.saveErrored,text:e.form.model.id?"Modify ":"Add to",loadedText:(e.form.model.id?"Modify ":"Add to")+"  success",erroredText:(e.form.model.id?"Modify ":"Add to")+"failure"},on:{click:e.handleCreateOrUpdate,callback:e.handleCreateOrUpdateCallback}}),e.form.model.id?a("a-popconfirm",{attrs:{title:"Are you sure you want to delete the picture？",okText:"Ok",cancelText:"Cancel"},on:{confirm:e.handleDelete}},[a("ReactiveButton",{attrs:{type:"danger",loading:e.form.deleting,errored:e.form.deleteErrored,text:"delete",loadedText:"delete success",erroredText:"delete failure"},on:{click:function(){},callback:e.handleDeleteCallback}})],1):e._e()],1)],1),a("AttachmentSelectDrawer",{attrs:{drawerWidth:480},on:{listenToSelect:e.handleAttachmentSelected},model:{value:e.attachmentSelectDrawer.visible,callback:function(t){e.$set(e.attachmentSelectDrawer,"visible",t)},expression:"attachmentSelectDrawer.visible"}})],1)],1)},i=[],r=(a("8e6e"),a("ac6a"),a("456d"),a("55dd"),a("ade3")),n=a("2f62"),l=a("ac0d"),s=a("975e"),m=a("482b"),c=a("9158");function d(e,t){var a=Object.keys(e);if(Object.getOwnPropertySymbols){var o=Object.getOwnPropertySymbols(e);t&&(o=o.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),a.push.apply(a,o)}return a}function u(e){for(var t=1;t<arguments.length;t++){var a=null!=arguments[t]?arguments[t]:{};t%2?d(Object(a),!0).forEach((function(t){Object(r["a"])(e,t,a[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(a)):d(Object(a)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(a,t))}))}return e}var f={mixins:[l["a"],l["b"]],data:function(){return{list:{data:[],loading:!1,pagination:{page:1,size:18,sort:null,total:1},queryParam:{page:0,size:18,sort:null,keyword:null,team:null}},form:{model:{},visible:!1,rules:{url:[{required:!0,message:"* Image address cannot be empty",trigger:["change"]}],thumbnail:[{required:!0,message:"* Thumbnail address cannot be empty",trigger:["change"]}],name:[{required:!0,message:"* image name cannot be empty",trigger:["change"]}]},saving:!1,saveErrored:!1,deleting:!1,deleteErrored:!1},attachmentSelectDrawer:{visible:!1},optionFormVisible:!1,teams:[],options:[]}},created:function(){this.hanldeListPhotos(),this.hanldeListPhotoTeams(),this.hanldeListOptions()},computed:{takeTimeDefaultValue:function(){if(this.form.model.takeTime){var e=new Date(this.form.model.takeTime);return Object(c["a"])(e,"YYYY-MM-DD HH:mm:ss")}return Object(c["a"])(new Date,"YYYY-MM-DD HH:mm:ss")},computedTeams:function(){return this.teams.filter((function(e){return""!==e}))}},methods:u(u({},Object(n["b"])(["refreshOptionsCache"])),{},{hanldeListPhotos:function(){var e=this;this.list.loading=!0,this.list.queryParam.page=this.list.pagination.page-1,this.list.queryParam.size=this.list.pagination.size,this.list.queryParam.sort=this.list.pagination.sort,s["a"].query(this.list.queryParam).then((function(t){e.list.data=t.data.data.content,e.list.pagination.total=t.data.data.total})).finally((function(){setTimeout((function(){e.list.loading=!1}),200)}))},handleQuery:function(){this.handlePaginationChange(1,this.list.pagination.size)},hanldeListOptions:function(){var e=this;m["a"].listAll().then((function(t){e.options=t.data.data}))},hanldeListPhotoTeams:function(){var e=this;s["a"].listTeams().then((function(t){e.teams=t.data.data}))},handleCreateOrUpdate:function(){var e=this;e.$refs.photoForm.validate((function(t){t&&(e.form.saving=!0,e.form.model.id?s["a"].update(e.form.model.id,e.form.model).catch((function(){e.form.saveErrored=!0})).finally((function(){setTimeout((function(){e.form.saving=!1}),400)})):s["a"].create(e.form.model).catch((function(){e.form.saveErrored=!0})).finally((function(){setTimeout((function(){e.form.saving=!1}),400)})))}))},handleCreateOrUpdateCallback:function(){this.form.saveErrored?this.form.saveErrored=!1:(this.form.model={},this.form.visible=!1,this.hanldeListPhotos(),this.hanldeListPhotoTeams())},handleOpenEditForm:function(e){this.form.model=e,this.form.visible=!0},handlePaginationChange:function(e,t){this.$log.debug("Current: ".concat(e,", PageSize: ").concat(t)),this.list.pagination.page=e,this.list.pagination.size=t,this.hanldeListPhotos()},handleDelete:function(){var e=this;this.form.deleting=!0,s["a"].delete(this.form.model.id).catch((function(){e.form.deleteErrored=!0})).finally((function(){setTimeout((function(){e.form.deleting=!1}),400)}))},handleDeleteCallback:function(){this.form.deleteErrored?this.form.deleteErrored=!1:(this.form.model={},this.form.visible=!1,this.hanldeListPhotos(),this.hanldeListPhotoTeams())},handleAttachmentSelected:function(e){this.form.model.url=encodeURI(e.path),this.form.model.thumbnail=encodeURI(e.thumbPath),this.attachmentSelectDrawer.visible=!1},handleResetParam:function(){this.list.queryParam.keyword=null,this.list.queryParam.team=null,this.handlePaginationChange(1,this.list.pagination.size),this.hanldeListPhotoTeams()},onDrawerClose:function(){this.form.visible=!1,this.form.model={}},handleSaveOptions:function(){var e=this;m["a"].save(this.options).then((function(t){e.$message.success("Saved successfully！"),e.optionFormVisible=!1})).finally((function(){e.hanldeListOptions(),e.refreshOptionsCache()}))},onTakeTimeChange:function(e,t){this.form.model.takeTime=e.valueOf()},onTakeTimeSelect:function(e){this.form.model.takeTime=e.valueOf()}})},p=f,h=a("2877"),b=Object(h["a"])(p,o,i,!1,null,null,null);t["default"]=b.exports},"975e":function(e,t,a){"use strict";var o=a("9efd"),i="/api/admin/photos",r={query:function(e){return Object(o["a"])({url:i,params:e,method:"get"})},create:function(e){return Object(o["a"])({url:i,data:e,method:"post"})},update:function(e,t){return Object(o["a"])({url:"".concat(i,"/").concat(e),method:"put",data:t})},delete:function(e){return Object(o["a"])({url:"".concat(i,"/").concat(e),method:"delete"})},listTeams:function(){return Object(o["a"])({url:"".concat(i,"/teams"),method:"get"})}};t["a"]=r}}]);