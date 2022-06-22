package ro.fasttrack.lab17.calculator.dataTransferObject;

/**
 * @author flo
 * @since 22.06.2022.
 */
public record OperationDTO(
        Integer op1,
        String op,
        Integer op2,
        double result
) {
}
