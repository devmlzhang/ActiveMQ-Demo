package com.example.demo.message.time;

import com.example.demo.message.queue.Producer;
import com.example.demo.message.topic.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import javax.jms.Topic;


@Component
@EnableScheduling
public class taskService {

    @Autowired
    PublishService publishService;

    @Autowired
    Producer  producer;

    @Value("${queue}")
    private String queue;
    @Value("{topic$}")
    private String topic;

    @Scheduled(fixedRate = 2000) //每分钟执行一次statusCheck方法
    public void statusCheck() {
        for(int i=0;i<=1;i++){
            producer.sendQueueMessage(queue,"测试}"+i);
           // publishService.publish("测试"+i);
        }

    //statusTask.healthCheck();  
    }
}
