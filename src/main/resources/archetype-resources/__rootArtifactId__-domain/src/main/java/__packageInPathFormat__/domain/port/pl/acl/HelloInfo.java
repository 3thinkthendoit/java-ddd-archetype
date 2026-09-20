package ${package}.domain.port.pl.acl;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HelloInfo {

    private String helloId;
    private String name;
    private String greeting;
}
