package com.cucumbercraft.framework;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;



public class EmailReport {
	private static String pathproject=System.getProperty("user.dir");
	private static Properties prop;
	public static void sendEmail(String extendpath,String htmlFile,String subject) throws IOException {
		  //String to="Indumadhab.Chanda1@cbre.com,jayaprakash.sahoo@cbre.com,Santu.Manna@cbre.com";//change accordingly  
		  prop = Settings.getInstance();
		  String to=prop.getProperty("toemail");
		  final String user=prop.getProperty("smtpuser");//change accordingly  
		  final String password=prop.getProperty("smtpassword");//change accordingly  
		   
		  //1) get the session object     
		  Properties properties = System.getProperties();  
		  properties.setProperty("mail.smtp.host", prop.getProperty("smtphost"));  
		  properties.put("mail.smtp.auth", "true");  
		  
		  Session session = Session.getDefaultInstance(properties,  
		   new javax.mail.Authenticator() {  
		   protected PasswordAuthentication getPasswordAuthentication() {  
		   return new PasswordAuthentication(user,password);  
		   }  
		  });    
		  //2) compose message     
		  try{  
		    MimeMessage message = new MimeMessage(session);  
		    message.setFrom(new InternetAddress(prop.getProperty("fromemail"),prop.getProperty("fromname")));  
		    InternetAddress[] address = InternetAddress.parse(to);
		    message.addRecipients(Message.RecipientType.TO,address); 
			message.setSubject(subject);
		    //3) create MimeBodyPart object and set your message text     
		    BodyPart messageBodyPart1 = new MimeBodyPart();  
		    //messageBodyPart1.setText("This is message body");
		    messageBodyPart1.setContent(htmlFile, "text/html");  
		    //4) create new MimeBodyPart object and set DataHandler object to this object      
		    MimeBodyPart messageBodyPart2 = new MimeBodyPart();  
		    String filename = extendpath;//change accordingly  
		    DataSource source = new FileDataSource(filename);  
		    messageBodyPart2.setDataHandler(new DataHandler(source));  
		    messageBodyPart2.setFileName(filename);  
		     
		     
		    //5) create Multipart object and add MimeBodyPart objects to this object      
		    Multipart multipart = new MimeMultipart();  
		    multipart.addBodyPart(messageBodyPart1);  
		    multipart.addBodyPart(messageBodyPart2);  
		  
		    //6) set the multiplart object to the message object  
		    message.setContent(multipart );  
		     
		    //7) send message  
		    Transport.send(message);  
		   
		   System.out.println("message sent....");  
		   }catch (MessagingException ex) {ex.printStackTrace();} 
	}
	
    private static StringBuilder getString() throws IOException {
    	BufferedReader in;
    	StringBuilder contentBuilder = new StringBuilder();
    	in = new BufferedReader(new FileReader(pathproject+"\\test-output\\emailable-report.html"));
        //BufferedReader in = new BufferedReader(new FileReader(reportPath + "\\HTML Results\\Summary.html"));
        String str;
        while ((str = in.readLine()) != null) {
            contentBuilder.append(str);
        }
        return contentBuilder;
    }
}
