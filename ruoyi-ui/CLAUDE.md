[根目录](../CLAUDE.md) > **ruoyi-ui**

# ruoyi-ui 前端模块文档

## 变更记录 (Changelog)

### 2025-08-30 16:29:18
- 初始化前端模块文档
- 记录 Vue.js 应用架构和组件结构
- 整理 API 接口层和路由配置

## 模块职责

ruoyi-ui 是 RuoYi-Vue 系统的**前端用户界面模块**，基于 Vue.js 2.x 技术栈构建，负责：

- **用户界面：** 提供完整的管理后台界面
- **交互逻辑：** 实现用户操作和数据展示逻辑
- **API通信：** 与后端服务进行数据交互
- **权限控制：** 前端路由和按钮级权限控制

## 入口与启动

### 应用入口
**文件：** `src/main.js`

```javascript
import Vue from 'vue'
import App from './App'
import store from './store'
import router from './router'
import Element from 'element-ui'

// 全局组件注册
Vue.component('DictTag', DictTag)
Vue.component('Pagination', Pagination)
Vue.component('RightToolbar', RightToolbar)
// ...

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
```

### 启动方式
```bash
# 开发环境启动
npm run dev

# 生产环境构建
npm run build:prod

# 预发环境构建  
npm run build:stage

# 预览构建结果
npm run preview
```

### 访问地址
- **开发环境：** http://localhost:80
- **生产环境：** 根据部署配置

## 对外接口

### 技术栈
- **框架：** Vue.js 2.6.12
- **UI库：** Element UI 2.15.14
- **路由：** Vue Router 3.4.9
- **状态管理：** Vuex 3.6.0  
- **HTTP客户端：** Axios 0.28.1
- **构建工具：** Vue CLI 4.4.6

### 项目结构
```
src/
├── api/              # API接口层
├── assets/           # 静态资源
├── components/       # 通用组件
├── directive/        # 自定义指令
├── layout/           # 布局组件
├── plugins/          # 插件配置
├── router/           # 路由配置
├── store/            # Vuex状态管理
├── utils/            # 工具函数
├── views/            # 页面组件
├── App.vue           # 根组件
└── main.js           # 入口文件
```

## 关键依赖与配置

### 核心依赖
```json
{
  "vue": "2.6.12",
  "element-ui": "2.15.14", 
  "vue-router": "3.4.9",
  "vuex": "3.6.0",
  "axios": "0.28.1",
  "echarts": "5.4.0",
  "js-cookie": "3.0.1",
  "nprogress": "0.2.0"
}
```

### 开发依赖
```json
{
  "@vue/cli-service": "4.4.6",
  "sass": "1.32.13",
  "sass-loader": "10.1.1",
  "vue-template-compiler": "2.6.12"
}
```

### 构建配置
**文件：** `vue.config.js` (推测存在)
- 开发服务器配置
- 代理配置指向后端API
- 构建优化配置

## API接口层

### 接口模块结构
```
src/api/
├── login.js          # 登录认证
├── menu.js           # 菜单数据
├── monitor/          # 监控相关
│   ├── cache.js      # 缓存监控
│   ├── job.js        # 定时任务
│   ├── server.js     # 服务器监控
│   └── ...
├── system/           # 系统管理
│   ├── user.js       # 用户管理
│   ├── role.js       # 角色管理
│   ├── dept.js       # 部门管理
│   ├── dict/         # 字典管理
│   └── ...
├── tool/             # 开发工具
│   └── gen.js        # 代码生成
└── business/         # 业务模块
    └── news.js       # 新闻模块(新增)
```

### API封装示例
```javascript
// src/api/system/user.js
import request from '@/utils/request'

// 查询用户列表
export function listUser(query) {
  return request({
    url: '/system/user/list',
    method: 'get',
    params: query
  })
}

// 新增用户
export function addUser(data) {
  return request({
    url: '/system/user',
    method: 'post', 
    data: data
  })
}
```

## 页面组件架构

### 主要页面模块
- **登录页面：** `views/login/index.vue`
- **系统管理：** `views/system/` - 用户、角色、部门、菜单管理等
- **系统监控：** `views/monitor/` - 在线用户、定时任务、系统日志等  
- **系统工具：** `views/tool/` - 代码生成、API文档等
- **个人中心：** `views/profile/` - 个人信息、修改密码等

### 通用组件
- **Pagination** - 分页组件
- **RightToolbar** - 表格工具栏  
- **Editor** - 富文本编辑器
- **FileUpload** - 文件上传
- **ImageUpload** - 图片上传
- **DictTag** - 字典标签

## 路由与权限

### 路由配置
**文件：** `src/router/index.js`

```javascript
// 公共路由
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login'),
    hidden: true
  },
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard'
  }
  // ...
]

// 动态路由（权限控制）
export const asyncRoutes = [
  // 根据用户权限动态加载
]
```

### 权限控制
- **路由守卫：** `src/permission.js` 实现路由级权限控制
- **按钮权限：** 使用 `v-hasPermi` 指令控制按钮显示
- **角色权限：** 基于用户角色动态加载菜单

## 状态管理

### Vuex模块
- **app** - 应用基础状态（侧边栏、主题等）
- **user** - 用户信息和权限
- **permission** - 动态路由权限
- **settings** - 系统设置

### 状态持久化
使用 localStorage 和 sessionStorage 保存用户状态和配置信息。

## 测试与质量

### 当前状态
- ❌ **单元测试：** 无测试用例 
- ❌ **组件测试：** 无组件测试
- ❌ **E2E测试：** 无端到端测试
- ✅ **代码规范：** 使用 ESLint 进行代码检查
- ✅ **构建优化：** 支持代码分割和懒加载

### 测试建议
1. **单元测试：** 使用 Jest + Vue Test Utils 测试工具函数和组件逻辑
2. **组件测试：** 对关键业务组件进行快照测试和行为测试
3. **E2E测试：** 使用 Cypress 测试关键业务流程
4. **性能测试：** 使用 Lighthouse 进行性能评估

## 常见问题 (FAQ)

### Q: 如何添加新的页面？
A: 在 `src/views/` 下创建 Vue 组件，在 `src/router/index.js` 中配置路由。

### Q: 如何调用后端API？
A: 在 `src/api/` 下创建对应的接口文件，使用统一的 request 工具进行HTTP请求。

### Q: 如何实现权限控制？
A: 使用 `v-hasPermi` 指令控制元素显示，使用路由守卫控制页面访问。

### Q: 如何自定义主题？
A: 修改 `src/assets/styles/element-variables.scss` 中的 Element UI 主题变量。

### Q: 如何处理跨域问题？
A: 在 `vue.config.js` 中配置 proxy 代理到后端服务。

## 相关文件清单

### 核心配置文件
- `package.json` - 项目依赖和脚本配置
- `vue.config.js` - Vue CLI 构建配置（如存在）
- `.eslintrc.js` - ESLint代码规范配置（如存在）

### 入口文件
- `src/main.js` - 应用程序入口  
- `src/App.vue` - 根组件
- `public/index.html` - HTML模板

### 核心目录
- `src/api/` - API接口封装
- `src/views/` - 页面组件
- `src/components/` - 通用组件
- `src/router/` - 路由配置
- `src/store/` - 状态管理
- `src/utils/` - 工具函数

### 样式资源
- `src/assets/styles/` - 样式文件
- `src/assets/icons/` - 图标资源