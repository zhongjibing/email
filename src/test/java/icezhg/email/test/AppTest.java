package icezhg.email.test;

import java.util.Collections;

import icezhg.email.MailAuth;
import icezhg.email.MailDetail;
import icezhg.email.SimpleMailSender;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private static MailDetail getMailInfo() {
        MailDetail detail = new MailDetail();
        detail.setToAddress(Collections.singletonList("zhongjibing@126.com"));
        detail.setCcAddress(Collections.singletonList("zhongjibing@126.com"));
        detail.setSubject("test send mail type");
        detail.setContent(getMailContent());
        detail.setAttachments(Collections.singletonList("D:\\Pictures\\2017-04-17.png"));
        return detail;
    }

    private static MailAuth getMailAuth() {
        MailAuth auth = new MailAuth();
        auth.setMailServerHost("smtp.126.com");
        auth.setMailServerPort(25);
        auth.setUserName("zhongjibing@126.com");
        auth.setPassword("");
        return auth;
    }

    private static String getMailContent() {
        return "The protocol providers are configured using the follow\n" +
                "<ul>\n" +
                " <li> <code>javamail.providers</code> and\n" +
                "\t<code>javamail.default.providers</code> </li>\n" +
                " <li> <code>javamail.address.map</code> and\n" +
                "\t<code>javamail.default.address.map</code> </li>\n" +
                "</ul>\n" +
                "<p>\n" +
                "Each <code>javamail.</code><i>X</i> resource file is searched fo\n" +
                "three methods in the following order:\n" +
                "<ol>\n" +
                " <li> <code>java.home/lib/javamail.</code><i>X</i> </li>\n" +
                " <li> <code>META-INF/javamail.</code><i>X</i> </li>\n" +
                " <li> <code>META-INF/javamail.default.</code><i>X</i> </li>\n" +
                "</ol>\n" +
                "<p>\n";
    }

    public static void main(String[] args) {
        SimpleMailSender mailSender = new SimpleMailSender(getMailAuth());
        mailSender.sendText(getMailInfo());
        mailSender.send(getMailInfo());
    }

}
