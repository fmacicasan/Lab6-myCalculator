package ro.fasttrack.lab18.homework;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

/**
 * @author flo
 * @since 06.07.2022.
 */
public class PersonGroupingByCityTest {
    @Test
    public void testIfNullPersonListThenGroupingIsEmpty() {
        Map<String, Long> grouping = PersonStream.groupByCity(null);

        Assertions.assertEquals(Map.of(), grouping);
    }

    @Test
    public void testIfSinglePersonListThenGroupingIsHasCityWithValue1() {

        Map<String, Long> grouping = PersonStream.groupByCity(
                List.of(new Person("cucu", "asd", 15, "Cluj"))
        );

        Assertions.assertEquals(Map.of("Cluj", 1L), grouping);
    }

    @Test
    public void testIfDifferentCityPersonListThenGroupingIsHasEachCityWithValue1() {

        Map<String, Long> grouping = PersonStream.groupByCity(
                List.of(
                        new Person("cucu", "asd", 15, "Buc"),
                        new Person("cucu", "asd", 15, "Cluj")
                )
        );

        Assertions.assertEquals(Map.of("Buc", 1L, "Cluj", 1L), grouping);
    }
}
