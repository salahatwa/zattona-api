(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d0b64bf"],{"1d0f":function(t,e,a){"use strict";a.r(e);var s=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("page-view",[a("div",{staticClass:"card-container"},[a("a-tabs",{attrs:{type:"card"}},[a("a-tab-pane",{key:"1",attrs:{tab:"article"}},[a("comment-tab",{attrs:{type:"posts"}})],1),a("a-tab-pane",{key:"2",attrs:{tab:"page"}},[a("comment-tab",{attrs:{type:"sheets"}})],1)],1)],1)])},n=[],o=a("680a"),i=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"comment-tab-wrapper"},[a("a-card",{attrs:{bordered:!1,bodyStyle:{padding:0}}},[a("div",{staticClass:"table-page-search-wrapper"},[a("a-form",{attrs:{layout:"inline"}},[a("a-row",{attrs:{gutter:48}},[a("a-col",{attrs:{md:6,sm:24}},[a("a-form-item",{attrs:{label:"Keywords："}},[a("a-input",{on:{keyup:function(e){return!e.type.indexOf("key")&&t._k(e.keyCode,"enter",13,e.key,"Enter")?null:t.handleQuery()}},model:{value:t.queryParam.keyword,callback:function(e){t.$set(t.queryParam,"keyword",e)},expression:"queryParam.keyword"}})],1)],1),a("a-col",{attrs:{md:6,sm:24}},[a("a-form-item",{attrs:{label:"Comment status："}},[a("a-select",{attrs:{placeholder:"Please select comment status",allowClear:""},on:{change:function(e){return t.handleQuery()}},model:{value:t.queryParam.status,callback:function(e){t.$set(t.queryParam,"status",e)},expression:"queryParam.status"}},t._l(Object.keys(t.commentStatus),(function(e){return a("a-select-option",{key:e,attrs:{value:e}},[t._v(t._s(t.commentStatus[e].text))])})),1)],1)],1),a("a-col",{attrs:{md:12,sm:24}},[a("span",{staticClass:"table-page-search-submitButtons"},[a("a-space",[a("a-button",{attrs:{type:"primary"},on:{click:function(e){return t.handleQuery()}}},[t._v("Search")]),a("a-button",{on:{click:function(e){return t.handleResetParam()}}},[t._v("Reset")])],1)],1)])],1)],1)],1),a("div",{staticClass:"table-operator"},[a("a-dropdown",{directives:[{name:"show",rawName:"v-show",value:null!=t.queryParam.status&&""!=t.queryParam.status&&!t.isMobile(),expression:"queryParam.status!=null && queryParam.status!='' && !isMobile()"}]},[a("a-menu",{attrs:{slot:"overlay"},slot:"overlay"},["AUDITING"===t.queryParam.status?a("a-menu-item",{key:"1"},[a("a",{attrs:{href:"javascript:void(0);"},on:{click:function(e){return t.handleEditStatusMore(t.commentStatus.PUBLISHED.value)}}},[t._v(" by ")])]):t._e(),"PUBLISHED"===t.queryParam.status||"AUDITING"===t.queryParam.status?a("a-menu-item",{key:"2"},[a("a",{attrs:{href:"javascript:void(0);"},on:{click:function(e){return t.handleEditStatusMore(t.commentStatus.RECYCLE.value)}}},[t._v(" Move to trash ")])]):t._e(),"RECYCLE"===t.queryParam.status?a("a-menu-item",{key:"3"},[a("a",{attrs:{href:"javascript:void(0);"},on:{click:t.handleDeleteMore}},[t._v(" delete permanently ")])]):t._e()],1),a("a-button",[t._v(" Batch operation "),a("a-icon",{attrs:{type:"down"}})],1)],1)],1),a("div",{staticClass:"mt-4"},[t.isMobile()?a("a-list",{attrs:{itemLayout:"vertical",size:"large",pagination:!1,dataSource:t.formattedComments,loading:t.loading},scopedSlots:t._u([{key:"renderItem",fn:function(e,s){return a("a-list-item",{key:s},[a("template",{slot:"actions"},[a("a-dropdown",{attrs:{placement:"topLeft",trigger:["click"]}},[a("span",[a("a-icon",{attrs:{type:"bars"}})],1),a("a-menu",{attrs:{slot:"overlay"},slot:"overlay"},["AUDITING"===e.status?a("a-menu-item",[a("a",{attrs:{href:"javascript:;"},on:{click:function(a){return t.handleEditStatusClick(e.id,"PUBLISHED")}}},[t._v("by")])]):t._e(),"AUDITING"===e.status?a("a-menu-item",[a("a",{attrs:{href:"javascript:;"},on:{click:function(a){return t.handleReplyAndPassClick(e)}}},[t._v("Pass and reply")])]):"PUBLISHED"===e.status?a("a-menu-item",[a("a",{attrs:{href:"javascript:;"},on:{click:function(a){return t.handleReplyClick(e)}}},[t._v("Reply")])]):"RECYCLE"===e.status?a("a-menu-item",[a("a-popconfirm",{attrs:{title:"Are you sure you want to restore this comment？",okText:"Ok",cancelText:"cancel"},on:{confirm:function(a){return t.handleEditStatusClick(e.id,"PUBLISHED")}}},[a("a",{attrs:{href:"javascript:;"}},[t._v("reduction")])])],1):t._e(),"PUBLISHED"===e.status||"AUDITING"===e.status?a("a-menu-item",[a("a-popconfirm",{attrs:{title:"Are you sure you want to move this comment to the trash？",okText:"Ok",cancelText:"Cancel"},on:{confirm:function(a){return t.handleEditStatusClick(e.id,"RECYCLE")}}},[a("a",{attrs:{href:"javascript:;"}},[t._v("Recycle bin")])])],1):"RECYCLE"===e.status?a("a-menu-item",[a("a-popconfirm",{attrs:{title:"Are you sure you want to permanently delete this comment？",okText:"Ok",cancelText:"Cancel"},on:{confirm:function(a){return t.handleDeleteClick(e.id)}}},[a("a",{attrs:{href:"javascript:;"}},[t._v("delete")])])],1):t._e()],1)],1)],1),a("template",{slot:"extra"},[a("span",[a("a-badge",{attrs:{status:e.statusProperty.status,text:e.statusProperty.text}})],1)]),a("a-list-item-meta",[a("template",{slot:"description"},[t._v(" published in "),"posts"===t.type?a("a",{attrs:{href:e.post.fullPath,target:"_blank"}},[t._v("《"+t._s(e.post.title)+"》")]):t._e(),"sheets"===t.type?a("a",{attrs:{href:e.sheet.fullPath,target:"_blank"}},[t._v("《"+t._s(e.sheet.title)+"》")]):t._e()]),a("a-avatar",{attrs:{slot:"avatar",size:"large",src:"//cn.gravatar.com/avatar/"+e.gravatarMd5+"&d=mm"},slot:"avatar"}),e.authorUrl?a("span",{staticStyle:{"max-width":"300px",display:"block","white-space":"nowrap",overflow:"hidden","text-overflow":"ellipsis"},attrs:{slot:"title"},slot:"title"},[e.isAdmin?a("a-icon",{staticStyle:{"margin-right":"3px"},attrs:{type:"user"}}):t._e(),t._v(" "),a("a",{attrs:{href:e.authorUrl,target:"_blank"}},[t._v(t._s(e.author))]),t._v(" "),a("small",{staticStyle:{color:"rgba(0, 0, 0, 0.45)"}},[t._v(t._s(t._f("timeAgo")(e.createTime)))])],1):a("span",{staticStyle:{"max-width":"300px",display:"block","white-space":"nowrap",overflow:"hidden","text-overflow":"ellipsis"},attrs:{slot:"title"},slot:"title"},[e.isAdmin?a("a-icon",{staticStyle:{"margin-right":"3px"},attrs:{type:"user"}}):t._e(),t._v(" "+t._s(e.author)+" "),a("small",{staticStyle:{color:"rgba(0, 0, 0, 0.45)"}},[t._v(t._s(t._f("timeAgo")(e.createTime)))])],1)],2),a("p",{domProps:{innerHTML:t._s(e.content)}})],2)}}],null,!1,1743339856)}):a("a-table",{attrs:{rowKey:function(t){return t.id},rowSelection:{selectedRowKeys:t.selectedRowKeys,onChange:t.onSelectionChange,getCheckboxProps:t.getCheckboxProps},columns:t.columns,dataSource:t.formattedComments,loading:t.loading,pagination:!1,scrollToFirstRowOnChange:""},scopedSlots:t._u([{key:"author",fn:function(e,s){return[s.isAdmin?a("a-icon",{staticStyle:{"margin-right":"3px"},attrs:{type:"user"}}):t._e(),s.authorUrl?a("a",{attrs:{href:s.authorUrl,target:"_blank"}},[t._v(t._s(e))]):a("span",[t._v(t._s(e))])]}},{key:"content",fn:function(e){return a("p",{staticClass:"comment-content-wrapper",domProps:{innerHTML:t._s(e)}})}},{key:"status",fn:function(t){return a("span",{},[a("a-badge",{attrs:{status:t.status,text:t.text}})],1)}},{key:"post",fn:function(e){return"posts"===t.type?a("a",{attrs:{href:e.fullPath,target:"_blank"}},[t._v(t._s(e.title))]):t._e()}},{key:"sheet",fn:function(e){return"sheets"===t.type?a("a",{attrs:{href:e.fullPath,target:"_blank"}},[t._v(t._s(e.title))]):t._e()}},{key:"createTime",fn:function(e){return a("span",{},[a("a-tooltip",{attrs:{placement:"top"}},[a("template",{slot:"title"},[t._v(" "+t._s(t._f("moment")(e))+" ")]),t._v(" "+t._s(t._f("timeAgo")(e))+" ")],2)],1)}},{key:"action",fn:function(e,s){return a("span",{},["AUDITING"===s.status?a("a-dropdown",{attrs:{trigger:["click"]}},[a("a",{staticClass:"ant-dropdown-link",attrs:{href:"javascript:void(0);"}},[t._v("by")]),a("a-menu",{attrs:{slot:"overlay"},slot:"overlay"},[a("a-menu-item",{key:"1"},[a("a",{attrs:{href:"javascript:void(0);"},on:{click:function(e){return t.handleEditStatusClick(s.id,"PUBLISHED")}}},[t._v("by")])]),a("a-menu-item",{key:"2"},[a("a",{attrs:{href:"javascript:void(0);"},on:{click:function(e){return t.handleReplyAndPassClick(s)}}},[t._v("Pass and reply")])])],1)],1):"PUBLISHED"===s.status?a("a",{attrs:{href:"javascript:void(0);"},on:{click:function(e){return t.handleReplyClick(s)}}},[t._v("Reply")]):"RECYCLE"===s.status?a("a-popconfirm",{attrs:{title:"Are you sure you want to restore this comment？",okText:"Ok",cancelText:"Cancel"},on:{confirm:function(e){return t.handleEditStatusClick(s.id,"PUBLISHED")}}},[a("a",{attrs:{href:"javascript:;"}},[t._v("reduction")])]):t._e(),a("a-divider",{attrs:{type:"vertical"}}),"PUBLISHED"===s.status||"AUDITING"===s.status?a("a-popconfirm",{attrs:{title:"Are You want to move this comment to the trash？",okText:"Ok",cancelText:"Cancel"},on:{confirm:function(e){return t.handleEditStatusClick(s.id,"RECYCLE")}}},[a("a",{attrs:{href:"javascript:;"}},[t._v("Recycle bin")])]):"RECYCLE"===s.status?a("a-popconfirm",{attrs:{title:"Are You want to delete the comment permanently？",okText:"Ok",cancelText:"Cancel"},on:{confirm:function(e){return t.handleDeleteClick(s.id)}}},[a("a",{attrs:{href:"javascript:;"}},[t._v("delete")])]):t._e()],1)}}],null,!0)}),a("div",{staticClass:"page-wrapper"},[a("a-pagination",{staticClass:"pagination",attrs:{current:t.pagination.page,total:t.pagination.total,defaultPageSize:t.pagination.size,pageSizeOptions:["1","2","5","10","20","50","100"],showSizeChanger:"",showLessItems:""},on:{showSizeChange:t.handlePaginationChange,change:t.handlePaginationChange}})],1)],1)]),t.selectedComment?a("a-modal",{attrs:{title:"Reply to："+t.selectedComment.author,destroyOnClose:""},on:{close:t.onReplyClose},model:{value:t.replyCommentVisible,callback:function(e){t.replyCommentVisible=e},expression:"replyCommentVisible"}},[a("template",{slot:"footer"},[a("ReactiveButton",{attrs:{type:"primary",loading:t.replying,errored:t.replyErrored,text:"Reply",loadedText:"Reply successfully",erroredText:"Reply failed"},on:{click:t.handleCreateClick,callback:t.handleRepliedCallback}})],1),a("a-form-model",{ref:"replyCommentForm",attrs:{model:t.replyComment,rules:t.replyCommentRules,layout:"vertical"}},[a("a-form-model-item",{attrs:{prop:"content"}},[a("a-input",{ref:"contentInput",attrs:{type:"textarea",autoSize:{minRows:8}},model:{value:t.replyComment.content,callback:function(e){t.$set(t.replyComment,"content","string"===typeof e?e.trim():e)},expression:"replyComment.content"}})],1)],1)],2):t._e()],1)},r=[],l=(a("55dd"),a("ac0d")),c=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("a-drawer",{attrs:{title:"Comment details",width:t.isMobile()?"100%":"480",closable:"",visible:t.visible,destroyOnClose:""},on:{close:t.onClose}},[a("a-row",{attrs:{type:"flex",align:"middle"}},[a("a-col",{attrs:{span:24}},[a("a-list",{attrs:{itemLayout:"horizontal"}},[a("a-list-item",[a("a-list-item-meta",{attrs:{description:t.comment.author}},[a("span",{attrs:{slot:"title"},slot:"title"},[t._v("Commenter's nickname：")])])],1),a("a-list-item",[a("a-list-item-meta",{attrs:{description:t.comment.email}},[a("span",{attrs:{slot:"title"},slot:"title"},[t._v("Commenter Email：")])])],1),a("a-list-item",[a("a-list-item-meta",{attrs:{description:t.comment.ipAddress}},[a("span",{attrs:{slot:"title"},slot:"title"},[t._v("Comment by IP：")])])],1),a("a-list-item",[a("a-list-item-meta",[a("a",{attrs:{slot:"description",target:"_blank",href:t.comment.authorUrl},slot:"description"},[t._v(t._s(t.comment.authorUrl))]),a("span",{attrs:{slot:"title"},slot:"title"},[t._v("Commenter URL：")])])],1),a("a-list-item",[a("a-list-item-meta",[a("span",{attrs:{slot:"description"},slot:"description"},[a("a-badge",{attrs:{status:t.comment.statusProperty.status,text:t.comment.statusProperty.text}})],1),a("span",{attrs:{slot:"title"},slot:"title"},[t._v("Comment status：")])])],1),a("a-list-item",[a("a-list-item-meta",["posts"==this.type?a("a",{attrs:{slot:"description",target:"_blank",href:t.comment.post.fullPath},slot:"description"},[t._v(t._s(t.comment.post.title))]):"sheets"==this.type?a("a",{attrs:{slot:"description",target:"_blank",href:t.comment.sheet.fullPath},slot:"description"},[t._v(t._s(t.comment.sheet.title))]):t._e(),"posts"==this.type?a("span",{attrs:{slot:"title"},slot:"title"},[t._v("Review articles：")]):"sheets"==this.type?a("span",{attrs:{slot:"title"},slot:"title"},[t._v("Comment page：")]):t._e()])],1),a("a-list-item",[a("a-list-item-meta",[t.editable?a("template",{slot:"description"},[a("a-input",{attrs:{type:"textarea",autoSize:{minRows:5}},model:{value:t.comment.content,callback:function(e){t.$set(t.comment,"content",e)},expression:"comment.content"}})],1):a("span",{attrs:{slot:"description"},domProps:{innerHTML:t._s(t.comment.content)},slot:"description"}),a("span",{attrs:{slot:"title"},slot:"title"},[t._v("comments：")])],2)],1)],1)],1)],1),a("a-divider",{staticClass:"divider-transparent"}),a("div",{staticClass:"bottom-control"},[a("a-space",[t.editable?t._e():a("a-button",{attrs:{type:"dashed"},on:{click:t.handleEditComment}},[t._v("edit")]),t.editable?a("a-button",{attrs:{type:"primary"},on:{click:t.handleUpdateComment}},[t._v("save")]):t._e(),a("a-popconfirm",{attrs:{title:"Are you sure you want to add this commenter to the blacklist？",okText:"Ok",cancelText:"Cancel"}},[a("a-button",{attrs:{type:"danger"}},[t._v("add to blacklist")])],1)],1)],1)],1)},m=[],u=a("063c"),d={name:"CommentDetail",mixins:[l["a"],l["b"]],components:{},data:function(){return{editable:!1,commentStatus:u["a"].commentStatus,keys:["blog_url"]}},model:{prop:"visible",event:"close"},props:{comment:{type:Object,required:!0},visible:{type:Boolean,required:!1,default:!0},type:{type:String,required:!1,default:"posts",validator:function(t){return-1!==["posts","sheets","journals"].indexOf(t)}}},methods:{handleEditComment:function(){this.editable=!0},handleUpdateComment:function(){var t=this;u["a"].update(this.type,this.comment.id,this.comment).then((function(e){t.$log.debug("Updated comment",e.data.data),t.$message.success("Comment modified successfully！")})),this.editable=!1},onClose:function(){this.$emit("close",!1)}}},p=d,h=a("2877"),y=Object(h["a"])(p,c,m,!1,null,null,null),f=y.exports,C=a("0e54"),v=a.n(C),g=a("ca00"),_=[{title:"Name",dataIndex:"author",width:"150px",ellipsis:!0,scopedSlots:{customRender:"author"}},{title:"content",dataIndex:"content",scopedSlots:{customRender:"content"}},{title:"status",className:"status",dataIndex:"statusProperty",width:"100px",scopedSlots:{customRender:"status"}},{title:"Review articles",dataIndex:"post",width:"200px",ellipsis:!0,scopedSlots:{customRender:"post"}},{title:"date",dataIndex:"createTime",width:"170px",scopedSlots:{customRender:"createTime"}},{title:"operating",dataIndex:"action",width:"180px",scopedSlots:{customRender:"action"}}],k=[{title:"Name",dataIndex:"author",width:"150px",ellipsis:!0,scopedSlots:{customRender:"author"}},{title:"content",dataIndex:"content",scopedSlots:{customRender:"content"}},{title:"status",className:"status",dataIndex:"statusProperty",width:"100px",scopedSlots:{customRender:"status"}},{title:"Comment page",dataIndex:"sheet",width:"200px",ellipsis:!0,scopedSlots:{customRender:"sheet"}},{title:"date",dataIndex:"createTime",width:"170px",scopedSlots:{customRender:"createTime"}},{title:"operating",dataIndex:"action",width:"180px",scopedSlots:{customRender:"action"}}],b={name:"CommentTab",mixins:[l["a"],l["b"]],components:{CommentDetail:f},props:{type:{type:String,required:!1,default:"posts",validator:function(t){return-1!==["posts","sheets","journals"].indexOf(t)}}},data:function(){return{columns:"posts"===this.type?_:k,replyCommentVisible:!1,pagination:{page:1,size:10,sort:null,total:1},queryParam:{page:0,size:10,sort:null,keyword:null,status:null},selectedRowKeys:[],selectedRows:[],comments:[],selectedComment:{},replyComment:{},replyCommentRules:{content:[{required:!0,message:"* the content can not be blank",trigger:["change"]}]},loading:!1,commentStatus:u["a"].commentStatus,commentDetailVisible:!1,replying:!1,replyErrored:!1}},created:function(){this.handleListComments()},computed:{formattedComments:function(){var t=this;return this.comments.map((function(e){return e.statusProperty=t.commentStatus[e.status],e.content=v()(Object(g["a"])(e.content)),e}))}},methods:{handleListComments:function(){var t=this;this.loading=!0,this.queryParam.page=this.pagination.page-1,this.queryParam.size=this.pagination.size,this.queryParam.sort=this.pagination.sort,u["a"].queryComment(this.type,this.queryParam).then((function(e){t.comments=e.data.data.content,t.pagination.total=e.data.data.total})).finally((function(){setTimeout((function(){t.loading=!1}),200)}))},handleQuery:function(){this.handleClearRowKeys(),this.handlePaginationChange(1,this.pagination.size)},handleEditStatusClick:function(t,e){var a=this;u["a"].updateStatus(this.type,t,e).then((function(t){a.$message.success("Successful operation！")})).finally((function(){a.handleListComments()}))},handleDeleteClick:function(t){var e=this;u["a"].delete(this.type,t).then((function(t){e.$message.success("successfully deleted！")})).finally((function(){e.handleListComments()}))},handleReplyAndPassClick:function(t){this.handleReplyClick(t),this.handleEditStatusClick(t.id,"PUBLISHED")},handleReplyClick:function(t){var e=this;this.selectedComment=t,this.replyCommentVisible=!0,this.replyComment.parentId=t.id,"posts"===this.type?this.replyComment.postId=t.post.id:this.replyComment.postId=t.sheet.id,this.$nextTick((function(){e.$refs.contentInput.focus()}))},handleCreateClick:function(){var t=this;t.$refs.replyCommentForm.validate((function(e){e&&(t.replying=!0,u["a"].create(t.type,t.replyComment).catch((function(){t.replyErrored=!0})).finally((function(){setTimeout((function(){t.replying=!1}),400)})))}))},handleRepliedCallback:function(){this.replyErrored?this.replyErrored=!1:(this.replyComment={},this.selectedComment={},this.replyCommentVisible=!1,this.handleListComments())},handlePaginationChange:function(t,e){this.$log.debug("Current: ".concat(t,", PageSize: ").concat(e)),this.pagination.page=t,this.pagination.size=e,this.handleListComments()},handleResetParam:function(){this.queryParam.keyword=null,this.queryParam.status=null,this.handleClearRowKeys(),this.handlePaginationChange(1,this.pagination.size)},handleEditStatusMore:function(t){var e=this;this.selectedRowKeys.length<=0?this.$message.info("Please select at least one！"):u["a"].updateStatusInBatch(this.type,this.selectedRowKeys,t).then((function(a){e.$log.debug("commentIds: ".concat(e.selectedRowKeys,", status: ").concat(t)),e.selectedRowKeys=[]})).finally((function(){e.handleListComments()}))},handleDeleteMore:function(){var t=this;this.selectedRowKeys.length<=0?this.$message.info("Please select at least one！"):u["a"].deleteInBatch(this.type,this.selectedRowKeys).then((function(e){t.$log.debug("delete: ".concat(t.selectedRowKeys)),t.selectedRowKeys=[]})).finally((function(){t.handleListComments()}))},handleClearRowKeys:function(){this.selectedRowKeys=[]},onReplyClose:function(){this.replyComment={},this.selectedComment={},this.replyCommentVisible=!1},onSelectionChange:function(t){this.selectedRowKeys=t,this.$log.debug("SelectedRowKeys: ".concat(t))},getCheckboxProps:function(t){return{props:{disabled:null==this.queryParam.status||""===this.queryParam.status,name:t.author}}},handleShowDetailDrawer:function(t){this.selectedComment=t,this.commentDetailVisible=!0}}},w=b,x=Object(h["a"])(w,i,r,!1,null,null,null),S=x.exports,P={components:{PageView:o["c"],CommentTab:S}},R=P,E=Object(h["a"])(R,s,n,!1,null,null,null);e["default"]=E.exports}}]);