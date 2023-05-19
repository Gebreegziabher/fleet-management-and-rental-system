package miu.edu.carfleet.integration.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import miu.edu.carfleet.common.CarAvailabilityInstruction;
import miu.edu.carfleet.event.CarAvailabilityChangedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JMSListener {
    @Autowired
    private ApplicationEventPublisher publisher;
    private Logger logger = LoggerFactory.getLogger(JMSListener.class);

    @JmsListener(destination = "carReservationQueue")
    public void receiveMessage(String instructionAsString) {
        logger.info("Message received: " + instructionAsString);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            CarAvailabilityInstruction inst = objectMapper.readValue(instructionAsString, CarAvailabilityInstruction.class);
            var instruction = new CarAvailabilityInstruction(inst.getLicencePlate(), inst.getAvailability());
            var event = new CarAvailabilityChangedEvent(instruction);
            publisher.publishEvent(event);
        } catch (IOException e) {
            logger.error("JMS receiver: Cannot convert : " + instructionAsString + " to CarInventoryInstruction object");
        }
    }
}
