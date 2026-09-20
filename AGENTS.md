# Agent instructions

This repository is a **Maven archetype**. When helping users generate projects or extend the template:

1. Keep the four-module DDD layout and locked tech stack.
2. Treat [`skills/ddd-scaffold/SKILL.md`](skills/ddd-scaffold/SKILL.md) as the coding contract for **generated** projects.
3. Prefer editing `src/main/resources/archetype-resources/` so `mvn clean install` regenerates the archetype correctly.
