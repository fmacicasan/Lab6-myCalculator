import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/calculator")
public class Calculator extends HttpServlet {

    private List<String> history = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        //get input as string
        String sNr1=request.getParameter("nr1");
        String sNr2=request.getParameter("nr2");
        String sOp=request.getParameter("op");

        // parse strings to integer
        int nr1=Integer.parseInt(sNr1);
        int nr2=Integer.parseInt(sNr2);

        // do the work (apply the operation)
        double resultValue=0;

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
            default:
                System.out.println("Operation not supported " + sOp);
                break;
        }

        //save history
        String operation = nr1 + " " + sOp + " " + nr2 + "=" + resultValue;
        history.add(operation);
        System.out.println(operation);

        // write results to response
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out =resp.getWriter();
        out.println("<h2>Calculate </h2>");

        // result of <b>10 + 5 = 15<b>
        out.println("result of <b>"+operation+"</b><br/>");
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
        out.println("<th>Operation</th>");
        out.println("</tr>");
        for (int i = 0; i < history.size() ; i++) {
            out.println("<tr>");
            out.println("<td>" + i + "</td>");
            out.println("<td>" + history.get(i) + "</td>");
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