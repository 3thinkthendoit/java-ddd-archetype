package ${package}.app.service;

import ${package}.domain.model.aggregate.hello.HelloAggregate;
import ${package}.domain.port.pl.acl.HelloInfo;
import ${package}.domain.port.pl.osh.command.HelloCreateCommand;
import ${package}.domain.port.pl.osh.query.HelloQuery;
import ${package}.domain.port.repository.HelloRepository;
import ${package}.domain.service.HelloDomainService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class HelloAppService {

    @Resource
    private HelloDomainService helloDomainService;
    @Resource
    private HelloRepository helloRepository;

    /** Write path: AppService orchestrates aggregate + domain service + repository. */
    public HelloInfo create(HelloCreateCommand command) {
        HelloAggregate aggregate = HelloAggregate.create(command);
        helloDomainService.enrich(aggregate);
        helloRepository.save(aggregate);
        return HelloInfo.builder()
                .helloId(aggregate.getHelloId().getValue())
                .name(aggregate.getName())
                .greeting(aggregate.getGreeting())
                .build();
    }

    /** Read path: query bypasses domain model and hits the repository/gateway directly. */
    public HelloInfo query(HelloQuery query) {
        return helloRepository.findById(query.getHelloId())
                .orElseThrow(() -> new IllegalArgumentException("hello not found: " + query.getHelloId()));
    }
}
