package com.ltp;

import com.ltp.model.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootAmqpApplicationTests {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpAdmin amqpAdmin;

    /**
     * @return
     * @Author Ltp
     * @Description 点对点发送消息
     * @Date 2019/5/10 13:56
     * @Param
     **/
    @Test
    public void contextLoads() {
        //Message需要自己构造一个;定义消息体内容和消息头
        //rabbitTemplate.send(exchage,routeKey,message);

        Map<String, Object> map = new HashMap<>();
        map.put("msg", "第一次使用，有点紧张");
        map.put("list", Arrays.asList("测试", 123, true, false));
        //object默认当成消息体，只需要传入要发送的对象，自动序列化发送给rabbitmq,默认采用JDK方式序列化
        //rabbitTemplate.convertAndSend(exchage,routeKey,object);
        rabbitTemplate.convertAndSend("exchange.direct", "ltp", map);
    }

    /**
     * @return
     * @Author Ltp
     * @Description 接收消息
     * @Date 2019/5/10 13:56
     * @Param
     **/
    @Test
    public void receiveMessage() {
        Object o = rabbitTemplate.receiveAndConvert("ltp");
        System.out.println(o.getClass());
        System.out.println(o);
    }

    /**
     * @return
     * @Author Ltp
     * @Description 采用广播方式发送消息
     * @Date 2019/5/10 14:05
     * @Param
     **/
    @Test
    public void sendMsg() {
        rabbitTemplate.convertAndSend("exchange.fanout", "", new Book("jugg", "不知道"));
    }

    /**
     * @return
     * @Author Ltp
     * @Description 增加Exchange
     * @Date 2019/5/10 16:32
     * @Param
     **/
    @Test
    public void createExchange() {
        amqpAdmin.declareExchange(new DirectExchange("direct.ltp", true, true));
    }
    /**
     * @return
     * @Author Ltp
     * @Description 创建Queue
     * @Date 2019/5/10 16:32
     * @Param
     **/
    @Test
    public void createQueue() {
        amqpAdmin.declareQueue(new Queue("jugg.emps",true));
    }
    /**
     * @return
     * @Author Ltp
     * @Description 创建Bingding
     * @Date 2019/5/10 16:32
     * @Param
     **/
    @Test
    public void createBingding() {
        amqpAdmin.declareBinding(new Binding("jugg.emps",Binding.DestinationType.QUEUE,"direct.ltp","jugg.emps",null));
    }
}
