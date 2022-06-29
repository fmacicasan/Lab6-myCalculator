package ro.fasttrack.lab18;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author flo
 * @since 29.06.2022.
 */
public class PlayStream {
    public static void main(String[] args) {
        List<String> names = List.of("Ion", "Maria", "Daniel", "Gheorghe", "Danna");
        Map<Integer, List<String>> collected = names.stream()
//                .filter(s -> s.startsWith("D"))
                .filter(s -> s.length() > 4)
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(String::length));
//                .forEach(System.out::println);
        System.out.println(collected);

        Map<Integer, List<String>> collectedIterative = new HashMap<>();
        for (String name : names) {
            if(name.startsWith("D")) {
                System.out.println(name.toLowerCase());
                if (!collectedIterative.containsKey(name.length())) {
                    collectedIterative.put(name.length(), new ArrayList<>());
                }
                collectedIterative.get(name.length()).add(name);
            }
        }
        System.out.println(collectedIterative);
    }
}
