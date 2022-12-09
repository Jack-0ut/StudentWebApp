/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package sumdu.edu.ua;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Implementation of the User adding
 * @author HP
 */
@WebServlet(name = "Default", urlPatterns = {"/"},loadOnStartup=1)
public class StudentAdd extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter printWriter = null;
        // initialize printWriter to get data from server response 
        try{
            printWriter = response.getWriter();
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException ex) {
            ex.printStackTrace(printWriter);
            printWriter.print(ex.getMessage());
        }
        
        // connect to mysql db 
        Connection conn = null;
        try {
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3311/university","root","root");
            
            // check if user name and surname aren't empty or null
            // and age is parsable to int
            
            if(!"".equals(request.getParameter("name")) && !"".equals(request.getParameter("surname"))
                && isValidAge(request.getParameter("age"))){
                
                //insert into db new user's data 
                String query="INSERT INTO Student(name, surname, age, email, group_, faculty)VALUES(?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(
                        query
                );
                
                ps.setString(1,request.getParameter("name"));
                ps.setString(2,request.getParameter("surname"));
                ps.setInt(3,Integer.parseInt(request.getParameter("age")));
                ps.setString(4,request.getParameter("email"));
                ps.setString(5,request.getParameter("group"));
                ps.setString(6,request.getParameter("faculty"));
                // update db so new data could be saved  
                ps.executeUpdate();
                // reload the web page 
                response.sendRedirect("./");
            }

            // Select all users from DB 
            Statement s = (Statement) conn.createStatement();
            ResultSet resultSet = s.executeQuery("Select * From Student");

            List<Student> students = new LinkedList<>();
            // get data from db and add it to the list 'students'
            while(resultSet.next()){
                Student student = new Student(
                        resultSet.getString(2),resultSet.getString(3),
                        resultSet.getInt(4),resultSet.getString(5),
                        resultSet.getString(6),resultSet.getString(7)
                );
                students.add(student);
            }
            // set list as an attribute 'students' on jsp 
            request.setAttribute("students",students);
            request.getRequestDispatcher("view.jsp").forward(request,response);
        } catch (SQLException ex) {
            printWriter.print(ex.getMessage());
            ex.printStackTrace();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            // close connection
            if (conn != null){
                try{
                    conn.close();
                } catch (SQLException ex) {
                    ex.getMessage();
                }
            }
        }
           
    }   
    // Check if the age is string contains the integer and it's integer is in range for age 
    private boolean isValidAge(String strNum) {
    if (strNum == null) {
        return false;
    }
    try {
        int d = Integer.parseInt(strNum);
        return d >= 15 && d <= 50;
    } catch (NumberFormatException nfe) {
        return false;
    }
}
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
