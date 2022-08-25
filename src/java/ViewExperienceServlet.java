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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USER
 */

@WebServlet("/ViewExperienceServlet")
public class ViewExperienceServlet extends HttpServlet {
    int i;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            i++;
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.println("<html><body style=\"background-color: #deeled\">");
            
            String companyname= request.getParameter("combo");
 
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/stss?useSSL=false&allowPublicKeyRetrieval=true", "root", "root");
            pst = con.prepareStatement("select company_name, stu_reg_no, description_content from experiences where company_name='"+companyname+"'");
            rs = pst.executeQuery();
            
             out.println("<table width=60% border= 1 style=\"margin-left: 500px; margin-top: 150px; font-size: 35px\">");
             out.println("<tr><td colspan=4 " );
             out.println("<center><h2 style=\"background-color: blue; color: white\">Result of Search Page</h2></center>");
             out.println("<tr style=\"background-color: orange\">");
             out.println("<th>Company name</th>");
             out.println("<th>Reg no</th>");
             out.println("<th>description content</th>");
             out.println("</tr>");
              
              while(rs.next())
              {
                  out.println("<tr>");
              
                  out.println("<td>" + rs.getString("company_name") + "</td> ");
                  out.println("<td>" + rs.getString("stu_reg_no") + "</td> ");
                  out.println("<td>" + rs.getString("description_content") + "</td> ");
                  out.println("</tr>");
              }
              
              out.println("</table>");
              out.println("</html></body>");
        
               }
        catch (ClassNotFoundException ex) {
          
        }catch (Exception e)
        { throw new ServletException("error", e); }
    }
    
    public void destory()
    {
        i = 0;
    }

}
