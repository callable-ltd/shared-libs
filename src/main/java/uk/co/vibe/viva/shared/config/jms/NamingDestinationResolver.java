package uk.co.vibe.viva.shared.config.jms;


import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.destination.DestinationResolver;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;

@Configuration
public class NamingDestinationResolver implements DestinationResolver {

    @Override
    public Destination resolveDestinationName(Session session,
                                              String destinationName,
                                              boolean b) throws JMSException {
        if (destinationName.contains("queue")) {
            return session.createQueue(destinationName);
        } else
            return session.createTopic(destinationName);
    }

}
