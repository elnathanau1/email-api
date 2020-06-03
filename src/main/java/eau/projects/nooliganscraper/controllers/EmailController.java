package eau.projects.nooliganscraper.controllers;

import eau.projects.nooliganscraper.services.EmailService;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
public class EmailController {
    @Autowired
    private EmailService emailService;

    @Value("${controllers.password}")
    private String controllersPassword;

    Logger logger = LoggerFactory.getLogger(EmailController.class);

    @PostMapping("/email")
    String sendEmail(@RequestBody EmailRequest emailRequest, @RequestHeader String authorization) {
        try {
            if (authorization.equals(controllersPassword)) {
                logger.info("{}, {}, {}", emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getMessage());
                emailService.sendSimpleMessage(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getMessage());
                return "Success";
            }
            else {
                return "Unauthorized";
            }

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }

    }
}

@Getter
@Setter
class EmailRequest {
    private String to;
    private String subject;
    private String message;
}