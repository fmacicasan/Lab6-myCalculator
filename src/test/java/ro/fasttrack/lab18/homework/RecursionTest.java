package ro.fasttrack.lab18.homework;

import org.junit.jupiter.api.*;

/**
 * @author flo
 * @since 06.07.2022.
 */
public class RecursionTest {

    @BeforeAll
    public static void setupSomethingBeforeAllTests() {
        System.out.println("Ma execut o singura data inainte de toate testele");
    }

    @AfterAll
    public static void destroySomethingAfterAllTests() {
        System.out.println("Ma execut o singura data dupa toate testele");
    }

    @BeforeEach
    public void setupBeforeEachTest() {
        System.out.println("Ma execut inainte de fiecare test.");
    }

    @AfterEach
    public void setupAfterEachTest() {
        System.out.println("Ma execut dupa fiecare test.");
    }

    @Test
    public void testSumDiggitsForNegativeSingleDiggitNumbers() {
        System.out.println("test 1");
        //setup
        int input = -4;
        //run
        int output = Recursion.sumDiggits(input);
        //assert
//        System.out.println(output == 4);
        Assertions.assertEquals(4, output);
    }

    @Test
    public void testSumDiggitsForNegativeTripleDigitNumbers() {
        System.out.println("test2");
        //setup
        int input = -124;
        //run
        int output = Recursion.sumDiggits(input);
        //assert
//        System.out.println(output == 7);
        Assertions.assertEquals(7, output);
    }
}
