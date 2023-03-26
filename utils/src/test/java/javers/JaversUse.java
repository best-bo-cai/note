package operlog.javers;

import org.assertj.core.util.Lists;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;

import java.math.BigDecimal;
import java.util.Optional;

public class JaversUse {
    // TODO: 2023/4/11 https://javers.org/documentation/getting-started/
    // TODO: 2023/4/11 https://blog.csdn.net/footless_bird/article/details/116203893
    public static void main(String[] args) {
        Staff tommy = Staff.builder()
                .name("Tommy")
                .age(18)
                .height(180d)
                .salary(new BigDecimal("10000"))
                .hobbies(Lists.newArrayList("film", "game"))
//                .manager(Staff.builder().name("ok").build())
                .build();
        Staff ggg = Staff.builder()
                .name("ggg")
                .age(18)
//                .height(180.000000001d)
                .hobbies(Lists.newArrayList("game", "music", "travel"))
//                .manager(Staff.builder().name("ok").build())
                .build();

        Javers javers = JaversBuilder.javers().build();
        Diff diff = javers.compare(tommy, ggg);
        System.out.println(diff);
        boolean b = diff.hasChanges();
        diff.getChanges().getPropertyChanges("height");
        diff.getChanges().forEach(change -> {
            System.out.println(change.toString());
        });
    }
}
