package ${package}.infrastructure.acl.repository;

import ${package}.domain.model.aggregate.hello.HelloAggregate;
import ${package}.domain.port.pl.acl.HelloInfo;
import ${package}.domain.port.repository.HelloRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class HelloRepositoryImpl implements HelloRepository {

    private final Map<String, HelloInfo> store = new ConcurrentHashMap<>();

    @Override
    public void save(HelloAggregate aggregate) {
        HelloInfo info = HelloInfo.builder()
                .helloId(aggregate.getHelloId().getValue())
                .name(aggregate.getName())
                .greeting(aggregate.getGreeting())
                .build();
        store.put(info.getHelloId(), info);
    }

    @Override
    public Optional<HelloInfo> findById(String helloId) {
        return Optional.ofNullable(store.get(helloId));
    }
}
