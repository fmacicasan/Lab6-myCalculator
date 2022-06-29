package ro.fasttrack.lab18;

import java.util.function.Function;

/**
 * @author flo
 * @since 29.06.2022.
 */
public class PlayLambdas {
    void printTransform(Function<String, String> logic) {
        System.out.println(logic.apply("hello"));
    }
    public static void main(String[] args) {
        final PlayLambdas example = new PlayLambdas();

        example.printTransform(String::toUpperCase);
        example.printTransform(a -> addSuffix(a));
        example.printTransform(PlayLambdas::addSuffix);
        example.printTransform(a -> example.anotherSuffix(a));
        example.printTransform(example::anotherSuffix);
    }
    public String anotherSuffix(String a) { return a + "another";}
    public static String addSuffix(String v) {return v + "suffix";}
}
