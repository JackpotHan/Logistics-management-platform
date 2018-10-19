package com.jackpotHan.message;

import org.apache.commons.lang3.StringEscapeUtils;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import java.util.Properties;
/**
 * @Author: Hanjt
 * @Date: 2018/8/15 15:24
 * @Description:
 */
public class MailUtil {

    private static String smtp_host = "smtp.163.com"; // 网易
    private static String username = "hjt950722@163.com"; // 邮箱账户
    private static String password = "hjt950722"; // 邮箱授权码

    private static String from = "hjt950722@163.com"; // 使用当前账户
    public static String emailUrl ="" ;

    public static void sendMail(String subject, String content, String to) {
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", smtp_host);
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.auth", "true");
        Session session = Session.getInstance(props);
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from));
            message.setRecipient(RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setContent(content, "text/html;charset=utf-8");
            Transport transport = session.getTransport();
            transport.connect(smtp_host, username, password);
            transport.sendMessage(message, message.getAllRecipients());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("邮件发送失败...");
        }
    }

//    public static void main(String[] args) {
//        sendMail("测试","测试测试，收到请Q1","1599309975@qq.com");
//    }

    public static void main(String[] args) {
        String str = "{\\\"name\\\":\\\"spy\\\"}";
        System.out.println("原始 str = " + str);
        String str1 = StringEscapeUtils.unescapeJava(str);
        System.out.println("目标 str1 = " + str1);
    }
}
