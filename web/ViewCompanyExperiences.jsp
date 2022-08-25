<%-- 
    Document   : ViewCompanyExperiences
    Created on : 24 Aug, 2022, 12:49:28 AM
    Author     : USER
--%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Shared Experiences</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
        
        <style>
            * {
                padding: 0;
                margin: 0;
                font-family: "Times New Roman", Times, serif;
            }
            .banner {
                padding: 20px;
                text-align: center;
                background: rgb(2,0,36);
                background: linear-gradient(90deg, rgba(2,0,36,1) 0%, rgba(9,9,121,1) 35%, rgba(0,212,255,1) 100%);
                color: white;
                font-size: 15px;
            }
            .banner input{
                font-size: 20px;
                font-weight: bold;
                padding: 8px;
                width: 5%;
                border: 0;
                color: white;
                background-color: #f00;
                float: right;
                align-content: center;
            }
            .banner input:hover{
                background: greenyellow;
                color: black;
            }
            body{
                background-color: #dee1ed;
            }
            .container{
                background-color: white;
                margin-bottom: 100px;
            }
            .container h1, h3{
                color: blue;
                margin-top: 50px;
                text-align: center;
                font-size: 32px;
                
            }
            
            .input-group{
                margin: 30px 450px;
            }
            
        </style>
    </head>
    <body>
        <div class="banner">
            <input type="submit" name="back" value="back" onclick="history.back()">
            <h1>STUDENT TECHNICAL SUPPORT SYSTEM</h1>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <h1>View Posts</h1>
                    <hr>
                    <div class="input-group">
                        <form method="get" action="ViewExperienceServlet">    
                      <div class="form-group">
                          <h3>Select Company</h3>
                    </div>
                </div>
            </div>
       <%
           Connection con;
            PreparedStatement pst;
            ResultSet rs;
         try
{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/stss?useSSL=false&allowPublicKeyRetrieval=true", "root", "root");
            pst = con.prepareStatement("select name from companies");
            rs=pst.executeQuery();
            
            if(rs.next())
   {
       
   out.println("<div style=\"font-size: 40px; font-align: center; padding-bottom: 80px; padding-left: 200px;\">");
      out.println("<tr>");
              out.println("<td>Choose the company</td>");
      out.println("<td>");
      out.println("<select name='combo'>");
      do{
            String company_name = rs.getString("name");
    out.println("<option value='"+ company_name   +"'>" +  company_name+  "</option>");
          }
            while(rs.next());
   out.println("</select>");
           rs.close();
   out.println("</td></tr>");
           out.println("</tr><td colspan=2 align=center><input type=submit  value=search></td></tr>");
           }
           else
            {
           out.println("<tr>");
   out.println("<td colspan=2 align=right>");
   out.println("Sorry table is Empty");
           out.println("</td>");
            }
 
   out.println("</form>");
   out.println("</div>");
  }
        catch(Exception e)
        {
                 e.printStackTrace(); // Or System.out.println(e);
        }
                              
        %>
         </div>       
          </form>                    
                    
              </div>      
              
     </div>
</div>
    </body>
</html>
