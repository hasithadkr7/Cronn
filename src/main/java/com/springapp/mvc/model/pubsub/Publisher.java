package com.springapp.mvc.model.pubsub;

/**
 * Created by Hasitha on 6/4/2015.
 */

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Publisher{
    private TextMessage message;
    private Topic topics;
    private final Logger logger = Logger.getLogger("Publisher.class");
    private String topicName;
    private String userName;
    private String passWrd;
    private String providerUrl;
    private String connectionFac;

    public TopicConnection ConnectionSetup(String topic){
        try{
            this.topicName = topic;
            Properties env = new Properties();
            env.put(Context.PROVIDER_URL, providerUrl);
            env.put(Context.INITIAL_CONTEXT_FACTORY,"org.jboss.naming.remote.client.InitialContextFactory");
            env.put(Context.SECURITY_PRINCIPAL, userName);
            env.put(Context.SECURITY_CREDENTIALS, passWrd);
            InitialContext jndi = new InitialContext(env);
            System.out.println();
            TopicConnectionFactory connFactory = (TopicConnectionFactory)jndi.lookup(connectionFac);
            this.topics = ((Topic)jndi.lookup(this.topicName));

            TopicConnection topicConn = connFactory.createTopicConnection(userName,passWrd);
            logger.log(Level.INFO,"Topic connection creation successful");
            logger.info("Topic connection creation successful");
            return topicConn;
        }
        catch (NamingException e){
            e.printStackTrace();
            return null;
        }
        catch (JMSException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void send_message(String publishMsg,String topic){
        try {
            TopicConnection topicConn = ConnectionSetup(topic);
            TopicSession topicSession = topicConn.createTopicSession(true, 1);
            MessageProducer topicPublisher = topicSession
                    .createPublisher(this.topics);
            int i = 0;
            topicPublisher.setDeliveryMode(2);
            this.message = topicSession.createTextMessage();
            this.message.setText(publishMsg);
            this.message.setStringProperty("MSGID", "MSG-" + i);

            topicPublisher.send(this.message);
            topicSession.commit();
            System.out.println("***************************");
            String time = new SimpleDateFormat("yyyy/MM/DD hh:mm:ss")
                    .format(new Date());
            System.out.println("Published time : " + time);
            System.out.println("Topic name : " + this.topicName);
            System.out.println("ID : " + this.message.getJMSMessageID());
            System.out.println("Message published=" + this.message.getText() +
                    "\nEVENT_TYPE=" +
                    this.message.getStringProperty("EVENT_TYPE") + "\nMSGID=" +
                    this.message.getStringProperty("MSGID"));

            System.out.println("***************************");
            logger.log(Level.INFO, "Published time : " + time + "\nMessage published=" +
                    this.message.getText() + "|EVENT_TYPE=" +
                    this.message.getStringProperty("EVENT_TYPE") + "|MSGID=" +
                    this.message.getStringProperty("MSGID"));
//            logger.info("Published time : " + time + "\nMessage published=" +
//                    this.message.getText() + "|EVENT_TYPE=" +
//                    this.message.getStringProperty("EVENT_TYPE") + "|MSGID=" +
//                    this.message.getStringProperty("MSGID"));
            i++;
            topicConn.close();
            topicSession.close();
        }catch (JMSException e){
            e.printStackTrace();
        }
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassWrd(String passWrd) {
        this.passWrd = passWrd;
    }

    public void setProviderUrl(String providerUrl) {
        this.providerUrl = providerUrl;
    }

    public void setConnectionFac(String connectionFac) {
        this.connectionFac = connectionFac;
    }
}