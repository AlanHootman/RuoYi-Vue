[根目录](../CLAUDE.md) > **ruoyi-generator**

# ruoyi-generator 代码生成模块文档

## 变更记录 (Changelog)

### 2025-08-30 16:29:18
- 初始化代码生成模块文档
- 识别代码生成器功能和模板体系
- 记录前后端代码生成能力

## 模块职责

ruoyi-generator 是 RuoYi-Vue 系统的**代码生成工具模块**，提供快速开发能力：

- **数据库逆向：** 从数据库表结构生成代码
- **代码模板：** 基于 Velocity 模板引擎的代码生成
- **多层代码：** 生成 Entity、Mapper、Service、Controller 等完整分层代码
- **前端代码：** 生成 Vue.js 页面和API接口代码
- **配置化生成：** 支持字段配置、生成选项定制

## 入口与启动

### 模块特点
- **无独立启动：** 集成到主应用提供生成服务
- **Web界面：** 通过系统工具-代码生成菜单访问
- **在线生成：** 实时预览和下载生成代码

### 访问方式
- **管理界面：** 系统工具 > 代码生成
- **API接口：** `/tool/gen/*` 相关接口

## 对外接口

### 核心服务（推测）
- **IGenTableService** - 代码生成表业务服务
- **IGenTableColumnService** - 表字段配置服务
- **GenUtils** - 代码生成工具类
- **VelocityEngine** - 模板引擎配置

### 主要功能
- **表导入：** 从数据库导入表结构
- **字段配置：** 配置字段属性、验证规则、显示方式
- **代码预览：** 实时预览生成的代码
- **代码下载：** 打包下载完整的前后端代码
- **批量生成：** 支持多表批量代码生成

## 关键依赖与配置

### 主要依赖（推测）
```xml
<!-- Velocity 模板引擎 -->
<dependency>
    <groupId>org.apache.velocity</groupId>
    <artifactId>velocity-engine-core</artifactId>
</dependency>

<!-- 通用工具模块 -->
<dependency>
    <groupId>com.ruoyi</groupId>
    <artifactId>ruoyi-common</artifactId>
</dependency>

<!-- 数据库相关 -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
```

### 模板配置
模板文件位置（推测）：
- `src/main/resources/vm/` - Velocity模板文件目录
- `vm/java/` - Java后端代码模板
- `vm/js/` - 前端JavaScript代码模板
- `vm/xml/` - MyBatis XML映射文件模板
- `vm/sql/` - SQL脚本模板

## 数据模型

### 生成表信息（推测）
```java
// 代码生成业务表
public class GenTable {
    private Long tableId;         // 编号
    private String tableName;     // 表名称
    private String tableComment;  // 表描述
    private String subTableName;  // 关联子表的表名
    private String className;     // 实体类名称
    private String packageName;   // 生成包路径
    private String moduleName;    // 生成模块名
    private String businessName;  // 生成业务名
    private String functionName;  // 生成功能名
    private String functionAuthor; // 生成功能作者
    private String genType;       // 生成代码方式
    private String genPath;       // 生成路径
    private String options;       // 其它生成选项
    // ... 其他字段
}

// 代码生成业务表字段
public class GenTableColumn {
    private Long columnId;        // 编号
    private Long tableId;         // 归属表编号
    private String columnName;    // 列名称
    private String columnComment; // 列描述
    private String columnType;    // 列类型
    private String javaType;      // JAVA类型
    private String javaField;     // JAVA字段名
    private String htmlType;      // HTML显示类型
    private String queryType;     // 查询方式
    // ... 其他字段
}
```

### 数据库表
- **gen_table** - 代码生成业务表
- **gen_table_column** - 代码生成业务表字段

## 生成能力

### 后端代码生成
- **Entity实体：** 数据库表对应的Java实体类
- **Mapper接口：** MyBatis数据访问层接口
- **Mapper XML：** MyBatis SQL映射文件
- **Service接口：** 业务逻辑层接口
- **Service实现：** 业务逻辑层实现类
- **Controller：** REST API控制器
- **SQL脚本：** 菜单权限SQL脚本

