package com.example.mymall.listener;

import com.alibaba.fastjson2.JSON;
import com.example.mymall.pojo.PayInfo;
import com.example.mymall.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 黄烨轩
 * @version : 1.0
 * @Project : mymall
 * @Package : com.example.mymall.listener
 * @ClassName : PayMagListener.java
 * @createTime : 2023/8/1 21:25
 */
@Component
@RabbitListener(queues = "payNotify")
@Slf4j
public class PayMagListener {
    @Autowired
    private IOrderService orderService;
    @RabbitHandler
    public void process(String msg){
        log.info("接收到消息：{}" , msg);
        // 将消息转换为PayInfo对象，应该由pay项目提供一个公共的jar包，然后引入到各个项目中
        PayInfo payInfo = JSON.parseObject(msg, PayInfo.class);
        if (payInfo.getPlatformStatus().equals("SUCCESS")){
            //修改订单状态
            orderService.paid(payInfo.getOrderNo());
        }
    }
}
