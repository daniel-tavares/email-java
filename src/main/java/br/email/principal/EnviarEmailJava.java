package br.email.principal;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class EnviarEmailJava {

   public static void main(String [] args) {

      String to = "djtavaresieq@gmail.com";
      String from = "no-reply@sefa.pa.gov.br";
      String host = "smtp2.sefa.pa.gov.br";

      Properties properties = System.getProperties();
      properties.setProperty("mail.smtp.host", host);
      properties.setProperty("proxySet","true"); 
      properties.setProperty("socksProxyHost","10.3.1.22"); // IP do Servidor Proxy 
      properties.setProperty("socksProxyPort","80");  // Porta do servidor Proxy 

      Session session = Session.getDefaultInstance(properties);

      try{

         MimeMessage message = new MimeMessage(session);
         message.setFrom(new InternetAddress(from));
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

         message.setSubject("SUBJECT !");

         BodyPart messageBodyPart = new MimeBodyPart();
         messageBodyPart.setText("CORPO DA MENSAGEM");

         Multipart multipart = new MimeMultipart();
         multipart.addBodyPart(messageBodyPart);

         messageBodyPart = new MimeBodyPart();
         String arquivo = "SEU_ARQUIVO.txt";
         DataSource source = new FileDataSource(arquivo);
         messageBodyPart.setDataHandler(new DataHandler(source));
         messageBodyPart.setFileName(arquivo);
         multipart.addBodyPart(messageBodyPart);

         message.setContent(multipart);

         Transport.send(message);
         
         System.out.println("Mensagem enviada com sucesso...");

      }catch (MessagingException mex) {
         mex.printStackTrace();
      }
   }
}