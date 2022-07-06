package ro.fasttrack.lab18.homework;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author flo
 * @since 06.07.2022.
 */
public class PersonStream {
    public static void main(String[] args) {
        List<Person> person = List.of(
          new Person("george", "bacovia", 23, "Buc"),
          new Person("liviu", "dan", 655, "Cluj"),
          new Person("liviu", "pop", 65, "Cluj"),
          new Person("maria", "macovi", 14, "Timisoara")
        );

        System.out.println(ageBetween(18, 65, person));
        System.out.println(uniqueFirstName(person));
    }

    private static Set<String> uniqueFirstName(List<Person> person) {
        Set<String> names = new HashSet<>();
        for (Person p : person) {
            String firstName = p.firstName();
            String capitalized = firstName.substring(0, 1).toUpperCase()
                    + firstName.substring(1);
            names.add(capitalized);
        }


        return person.stream()
                .map(Person::firstName)
                .map(i -> i.substring(0, 1).toUpperCase() + i.substring(1))
                .collect(Collectors.toSet());
    }

    private static List<Person> ageBetween(int min, int max, List<Person> person) {
        return person.stream()
//                .filter(p -> p.age() > min && p.age() < max)
                .filter(p -> p.age() > min)
                .filter(p -> p.age() < max)
                .toList();
    }
}

record Person (String firstName, String lastName, int age, String city){};
