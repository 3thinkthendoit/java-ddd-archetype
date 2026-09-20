package ${package}.osh.controller;

import ${package}.domain.port.pl.acl.HelloInfo;
import ${package}.domain.port.pl.osh.command.HelloCreateCommand;
import ${package}.domain.port.pl.osh.query.HelloQuery;
import ${package}.local.HelloLocalService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/hello")
public class HelloController {

    @Resource
    private HelloLocalService helloLocalService;

    @PostMapping
    public HelloInfo create(@RequestBody Map<String, String> body) {
        HelloCreateCommand command = HelloCreateCommand.builder()
                .helloId(UUID.randomUUID().toString().replace("-", ""))
                .name(body.getOrDefault("name", "world"))
                .build();
        return helloLocalService.create(command);
    }

    @GetMapping
    public HelloInfo query(@RequestParam("helloId") String helloId) {
        return helloLocalService.query(HelloQuery.builder().helloId(helloId).build());
    }

    /** Convenience endpoint: create then return greeting in one call. */
    @GetMapping("/ping")
    public HelloInfo ping(@RequestParam(value = "name", defaultValue = "DDD") String name) {
        HelloCreateCommand command = HelloCreateCommand.builder()
                .helloId(UUID.randomUUID().toString().replace("-", ""))
                .name(name)
                .build();
        return helloLocalService.create(command);
    }
}
