package eau.projects.email.api.controllers;

import eau.projects.email.api.services.EmailService;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/email")
    public ResponseEntity sendEmail(@RequestBody EmailRequest emailRequest, @RequestHeader String authorization) {
        if (authorization.equals(controllersPassword)) {
            emailService.sendSimpleMessage(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getMessage());
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
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
