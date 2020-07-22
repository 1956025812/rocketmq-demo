package com.yss.mq.producer.order;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * <p>
 *
 * </p>
 *
 * @author DuXueBo
 * @since 2020-07-22 20:53
 */
@RequestMapping("/order")
public class OrderController {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @GetMapping("/send")
    public void sendMqMessage() {

    }


}
