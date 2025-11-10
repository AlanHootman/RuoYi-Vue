[根目录](../CLAUDE.md) > **ruoyi-common**

# ruoyi-common 通用工具模块文档

## 变更记录 (Changelog)

### 2025-08-30 16:29:18
- 初始化通用模块文档
- 识别注解、工具类、异常处理等核心功能
- 记录模块依赖关系和使用方式

## 模块职责

ruoyi-common 是 RuoYi-Vue 系统的**通用工具模块**，作为底层基础库，为其他模块提供：

- **通用注解：** 数据权限、日志记录、Excel导出等注解
- **核心域对象：** 基础实体类、响应对象、分页对象等
- **工具类库：** 数据类型转换、文本处理、加密解密等
- **异常体系：** 统一的异常定义和处理机制
- **过滤器：** XSS防护、重复请求处理等

## 入口与启动

### 模块特点
- **无独立启动：** 作为依赖库被其他模块引用
- **自动配置：** 通过 Spring Boot 自动配置机制生效
- **配置入口：** `com.ruoyi.common.config.RuoYiConfig`

### 依赖关系
```xml
<!-- 其他模块通过以下方式引用 -->
<dependency>
    <groupId>com.ruoyi</groupId>
    <artifactId>ruoyi-common</artifactId>
    <version>3.9.0</version>
</dependency>
```

## 对外接口

### 核心注解

#### 数据权限注解
- **@DataScope** - 数据权限过滤
- **@DataSource** - 多数据源切换

#### 日志记录注解  
- **@Log** - 操作日志记录
- **@Anonymous** - 匿名访问（跳过认证）

#### 功能增强注解
- **@Excel/@Excels** - Excel导入导出标记
- **@RateLimiter** - 接口限流  
- **@RepeatSubmit** - 防重复提交
- **@Sensitive** - 敏感数据脱敏

### 核心域对象

#### 响应对象
```java
// 统一响应结果
public class AjaxResult extends HashMap<String, Object>

// 通用响应对象
public class R<T> 

// 分页数据对象  
public class TableDataInfo
```

#### 基础实体
```java
// 实体基类
public class BaseEntity 

// 树形实体基类
public class TreeEntity<T>

// 树形选择对象
public class TreeSelect
```

#### 核心实体
- **SysUser** - 用户实体
- **SysRole** - 角色实体  
- **SysMenu** - 菜单实体
- **SysDept** - 部门实体
- **SysDictType/SysDictData** - 字典实体

## 关键依赖与配置

### 主要依赖
```xml
<!-- Spring Boot核心 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<!-- Spring Security -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>

<!-- Redis -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>

<!-- JSON处理 -->
<dependency>
    <groupId>com.alibaba.fastjson2</groupId>
    <artifactId>fastjson2</artifactId>
</dependency>
```

### 配置类
- **RuoYiConfig** - 系统基础配置
- **ThreadPoolConfig** - 线程池配置（如存在）

## 工具类库

### 数据处理工具
- **Convert** - 数据类型转换
- **Arith** - 精确计算工具
- **StringUtils** - 字符串处理扩展

### 安全工具
- **SecurityUtils** - 安全上下文工具
- **PasswordUtils** - 密码处理工具  
- **JwtUtils** - JWT令牌工具

### Redis工具
- **RedisCache** - Redis缓存封装
- **CacheUtils** - 缓存操作工具

### 文本处理
- **CharsetKit** - 字符集工具
- **StrFormatter** - 字符串格式化
- **Convert** - 类型转换

## 异常体系

### 异常层次结构
```
BaseException (基础异常)
├── ServiceException (业务异常)
├── GlobalException (全局异常)  
├── DemoModeException (演示模式异常)
├── UtilException (工具类异常)
├── FileException (文件异常)
│   ├── FileUploadException
│   ├── FileSizeLimitExceededException  
│   └── InvalidExtensionException
├── UserException (用户异常)
│   ├── UserNotExistsException
│   ├── UserPasswordNotMatchException
│   └── CaptchaException
└── TaskException (任务异常)
```

### 使用示例
```java
// 抛出业务异常
throw new ServiceException("用户名已存在");

// 抛出文件异常
throw new FileSizeLimitExceededException(maxSize);
```

## 过滤器与安全

### Web过滤器
- **XssFilter** - XSS攻击防护
- **RepeatableFilter** - 请求重复读取支持
- **RepeatedlyRequestWrapper** - 请求包装器

### 安全特性
- **XSS防护：** 过滤恶意脚本注入
- **SQL注入防护：** 通过MyBatis预处理防护
- **CSRF防护：** 通过Spring Security提供
- **敏感数据脱敏：** 使用@Sensitive注解

## 数据权限

### 数据权限实现
使用 `@DataScope` 注解实现基于角色的数据权限控制：

```java
@DataScope(deptAlias = "d", userAlias = "u")
public List<SysUser> selectUserList(SysUser user)
```

### 权限级别
1. **全部数据权限** - 可查看所有数据
2. **自定数据权限** - 可查看指定部门数据  
3. **部门数据权限** - 可查看本部门数据
4. **部门及以下数据权限** - 可查看本部门及子部门数据
5. **仅本人数据权限** - 只能查看自己的数据

## 测试与质量

### 当前状态
- ❌ **单元测试：** 缺少工具类的单元测试
- ❌ **集成测试：** 缺少注解功能的集成测试  
- ✅ **代码规范：** 遵循Java编码规范
- ✅ **文档注释：** 核心类和方法包含Javadoc

### 测试建议
1. **工具类测试：** 对Convert、StringUtils等工具类添加完整测试
2. **注解测试：** 测试@DataScope、@Excel等注解的功能
3. **异常测试：** 验证异常抛出和处理逻辑
4. **安全测试：** 测试XSS过滤和数据权限控制

## 常见问题 (FAQ)

### Q: 如何使用数据权限注解？
A: 在Mapper方法上添加@DataScope注解，系统会自动注入权限SQL条件。

### Q: 如何自定义异常处理？
A: 继承BaseException创建自定义异常，在GlobalExceptionHandler中添加处理逻辑。

### Q: 如何使用Excel导入导出？
A: 在实体字段上添加@Excel注解，使用ExcelUtil工具类进行操作。

### Q: 如何实现接口限流？
A: 在Controller方法上添加@RateLimiter注解，配置限流参数。

### Q: 如何自定义数据源切换？
A: 使用@DataSource注解指定数据源名称，需要配合多数据源配置。

## 相关文件清单

### 注解定义
- `src/main/java/com/ruoyi/common/annotation/` - 所有自定义注解

### 核心域对象  
- `src/main/java/com/ruoyi/common/core/domain/` - 基础实体和响应对象
- `src/main/java/com/ruoyi/common/core/domain/entity/` - 系统核心实体
- `src/main/java/com/ruoyi/common/core/domain/model/` - 业务模型对象

### 工具类库
- `src/main/java/com/ruoyi/common/utils/` - 各类工具函数

### 异常定义
- `src/main/java/com/ruoyi/common/exception/` - 异常体系定义

### 配置和常量
- `src/main/java/com/ruoyi/common/config/` - 配置类
- `src/main/java/com/ruoyi/common/constant/` - 常量定义
- `src/main/java/com/ruoyi/common/enums/` - 枚举定义

### 过滤器
- `src/main/java/com/ruoyi/common/filter/` - Web过滤器实现