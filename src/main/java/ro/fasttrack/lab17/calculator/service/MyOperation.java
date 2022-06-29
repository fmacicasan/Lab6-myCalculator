package ro.fasttrack.lab17.calculator.service;

/**
 * @author flo
 * @since 29.06.2022.
 */

@FunctionalInterface
public interface MyOperation {
    double doOp(int nr1, int nr2);
//    double anotherDoOp(int nr1, int nr2);
}
