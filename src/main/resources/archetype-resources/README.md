# ${rootArtifactId}

DDD four-layer Spring Boot project generated from [`java-ddd-archetype`](https://github.com/).

> **Languages:** English (default) · [中文版](./README.zh-CN.md)

## Modules

```text
${rootArtifactId}-domain          # aggregates / domain services / ports / PL
${rootArtifactId}-application     # AppService use-case orchestration
${rootArtifactId}-infrastructure  # ACL port implementations + tech adapters
${rootArtifactId}-interface       # OSH northbound + Spring Boot entry
```

Dependency rule: **outer depends on inner**; domain never depends on application / infrastructure / interface.

## Tech stack (locked)

| Item | Version |
|------|---------|
| Java | 17 |
| Spring Boot | 3.5.9 |
| MyBatis-Plus | 3.5.17 |
| Druid | 1.2.28 |
| RocketMQ starter | 2.3.6 |
| OkHttp | 4.12.0 |
| Lombok | 1.18.42 |

## Hello sample

```bash
mvn -pl ${rootArtifactId}-interface -am spring-boot:run
curl "http://localhost:8080/api/hello/ping?name=DDD"
```

## AI coding skill (any agent)

This project ships a **tool-agnostic** skill for AI coding agents:

- [`skills/ddd-scaffold/SKILL.md`](skills/ddd-scaffold/SKILL.md) — coding rules
- [`AGENTS.md`](AGENTS.md) — entry point that tells agents to load the skill

Point any AI agent at those files before implementing features.

## Package map

- `${package}.osh` — controllers / dto / listeners / mq / rpc
- `${package}.local` — local facade → AppService
- `${package}.app.service` — application services
- `${package}.domain.model` — aggregates / dp / valueobject / constant
- `${package}.domain.port` — gateway / repository / publisher + `pl/osh` + `pl/acl`
- `${package}.domain.service` — domain services
- `${package}.infrastructure.acl` — port implementations
- `${package}.infrastructure.core` — mybatis / http / redis / mq
