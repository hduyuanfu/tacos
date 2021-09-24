package tacos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tacos.service.MailService;

/**
 * @author yuanfu
 * @data 2021/5/15 10:11
 */
@RestController
@RequestMapping("/mail")
public class MailController {

    private MailService mailService;

    @Autowired
    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping("/simpleMail")
    public String sendSimpleMail(@RequestParam("mailTo") String mailTo) {
        mailService.sendSimpleMail(mailTo);
        return "simpleMail success";
    }

    @GetMapping("/mimeMail")
    public String sendMimeMail(String mailTo) {
        mailService.sendMimeMail(mailTo);
        return "MimeMail success";
    }
}
