package ${package}.local;

import ${package}.app.service.HelloAppService;
import ${package}.domain.port.pl.acl.HelloInfo;
import ${package}.domain.port.pl.osh.command.HelloCreateCommand;
import ${package}.domain.port.pl.osh.query.HelloQuery;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class HelloLocalService {

    @Resource
    private HelloAppService helloAppService;

    public HelloInfo create(HelloCreateCommand command) {
        return helloAppService.create(command);
    }

    public HelloInfo query(HelloQuery query) {
        return helloAppService.query(query);
    }
}
