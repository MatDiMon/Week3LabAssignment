package servlets;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ArithmeticCalculatorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/arithmeticcalculator.jsp")
                .forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String first = request.getParameter("first");
        String second = request.getParameter("second");

        request.setAttribute("first",first);
        request.setAttribute("second",second);        
        if( first == null || first.equals("") || second == null || second.equals("")){
            request.setAttribute("result", "invalid");
            getServletContext().getRequestDispatcher("/WEB-INF/arithmeticcalculator.jsp")
                .forward(request, response);
            return;
        } else if ( !first.chars().allMatch(Character::isDigit) || !second.chars().allMatch(Character::isDigit)) {
            request.setAttribute("result", "invalid");
            getServletContext().getRequestDispatcher("/WEB-INF/arithmeticcalculator.jsp")
                .forward(request, response);
            return;
        }
        
        
        int firstNumber =  Integer.parseInt(first);
        int secondNumber =  Integer.parseInt(second);
        
        double result = 0;
        
        Map parameters = request.getParameterMap();
            if (parameters.containsKey("plus")) {
                result = firstNumber + secondNumber;
            } else if (parameters.containsKey("minus")) {
                result = firstNumber - secondNumber;
            } else if (parameters.containsKey("divide")) {
                result = firstNumber * secondNumber;
            } else if (parameters.containsKey("multiply")) {
                result = firstNumber / secondNumber;
            }
            
            request.setAttribute("result", result);
     
        getServletContext().getRequestDispatcher("/WEB-INF/arithmeticcalculator.jsp")
                .forward(request, response);

    }

  

}
