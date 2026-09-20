package ${package}.domain.service;

import ${package}.domain.model.aggregate.hello.HelloAggregate;
import org.springframework.stereotype.Service;

/**
 * Cross-aggregate / southbound collaboration lives here.
 * Hello sample keeps it thin on purpose.
 */
@Service
public class HelloDomainService {

    public void enrich(HelloAggregate aggregate) {
        aggregate.greet();
    }
}
