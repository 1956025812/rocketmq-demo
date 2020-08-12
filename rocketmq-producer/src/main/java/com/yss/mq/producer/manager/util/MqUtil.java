package com.yss.mq.producer.manager.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * rocketMq工具类
 * 使用规范说明：
 * 1. 官方建议： 一个项目只有一个groupName和一个topic, 都采用项目名称
 * 2. 优先使用tags, tags无法区分再使用keys做细分
 * 3. obj统一使用字符串，如果是对象，则采用json字符串
 *
 * </p>
 *
 * @author DuXueBo
 * @since 2020-07-24 19:58
 */
@Component
@Slf4j
public class MqUtil {

    @Value("${spring.application.name}")
    private String springApplicationName;

    @Resource
    private RocketMQTemplate rocketMQTemplate;


    /**
     * sendOneWay发送, 指定topic和tags和keys
     *
     * @param tags tags
     * @param keys keys
     * @param obj  内容
     */
    public void sendOneWay(String tags, String keys, Object obj) {
        String destination = String.format("%s:%s", this.springApplicationName, tags);
        Message<Object> message = MessageBuilder.withPayload(obj).setHeader(RocketMQHeaders.KEYS, keys).build();
        log.info("发送sendOneWay消息，destination为：{}，keys为：{}, message为: {}, obj为: {}", destination, keys,
                JSONObject.toJSONString(message), JSONObject.toJSONString(obj));
        this.rocketMQTemplate.sendOneWay(destination, message);
    }


    /**
     * syncSend发送, 指定topic和tags和keys
     *
     * @param tags tags
     * @param keys keys
     * @param obj  内容
     * @return SendResult
     */
    public SendResult syncSend(String tags, String keys, Object obj) {
        String destination = String.format("%s:%s", this.springApplicationName, tags);
        Message<Object> message = MessageBuilder.withPayload(obj).setHeader(RocketMQHeaders.KEYS, keys).build();
        log.info("发送syncSend消息，destination为：{}，keys为：{}，message为：{}, obj: {}", destination, keys,
                JSONObject.toJSONString(message), JSONObject.toJSONString(obj));
        return this.rocketMQTemplate.syncSend(destination, message);
    }


    /**
     * syncSend批量发送, 指定topic和tags和keys
     *
     * @param tags    tags
     * @param keys    keys
     * @param objList 内容集合
     * @return SendResult
     */
    public SendResult syncSendBatch(String tags, String keys, List<Object> objList) {
        String destination = String.format("%s:%s", this.springApplicationName, tags);
        List<Message> messageList = new ArrayList<>(objList.size());
        objList.forEach(eachObj -> {
            Message<Object> eachMessage = MessageBuilder.withPayload(eachObj).setHeader(RocketMQHeaders.KEYS, keys).build();
            eachMessage.getHeaders().put(RocketMQHeaders.KEYS, keys);
            messageList.add(eachMessage);
        });
        log.info("发送syncSend消息，destination为：{}，keys为：{}， messageList为：{}, objList为: {}", destination, keys,
                JSONObject.toJSONString(messageList), JSONObject.toJSONString(objList));
        return this.rocketMQTemplate.syncSend(destination, messageList);
    }


    /**
     * syncSend顺序发送, 指定topic和tags和keys
     *
     * @param tags    tags
     * @param keys    keys
     * @param hashKey 根据这个来选定队列，需要顺序发送的消息该值必须一致
     * @param obj     内容
     */
    public SendResult syncSendOrderly(String tags, String keys, String hashKey, Object obj) {
        String destination = String.format("%s:%s", this.springApplicationName, tags);
        Message<Object> message = MessageBuilder.withPayload(obj).setHeader(RocketMQHeaders.KEYS, keys).build();
        log.info("发送syncSendOrder消息，destination为：{}，keys为：{}， message为：{}, obj为: {}", destination, keys,
                JSONObject.toJSONString(message), JSONObject.toJSONString(obj));
        return this.rocketMQTemplate.syncSendOrderly(destination, message, hashKey);
    }


    /**
     * asyncSend发送
     *
     * @param tags         tags
     * @param keys         keys
     * @param obj          内容
     * @param sendCallback 回调事件
     */
    public void asyncSend(String tags, String keys, Object obj, SendCallback sendCallback) {
        String destination = String.format("%s:%s", this.springApplicationName, tags);
        Message<Object> message = MessageBuilder.withPayload(obj).setHeader(RocketMQHeaders.KEYS, keys).build();
        log.info("发送asyncSend消息，destination为：{}，keys为：{}， message为：{}, obj为: {}", destination, keys,
                JSONObject.toJSONString(message), JSONObject.toJSONString(obj));
        this.rocketMQTemplate.asyncSend(destination, message, sendCallback);
    }


    /**
     * asyncSendOrderly发送
     * TODO 异步的顺序消息有问题
     *
     * @param tags         tags
     * @param keys         keys
     * @param hashKey      根据这个来选定队列，需要顺序发送的消息该值必须一致
     * @param obj          内容
     * @param sendCallback 回调事件
     */
    public void asyncSendOrderly(String tags, String keys, String hashKey, Object obj, SendCallback sendCallback) {
        String destination = String.format("%s:%s", this.springApplicationName, tags);
        Message<Object> message = MessageBuilder.withPayload(obj).setHeader(RocketMQHeaders.KEYS, keys).build();
        log.info("发送asyncSendOrderly消息，destination为：{}，message为：{}, obj为: {}", destination,
                JSONObject.toJSONString(message), JSONObject.toJSONString(obj));
        this.rocketMQTemplate.asyncSendOrderly(destination, message, hashKey, sendCallback);
    }


}
