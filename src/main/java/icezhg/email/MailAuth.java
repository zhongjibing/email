package icezhg.email;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Created by zhongjibing on 2017/4/20.
 */
public class MailAuth extends Authenticator {

    private String mailServerHost;
    private int mailServerPort = 25;
    private String userName;
    private String password;

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, password);
    }

    public Properties properties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", mailServerHost);
        properties.put("mail.smtp.port", mailServerPort);
        properties.put("mail.smtp.auth", "true");
        return properties;
    }

    public String getMailServerHost() {
        return mailServerHost;
    }

    public void setMailServerHost(String mailServerHost) {
        this.mailServerHost = mailServerHost;
    }

    public int getMailServerPort() {
        return mailServerPort;
    }

    public void setMailServerPort(int mailServerPort) {
        this.mailServerPort = mailServerPort;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
