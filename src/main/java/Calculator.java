import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/calculator")
public class Calculator extends HttpServlet {

    private String[] history = new String[5];
    private int index = 0;

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
        //save history
        String operation = nr1 + " " + sOp + " " + nr2;
        history[index] = operation;
        index++;
        System.out.println(operation);
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

        // write results to response
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out =resp.getWriter();
        out.println("<h2>Calculate </h2>");

        out.println("result is: <b>"+resultValue+"</b><br/>");
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
        out.println("Istoric:");
        for (int i=0; i< index; i++) {
            out.println(history[i] + "<br />");
        }


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