package com.example.demo.message.queue;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

@Component
public class QueueConsumer {


    //定义消费者监听
    @JmsListener(destination = "${queue}",containerFactory = "myFactory")
    public void receiveQueue(final TextMessage message, Session session) throws JMSException {
        try {
            String text = message.getText();
            System.out.println(Thread.currentThread().getName() + " 消费消息：" + text);

            //制造异常
           /* int number = Integer.parseInt(text.substring(text.indexOf("}") + 1));
            if(number==49){
                number = 0/0;
            }
           */

            //消息确认
            message.acknowledge();
        } catch (JMSException e) {
            e.printStackTrace();
            //异常重试
            session.recover();
        }
    }
}
