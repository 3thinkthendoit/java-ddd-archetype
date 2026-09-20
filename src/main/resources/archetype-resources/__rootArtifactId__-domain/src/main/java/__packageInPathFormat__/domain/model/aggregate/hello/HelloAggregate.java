package ${package}.domain.model.aggregate.hello;

import ${package}.domain.model.dp.HelloId;
import ${package}.domain.port.pl.osh.command.HelloCreateCommand;
import lombok.Getter;
import org.springframework.util.Assert;

@Getter
public class HelloAggregate {

    private HelloId helloId;
    private String name;
    private String greeting;

    public static HelloAggregate create(HelloCreateCommand command) {
        command.validate();
        HelloAggregate aggregate = new HelloAggregate();
        aggregate.helloId = new HelloId(command.getHelloId());
        aggregate.name = command.getName();
        return aggregate;
    }

    public void greet() {
        Assert.hasText(this.name, "name must not be blank");
        this.greeting = "Hello, " + this.name + "!";
    }
}
