import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author flo
 * @since 10/01/2017.
 */
public class HistoryDBAccess {


    public List<Operation> readHistory() throws SQLException, ClassNotFoundException {

        // 1. load the driver
        Class.forName("org.postgresql.Driver");

        // 2. obtain a connection
        Connection conn = DriverManager.getConnection(DemoCRUDOperations.URL, DemoCRUDOperations.USERNAME, DemoCRUDOperations.PASSWORD);

        // 3. create a query statement
        Statement st = conn.createStatement();

        // 4. execute a query
        ResultSet rs = st.executeQuery("SELECT nr1,nr2,op,result FROM history");

        List<Operation> operations = new ArrayList<>();
        // 5. iterate the result set and print the values
        while (rs.next()) {
            Operation operation = new Operation(
                    rs.getString("nr1"),
                    rs.getString("nr2"),
                    rs.getString("op"),
                    rs.getString("result")
            );
            operations.add(operation);
            System.out.println(operation);
        }

        // 6. close the objects
        rs.close();
        st.close();
        conn.close();

        return operations;
    }

    public void addHistory(Operation operation) throws SQLException, ClassNotFoundException {

        // 1. load the driver
        Class.forName("org.postgresql.Driver");

        // 2. obtain a connection
        Connection conn = DriverManager.getConnection(DemoCRUDOperations.URL, DemoCRUDOperations.USERNAME, DemoCRUDOperations.PASSWORD);


        // 3. create a query statement
        PreparedStatement pSt = conn.prepareStatement("INSERT INTO history (nr1, nr2, op, result) VALUES (?,?,?,?)");
        pSt.setString(1, operation.getNr1());
        pSt.setString(2, operation.getNr2());
        pSt.setString(3, operation.getOp());
        pSt.setString(4, operation.getResult());

        // 4. execute a prepared statement
        int rowsInserted = pSt.executeUpdate();
        System.out.println("Inserted " + rowsInserted + " rows.");

        // 5. close the objects
        pSt.close();
        conn.close();
    }
}
