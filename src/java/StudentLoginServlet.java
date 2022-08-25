/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class StudentLoginServlet extends HttpServlet {

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stss?useSSL=false&allowPublicKeyRetrieval=true", "root", "root");
            Statement s = con.createStatement();
            ResultSet r = s.executeQuery("select * from student where reg_no= '"+username+"' and password= '" + password + "'");
            if(r.next() == true){
                response.sendRedirect("StudentHome.html");
                
            }
            else{
                response.sendRedirect("StudentLogin.html");
            }
            con.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(StudentLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
