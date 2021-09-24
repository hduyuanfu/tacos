package tacos.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import tacos.service.MailService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author yuanfu
 * @data 2021/5/15 9:55
 */
@Slf4j
@Service
public class MailServiceImpl implements MailService {

    private JavaMailSender mailSender;

    @Value("${mail.from}")
    private String mailFrom;

    @Autowired
    public MailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendSimpleMail(String mailTo) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailFrom);
        message.setTo(mailTo);
        message.setSubject("Simple mail");
        message.setText("hello world");
        mailSender.send(message);
        log.info("简单邮件已经发送");
    }

    @Override
    public void sendMimeMail(String mailTo) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try{
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(mailFrom);
            helper.setTo(mailTo);
            helper.setSubject("Mime mail");
            helper.setText("hello world", true);
            FileSystemResource file = new FileSystemResource(new File("H:\\显卡汇报.xlsx"));
            String fileName = file.getFilename();
            helper.addAttachment(fileName, file);
            mailSender.send(message);
            log.info("Mime邮件已发送");
        } catch (MessagingException e) {
            log.error("MimeMail error", e);
        }
    }
}
