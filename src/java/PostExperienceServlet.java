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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USER
 */
public class PostExperienceServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String companyname = request.getParameter("combo");
        String regno = request.getParameter("regno");
        String description = request.getParameter("description_content");
        if(companyname != null && regno != null && description != null){
            try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stss?useSSL=false&allowPublicKeyRetrieval=true", "root", "root");
            PreparedStatement s;
            s = con.prepareStatement("insert into Experiences(stu_reg_no, company_name, description_content) values(?, ?, ?)");
            s.setString(1, regno);
            s.setString(2, companyname);
            s.setString(3, description);
            int x = s.executeUpdate();
            
            response.sendRedirect("PostExperience.jsp");
            
            s.close();
            con.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(StudentLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        else{
            response.sendRedirect("PostExperience.jsp");
        }
    }
}
