package ro.fasttrack.lab17.calculator.repository;

import ro.fasttrack.lab17.calculator.dataTransferObject.OperationDTO;
import ro.fasttrack.lab17.calculator.domain.Operation;

import java.util.List;

/**
 * @author flo
 * @since 22.06.2022.
 */
public interface OperationRepository {
    List<Operation> findAll();

    void save(OperationDTO op);
}
