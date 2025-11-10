[根目录](../CLAUDE.md) > **ruoyi-quartz**

# ruoyi-quartz 定时任务模块文档

## 变更记录 (Changelog)

### 2025-08-30 16:29:18
- 初始化定时任务模块文档
- 识别Quartz调度器集成方式
- 记录任务管理和调度功能

## 模块职责

ruoyi-quartz 是 RuoYi-Vue 系统的**定时任务模块**，基于 Quartz 框架实现：

- **任务调度：** 基于Cron表达式的定时任务调度
- **任务管理：** 任务的增删改查、启动停止
- **执行监控：** 任务执行状态和结果监控
- **日志记录：** 任务执行日志的记录和查询
- **集群支持：** 支持多实例集群部署

## 入口与启动

### 模块特点
- **无独立启动：** 集成到主应用中自动初始化
- **自动调度：** 应用启动后根据配置自动执行任务
- **数据库驱动：** 任务配置存储在数据库中

### 集成方式
```xml
<!-- ruoyi-admin 中的引用 -->
<dependency>
    <groupId>com.ruoyi</groupId>
    <artifactId>ruoyi-quartz</artifactId>
</dependency>
```

## 对外接口

### 核心组件（推测）
- **QuartzConfig** - Quartz调度器配置
- **ISysJobService** - 定时任务业务服务
- **ISysJobLogService** - 任务执行日志服务
- **QuartzDisallowConcurrentExecution** - 不允许并发执行的任务
- **QuartzJobExecution** - 允许并发执行的任务

### 任务管理功能
- **任务调度控制：** 启动、暂停、恢复、删除任务
- **立即执行：** 手动触发任务执行
- **任务状态监控：** 查看任务运行状态
- **执行日志查询：** 查看任务执行历史和结果

## 关键依赖与配置

### 主要依赖（推测）
```xml
<!-- Quartz 调度器 -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context-support</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-quartz</artifactId>
</dependency>

<!-- 通用工具模块 -->
<dependency>
    <groupId>com.ruoyi</groupId>
    <artifactId>ruoyi-common</artifactId>
</dependency>
```

### 数据库支持
使用 `sql/quartz.sql` 创建 Quartz 所需的数据表：
- **QRTZ_JOB_DETAILS** - 任务详情表
- **QRTZ_TRIGGERS** - 触发器表
- **QRTZ_CRON_TRIGGERS** - Cron触发器表
- **QRTZ_SCHEDULER_STATE** - 调度器状态表
- 等 Quartz 标准表结构

## 数据模型

### 任务实体（推测）
```java
// 定时任务信息
public class SysJob {
    private Long jobId;           // 任务ID
    private String jobName;       // 任务名称
    private String jobGroup;      // 任务组名
    private String invokeTarget;  // 调用目标字符串
    private String cronExpression; // cron执行表达式
    private String misfirePolicy; // 计划执行错误策略
    private String concurrent;    // 是否并发执行
    private String status;        // 状态
    // ... 其他字段
}

// 定时任务调度日志
public class SysJobLog {
    private Long jobLogId;        // 任务日志ID
    private String jobName;       // 任务名称
    private String jobGroup;      // 任务组名
    private String invokeTarget;  // 调用目标字符串
    private String jobMessage;    // 日志信息
    private String status;        // 执行状态
    private Date startTime;       // 开始时间
    private Date stopTime;        // 结束时间
    // ... 其他字段
}
```

### 数据库表
- **sys_job** - 定时任务配置表
- **sys_job_log** - 定时任务执行日志表

## 任务执行机制

### 任务类型
- **Bean任务：** 调用Spring Bean的方法
- **Class任务：** 执行指定的Java类
- **Script任务：** 执行脚本文件（如支持）

### 调用方式示例
```
# Bean调用示例
ryTask.ryNoParams          # 无参方法调用
ryTask.ryParams('hello')   # 有参方法调用

# 多参数调用
ryTask.ryMultipleParams('hello', true, 2000L, 316.50D, 100)
```

### 执行策略
- **立即执行：** 错过执行时间立即执行
- **执行一次：** 错过执行时间执行一次
- **放弃执行：** 错过执行时间不执行

## 并发控制

### 并发策略
- **允许并发：** 任务可以同时多个实例运行
- **禁止并发：** 同一任务同时只能有一个实例运行

### 集群支持
- **负载均衡：** 任务在集群节点间自动分配
- **故障转移：** 节点故障时任务自动转移
- **状态同步：** 集群间任务状态实时同步

## 监控与日志

### 执行监控
- **任务状态：** 正常、暂停、错误状态监控
- **执行统计：** 成功次数、失败次数统计
- **性能监控：** 任务执行耗时分析

### 日志管理
- **执行日志：** 详细的任务执行过程记录
- **异常日志：** 任务执行异常的详细信息
- **日志清理：** 定期清理历史日志记录

## 测试与质量

### 当前状态
- ❌ **单元测试：** 缺少任务调度逻辑测试
- ❌ **集成测试：** 缺少完整调度流程测试
- ✅ **功能验证：** 可通过管理界面验证功能
- ✅ **日志监控：** 完整的执行日志记录

### 测试建议
1. **调度测试：** 验证任务按Cron表达式正确执行
2. **并发测试：** 测试并发控制策略是否生效
3. **集群测试：** 验证集群环境下任务分配和故障转移
4. **性能测试：** 测试大量任务并发执行的性能表现

## 常见问题 (FAQ)

### Q: 如何添加自定义定时任务？
A: 创建任务处理类，在系统管理-定时任务中添加任务配置，设置Cron表达式。

### Q: 如何处理任务执行异常？
A: 任务异常会被记录到sys_job_log表，可配置异常处理策略和通知机制。

### Q: Cron表达式如何编写？
A: 格式为"秒 分 时 日 月 周 年"，例如"0 0 12 * * ?"表示每天12点执行。

### Q: 如何实现任务的集群部署？
A: 配置相同的数据库，Quartz会自动实现集群调度和负载均衡。

### Q: 如何停止正在执行的任务？
A: 通过管理界面的"立即执行"功能可以中断任务，或者重启应用。

## 相关文件清单

### 核心类（推测）
- `src/main/java/com/ruoyi/quartz/domain/` - 任务实体类
- `src/main/java/com/ruoyi/quartz/service/` - 任务业务服务
- `src/main/java/com/ruoyi/quartz/util/` - Quartz工具类
- `src/main/java/com/ruoyi/quartz/task/` - 系统内置任务类

### 配置文件
- `pom.xml` - Maven依赖配置
- `src/main/resources/` - 可能包含Quartz配置文件

### 数据库脚本
- `sql/quartz.sql` - Quartz框架所需表结构

### 注意事项
由于该模块未进行深度扫描，上述内容基于标准Quartz集成模块的结构推测。实际实现可能包含更多自定义功能和配置。建议进一步扫描该模块以获取准确的文件结构和实现细节。