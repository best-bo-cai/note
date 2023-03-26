import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SetSortTest {
    public static void main(String[] args) {
        Set<String> timeStampAll = new HashSet<>();

        Set<String> timeStamp1 = new HashSet<>();
        timeStamp1.add("1680142308136");
        timeStamp1.add("1680142308236");
        timeStamp1.add("1680142308336");
        timeStamp1.add("1680142308436");

        Set<String> timeStamp2 = new HashSet<>();
        timeStamp2.add("1680142308436");
        timeStamp2.add("1680141308436");
        timeStamp2.add("1680143308436");
        timeStamp2.add("1680144308436");
        timeStamp2.add("1680145308436");

        timeStampAll.addAll(timeStamp2);
        timeStampAll.addAll(timeStamp1);

        Set<String> setSort = timeStampAll.stream()
                .sorted()
                .collect(Collectors.toCollection(LinkedHashSet::new));

        setSort.forEach(System.out::println);
    }
}
