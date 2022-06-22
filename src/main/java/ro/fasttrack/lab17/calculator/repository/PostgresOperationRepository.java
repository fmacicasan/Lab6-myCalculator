package ro.fasttrack.lab17.calculator.repository;

import ro.fasttrack.lab17.calculator.dataTransferObject.OperationDTO;
import ro.fasttrack.lab17.calculator.domain.Operation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author flo
 * @since 22.06.2022.
 */
public class PostgresOperationRepository implements OperationRepository {

    final static String URL = "jdbc:postgresql://localhost:5432/lab17";
    final static String USERNAME = "postgres";
    final static String PASSWORD = System.getenv("POSTGRES_PASSWORD");

    public PostgresOperationRepository() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RepositoryAccessException(e);
        }
    }

    @Override
    public List<Operation> findAll() {

        try(// 3. obtain a connection
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // 4. create a query statement
            Statement st = conn.createStatement();

            // 5. execute a query
            ResultSet rs = st.executeQuery("SELECT * FROM operationhistory");

            // 6. iterate the result set and print the values
        ) {
            List<Operation> operations = new ArrayList<>();
            while (rs.next()) {
                long id = rs.getLong("history_id");
                int nr1 = rs.getInt("nr1");
                String op = rs.getString(3);
                int nr2 = rs.getInt(4);
                double result = rs.getDouble("result");
                operations.add(new Operation(id, nr1, op, nr2, result));
            }
            return operations;
        } catch (SQLException e) {
            throw new RepositoryAccessException(e);
        }
    }

    @Override
    public void save(OperationDTO op) {
        // 1. load driver, no longer needed in new versions of JDBC
        try (// 3. obtain a connection
             Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             // 4. create a query statement
             PreparedStatement pSt = conn.prepareStatement(
                     "INSERT INTO operationhistory (nr1, op, nr2, result) VALUES (?,?,?,?)"
             )
        ){
//            Class.forName("org.postgresql.Driver");
//            // 2. define connection params to db



            pSt.setInt(1, op.op1());
            pSt.setString(2, op.op());
            pSt.setInt(3, op.op2());
            pSt.setDouble(4, op.result());

            // 5. execute a prepared statement
            int rowsInserted = pSt.executeUpdate();
            System.out.println("Inserted " + rowsInserted);

            // 6. close the objects will happen due to the try-with-resources + AutoClosable interface
        } catch ( SQLException e) {
            throw new RepositoryAccessException(e);
        }


    }
}
