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
