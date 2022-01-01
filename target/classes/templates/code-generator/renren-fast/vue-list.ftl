<template>
    <div class="mod-config">
        <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
            <el-form-item>
                <el-input v-model="dataForm.key" placeholder="parameter name" clearable></el-input>
            </el-form-item>
            <el-form-item>
                <el-button @click="getDataList()">Inquire</el-button>
                <el-button v-if="isAuth('generator:${classInfo.className?uncap_first}:save')" type="primary" @click="addOrUpdateHandle()">Add</el-button>
                <el-button v-if="isAuth('generator:${classInfo.className?uncap_first}:delete')" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
            </el-form-item>
        </el-form>
        <el-table
                :data="dataList"
                border
                v-loading="dataListLoading"
                @selection-change="selectionChangeHandle"
                style="width: 100%;">
            <el-table-column
                    type="selection"
                    header-align="center"
                    align="center"
                    width="50">
            </el-table-column>
            <#if classInfo.fieldList?exists && classInfo.fieldList?size gt 0>
                <#list classInfo.fieldList as fieldItem >
                    <el-table-column
                            prop="${fieldItem.fieldName}"
                            header-align="center"
                            align="center"
                            label="${fieldItem.fieldComment}">
                    </el-table-column>
                </#list>
            </#if>
            <el-table-column
                    fixed="right"
                    header-align="center"
                    align="center"
                    width="150"
                    label="operate">
                <template slot-scope="scope">
                    <!-- please${classInfo.className?uncap_first}Id Replace with the correct ID -->
                    <el-button type="text" size="small" @click="addOrUpdateHandle(scope.row.${classInfo.className?uncap_first}Id)">Revise</el-button>
                    <el-button type="text" size="small" @click="deleteHandle(scope.row.${classInfo.className?uncap_first}Id)">delete</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination
                @size-change="sizeChangeHandle"
                @current-change="currentChangeHandle"
                :current-page="pageIndex"
                :page-sizes="[10, 20, 50, 100]"
                :page-size="pageSize"
                :total="totalPage"
                layout="total, sizes, prev, pager, next, jumper">
        </el-pagination>
        <!-- Popup, Add / Modify -->
        <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
    </div>
</template>

<script>
    import AddOrUpdate from './${classInfo.className?uncap_first}-add-or-update'
    export default {
        data () {
            return {
                dataForm: {
                    key: ''
                },
                dataList: [],
                pageIndex: 1,
                pageSize: 10,
                totalPage: 0,
                dataListLoading: false,
                dataListSelections: [],
                addOrUpdateVisible: false
            }
        },
        components: {
            AddOrUpdate
        },
        activated () {
            this.getDataList()
        },
        methods: {
            // Get data list
            getDataList () {
                this.dataListLoading = true
                this.http({
                url: this.http.adornUrl('/generator/${classInfo.className?uncap_first}/list'),
                    method: 'get',
                    params: this.http.adornParams({
                        'page': this.pageIndex,
                        'limit': this.pageSize,
                        'key': this.dataForm.key
                    })
            }).then(({data}) => {
                    if (data && data.code === 0) {
                        this.dataList = data.page.list
                        this.totalPage = data.page.totalCount
                    } else {
                        this.dataList = []
                        this.totalPage = 0
                    }
                    this.dataListLoading = false
                })
            },
            // Per page
            sizeChangeHandle (val) {
                this.pageSize = val
                this.pageIndex = 1
                this.getDataList()
            },
            // current page
            currentChangeHandle (val) {
                this.pageIndex = val
                this.getDataList()
            },
            // Multiple choice
            selectionChangeHandle (val) {
                this.dataListSelections = val
            },
            // Add / Update
            addOrUpdateHandle (id) {
                this.addOrUpdateVisible = true
                this.nextTick(() => {
                    this.refs.addOrUpdate.init(id)
                })
            },
            // Delete
            deleteHandle (id) {
                var ids = id ? [id] : this.dataListSelections.map(item => {
                    return item.${classInfo.className?uncap_first}Id
                })
                this.confirm(`Ok [id={ids.join(',')}]Perform [{id?'Delete':'Batch delete'}] operation?`,'Prompt', {
                    confirmButtonText: 'Ok',
                    cancelButtonText: 'Cancel',
                    type: 'warning'
            }).then(() => {
                    this.http({
                        url: this.http.adornUrl('/generator/${classInfo.className?uncap_first}/delete'),
                        method: 'post',
                        data: this.http.adornData(ids, false)
                }).then(({data}) => {
                        if (data && data.code === 0) {
                            this.message({
                                message: 'Successful operation',
                                type: 'success',
                                duration: 1500,
                                onClose: () => {
                                this.getDataList()
                            }
                        })
                        } else {
                            this.message.error(data.msg)
                        }
                    })
                })
            }
        }
    }
</script>
