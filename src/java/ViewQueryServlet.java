/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USER
 */
public class ViewQueryServlet extends HttpServlet {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    int i;

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            i++;
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.println("<html><body style=\"background-color: #deeled\">");
            
            
 
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/stss?useSSL=false&allowPublicKeyRetrieval=true", "root", "root");
            pst = con.prepareStatement("select q_id, email, stu_reg_no, query_content from query");
            rs = pst.executeQuery();
            out.println("<form action=\"PostReply\" method=\"get\">");
             out.println("<table width=60% border= 1 style=\"margin-left: 500px; margin-top: 150px; font-size: 35px\">");
             out.println("<tr><td colspan=6" );
             out.println("<center><h2 style=\"background-color: blue; color: white\">Result of Search Page</h2></center>");
             out.println("<tr style=\"background-color: orange\">");
             out.println("<th>query id</th>");
             out.println("<th>Reg No</th>");
             out.println("<th>query content</th>");
             out.println("<th>enter query id</th>");
             out.println("<th>post replay</th>");
             out.println("<th>submit</th>");
             out.println("</tr>");
              while(rs.next())
              {
                   
                  
                  out.println("<tr>");
                  out.println("<td name=\"qid\" value=\"qid\">" + rs.getInt("q_id") + "</td> ");
                  out.println("<td>" + rs.getString("stu_reg_no") + "</td> ");
                  out.println("<td>" + rs.getString("query_content") + "</td> ");
                  out.println("<td><textarea id='qid' class='qid' name='qid' rows='2' cols='5' style=\"font-size: 25px; font-weight: bold;align-content: center;border: 5px;border-color: black;margin: 0; \" ></textarea></td>");
                  out.println("<td><textarea id='description_content' class='description_content' name='description_content' rows='6' cols='30' style=\"font-size: 25px; font-weight: bold;align-content: center;border: 5px;border-color: black;margin-top: 10px; padding-right: 40px; \" ></textarea></td>");
                  out.println("<td><button onMouseOver=\"this.style.backgroundColor='red'\"\n" +
"    onMouseOut=\"this.style.backgroundColor='orange'\" onMouseOver=\"this.style.color='red'\"\n" + "type=\"submit\" name=\"Post\" value=\"Post\" onclick=\"form.action='PostReply'\" style=\"font-size: 30px;\n" +
"                font-weight: bold;\n" +
"                margin: 35px 35px;\n" +
"                padding: 15px;\n" +
"                width: 65%;\n" +
"                border-radius: 25px;\n" +
"                border: 0;\n" +
"                color: white;\n" +
"                background-color: tomato;\n" +
"                align-content: center;\">post<button></td>");
                  out.println("</tr>");
              }
              
              out.println("</table>");
              out.println("</form>");
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

    


