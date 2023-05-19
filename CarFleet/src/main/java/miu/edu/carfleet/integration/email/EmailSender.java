package miu.edu.carfleet.integration.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {
    private Logger logger = LoggerFactory.getLogger(EmailSender.class);

    public void sendEmail(String message){
        logger.info("Sending email message: "+message);
        System.out.println(message);
    }
}
