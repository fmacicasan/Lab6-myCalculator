package ro.fasttrack.lab18.homework;

/**
 * @author flo
 * @since 06.07.2022.
 */
public class Recursion {
    public static void main(String[] args) {
        //156 => sumDiggits(156) => 12
        System.out.println(sumDiggits(156) == 12);
        System.out.println(sumDiggits(456) == 15);
        System.out.println(sumDiggits(4) == 4);
        System.out.println(sumDiggits(-4) == 4);
        System.out.println(sumDiggits(-124) == 7);
    }

    private static int sumDiggits(int number) {
        if(number < 0) {
            // number = number * -1 // solutie in care modificam input-ul si nu e in spirit recursiv si functional
            return sumDiggits(-1 * number);
        }
        if(number == 0) {
            return 0;
        }
        return number % 10  + sumDiggits(number / 10);
    }

    private static int sumDiggitsIterativ(int number) {
        int sumDiggits = 0;
        int theNumber = number;
        while(theNumber > 0) {
            sumDiggits += theNumber % 10;
            theNumber /= 10;
        }
        return sumDiggits;
    }
}
