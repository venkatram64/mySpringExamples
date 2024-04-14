package com.venkat.myapp.controller;

import com.venkat.myapp.service.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/my-app/v1")
public class SendingEmailController {

    private EmailService emailService;

    public SendingEmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public String mailSender(@RequestParam(value = "file", required = false) MultipartFile[] file,
                             String to , String[] cc, String subject, String body){
        return this.emailService.sendMail(file, to , cc, subject, body);
    }

}
