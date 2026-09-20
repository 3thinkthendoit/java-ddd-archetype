package ${package}.domain.port.pl.osh.query;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HelloQuery {

    private String helloId;
}
