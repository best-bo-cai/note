package operlog.javers;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class Staff{
    private String name;
    private int age;
    private Double height;
    private BigDecimal salary;
    private Staff manager;
    private List<String> hobbies;
    private Map<String, String> phones;
}