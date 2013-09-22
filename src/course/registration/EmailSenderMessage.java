package course.registration;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

// program that prepares n
public class EmailSenderMessage {

    private static String email;
    private static String passwd;
    private static String message;

    public EmailSenderMessage(String mail, String pass, String msg) throws AddressException, MessagingException {
        email = mail;
        passwd = pass;
        message = msg;
        
        sendMail();
    }

//    public static void main(String[] args)
//            throws AddressException, MessagingException {
//        String host = "smtp.gmail.com";
//        String port = "587";
//        String mailFrom = email;
//        String mailTo = "suthipong.t@stamford.edu";
//        String password = passwd;
//        String subject = "Course Selection";
//        String bodyMessage =
//                "<html><b>Hello</b><br/><i>This is email with an attachment</i></html>";
//        String[] attachment = {"c:/classes/fourfriends.txt"};
//        EmailSender sender = new EmailSender();
//        sender.sendEmail(host, port, mailFrom, password, mailTo, subject,
//                bodyMessage, null);
//    }
    private void sendMail() throws AddressException, MessagingException {
        String host = "smtp.gmail.com";
        String port = "587";
        String mailFrom = email;
        String mailTo = email;
        String password = passwd;
        String subject = "Course Selection";
        String bodyMessage =
                "<html><b>Hello</b><br/><i>Courses you have selected : " + message + "</i></html>";
        //String[] attachment = {"c:/classes/fourfriends.txt"};
        EmailSender sender = new EmailSender();
        sender.sendEmail(host, port, mailFrom, password, mailTo, subject, bodyMessage, null);
    }
}