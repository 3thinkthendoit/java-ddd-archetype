# java-ddd-archetype

用于生成 **Java 17 + Spring Boot 3.5.9** DDD 四层多模块工程的 Maven Archetype（结构对齐 `think-oms`），内含最小 Hello 示例，以及**不绑定任何 IDE 的 AI 编码 Skill**。

> **语言：** [English](./README.md) · 中文

## 本地安装

```bash
cd D:/code/java/java-ddd-archetype
mvn clean install
```

## 生成工程

```bash
cd D:/code/java
mvn archetype:generate \
  -DarchetypeGroupId=com.think \
  -DarchetypeArtifactId=java-ddd-archetype \
  -DarchetypeVersion=1.0.0 \
  -DgroupId=com.example \
  -DartifactId=demo-ddd \
  -Dversion=1.0.0-SNAPSHOT \
  -Dpackage=com.example.demo \
  -DinteractiveMode=false
```

然后：

```bash
cd demo-ddd
mvn -pl demo-ddd-interface -am spring-boot:run
curl "http://localhost:8080/api/hello/ping?name=DDD"
```

## 生成后的 Hello — 分层架构

生成结果是标准 **DDD 四层** 工程。Hello 是一条完整垂直切片，后续新功能按同样方式落层。

### 模块依赖

```text
demo-ddd-interface  →  application + infrastructure   （可启动，北向）
demo-ddd-application → domain                         （用例编排）
demo-ddd-infrastructure → domain                      （ACL + 技术适配）
demo-ddd-domain       → （无业务模块依赖）              （聚合 / 端口 / PL）
```

外层依赖内层。domain 不依赖 application、infrastructure、interface。

### Hello 调用链

**写路径（命令）**

```text
osh.controller.HelloController
  → local.HelloLocalService
  → app.service.HelloAppService
      → HelloAggregate.create(command)
      → HelloDomainService.enrich(aggregate)   # 生成问候语
      → HelloRepository.save(aggregate)
  → infrastructure.acl.repository.HelloRepositoryImpl  （内存实现）
```

**读路径（查询）** — **不经过** 聚合：

```text
HelloController → HelloLocalService → HelloAppService.query
  → HelloRepository.findById → HelloInfo
```

### Hello 类放在哪一层

| 层 | 类 | 包（示例 `com.example.demo`） |
|----|----|------------------------------|
| 接口层 | `Application`、`HelloController` | `...osh` / `...osh.controller` |
| 本地门面 | `HelloLocalService` | `...local` |
| 应用层 | `HelloAppService` | `...app.service` |
| 领域层 | `HelloAggregate`、`HelloId` | `...domain.model.aggregate.hello` / `...domain.model.dp` |
| 领域层 | `HelloCreateCommand`、`HelloQuery` | `...domain.port.pl.osh.command` / `...query` |
| 领域层 | `HelloInfo` | `...domain.port.pl.acl` |
| 领域层 | `HelloRepository`、`HelloDomainService` | `...domain.port.repository` / `...domain.service` |
| 基础设施层 | `HelloRepositoryImpl` | `...infrastructure.acl.repository` |

`osh` = Open Host Service（北向）。`acl` = Anti-Corruption Layer（南向）。

## AI Skill（通用，非 Cursor 专用）

生成出的工程会带上：

| 文件 | 作用 |
|------|------|
| `skills/ddd-scaffold/SKILL.md` | 脚手架编码规则 |
| `AGENTS.md` | 引导任意 AI Agent 先读 Skill |

本仓库源文件：[`skills/ddd-scaffold/SKILL.md`](skills/ddd-scaffold/SKILL.md)。

Cursor / Claude Code / Codex / Copilot / Windsurf 等工具均可使用——把 Agent 指向这两份文件即可。

## 锁定技术栈

| 项 | 版本 |
|----|------|
| Java | 17 |
| Spring Boot | 3.5.9 |
| MyBatis-Plus | 3.5.17 |
| Druid | 1.2.28 |
| RocketMQ starter | 2.3.6 |
| OkHttp | 4.12.0 |
| Lombok | 1.18.42 |