### 前端代码生成
- **Vue页面：** 列表、新增、修改页面组件
- **API接口：** 前端API调用封装
- **路由配置：** Vue Router路由配置
- **菜单配置：** 系统菜单SQL脚本

### 模板特性
- **字段类型映射：** 数据库类型到Java类型的智能映射
- **UI组件选择：** 根据字段类型自动选择合适的前端组件
- **验证规则：** 自动生成前后端字段验证规则
- **CRUD操作：** 完整的增删改查功能代码

## 配置选项

### 生成配置
- **生成模板：** 单表CRUD、树表CRUD、主子表CRUD
- **生成信息：** 作者、包路径、模块名、业务名
- **生成路径：** 自定义代码生成输出路径

### 字段配置
- **显示类型：** 输入框、下拉框、日期控件等
- **查询方式：** 等于、不等于、大于、小于、模糊查询等  
- **字段属性：** 主键、必填、插入、编辑、查询、列表等

### 关联配置
- **外键关联：** 支持主子表关联代码生成
- **字典配置：** 字段关联字典类型的下拉选择

## 模板引擎

### Velocity模板
使用 Apache Velocity 作为模板引擎：
- **语法简洁：** 使用 `${}` 语法进行变量替换
- **条件判断：** 支持 `#if/#else/#end` 条件语句
- **循环遍历：** 支持 `#foreach` 循环语句
- **工具方法：** 内置字符串、日期等工具方法

### 模板变量（推测）
- **${tableName}** - 表名
- **${className}** - 类名
- **${packageName}** - 包名
- **${columns}** - 字段列表
- **${author}** - 作者
- **${datetime}** - 生成时间

## 测试与质量

### 当前状态
- ❌ **单元测试：** 缺少代码生成逻辑测试
- ❌ **模板测试：** 缺少模板渲染测试
- ✅ **功能验证：** 可通过界面验证生成代码
- ✅ **模板完整：** 提供完整的前后端代码模板

### 测试建议
1. **生成测试：** 验证不同表结构的代码生成正确性
2. **模板测试：** 测试模板语法和变量替换的准确性
3. **集成测试：** 验证生成代码的编译和运行正确性
4. **边界测试：** 测试特殊字段类型和复杂表结构的处理

## 常见问题 (FAQ)

### Q: 如何自定义代码生成模板？
A: 在 `src/main/resources/vm/` 目录下修改对应的 Velocity 模板文件。

### Q: 如何添加新的字段类型映射？
A: 在 GenUtils 工具类中添加数据库类型到Java类型的映射规则。

### Q: 如何生成主子表关联代码？
A: 在表配置中设置关联子表，选择主子表生成模板即可。

### Q: 生成的代码如何集成到项目中？
A: 下载生成的代码包，按照目录结构复制到对应的项目模块中，导入菜单SQL即可。

### Q: 如何修改生成代码的包路径？
A: 在代码生成界面的"生成信息"中修改"生成包路径"配置项。

## 相关文件清单

### 核心类（推测）
- `src/main/java/com/ruoyi/generator/domain/` - 生成相关实体类
- `src/main/java/com/ruoyi/generator/service/` - 生成业务服务
- `src/main/java/com/ruoyi/generator/mapper/` - 生成数据访问层
- `src/main/java/com/ruoyi/generator/util/` - 代码生成工具类

### 模板文件（推测）
- `src/main/resources/vm/java/` - Java代码模板
- `src/main/resources/vm/js/` - JavaScript代码模板  
- `src/main/resources/vm/xml/` - XML配置模板
- `src/main/resources/vm/sql/` - SQL脚本模板

### 配置文件
- `pom.xml` - Maven依赖配置
- `src/main/resources/generator.yml` - 生成器配置文件（如存在）

### 注意事项
由于该模块未进行深度扫描，上述内容基于标准代码生成器模块的功能推测。实际模板和配置可能包含更多定制功能。建议进一步扫描该模块以获取准确的模板文件和实现细节。