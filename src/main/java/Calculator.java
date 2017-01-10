import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
 import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/calculator")
public class Calculator extends HttpServlet {

    private HistoryDBAccess historyDBAccess = new HistoryDBAccess();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        try {
            //get input as string
            String sNr1 = request.getParameter("nr1");
            String sNr2 = request.getParameter("nr2");
            String sOp = request.getParameter("op");

            // parse strings to integer
            int nr1, nr2;
            try {
                nr1 = Integer.parseInt(sNr1);
            } catch (NumberFormatException e) {
                publishResult(resp, new Operation(sNr1, sOp, sNr2, "Invalid Nr1"));
                return;
            }
            try {
                nr2 = Integer.parseInt(sNr2);
            } catch (NumberFormatException e) {
                publishResult(resp, new Operation(sNr1, sOp, sNr2, "Invalid Nr2"));
                return;
            }

            // do the work (apply the operation)
            double resultValue = 0;

            switch (sOp) {
                case "+":
                    resultValue = nr1 + nr2;
                    break;
                case "-":
                    resultValue = nr1 - nr2;
                    break;
                case "*":
                    resultValue = nr1 * nr2;
                    break;
                case "/":
                    resultValue = (double) nr1 / nr2;
                    break;
                case "%":
                    resultValue = nr1 % nr2;
                    break;
                default:
                    publishResult(resp, new Operation(sNr1, sOp, sNr2, "Invalid Operation"));
                    return;
            }

            //save history
            publishResult(resp, new Operation(sNr1, sOp, sNr2, String.valueOf(resultValue)));
        } catch (SQLException e) {
            System.out.println("Unable to connect to SQL");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void publishResult(HttpServletResponse resp, Operation result) throws IOException, SQLException, ClassNotFoundException {
        historyDBAccess.addHistory(result);
        writeResultToResponse(resp, result);
    }

    private void writeResultToResponse(HttpServletResponse resp, Operation result) throws IOException {
        System.out.println(result);
        // write results to response
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<h2>Calculate </h2>");

        // result - <b>10 + 5 = 15<b>
        out.println("result - <b>" + result.toString() + "</b><br/>");
        out.println("<a href='/'>Go Back</a>");

        // finished writing, send to browser
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<head>");
        out.println("<title>Istoric </title>");
        out.println("</head>");

        out.println("<body>");
        out.println("<table>");
        out.println("<tr>");
        out.println("<th>Index</th>");
        out.println("<th>Parameter1</th>");
        out.println("<th>Operation</th>");
        out.println("<th>Parameter2</th>");
        out.println("<th>Result</th>");
        out.println("</tr>");
        List<Operation> dbHistory = new ArrayList<>();
        try {
            dbHistory = historyDBAccess.readHistory();
        } catch (SQLException e) {
            out.println("Unable to connect to SQL");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < dbHistory.size(); i++) {
            out.println("<tr>");
            out.println("<td>" + i + "</td>");
            out.println("<td>" + dbHistory.get(i).getNr1() + "</td>");
            out.println("<td>" + dbHistory.get(i).getOp() + "</td>");
            out.println("<td>" + dbHistory.get(i).getNr2() + "</td>");
            out.println("<td>" + dbHistory.get(i).getResult() + "</td>");
            out.println("</tr>");
        }

        out.println("</table>");
        out.println("</body>");
        out.close();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("init() called");
    }

    @Override
    public void destroy() {
        System.out.println("Destroying Servlet!");
        super.destroy();
    }
}