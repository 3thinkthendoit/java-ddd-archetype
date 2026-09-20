# java-ddd-archetype

Maven archetype that generates a **Java 17 + Spring Boot 3.5.9** DDD four-layer multi-module project (same shape as `think-oms`), with a minimal Hello vertical slice and a **tool-agnostic AI coding skill**.

> **Languages:** English (default) · [中文版](./README.zh-CN.md)

## Install (local)

```bash
cd D:/code/java/java-ddd-archetype
mvn clean install
```

## Generate a project

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

Then:

```bash
cd demo-ddd
mvn -pl demo-ddd-interface -am spring-boot:run
curl "http://localhost:8080/api/hello/ping?name=DDD"
```

## AI skill (any coding agent)

Not Cursor-specific. Generated projects include:

| File | Role |
|------|------|
| `skills/ddd-scaffold/SKILL.md` | Coding rules for the scaffold |
| `AGENTS.md` | Tells any AI agent to load the skill first |

Source of truth in this repo: [`skills/ddd-scaffold/SKILL.md`](skills/ddd-scaffold/SKILL.md).

Works with Cursor, Claude Code, Codex, Copilot, Windsurf, and similar tools — just point the agent at those files.

## Locked tech stack

| Item | Version |
|------|---------|
| Java | 17 |
| Spring Boot | 3.5.9 |
| MyBatis-Plus | 3.5.17 |
| Druid | 1.2.28 |
| RocketMQ starter | 2.3.6 |
| OkHttp | 4.12.0 |
| Lombok | 1.18.42 |
