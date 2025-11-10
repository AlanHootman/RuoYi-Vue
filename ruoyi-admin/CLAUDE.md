[根目录](../CLAUDE.md) > **ruoyi-admin**

# ruoyi-admin 模块文档

## 变更记录 (Changelog)

### 2025-08-30 16:29:18
- 初始化模块文档
- 识别模块入口和API控制器结构
- 记录配置文件和启动方式

## 模块职责

ruoyi-admin 是整个 RuoYi-Vue 系统的**Web服务入口模块**，作为 Spring Boot 应用程序的主模块，负责：

- **应用启动：** 包含 Spring Boot 主启动类
- **Web接口：** 提供所有 RESTful API 端点  
- **依赖集成：** 集成所有业务模块（framework、system、quartz、generator）
- **配置管理：** 包含应用程序的核心配置文件

## 入口与启动

### 主启动类
**文件：** `src/main/java/com/ruoyi/RuoYiApplication.java`

```java
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class RuoYiApplication {
    public static void main(String[] args) {
        SpringApplication.run(RuoYiApplication.class, args);
    }
}
```

**特点：**
- 排除了默认数据源自动配置，使用自定义的多数据源配置
- 包含启动成功的ASCII艺术字输出

### 启动方式
```bash
# 方式1：Maven命令启动
mvn spring-boot:run

# 方式2：Java命令启动  
java -jar ruoyi-admin.jar

# 方式3：使用脚本启动
./ry.sh          # Linux/Mac
ry.bat           # Windows
```

## 对外接口

### API控制器结构

#### 1. 通用控制器 (`common`)
- **CaptchaController.java** - 验证码生成接口

#### 2. 系统管理 (`system`) 
- **SysConfigController.java** - 系统配置管理
- **SysDeptController.java** - 部门管理
- **SysDictDataController.java** - 字典数据管理  
- **SysDictTypeController.java** - 字典类型管理
- **SysIndexController.java** - 系统首页信息
- **SysLoginController.java** - 登录认证
- **SysMenuController.java** - 菜单权限管理
- **SysNoticeController.java** - 通知公告
- **SysPostController.java** - 岗位管理
- **SysRegisterController.java** - 用户注册
- **SysRoleController.java** - 角色管理
- **SysUserController.java** - 用户管理

#### 3. 系统监控 (`monitor`)
- **CacheController.java** - 缓存监控
- **ServerController.java** - 服务器信息监控
- **SysLogininforController.java** - 登录日志
- **SysOperlogController.java** - 操作日志  
- **SysUserOnlineController.java** - 在线用户监控

#### 4. 开发工具 (`tool`)
- **TestController.java** - 测试接口

### API规范
- **请求路径：** `/system/*`, `/monitor/*`, `/tool/*`
- **响应格式：** 统一使用 AjaxResult 封装
- **权限控制：** 使用 `@PreAuthorize` 注解
- **参数校验：** 使用 Bean Validation 注解

## 关键依赖与配置

### Maven依赖
```xml
<!-- 核心业务模块 -->
<dependency>
    <groupId>com.ruoyi</groupId>
    <artifactId>ruoyi-framework</artifactId>
</dependency>

<!-- 定时任务模块 -->  
<dependency>
    <groupId>com.ruoyi</groupId>
    <artifactId>ruoyi-quartz</artifactId>
</dependency>

<!-- 代码生成模块 -->
<dependency>
    <groupId>com.ruoyi</groupId>
    <artifactId>ruoyi-generator</artifactId>
</dependency>

<!-- Swagger API文档 -->
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-boot-starter</artifactId>
</dependency>

<!-- MySQL数据库驱动 -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
```

### 核心配置文件

#### application.yml
```yaml
# 服务器配置
server:
  port: 8080
  servlet:
    context-path: /

# 若依系统配置
ruoyi:
  name: RuoYi
  version: 3.9.0
  profile: /path/to/upload
  captchaType: math

# Spring配置  
spring:
  profiles:
    active: druid
  redis:
    host: 14.103.253.237
    port: 16380
    database: 3

# 认证配置
token:
  header: Authorization
  secret: abcdefghijklmnopqrstuvwxyz
  expireTime: 30
```

#### application-druid.yml  
- 数据库连接池配置
- 多数据源配置
- 监控配置

## 数据模型

### 主要实体
通过依赖的 ruoyi-system 模块提供：
- **用户体系：** SysUser, SysRole, SysMenu, SysDept
- **字典配置：** SysDictType, SysDictData  
- **系统配置：** SysConfig, SysNotice
- **操作日志：** SysOperLog, SysLogininfor

### 数据库表
参考 `sql/ry_20250522.sql` 包含的核心表：
- sys_user - 用户表
- sys_role - 角色表  
- sys_menu - 菜单表
- sys_dept - 部门表
- 等 20+ 张系统表

## 测试与质量

### 当前状态
- ❌ **单元测试：** 无测试用例
- ❌ **集成测试：** 无API测试
- ✅ **API文档：** 通过 Swagger 自动生成
- ✅ **代码质量：** 遵循标准 Spring Boot 项目结构

### 测试建议
1. **单元测试：** 为各 Controller 添加 MockMvc 测试
2. **集成测试：** 使用 @SpringBootTest 测试完整业务流程  
3. **性能测试：** 对高频API进行压力测试
4. **安全测试：** 验证权限控制和输入校验

## 常见问题 (FAQ)

### Q: 如何添加新的API接口？
A: 在对应的 Controller 包下创建控制器类，使用 `@RestController` 和 `@RequestMapping` 注解。

### Q: 如何配置新的数据源？  
A: 在 application-druid.yml 中添加数据源配置，并在 ruoyi-framework 模块中注册。

### Q: 如何处理跨域请求？
A: 在 ruoyi-framework 的 CorsConfig 中已配置CORS支持。

### Q: 如何启用/禁用Swagger文档？
A: 修改 application.yml 中的 `swagger.enabled` 配置项。

## 相关文件清单

### 核心文件
- `src/main/java/com/ruoyi/RuoYiApplication.java` - 启动类
- `src/main/java/com/ruoyi/RuoYiServletInitializer.java` - Servlet 初始化  
- `src/main/resources/application.yml` - 主配置文件
- `src/main/resources/application-druid.yml` - 数据源配置
- `src/main/resources/logback.xml` - 日志配置

### 控制器目录
- `src/main/java/com/ruoyi/web/controller/` - 所有API控制器
- `src/main/java/com/ruoyi/web/core/config/` - Web配置类

### 资源文件
- `src/main/resources/banner.txt` - 启动横幅
- `src/main/resources/i18n/` - 国际化资源
- `src/main/resources/mybatis/` - MyBatis配置

### 构建文件
- `pom.xml` - Maven项目配置