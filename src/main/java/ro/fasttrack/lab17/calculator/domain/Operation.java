package ro.fasttrack.lab17.calculator.domain;
/**
 * @author flo
 * @since 22.06.2022.
 */
public record Operation(
        long id,
        Integer op1,
        String op,
        Integer op2,
        double result
) {}
