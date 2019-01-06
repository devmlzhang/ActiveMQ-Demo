package com.example.demo.message.queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

@Service("producer")
public class Producer {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendTopicMessage(String destination, String message) {
        ActiveMQTopic activeMQTopic = new ActiveMQTopic(destination);
        jmsMessagingTemplate.convertAndSend(activeMQTopic, message);
    }

    public void sendQueueMessage(String destinationName,String message){
        //队列
        Destination destination = new ActiveMQQueue(destinationName);
        jmsMessagingTemplate.convertAndSend(destination,message);
    }
}
