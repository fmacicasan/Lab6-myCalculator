package ro.fasttrack.lab17.calculator.web;

import ro.fasttrack.lab17.calculator.dataTransferObject.OperationDTO;
import ro.fasttrack.lab17.calculator.service.OperationNotSupported;
import ro.fasttrack.lab17.calculator.service.OperationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/calc")
public class Calculator extends HttpServlet {

    private OperationService operationService = new OperationService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        //get input as string
        String sNr1=request.getParameter("nr1");
        String sNr2=request.getParameter("nr2");
        String sOp=request.getParameter("op");

        // parse strings to integer
        try {
           double resultValue = operationService.performOperation(sNr1, sOp, sNr2);

        // write results to response
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out =resp.getWriter();
        out.println("<h2>Calculate </h2>");

        out.println("result is: <b>"+resultValue+"</b><br/>");
        out.println("<a href='/calculator_war_exploded'>Go Back</a>");

        // finished writing, send to browser
        out.close();
        } catch (NumberFormatException e) {
            endRequestWithError(resp, "The numbers are invalid");
        } catch (OperationNotSupported e) {
            endRequestWithError(resp, "Operation " + e.getOp() + " is not supported.");
        }
    }

    private void endRequestWithError(HttpServletResponse resp, String message) throws IOException {
        PrintWriter writer = resp.getWriter();
        writer.write(message);
        resp.setStatus(422);
        writer.close();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<head>");
        out.println("<title>Istoric </title>");
        out.println("</head>");

        out.println("<body>");
        out.println("Istoric:<br />");
        out.println("<table>");
        // op1 op op2 result
        out.println("<tr><th>op1</th><th>op</th><th>op2</th><th>result</th>");
        //history[0] = "5 + 10 = 15"
        // 5 + 10 15

        for (OperationDTO value : operationService.listOperations()) {
            out.print("<tr>");
            out.print("<td>" + value.op1() + "</td>");
            out.print("<td>" + value.op() + "</td>");
            out.print("<td>" + value.op2() + "</td>");
            out.print("<td>" + value.result() + "</td>");
            out.print("</tr>");
        }
        out.println("</table>");

        out.println("</body>");
        out.close();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        getServletContext().log("init() called");
    }

    @Override
    public void destroy() {
        System.out.println("Destroying Servlet!");
        super.destroy();
    }
}