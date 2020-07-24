package com.yss.mq.producer.ordermanager.controller;

import com.alibaba.fastjson.JSONObject;
import com.yss.mq.producer.constant.MqEnum;
import com.yss.mq.producer.ordermanager.entity.Goods;
import com.yss.mq.producer.ordermanager.util.MqUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
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


    @GetMapping("/send")
    public void sendMqMessage() {
        Goods goods = new Goods(2, "goods002");

        // 同步发送：  topic:tags
        SendResult sendResult = this.rocketMQTemplate.syncSend(MqUtil.makeupDestination(MqEnum.BUY_GOODS.getTopic(), MqEnum.BUY_GOODS.getTags()), JSONObject.toJSONString(goods));
        log.info("发送结果：{}", JSONObject.toJSONString(sendResult));

        // 同步发送： topic:tags 和 keys
        Message<Goods> goodsMessage = MessageBuilder.withPayload(goods).setHeader(MqUtil.KEYS, MqEnum.BUY_GOODS.getKeys()).build();
        this.rocketMQTemplate.syncSend(MqUtil.makeupDestination(MqEnum.BUY_GOODS.getTopic(), MqEnum.BUY_GOODS.getTags()), goodsMessage);


    }


}
