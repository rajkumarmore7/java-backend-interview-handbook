import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamLab {
    public static void main(String[] args) {
        var numbers = List.of(1, 2, 2, 3, 3, 4);
        Map<Integer, Long> frequency = numbers.stream()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        var duplicates = frequency.entrySet().stream()
            .filter(entry -> entry.getValue() > 1)
            .map(Map.Entry::getKey)
            .sorted()
            .toList();
        System.out.println(duplicates);
    }
}
