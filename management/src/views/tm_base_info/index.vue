<template>
<div class="app-container">
  <!--工具栏-->
  <div class="head-container">
    <div v-if="crud.props.searchToggle">
    <!-- 搜索 -->
            <label class="el-form-item-label">适用项目</label>
            <el-input v-model="query.domain" clearable placeholder="适用项目" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
            <label class="el-form-item-label">中英文类型</label>
            <el-input v-model="query.contentType" clearable placeholder="中英文类型" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
            <label class="el-form-item-label">类似群组</label>
            <el-input v-model="query.likeGroup" clearable placeholder="类似群组" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
       <date-range-picker
        v-model="query.regDate"
        start-placeholder="regDateStart"
        end-placeholder="regDateStart"
        class="date-item"
      />
       <date-range-picker
        v-model="query.tagPrice"
        start-placeholder="tagPriceStart"
        end-placeholder="tagPriceStart"
        class="date-item"
      />
       <date-range-picker
        v-model="query.floorPrice"
        start-placeholder="floorPriceStart"
        end-placeholder="floorPriceStart"
        class="date-item"
      />
      <rrOperation/>
    </div>
    <!--如果想在工具栏加入更多按钮，可以使用插槽方式， slot = 'left' or 'right'-->
    <crudOperation :permission="permission"/>
    <!--表单组件-->
    <el-dialog :close-on-click-modal="false" :before-close="crud.cancelCU" :visible.sync="crud.status.cu > 0" :title="crud.status.title" width="500px">
      <el-form ref="form" :model="form" :rules="rules" size="small" label-width="80px">
        <el-form-item label="主键ID">
          <el-input v-model="form.id" style="width: 370px;"/>
        </el-form-item>
        <el-form-item label="注册号" prop="regId">
          <el-input v-model="form.regId" style="width: 370px;"/>
        </el-form-item>
        <el-form-item label="商标名称" prop="name">
          <el-input v-model="form.name" style="width: 370px;"/>
        </el-form-item>
        <el-form-item label="商标分类" prop="category">
          <el-input v-model="form.category" style="width: 370px;"/>
        </el-form-item>
        <el-form-item label="适用项目" prop="domain">
          <el-input v-model="form.domain" style="width: 370px;"/>
        </el-form-item>
        <el-form-item label="中英文类型" prop="contentType">
          <el-input v-model="form.contentType" style="width: 370px;"/>
        </el-form-item>
        <el-form-item label="注册日期" prop="regDate">
          <el-date-picker v-model="form.regDate" type="datetime" style="width: 370px;"/>
        </el-form-item>
        <el-form-item label="有效日期">
          <el-input v-model="form.validDate" style="width: 370px;"/>
        </el-form-item>
        <el-form-item label="注册人">
          <el-input v-model="form.regUserName" style="width: 370px;"/>
        </el-form-item>
        <el-form-item label="注册人联系方式">
          <el-input v-model="form.regUserContact" style="width: 370px;"/>
        </el-form-item>
        <el-form-item label="持有人">
          <el-input v-model="form.ownerUserName" style="width: 370px;"/>
        </el-form-item>
        <el-form-item label="持有人联系方式">
          <el-input v-model="form.ownerContact" style="width: 370px;"/>
        </el-form-item>
        <el-form-item label="持有人标量">
          <el-input v-model="form.ownerHolderCnt" style="width: 370px;"/>
        </el-form-item>
        <el-form-item label="商标图片路径" prop="picPath">
          <el-input v-model="form.picPath" style="width: 370px;"/>
        </el-form-item>
        <el-form-item label="标价" prop="tagPrice">
          <el-input v-model="form.tagPrice" style="width: 370px;"/>
        </el-form-item>
        <el-form-item label="底价">
          <el-input v-model="form.floorPrice" style="width: 370px;"/>
        </el-form-item>
        <el-form-item label="商标长度">
          <el-input v-model="form.lengthRanger" style="width: 370px;"/>
        </el-form-item>
        <el-form-item label="上下架状态" prop="state">
          <el-input v-model="form.state" style="width: 370px;"/>
        </el-form-item>
        <el-form-item label="创建人">
          <el-input v-model="form.createBy" style="width: 370px;"/>
        </el-form-item>
        <el-form-item label="修改人">
          <el-input v-model="form.updateBy" style="width: 370px;"/>
        </el-form-item>
        <el-form-item label="创建时间">
          <el-input v-model="form.createTime" style="width: 370px;"/>
        </el-form-item>
        <el-form-item label="修改时间">
          <el-input v-model="form.updateTime" style="width: 370px;"/>
        </el-form-item>
        <el-form-item label="类似群组">
          <el-input v-model="form.likeGroup" style="width: 370px;"/>
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
        <el-table-column prop="id" label="主键ID"/>
        <el-table-column prop="regId" label="注册号"/>
        <el-table-column prop="name" label="商标名称"/>
        <el-table-column prop="category" label="商标分类"/>
        <el-table-column prop="domain" label="适用项目"/>
        <el-table-column prop="contentType" label="中英文类型"/>
        <el-table-column prop="regDate" label="注册日期">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.regDate) }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="validDate" label="有效日期">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.validDate) }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="regUserName" label="注册人"/>
        <el-table-column prop="regUserContact" label="注册人联系方式"/>
        <el-table-column prop="ownerUserName" label="持有人"/>
        <el-table-column prop="ownerContact" label="持有人联系方式"/>
        <el-table-column prop="ownerHolderCnt" label="持有人标量"/>
        <el-table-column prop="picPath" label="商标图片路径"/>
        <el-table-column prop="tagPrice" label="标价"/>
        <el-table-column prop="floorPrice" label="底价"/>
        <el-table-column prop="lengthRanger" label="商标长度"/>
        <el-table-column prop="state" label="上下架状态"/>
        <el-table-column prop="createBy" label="创建人"/>
        <el-table-column prop="updateBy" label="修改人"/>
        <el-table-column prop="createTime" label="创建时间">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.createTime) }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="updateTime" label="修改时间">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.updateTime) }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="likeGroup" label="类似群组"/>
        <el-table-column v-permission="['admin','tmBaseInfo:edit','tmBaseInfo:del']" label="操作" width="150px" align="center">
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
  import crudTmBaseInfo from '@/api/tmBaseInfo'
  import CRUD, {presenter, header, form, crud} from '@crud/crud'
  import rrOperation from '@crud/RR.operation'
  import crudOperation from '@crud/CRUD.operation'
  import udOperation from '@crud/UD.operation'
  import pagination from '@crud/Pagination'

  const defaultForm = {
  id:
  null,
regId:
  null,
name:
  null,
category:
  null,
domain:
  null,
contentType:
  null,
regDate:
  null,
validDate:
  null,
regUserName:
  null,
regUserContact:
  null,
ownerUserName:
  null,
ownerContact:
  null,
ownerHolderCnt:
  null,
picPath:
  null,
tagPrice:
  null,
floorPrice:
  null,
lengthRanger:
  null,
state:
  null,
createBy:
  null,
updateBy:
  null,
createTime:
  null,
updateTime:
  null,
likeGroup:
  null  }
  export default {
    name: 'TmBaseInfo',
    components: {pagination, crudOperation, rrOperation, udOperation},
    mixins: [presenter(), header(), form(defaultForm), crud()],
    cruds() {
      return CRUD({ title: 'TmBaseInfoService', url: 'api/tmBaseInfo', sort: 'id,desc', crudMethod: { ...crudTmBaseInfo }})
    },
    data() {
      return {
        permission: {
          add: ['admin', 'tmBaseInfo:add'],
          edit: ['admin', 'tmBaseInfo:edit'],
          del: ['admin', 'tmBaseInfo:del']
        },
        rules: {
      regId:
      [
        {required: true, message: '注册号不能为空', trigger: 'blur'}
      ],
      name:
      [
        {required: true, message: '商标名称不能为空', trigger: 'blur'}
      ],
      category:
      [
        {required: true, message: '商标分类不能为空', trigger: 'blur'}
      ],
      domain:
      [
        {required: true, message: '适用项目不能为空', trigger: 'blur'}
      ],
      contentType:
      [
        {required: true, message: '中英文类型不能为空', trigger: 'blur'}
      ],
      regDate:
      [
        {required: true, message: '注册日期不能为空', trigger: 'blur'}
      ],
      picPath:
      [
        {required: true, message: '商标图片路径不能为空', trigger: 'blur'}
      ],
      tagPrice:
      [
        {required: true, message: '标价不能为空', trigger: 'blur'}
      ],
      state:
      [
        {required: true, message: '上下架状态不能为空', trigger: 'blur'}
      ]    },
      queryTypeOptions: [
        {
          key: 'domain',
          display_name: '适用项目'
        }, 
        {
          key: 'contentType',
          display_name: '中英文类型'
        }, 
        {
          key: 'likeGroup',
          display_name: '类似群组'
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
