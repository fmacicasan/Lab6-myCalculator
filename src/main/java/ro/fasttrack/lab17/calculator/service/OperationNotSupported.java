package ro.fasttrack.lab17.calculator.service;

/**
 * @author flo
 * @since 22.06.2022.
 */
public class OperationNotSupported extends RuntimeException {
    private String op;

    public OperationNotSupported(String op) {
        this.op = op;
    }

    public String getOp() {
        return op;
    }
}
