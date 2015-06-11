package com.springapp.mvc.controller;

import com.springapp.mvc.model.pubsub.Publisher;
import com.springapp.mvc.model.pubsub.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PubSubController {

    @Qualifier("publisher")
    @Autowired
    private Publisher publisher;

    @Qualifier("subscriber")
    @Autowired
    private Subscriber subscriber;

    private static final Logger logger = LoggerFactory.getLogger(PubSubController.class);
    @RequestMapping(value = "publisher/{message:.+}", method = RequestMethod.GET)
    public void welcome(@PathVariable("message") String message){
        logger.debug("welcome() - name {}", message);
        publisher.send_message(message);
    }

    @RequestMapping(value = "subscriber/{message:.+}", method = RequestMethod.GET)
    public void ReceiveMessage(@PathVariable("message") String message){
        logger.debug("welcome() - message {}", message);
        subscriber.receiveMessage();
    }
}