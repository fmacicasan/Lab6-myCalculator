package ro.fasttrack.lab17.calculator.service;

import ro.fasttrack.lab17.calculator.dataTransferObject.OperationDTO;
import ro.fasttrack.lab17.calculator.domain.Operation;
import ro.fasttrack.lab17.calculator.repository.InMemoryOperationRepository;
import ro.fasttrack.lab17.calculator.repository.OperationRepository;
import ro.fasttrack.lab17.calculator.repository.PostgresOperationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author flo
 * @since 22.06.2022.
 */
public class OperationService {
    // three layers architecture
    // 1. web layer: servlets
    // 2. service layer: business logic (calculul operatilor)
    // 3. database/repository/infrastrature layer:  interact with DB
    // 2 & 3 use the domain objects

    private OperationRepository operationRepository = new PostgresOperationRepository();


    public List<OperationDTO> listOperations() {
        List<OperationDTO> operations = new ArrayList<>();
        for (Operation operation : operationRepository.findAll()) {
            operations.add(new OperationDTO(
                    operation.op1(),
                    operation.op(),
                    operation.op2(),
                    operation.result()
            ));
        }
        return operations;
    }

    public double performOperation(String sNr1, String sOp, String sNr2) {
        int nr1 = Integer.parseInt(sNr1);
        int nr2 = Integer.parseInt(sNr2);


        // do the work (apply the operation)
        double resultValue=0;
        //save history
        String operation = nr1 + " " + sOp + " " + nr2;
        MyOperation performedOperation;
        System.out.println(operation);
        switch (sOp) {
            case "+":
                performedOperation = Integer::sum;
                break;
            case "-":
                performedOperation = (a, b) -> a - b;
                break;
            case "*":
                performedOperation = (a, b) -> a * b;
                break;
            case "/":
                performedOperation = (n1, n2) -> (double) n1 / n2;
                break;
            case "%":
                performedOperation = (a, b) -> a % b;
                break;
            default:
                System.out.println("Operation not supported " + sOp);
                throw new OperationNotSupported(sOp);
        }
        resultValue = performedOperation.doOp(nr1, nr2);

        operationRepository.save(new OperationDTO(nr1, sOp, nr2, resultValue));
        return resultValue;
    }
}
