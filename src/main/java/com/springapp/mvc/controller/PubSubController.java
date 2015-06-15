package com.springapp.mvc.controller;

import com.springapp.mvc.model.pubsub.Publisher;
import com.springapp.mvc.model.pubsub.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class PubSubController {

    @Qualifier("publisher")
    @Autowired
    private Publisher publisher;

    @Qualifier("subscriber")
    @Autowired
    private Subscriber subscriber;

//    private final Logger logger = LoggerFactory.getLogger(PubSubController.class);
    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private Date date = new Date();
    private int id=0;

    private static final Logger logger = LoggerFactory.getLogger(PubSubController.class);
    @RequestMapping(value = "publisher/{topic:.+}", method = RequestMethod.GET)
    public void welcome(@PathVariable("topic") String topic){
        String message = "This is new message!";
        logger.info("Publish message to the topic : ", topic);
        logger.info("Message published :", message);
        logger.info("Published date & time :",dateFormat.format(date));
        publisher.send_message(message,topic);
    }

//    @RequestMapping(value = { "/publisher**" }, method = RequestMethod.GET)
//    public void publishMessage(@ModelAttribute("message") String message,
//                               @ModelAttribute("topic")String topic){
//        logger.info("Publish message to the topic : ", topic);
//        logger.info("Message published :", message);
//        logger.info("Published date & time :",dateFormat.format(date));
//        publisher.send_message(message,topic);
//    }

//    @RequestMapping(value = { "/subscriber**" }, method = RequestMethod.GET)
//    public void ReceiveMessage(@ModelAttribute("topic") String topic){
//        String client_ID = "subscriber_"+id;
//        logger.info("Received message from the topic : ", topic);
//        logger.info("Listening client : ", client_ID);
//        logger.info("Received date & time :", dateFormat.format(date));
//        subscriber.receiveMessage(topic,client_ID);
//        id++;
//    }

    @RequestMapping(value = "subscriber/{topic:.+}", method = RequestMethod.GET)
    public void ReceiveMessage(@PathVariable("topic") String topic){
        String client_ID = "subscriber_"+id;
        logger.info("Received message from the topic : ", topic);
        logger.info("Listening client : ", client_ID);
        logger.info("Received date & time :", dateFormat.format(date));
        subscriber.receiveMessage(topic,client_ID);
        id++;
    }
}