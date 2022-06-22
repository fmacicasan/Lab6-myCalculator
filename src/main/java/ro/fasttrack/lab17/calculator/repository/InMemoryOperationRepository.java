package ro.fasttrack.lab17.calculator.repository;

import ro.fasttrack.lab17.calculator.dataTransferObject.OperationDTO;
import ro.fasttrack.lab17.calculator.domain.Operation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author flo
 * @since 22.06.2022.
 */
public class InMemoryOperationRepository implements OperationRepository {
    private List<Operation> history = new ArrayList<>();

    @Override
    public List<Operation> findAll() {
        return Collections.unmodifiableList(history);
    }

    @Override
    public void save(OperationDTO op) {
        history.add(new Operation(history.size(), op.op1(), op.op(), op.op2(), op.result()));
    }

}
