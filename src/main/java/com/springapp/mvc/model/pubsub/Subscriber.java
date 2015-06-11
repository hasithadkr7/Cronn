package com.springapp.mvc.model.pubsub;

/**
 * Created by Hasitha on 6/4/2015.
 */

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Subscriber{
    public String sub;
    public TextMessage resvmsg;
    private static Topic topic1;
    public TopicSubscriber subscriber;
    Session topicSession;
    private String topicName;
    private String userName;
    private String passWrd;
    private String providerUrl;
    private String connectionFac;
    private String clientID;
    private final Logger logger = Logger.getLogger("Subscriber.class");

    public  TopicConnection ConnectionSetup() {
        try{
            Hashtable<String, String> env = new Hashtable();
            env.put("java.naming.provider.url",providerUrl);
            env.put("java.naming.factory.initial",
                    "org.jboss.naming.remote.client.InitialContextFactory");
            env.put("java.naming.security.principal", userName);
            env.put("java.naming.security.credentials", passWrd);
            Context ctx = new InitialContext(env);
            System.out.println();

            TopicConnectionFactory connFactory = (TopicConnectionFactory)ctx
                    .lookup(connectionFac);

            topic1 = (Topic)ctx.lookup(topicName);

            return connFactory.createTopicConnection(userName,passWrd);
        }
        catch (NamingException e){
            e.printStackTrace();
            return null;
        }
        catch (JMSException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void receiveMessage() {
        try {
            TopicConnection topicConn = ConnectionSetup();
            topicConn.setClientID(clientID);
            topicConn.start();
            this.topicSession = topicConn.createSession(false, 1);
            this.subscriber = this.topicSession.createDurableSubscriber(topic1, this.sub);
            String time = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(new Date());
            int i = 0;
            while(subscriber!=null){
                this.resvmsg = ((TextMessage)this.subscriber.receive(100L));
                if (this.resvmsg != null) {
                    System.out.println("***********************************");
                    System.out.println("Received time : " + time);
                    System.out.println("Topic : " + topic1.getTopicName());
                    System.out.println("Received message : " + this.resvmsg.getText());
                    System.out.println(" EVENT-TYPE : " +
                            this.resvmsg.getStringProperty("EVENT_TYPE"));
                    System.out.println(" MSGID : " +
                            this.resvmsg.getStringProperty("MSGID"));
                    System.out.println("***********************************");
                    logger.info(this.resvmsg.getText());
                }
                else {
                    System.out.println("No messages ");
                    logger.info("No messages");
                }
                Thread.currentThread().sleep(3000);
            }
        }
        catch (JMSException e){
            System.out.println("JMSException has occurred.");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("InterruptedException has occurred.");
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

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }
}