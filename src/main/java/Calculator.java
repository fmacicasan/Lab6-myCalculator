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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String sNr1=req.getParameter("nr1");
        String sNr2=req.getParameter("nr2");
        String sOp=req.getParameter("op");

        int nr1=Integer.parseInt(sNr1);
        int nr2=Integer.parseInt(sNr2);
        double resultValue=0;

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


        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out =resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Calcule </title>");
        out.println("</head>");

        out.println("<body>");


        out.println("result is: <b>"+resultValue+"</b><br/>");
        out.println("<a href='/'>Go Backk</a>");

        out.println("</body>");
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Istoric </title>");
        out.println("</head>");

        out.println("<body>");

        for (String h : history) {
            out.println(h + "<br />");
        }


        out.println("</body>");
        out.close();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("Initializing Servlet!");
    }

    @Override
    public void destroy() {
        System.out.println("Destroying Servlet!");
        super.destroy();
    }
}