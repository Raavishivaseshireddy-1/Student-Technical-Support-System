<%-- 
    Document   : PostExperience
    Created on : 24 Aug, 2022, 2:11:20 PM
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
        <title>Post Experience</title>
        <style>
            * {
                padding: 0;
                margin: 0;
                font-family: "Times New Roman", Times, serif;
            }
            body{
                background-color: #dee1ed;
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
            
            .btn-add:hover{
                background-color: blue;
                display: flex;
            }
            .container{
                
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
            .container .container-2 input{
                font-size: 20px;
                font-weight: bold;
                padding: 8px;
                width: 12%;
                align-content: center;
                border: 5px;
                border-color: black;
                margin-left: 1100px; 
                margin-top: 10px;
            }
            .container .container-2 h1{
                margin-top: 0;
            }
            .container .textarea textarea{
                font-size: 20px;font-weight: bold;align-content: center;border: 5px;border-color: black;margin-left: 700px;margin-top: 10px;
            }
            
        </style>
    </head>
    <body>
        <div class="banner">
            <input type="submit" name="back" value="back" onclick="history.back()">
            <h1>STUDENT TECHNICAL SUPPORT SYSTEM</h1>
        </div>
        <div class="container">
            <h1>View Posts</h1>
            <hr>
            <h3>Select Company</h3>
            <%
           Connection con;
            PreparedStatement pst;
            ResultSet rs;
            try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/stss?useSSL=false&allowPublicKeyRetrieval=true", "root", "root");
            pst = con.prepareStatement("select name from companies");
            rs=pst.executeQuery();
            
            if(rs.next()){
                out.println("<div style=\"font-size: 40px; font-align: center; padding-bottom: 80px; padding-left: 1000px;\">");
                out.println("<form method=\"post\" action=\"PostExperienceServlet\">");
                out.println("<tr>");
                out.println("<td>Select company</td>");
                out.println("<td>");
                out.println("<select name='combo' style=\"padding-rigth: 25px; font-size: 35px; background-color: blue; color: white\">");
                do{
                    String company_name = rs.getString("name");
                    out.println("<option value='"+ company_name   +"'>" +  company_name+  "</option>");
                }
                while(rs.next());
                out.println("</select>");
                out.println("<h1 style=\"margin-left: -1100px \" >Reg No</h1>");
                out.println("<input type='text' class='form-control' id ='regno' name='regno' placeholder='Reg No' style=\"font-size: 20px; font-weight: bold; padding: 8px; width: 12%;align-content: center; border: 5px;border-color: black; margin-left: 120px; margin-top: 10px\"></br>");
                out.println("<h1 style=\"margin-left: -1100px \" >Content</h1>");
                out.println("<textarea id='description_content' class='description_content' name='description_content' rows='25' cols='150' style=\" font-size: 20px;font-weight: bold;align-content: center;border: 5px;border-color: black;margin-left: -350px;margin-top: 10px; \" ></textarea></br>");
                out.println("<input type=submit  value=Post style=\"padding-right: 20px; padding-left: 20px; font-size: 35px; background-color: green; color: white; margin-left: 220px; margin-top: 5px\">");
                rs.close();    
            }
            out.println("</form");
            out.println("</div>");
            }
            catch(Exception e){
                 e.printStackTrace();
            }
        %>
            </form>
    </div>
    </body>
</html>
