package ${package}.domain.port.repository;

import ${package}.domain.model.aggregate.hello.HelloAggregate;
import ${package}.domain.port.pl.acl.HelloInfo;

import java.util.Optional;

public interface HelloRepository {

    void save(HelloAggregate aggregate);

    Optional<HelloInfo> findById(String helloId);
}
