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

## Generated Hello — layered architecture

After generation you get a **four-layer** project. Hello is one vertical slice that shows how every new feature should land.

### Module dependency

```text
demo-ddd-interface  →  application + infrastructure   (bootable, northbound)
demo-ddd-application → domain                         (use-case orchestration)
demo-ddd-infrastructure → domain                      (ACL + tech adapters)
demo-ddd-domain       → (no business modules)         (aggregates / ports / PL)
```

Outer depends on inner. Domain never depends on application, infrastructure, or interface.

### Hello call flow

**Write (command)**

```text
osh.controller.HelloController
  → local.HelloLocalService
  → app.service.HelloAppService
      → HelloAggregate.create(command)
      → HelloDomainService.enrich(aggregate)   # greets
      → HelloRepository.save(aggregate)
  → infrastructure.acl.repository.HelloRepositoryImpl  (in-memory)
```

**Read (query)** — does **not** go through the aggregate:

```text
HelloController → HelloLocalService → HelloAppService.query
  → HelloRepository.findById → HelloInfo
```

### Where Hello classes live

| Layer | Class | Package (example `com.example.demo`) |
|-------|--------|--------------------------------------|
| Interface | `Application`, `HelloController` | `...osh` / `...osh.controller` |
| Local | `HelloLocalService` | `...local` |
| Application | `HelloAppService` | `...app.service` |
| Domain | `HelloAggregate`, `HelloId` | `...domain.model.aggregate.hello` / `...domain.model.dp` |
| Domain | `HelloCreateCommand`, `HelloQuery` | `...domain.port.pl.osh.command` / `...query` |
| Domain | `HelloInfo` | `...domain.port.pl.acl` |
| Domain | `HelloRepository`, `HelloDomainService` | `...domain.port.repository` / `...domain.service` |
| Infrastructure | `HelloRepositoryImpl` | `...infrastructure.acl.repository` |

`osh` = Open Host Service (northbound). `acl` = Anti-Corruption Layer (southbound).

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
