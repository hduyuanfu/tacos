package tacos.service;

/**
 * @author yuanfu
 * @data 2021/5/15 9:54
 */
public interface MailService {

    void sendSimpleMail(String mailTo);

    void sendMimeMail(String mailTo);
}
