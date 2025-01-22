package utilities;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MyEmail {
    private Properties properties;
    private Session session;
    private Message message;
    private Address toAddress;
    private Address ccAddress;
    private MimeMultipart multipart;
    private MimeBodyPart attachment1;
    private MimeBodyPart attachment2;
    private MimeBodyPart messageBodyPart;
    
    public MyEmail() {
    	properties = new Properties();
    	properties.put("mail.smtp.auth", true);
    	properties.put("mail.smtp.host","smtp.gmail.com");
    	properties.put("mail.smtp.port",587);
    	properties.put("mail.smtp.starttls.enable", true);
    	properties.put("mail.transport.protocol", "smtp");
    		
    }
    
    public void setSession(String senderEmailId,String password) {
       
    	session = Session.getInstance(properties,new Authenticator() {
    		@Override
    		protected PasswordAuthentication getPasswordAuthentication() {
    			return new PasswordAuthentication(senderEmailId,password);
    		}
    	});
    } 	
    
    public void draftMail(String subject,String receiverEmailId,String ccEmailId) throws MessagingException {
        message = new MimeMessage(session);
    	message.setSubject(subject);
    	//message.setContent(content, contentType); // same
    	
    	toAddress = new InternetAddress(receiverEmailId);
        ccAddress = new InternetAddress(ccEmailId);
    	message.setRecipient(Message.RecipientType.TO, toAddress);
    	message.setRecipient(Message.RecipientType.CC, ccAddress);
    }
    
   public void draftEmailBody(String body,String bodyFormat, String file1, String file2) throws IOException, MessagingException { 
       multipart = new MimeMultipart();
       
       // drafting emailbody
	   messageBodyPart = new MimeBodyPart();
       messageBodyPart.setContent(body, bodyFormat); // same
	   // file attachment1
	   attachment1 = new MimeBodyPart();		;
	   attachment1.attachFile(new File(file1));
	   // file attachment2
	   attachment2 = new MimeBodyPart();
	   attachment2.attachFile(new File(file2));
	
	   multipart.addBodyPart(messageBodyPart);
	   multipart.addBodyPart(attachment1);
	   multipart.addBodyPart(attachment2);
	   // combining all above changes 
	   message.setContent(multipart);
    } 
    
   public void sendIt() throws MessagingException {
   	    Transport.send(message);
   }
    
}
