package com.yss.mq.producer.orderManager.controller;

import com.alibaba.fastjson.JSONObject;
import com.yss.mq.producer.config.MqConfig;
import com.yss.mq.producer.orderManager.entity.Goods;
import com.yss.mq.producer.orderManager.util.MqUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * http://localhost:8888/rocketmq-producer/order/send
 * </p>
 *
 * @author DuXueBo
 * @since 2020-07-22 20:53
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Resource
    private RocketMQTemplate rocketMQTemplate;
    @Resource
    private MqConfig mqConfig;


    @GetMapping("/send")
    public void sendMqMessage() {
        log.info("topic: {}, tag: {}, keys: {}", mqConfig.getMqTopicGoodsBuyName(), mqConfig.getMqTopicGoodsBuyTags(), mqConfig.getMqTopicGoodsBuyKeys());
        Goods goods = new Goods(1, "goods001");

        // 同步发送：  topic:tags
        this.rocketMQTemplate.syncSend(MqUtil.makeupDestination(this.mqConfig.getMqTopicGoodsBuyName(), this.mqConfig.getMqTopicGoodsBuyTags()), JSONObject.toJSONString(goods));

        // 同步发送： topic:tags 和 keys
        Message<Goods> message1 = MessageBuilder.withPayload(goods).setHeader(MqUtil.KEYS, this.mqConfig.getMqTopicGoodsBuyKeys()).build();
        this.rocketMQTemplate.syncSend(MqUtil.makeupDestination(this.mqConfig.getMqTopicGoodsBuyName(), this.mqConfig.getMqTopicGoodsBuyTags()), message1);


    }


}
