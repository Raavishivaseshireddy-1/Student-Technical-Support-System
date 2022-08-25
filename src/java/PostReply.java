/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USER
 */
public class PostReply extends HttpServlet {

    

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String q_id = request.getParameter("qid");
            String content = request.getParameter("description_content");
            int qid = Integer.valueOf(q_id);
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stss?useSSL=false&allowPublicKeyRetrieval=true", "root", "root");
            Statement s = con.createStatement();
            getemail(content, qid);
            int r = s.executeUpdate("delete from query where q_id = '"+ qid +"'");
            
            if(r == 1){
                response.sendRedirect("ViewQueryServlet");
                
            }
            else{
                response.sendRedirect("ViewQueryServlet");
            }
            con.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(StudentLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void getemail(String content, int qid) throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stss?useSSL=false&allowPublicKeyRetrieval=true", "root", "root");
            Statement s = con.createStatement();
            ResultSet email = s.executeQuery("select email from query where q_id = '"+ qid +"'");
            if(email.next()){
                sendEmail(content, email.getString("email"));
            }
            
    }
    public static void sendEmail(String content, String email){
        String host="smtp.gmail.com";  
  final String user="raavi.shiva2222@gmail.com";//change accordingly  
  final String password="iaychksdwppgexmv";//change accordingly  
    
  String to= email;//change accordingly  
  
   //Get the session object  
   Properties props = new Properties();
   props.put("mail.smtp.host", host);
      props.put("mail.smtp.port", "465");
      props.put("mail.smtp.ssl.enable", "true");
      props.put("mail.smtp.auth", "true");
     
     
   Session session = Session.getInstance(props,new javax.mail.Authenticator() {  
      protected PasswordAuthentication getPasswordAuthentication() {  
    return new PasswordAuthentication(user,password);  
      }  
    });  
  
   //Compose the message  
    try {  
     MimeMessage message = new MimeMessage(session);  
     message.setFrom(new InternetAddress(user));  
     message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
     message.setSubject("Student Technical support system");  
     message.setText(content);  
       
    //send the message  
     Transport.send(message);  
  
     System.out.println("message sent successfully...");  
   
     } catch (MessagingException e) {e.printStackTrace();}  
         
    }
}
