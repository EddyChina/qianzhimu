<template>
<div class="app-container">
  <!--工具栏-->
  <div class="head-container">
    <div v-if="crud.props.searchToggle">
    <!-- 搜索 -->
            <label class="el-form-item-label">账号ID</label>
            <el-input v-model="query.accountId" clearable placeholder="账号ID" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
            <label class="el-form-item-label">商标注册号</label>
            <el-input v-model="query.tmRegId" clearable placeholder="商标注册号" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
       <date-range-picker
        v-model="query.createTime"
        start-placeholder="createTimeStart"
        end-placeholder="createTimeStart"
        class="date-item"
      />
       <date-range-picker
        v-model="query.updateTime"
        start-placeholder="updateTimeStart"
        end-placeholder="updateTimeStart"
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
        <el-form-item label="商标注册号" prop="tmRegId">
          <el-input v-model="form.tmRegId" style="width: 370px;"/>
        </el-form-item>
        <el-form-item label="订单创建时间" prop="createTime">
          <el-date-picker v-model="form.createTime" type="datetime" style="width: 370px;"/>
        </el-form-item>
        <el-form-item label="订单修改时间" prop="updateTime">
          <el-date-picker v-model="form.updateTime" type="datetime" style="width: 370px;"/>
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
        <el-table-column prop="id" label="自增ID"/>
        <el-table-column prop="accountId" label="账号ID"/>
        <el-table-column prop="tmRegId" label="商标注册号"/>
        <el-table-column prop="tmPrice" label="商标标价"/>
        <el-table-column prop="tmCommission" label="服务费"/>
        <el-table-column prop="orderAmount" label="订单金额"/>
        <el-table-column prop="payment" label="支付方式">
          <template slot-scope="scope">
            {{ dict.label.payment[scope.row.payment] }}
          </template>
        </el-table-column>
        <el-table-column prop="state" label="订单状态">
          <template slot-scope="scope">
            {{ dict.label.order_state[scope.row.state] }}
          </template>
        </el-table-column>
        <el-table-column prop="createTime" label="订单创建时间">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.createTime) }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="updateTime" label="订单修改时间">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.updateTime) }}</span>
          </template>
        </el-table-column>
        <el-table-column v-permission="['admin','owsOrder:edit','owsOrder:del']" label="操作" width="150px" align="center">
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
  import crudOwsOrder from '@/api/owsOrder'
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
tmRegId:
  null,
tmPrice:
  null,
tmCommission:
  null,
orderAmount:
  null,
payment:
  null,
state:
  null,
createTime:
  null,
updateTime:
  null  }
  export default {
    name: 'OwsOrder',
    components: {pagination, crudOperation, rrOperation, udOperation},
    mixins: [presenter(), header(), form(defaultForm), crud()],
    dicts: ['payment', 'order_state'],
    cruds() {
      return CRUD({ title: 'OwsOrderService', url: 'api/owsOrder', sort: 'id,desc', crudMethod: { ...crudOwsOrder }})
    },
    data() {
      return {
        permission: {
          add: ['admin', 'owsOrder:add'],
          edit: ['admin', 'owsOrder:edit'],
          del: ['admin', 'owsOrder:del']
        },
        rules: {
      accountId:
      [
        {required: true, message: '账号ID不能为空', trigger: 'blur'}
      ],
      tmRegId:
      [
        {required: true, message: '商标注册号不能为空', trigger: 'blur'}
      ],
      tmPrice:
      [
        {required: true, message: '商标标价不能为空', trigger: 'blur'}
      ],
      tmCommission:
      [
        {required: true, message: '服务费不能为空', trigger: 'blur'}
      ],
      orderAmount:
      [
        {required: true, message: '订单金额不能为空', trigger: 'blur'}
      ],
      payment:
      [
        {required: true, message: '支付方式不能为空', trigger: 'blur'}
      ],
      state:
      [
        {required: true, message: '订单状态不能为空', trigger: 'blur'}
      ],
      createTime:
      [
        {required: true, message: '订单创建时间不能为空', trigger: 'blur'}
      ],
      updateTime:
      [
        {required: true, message: '订单修改时间不能为空', trigger: 'blur'}
      ]    },
      queryTypeOptions: [
        {
          key: 'accountId',
          display_name: '账号ID'
        }, 
        {
          key: 'tmRegId',
          display_name: '商标注册号'
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
