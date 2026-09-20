package ${package}.domain.port.pl.osh.command;

import lombok.Builder;
import lombok.Data;
import org.springframework.util.Assert;

@Data
@Builder
public class HelloCreateCommand {

    private String helloId;
    private String name;

    public void validate() {
        Assert.hasText(helloId, "helloId must not be blank");
        Assert.hasText(name, "name must not be blank");
    }
}
