[根目录](../CLAUDE.md) > **ruoyi-framework**

# ruoyi-framework 框架核心模块文档

## 变更记录 (Changelog)

### 2025-08-30 16:29:18
- 初始化框架模块文档
- 识别核心框架配置和安全体系
- 记录数据源和缓存配置

## 模块职责

ruoyi-framework 是 RuoYi-Vue 系统的**核心框架模块**，负责系统底层架构和核心配置：

- **安全框架：** Spring Security 配置和JWT认证
- **数据源管理：** 多数据源配置和事务管理  
- **缓存配置：** Redis 缓存配置和管理
- **Web配置：** CORS、拦截器、异常处理等
- **MyBatis配置：** 数据库映射和分页插件

## 入口与启动

### 模块特点
- **无独立启动：** 作为框架依赖被 ruoyi-admin 引用
- **自动配置：** 通过 Spring Boot 自动配置生效
- **配置驱动：** 基于配置文件和注解驱动

### 被依赖关系
```xml
<!-- ruoyi-admin 中的引用 -->
<dependency>
    <groupId>com.ruoyi</groupId>
    <artifactId>ruoyi-framework</artifactId>
</dependency>
```

## 对外接口

### 核心配置类（推测）
- **SecurityConfig** - Spring Security 安全配置
- **DruidConfig** - 数据源配置
- **RedisConfig** - Redis 缓存配置  
- **MyBatisConfig** - MyBatis 框架配置
- **CorsConfig** - 跨域请求配置
- **SwaggerConfig** - API 文档配置

### 认证与授权
- **JWT Token管理** - 令牌生成、验证、刷新
- **用户认证服务** - 登录认证和用户信息获取
- **权限控制** - 基于角色和资源的权限验证

## 关键依赖与配置

### 核心依赖（推测）
```xml
<!-- Spring Security -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>

<!-- MyBatis Plus -->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
</dependency>

<!-- Druid 连接池 -->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid-spring-boot-starter</artifactId>
</dependency>

<!-- JWT -->
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt</artifactId>
</dependency>
```

## 数据源配置

### 多数据源支持
- **主数据源：** MySQL 主库连接
- **从数据源：** MySQL 从库连接（如配置）
- **动态切换：** 基于注解的数据源路由

### 连接池配置
- **Druid监控：** SQL监控、慢查询分析
- **连接池优化：** 最大连接数、超时配置
- **SQL防护：** SQL注入检测和防护

## 安全框架

### Spring Security 配置
- **认证方式：** JWT Token 认证
- **授权策略：** 基于URL和方法级权限控制
- **会话管理：** 无状态会话（Stateless）
- **密码策略：** BCrypt 加密存储

### JWT 令牌管理  
- **令牌生成：** 包含用户信息和权限
- **令牌验证：** 请求拦截和令牌有效性检查
- **令牌刷新：** 自动刷新和过期处理
- **安全配置：** 签名密钥和有效期管理

## 缓存管理

### Redis 集成
- **缓存配置：** Redis 连接和序列化配置
- **缓存策略：** 用户信息、权限数据、字典数据缓存
- **缓存工具：** RedisTemplate 和 RedisCache 封装
- **过期策略：** TTL 配置和自动过期

## 数据访问

### MyBatis 配置
- **映射配置：** XML 和注解混合映射
- **分页插件：** PageHelper 分页支持
- **类型处理：** 自定义类型转换器
- **SQL监控：** 慢查询检测和优化建议

### 事务管理
- **声明式事务：** @Transactional 注解支持
- **事务传播：** 多数据源事务协调
- **回滚策略：** 异常回滚和提交策略

## 测试与质量

### 当前状态
- ❌ **单元测试：** 缺少配置类的测试
- ❌ **集成测试：** 缺少安全和数据源测试
- ✅ **配置验证：** 通过启动验证配置正确性
- ✅ **监控支持：** Druid 提供运行时监控

### 测试建议
1. **安全测试：** 测试JWT生成、验证和权限控制
2. **数据源测试：** 验证多数据源切换和连接池
3. **缓存测试：** 验证Redis缓存读写和过期策略  
4. **集成测试：** 完整的认证授权流程测试

## 常见问题 (FAQ)

### Q: 如何配置多数据源？
A: 在配置类中定义多个DataSource，使用@DataSource注解进行切换。

### Q: 如何自定义JWT令牌内容？
A: 修改JwtUtils中的令牌生成逻辑，添加自定义声明。

### Q: 如何配置Redis集群？
A: 在application.yml中配置Redis Cluster节点信息。

### Q: 如何添加新的权限验证规则？
A: 在SecurityConfig中配置URL权限规则，或使用@PreAuthorize注解。

### Q: 如何监控SQL执行性能？
A: 访问Druid监控页面 /druid 查看SQL执行统计。

## 相关文件清单

### 配置类目录（推测）
- `src/main/java/com/ruoyi/framework/config/` - 所有框架配置类

### 安全相关（推测）  
- `src/main/java/com/ruoyi/framework/security/` - 安全配置和服务
- `src/main/java/com/ruoyi/framework/web/` - Web层配置

### 数据源相关（推测）
- `src/main/java/com/ruoyi/framework/datasource/` - 数据源配置

### 工具和服务（推测）
- `src/main/java/com/ruoyi/framework/util/` - 框架工具类
- `src/main/java/com/ruoyi/framework/service/` - 框架服务类

### 配置文件
- `pom.xml` - Maven依赖配置

### 注意事项
由于该模块未进行深度扫描，上述内容基于典型Spring Boot框架模块的标准结构推测。实际文件结构可能有所不同。建议进一步扫描该模块以获取准确信息。