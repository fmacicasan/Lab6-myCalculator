/**
 * @author flo
 * @since 06/01/2017.
 */
public class Operation {

    private String nr1;
    private String op;
    private String nr2;
    private String result;

    public Operation(String nr1, String op, String nr2, String result) {
        this.nr1 = nr1;
        this.op = op;
        this.nr2 = nr2;
        this.result = result;
    }

    public String getNr1() {
        return nr1;
    }

    public String getOp() {
        return op;
    }

    public String getNr2() {
        return nr2;
    }

    public String getResult() {
        return result;
    }

    @Override
    public String toString() {
        return nr1 + " " + op + " " + nr2 + " => " + result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Operation operation = (Operation) o;

        if (!nr1.equals(operation.nr1)) return false;
        if (!op.equals(operation.op)) return false;
        if (!nr2.equals(operation.nr2)) return false;
        return result.equals(operation.result);

    }

    @Override
    public int hashCode() {
        int result1 = nr1.hashCode();
        result1 = 31 * result1 + op.hashCode();
        result1 = 31 * result1 + nr2.hashCode();
        result1 = 31 * result1 + result.hashCode();
        return result1;
    }
}
