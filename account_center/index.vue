<template>
<div class="app-container">
  <!--工具栏-->
  <div class="head-container">
    <div v-if="crud.props.searchToggle">
    <!-- 搜索 -->
            <label class="el-form-item-label">账号ID</label>
            <el-input v-model="query.accountId" clearable placeholder="账号ID" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
       <date-range-picker
        v-model="query.createTime"
        start-placeholder="createTimeStart"
        end-placeholder="createTimeStart"
        class="date-item"
      />
      <rrOperation/>
    </div>
    <!--如果想在工具栏加入更多按钮，可以使用插槽方式， slot = 'left' or 'right'-->
    <crudOperation :permission="permission"/>
    <!--表单组件-->
    <el-dialog :close-on-click-modal="false" :before-close="crud.cancelCU" :visible.sync="crud.status.cu > 0" :title="crud.status.title" width="500px">
      <el-form ref="form" :model="form" :rules="rules" size="small" label-width="80px">
        <el-form-item label="账号ID" prop="accountId">
          <el-input v-model="form.accountId" style="width: 370px;"/>
        </el-form-item>
        <el-form-item label="收藏时间" prop="createTime">
          <el-date-picker v-model="form.createTime" type="datetime" style="width: 370px;"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="text" @click="crud.cancelCU">取消</el-button>
        <el-button :loading="crud.cu === 2" type="primary" @click="crud.submitCU">确认</el-button>
      </div>
    </el-dialog>
    <!--表格渲染-->
    <el-table ref="table" v-loading="crud.loading" :data="crud.data" size="small" style="width: 100%;" @selection-change="crud.selectionChangeHandler">
        <el-table-column type="selection" width="55"/>
        <el-table-column prop="id" label="PK"/>
        <el-table-column prop="accountId" label="账号ID"/>
        <el-table-column prop="tmId" label="商标ID"/>
        <el-table-column prop="createTime" label="收藏时间">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.createTime) }}</span>
          </template>
        </el-table-column>
        <el-table-column v-permission="['admin','owsFavoriteTrademarker:edit','owsFavoriteTrademarker:del']" label="操作" width="150px" align="center">
          <template slot-scope="scope">
            <udOperation :data="scope.row" :permission="permission" />
          </template>
        </el-table-column>
    </el-table>
    <!--分页组件-->
    <pagination/>
  </div>
</div>
</template>

<script>
  import crudOwsFavoriteTrademarker from '@/api/owsFavoriteTrademarker'
  import CRUD, {presenter, header, form, crud} from '@crud/crud'
  import rrOperation from '@crud/RR.operation'
  import crudOperation from '@crud/CRUD.operation'
  import udOperation from '@crud/UD.operation'
  import pagination from '@crud/Pagination'

  const defaultForm = {
  id:
  null,
accountId:
  null,
tmId:
  null,
createTime:
  null  }
  export default {
    name: 'OwsFavoriteTrademarker',
    components: {pagination, crudOperation, rrOperation, udOperation},
    mixins: [presenter(), header(), form(defaultForm), crud()],
    cruds() {
      return CRUD({ title: 'OwsFavoriteTmService', url: 'api/owsFavoriteTrademarker', sort: 'id,desc', crudMethod: { ...crudOwsFavoriteTrademarker }})
    },
    data() {
      return {
        permission: {
          add: ['admin', 'owsFavoriteTrademarker:add'],
          edit: ['admin', 'owsFavoriteTrademarker:edit'],
          del: ['admin', 'owsFavoriteTrademarker:del']
        },
        rules: {
      accountId:
      [
        {required: true, message: '账号ID不能为空', trigger: 'blur'}
      ],
      tmId:
      [
        {required: true, message: '商标ID不能为空', trigger: 'blur'}
      ],
      createTime:
      [
        {required: true, message: '收藏时间不能为空', trigger: 'blur'}
      ]    },
      queryTypeOptions: [
        {
          key: 'accountId',
          display_name: '账号ID'
        }
      ]
    }
    },
    methods: {
      // 钩子：在获取表格数据之前执行，false 则代表不获取数据
      [CRUD.HOOK.beforeRefresh]() {
        return true
      }
    }
  }
</script>
<style scoped>
</style>
