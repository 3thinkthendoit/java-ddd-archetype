# ${rootArtifactId}

由 [`java-ddd-archetype`](https://github.com/) 生成的 DDD 四层 Spring Boot 工程。

> **语言：** [English](./README.md) · 中文

## 模块

```text
${rootArtifactId}-domain          # 聚合 / 领域服务 / 端口 / PL
${rootArtifactId}-application     # AppService 用例编排
${rootArtifactId}-infrastructure  # ACL 端口实现 + 技术组件
${rootArtifactId}-interface       # OSH 北向入口 + Spring Boot 启动
```

依赖规则：**外层依赖内层**；domain 不依赖 application / infrastructure / interface。

```text
${rootArtifactId}-interface  →  application + infrastructure
${rootArtifactId}-application → domain
${rootArtifactId}-infrastructure → domain
${rootArtifactId}-domain       → （无业务模块依赖）
```

## Hello 分层架构

Hello 是示例垂直切片。新用例按同样方式落层。

### 写路径（命令）

```text
${package}.osh.controller.HelloController
  → ${package}.local.HelloLocalService
  → ${package}.app.service.HelloAppService
      → HelloAggregate.create(command)
      → HelloDomainService.enrich(aggregate)
      → HelloRepository.save(aggregate)
  → ${package}.infrastructure.acl.repository.HelloRepositoryImpl
```

### 读路径（查询）

查询 **不经过** 聚合：

```text
HelloController → HelloLocalService → HelloAppService.query
  → HelloRepository.findById → HelloInfo
```

### 类 → 分层对照

| 层 | 类 |
|----|----|
| 接口层 (`osh`) | `Application`、`HelloController` |
| 本地门面 | `HelloLocalService` |
| 应用层 | `HelloAppService` |
| 领域模型 | `HelloAggregate`、`HelloId` |
| 领域 PL（osh） | `HelloCreateCommand`、`HelloQuery` |
| 领域 PL（acl） | `HelloInfo` |
| 领域端口 / 服务 | `HelloRepository`、`HelloDomainService` |
| 基础设施 ACL | `HelloRepositoryImpl`（示例为内存实现） |

## 技术栈（锁定）

| 项 | 版本 |
|----|------|
| Java | 17 |
| Spring Boot | 3.5.9 |
| MyBatis-Plus | 3.5.17 |
| Druid | 1.2.28 |
| RocketMQ starter | 2.3.6 |
| OkHttp | 4.12.0 |
| Lombok | 1.18.42 |

## Hello 示例

```bash
mvn -pl ${rootArtifactId}-interface -am spring-boot:run
curl "http://localhost:8080/api/hello/ping?name=DDD"
```

## AI 编码 Skill（通用）

本仓库提供**不绑定任何 IDE** 的 AI 编码规范：

- [`skills/ddd-scaffold/SKILL.md`](skills/ddd-scaffold/SKILL.md) — 分层与编码规则
- [`AGENTS.md`](AGENTS.md) — 引导任意 AI Agent 先读 Skill

用 Cursor / Claude Code / Codex / Copilot 等工具时，都指向这两份文件即可。

## 包结构

- `${package}.osh` — controller / dto / listener / mq / rpc
- `${package}.local` — 本地门面 → AppService
- `${package}.app.service` — 应用服务
- `${package}.domain.model` — 聚合 / dp / 值对象 / 枚举
- `${package}.domain.port` — gateway / repository / publisher + `pl/osh` + `pl/acl`
- `${package}.domain.service` — 领域服务
- `${package}.infrastructure.acl` — 端口实现
- `${package}.infrastructure.core` — mybatis / http / redis / mq
