package ${package}.domain.model.dp;

import lombok.Getter;
import org.springframework.util.Assert;

@Getter
public class HelloId {

    private final String value;

    public HelloId(String value) {
        Assert.hasText(value, "helloId must not be blank");
        this.value = value;
    }
}
