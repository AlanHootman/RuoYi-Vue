[根目录](../CLAUDE.md) > **ruoyi-system**

# ruoyi-system 系统业务模块文档

## 变更记录 (Changelog)

### 2025-08-30 16:29:18
- 初始化系统业务模块文档
- 识别核心业务功能和数据模型
- 记录用户权限管理体系

## 模块职责

ruoyi-system 是 RuoYi-Vue 系统的**核心业务模块**，实现系统管理的核心功能：

- **用户管理：** 用户信息、认证、状态管理
- **组织架构：** 部门、岗位的层级管理
- **权限体系：** 角色、菜单、权限的分配和控制
- **系统配置：** 参数配置、字典管理、通知公告
- **日志审计：** 操作日志、登录日志的记录和查询

## 入口与启动

### 模块特点
- **无独立启动：** 作为业务依赖被 ruoyi-admin 调用
- **Service层实现：** 提供业务逻辑服务接口
- **Mapper层支持：** 提供数据访问接口

### 依赖关系
```xml
<!-- ruoyi-admin 中的间接引用 -->
<!-- 通过 ruoyi-framework 依赖传递 -->
<dependency>
    <groupId>com.ruoyi</groupId>
    <artifactId>ruoyi-system</artifactId>
</dependency>
```

## 对外接口

### 核心业务服务（推测）
- **ISysUserService** - 用户管理服务
- **ISysRoleService** - 角色管理服务
- **ISysMenuService** - 菜单管理服务
- **ISysDeptService** - 部门管理服务
- **ISysPostService** - 岗位管理服务
- **ISysConfigService** - 系统配置服务
- **ISysDictService** - 字典管理服务
- **ISysNoticeService** - 通知公告服务
- **ISysLogininforService** - 登录日志服务
- **ISysOperLogService** - 操作日志服务

### 数据访问层（推测）
- **SysUserMapper** - 用户数据访问
- **SysRoleMapper** - 角色数据访问  
- **SysMenuMapper** - 菜单数据访问
- **SysDeptMapper** - 部门数据访问
- 等对应的 Mapper 接口

## 关键依赖与配置

### 主要依赖（推测）
```xml
<!-- 通用工具模块 -->
<dependency>
    <groupId>com.ruoyi</groupId>
    <artifactId>ruoyi-common</artifactId>
</dependency>

<!-- MyBatis -->
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis-spring</artifactId>
</dependency>

<!-- PageHelper 分页 -->
<dependency>
    <groupId>com.github.pagehelper</groupId>
    <artifactId>pagehelper</artifactId>
</dependency>
```

## 数据模型

### 用户权限体系
```
用户 (sys_user)
├── 关联部门 (sys_dept)
├── 关联岗位 (sys_post) 
├── 分配角色 (sys_user_role)
└── 继承权限

角色 (sys_role)
├── 分配菜单 (sys_role_menu)
├── 数据权限范围
└── 状态控制

菜单 (sys_menu)
├── 层级结构 (parent_id)
├── 菜单类型 (目录/菜单/按钮)
├── 权限标识 (perms)
└── 组件路径 (component)
```

### 核心数据表
- **sys_user** - 用户信息表
- **sys_role** - 角色信息表
- **sys_menu** - 菜单权限表
- **sys_dept** - 部门信息表
- **sys_post** - 岗位信息表
- **sys_user_role** - 用户角色关联表
- **sys_role_menu** - 角色菜单关联表
- **sys_config** - 参数配置表
- **sys_dict_type** - 字典类型表
- **sys_dict_data** - 字典数据表
- **sys_notice** - 通知公告表
- **sys_logininfor** - 登录日志表
- **sys_oper_log** - 操作日志表

## 业务功能

### 用户管理
- **用户CRUD：** 新增、修改、删除、查询用户
- **状态管理：** 启用、禁用用户状态
- **密码管理：** 密码重置、修改密码
- **角色分配：** 为用户分配和取消角色
- **数据权限：** 基于部门的数据访问控制

### 角色管理
- **角色CRUD：** 角色的基本增删改查
- **权限分配：** 为角色分配菜单权限
- **数据权限：** 设置角色的数据访问范围
- **状态控制：** 角色的启用和禁用

### 菜单权限
- **菜单树：** 树形结构的菜单管理
- **权限标识：** 按钮级权限控制标识
- **路由配置：** 前端路由和组件映射
- **图标管理：** 菜单图标的配置

### 组织架构  
- **部门管理：** 树形部门结构管理
- **岗位管理：** 岗位信息的维护
- **数据权限：** 基于部门的数据范围控制

### 系统配置
- **参数管理：** 系统运行参数配置
- **字典管理：** 系统字典类型和数据
- **通知公告：** 系统通知信息管理

## 权限控制机制

### 菜单权限
- **层级控制：** 基于菜单树的层级权限
- **按钮权限：** 页面内按钮的显示控制
- **路由权限：** 前端路由的访问控制

### 数据权限  
- **全部数据：** 不限制数据范围
- **自定权限：** 自定义数据范围
- **部门数据：** 本部门数据
- **部门及下级：** 本部门及子部门数据
- **仅本人：** 仅能访问本人数据

## 测试与质量

### 当前状态
- ❌ **单元测试：** 缺少Service层的单元测试
- ❌ **集成测试：** 缺少完整业务流程测试
- ✅ **业务逻辑：** 实现完整的RBAC权限模型
- ✅ **数据完整性：** 通过数据库约束保证

### 测试建议
1. **Service测试：** 对各业务服务进行单元测试
2. **权限测试：** 验证权限控制的正确性
3. **数据权限测试：** 测试不同权限级别的数据访问
4. **业务流程测试：** 测试用户-角色-权限的完整流程

## 常见问题 (FAQ)

### Q: 如何添加新的业务功能？
A: 创建对应的实体、Mapper、Service和Controller，参考现有的CRUD模式。

### Q: 如何实现自定义数据权限？
A: 在Service方法上使用@DataScope注解，系统会自动注入权限SQL。

### Q: 如何添加新的字典类型？
A: 通过系统管理-字典管理功能添加，或直接在sys_dict_type表中插入。

### Q: 如何重置用户密码？
A: 通过用户管理功能重置，或调用ISysUserService的重置密码方法。

### Q: 如何查看用户操作日志？  
A: 通过系统监控-操作日志功能查看，或查询sys_oper_log表。

## 相关文件清单

### 实体类（推测）
- `src/main/java/com/ruoyi/system/domain/` - 系统实体类

### 服务接口（推测）
- `src/main/java/com/ruoyi/system/service/` - 业务服务接口

### 服务实现（推测）  
- `src/main/java/com/ruoyi/system/service/impl/` - 业务服务实现

### 数据访问（推测）
- `src/main/java/com/ruoyi/system/mapper/` - Mapper接口
- `src/main/resources/mapper/system/` - MyBatis XML映射文件

### 配置文件
- `pom.xml` - Maven依赖配置

### 注意事项
由于该模块未进行深度扫描，上述内容基于RuoYi系统的标准业务模块结构推测。实际文件结构和具体实现可能有所不同。建议进一步扫描该模块以获取准确的文件清单和实现细节。