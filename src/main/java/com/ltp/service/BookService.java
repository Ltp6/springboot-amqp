package com.ltp.service;

import com.ltp.model.Book;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @projectName springboot-amqp
 * @ClassName BookService
 * @Auther Ltp
 * @Date 2019/5/10 14:04
 * @Description Book业务层
 * @Version 1.0
 */
@Service
public class BookService {
    /**
     * @Author Ltp
     * @Description 监听消息队列
     * @Date 2019/5/10 14:06
     * @Param [book]
     * @return void
     **/
    @RabbitListener(queues = "ltp.news")
    public void receiveMsg(Book book) {
        System.out.println(book);
    }
}
