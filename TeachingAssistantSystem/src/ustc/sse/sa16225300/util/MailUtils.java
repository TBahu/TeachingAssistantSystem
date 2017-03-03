package ustc.sse.sa16225300.util;

import org.apache.commons.mail.EmailException;  
import org.apache.commons.mail.HtmlEmail;  

import ustc.sse.sa16225300.domain.Mail;
  
public class MailUtils {  
    public boolean send(Mail mail) {  
        // 发送email  
        HtmlEmail email = new HtmlEmail();  
        try {  
            // 这里是SMTP发送服务器的名字：163的如下："smtp.163.com"  
            email.setHostName(mail.getHost());  
            // 字符编码集的设置  
            email.setCharset(Mail.ENCODEING);  
            // 收件人的邮箱  
            email.addTo(mail.getReceiver());  
            // 发送人的邮箱  
            email.setFrom(mail.getSender(), mail.getName());  
            // 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码  
            email.setAuthentication(mail.getUsername(), mail.getPassword());  
            // 要发送的邮件主题  
            email.setSubject(mail.getSubject());  
            // 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签  
            email.setMsg(mail.getMessage());  
            // 发送  
            email.send();  
            return true;  
        } catch (EmailException e) {  
            e.printStackTrace();  
            return false;  
        }  
    }  
    /*
    public static void main(String[] args) {  
        Mail mail = new Mail();  
        mail.setHost("smtp.163.com"); // 设置邮件服务器,如果不用163的,自己找找看相关的  
        mail.setSender("jun21wang@163.com");  
        mail.setReceiver("1572127227@qq.com"); // 接收人  
        mail.setUsername("jun21wang@163.com"); // 登录账号,一般都是和邮箱名一样吧  
        mail.setPassword("31wjangun14zy"); // 发件人邮箱的登录密码  
        mail.setSubject("aaaaaaaaa");  
        mail.setMessage("bbbbbbbbbbbbbbbbb");  
        new MailUtils().send(mail);  
    } 
    */
    public static void main(String[] args) {  
        Mail mail = new Mail();  
        mail.setHost("mail.ustc.edu.cn"); // 设置邮件服务器,如果不用163的,自己找找看相关的  
        mail.setSender("junwangu@mail.ustc.edu.cn");  
        mail.setReceiver("wxw1992@mail.ustc.edu.cn"); // 接收人  
        mail.setUsername("junwangu@mail.ustc.edu.cn"); // 登录账号,一般都是和邮箱名一样吧  
        mail.setPassword("09jun21"); // 发件人邮箱的登录密码  
        mail.setSubject("aaaaaaaaa");  
        mail.setMessage("bbbbbbbbbbbbbbbbb");  
        new MailUtils().send(mail);  
    }
}  
