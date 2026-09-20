---
name: ddd-scaffold
description: >-
  Write and review Java code against the java-ddd-archetype four-layer DDD
  scaffold (domain / application / infrastructure / interface). For any AI
  coding agent. Use when adding features, aggregates, AppServices, ports, ACL
  implementations, controllers, or when the user mentions DDD scaffold,
  java-ddd-archetype, osh, or think-oms style layout.
---

# DDD Scaffold Coding Rules

**Audience:** any AI coding agent (Cursor, Claude Code, Codex, Copilot, Windsurf, etc.).

Before changing business code in a project generated from `java-ddd-archetype`, follow this skill. Match the Hello vertical slice already in the repo.

## Module dependency (hard rule)

```text
interface → application + infrastructure
application → domain
infrastructure → domain
domain → no other business modules
```

Never let domain import infrastructure or interface classes.

## Package map

| Layer | Package | Put here |
|-------|---------|----------|
| Interface (OSH) | `{base}.osh` | `controller`, `dto`, `job`, `listener`, `mq`, `rpc`, boot `Application` |
| Local facade | `{base}.local` | thin pass-through to AppService |
| Application | `{base}.app.service` | `*AppService` use-case orchestration |
| Domain model | `{base}.domain.model` | `aggregate/{bc}`, `dp`, `valueobject`, `constant` |
| Domain ports | `{base}.domain.port` | `gateway`, `repository`, `publisher` |
| Northbound PL | `{base}.domain.port.pl.osh` | `command`, `event`, `query` |
| Southbound PL | `{base}.domain.port.pl.acl` | `request`, `response`, transfer `*Info` |
| Domain service | `{base}.domain.service` | cross-aggregate / gateway collaboration |
| Infrastructure ACL | `{base}.infrastructure.acl` | `*Impl` for ports, channel `api/*` |
| Infrastructure core | `{base}.infrastructure.core` | mybatis / http / redis / mq |

## Write path (commands)

```text
Controller/Job/MQ/RPC
  → (DTO → *Command)
  → LocalService (optional)
  → *AppService
      → Aggregate.create(command)
      → DomainService (if needs gateways)
      → aggregate behavior methods
      → Repository.save / EventPublisher.publish
Infrastructure ACL implements ports
```

## Read path (queries)

Queries **do not** go through aggregates.

```text
Controller → LocalService → AppService.query
  → Gateway / Repository query method
  → return ACL *Info / response
```

## Coding conventions

- Inject with `jakarta.annotation.Resource` (not `@Autowired`)
- Aggregates: `@Getter`, factory `create(...)`, domain behaviors as methods; no public setters for state
- Domain services may use Spring `@Service` / `@Resource`
- Port interfaces in domain; implementations only in `infrastructure.acl`
- Keep AppService orchestration thin and explicit (like Hello create flow)
- Do not put MyBatis mappers, OkHttp clients, or RocketMQ templates in domain

## Checklist for a new use case

1. Add `*Command` / `*Query` under `port.pl.osh`
2. Add or extend aggregate under `model.aggregate/{bc}`
3. Add port under `port.repository` or `port.gateway` if needed
4. Add ACL `*Impl` under `infrastructure.acl`
5. Orchestrate in `*AppService`
6. Expose via `osh.controller` (and optional `local` facade)
7. Keep query methods on the read path (no aggregate)

## Locked stack

Java 17, Spring Boot 3.5.9, MyBatis-Plus 3.5.17, Druid 1.2.28, RocketMQ starter 2.3.6, OkHttp 4.12.0, Lombok 1.18.42. Do not bump casually.
