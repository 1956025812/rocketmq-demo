package com.yss.mq.producer.manager.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Value;
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
 * 3. content统一使用字符串，如果是对象，则采用json字符串
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
     * @param tags    tags
     * @param keys    keys
     * @param content 内容
     */
    public void sendOneWay(String tags, String keys, String content) {
        Message message = new Message(this.springApplicationName, keys, tags, content.getBytes());
        message.getProperties().put("content", content);
        String destination = String.format("%s:%s", this.springApplicationName, tags);
        log.info("发送sendOneWay消息，destination为：{}，keys为：{}， message为：{}, content为: {}", destination, keys, JSONObject.toJSONString(message), content);
        this.rocketMQTemplate.sendOneWay(destination, message);
    }


    /**
     * syncSend发送, 指定topic和tags和keys
     *
     * @param tags    tags
     * @param keys    keys
     * @param content 内容
     * @return SendResult
     */
    public SendResult syncSend(String tags, String keys, String content) {
        Message message = new Message(this.springApplicationName, tags, keys, content.getBytes());
        message.getProperties().put("content", content);
        String destination = String.format("%s:%s", this.springApplicationName, tags);
        log.info("发送syncSend消息，destination为：{}，keys为：{}， message为：{}, content: {}", destination, keys, JSONObject.toJSONString(message), content);
        return this.rocketMQTemplate.syncSend(destination, message);
    }


    /**
     * syncSend批量发送, 指定topic和tags和keys
     *
     * @param tags        tags
     * @param keys        keys
     * @param contentList 内容集合
     * @return SendResult
     */
    public SendResult sycnSendBatch(String tags, String keys, List<String> contentList) {
        String destination = String.format("%s:%s", this.springApplicationName, tags);
        List<Message> messageList = new ArrayList<>(contentList.size());
        contentList.forEach(eachContent -> {
            Message message = new Message(this.springApplicationName, tags, keys, eachContent.getBytes());
            message.getProperties().put("content", eachContent);
            messageList.add(message);
        });
        log.info("发送syncSend消息，destination为：{}，keys为：{}， messageList为：{}, contentList为: {}", destination, keys,
                JSONObject.toJSONString(messageList), JSONObject.toJSONString(contentList));
        return this.rocketMQTemplate.syncSend(destination, messageList);
    }


    /**
     * syncSend顺序发送, 指定topic和tags和keys
     * TODO 始终第一次发送是乱序的 后面就是好的
     *
     * @param tags    tags
     * @param keys    keys
     * @param content 内容
     */
    public SendResult syncSendOrder(String tags, String keys, String content) {
        String destination = String.format("%s:%s", this.springApplicationName, tags);
        Message message = new Message(this.springApplicationName, tags, keys, content.getBytes());
        message.getProperties().put("content", content);
        log.info("发送syncSendOrder消息，destination为：{}，keys为：{}， message为：{}, content为: {}", destination, keys,
                JSONObject.toJSONString(message), JSONObject.toJSONString(content));
        return this.rocketMQTemplate.syncSendOrderly(destination, message, keys);
    }


    /**
     * asyncSend发送
     *
     * @param tags         tags
     * @param keys         keys
     * @param content      内容
     * @param sendCallback 回调事件
     */
    public void asyncSend(String tags, String keys, String content, SendCallback sendCallback) {
        String destination = String.format("%s:%s", this.springApplicationName, tags);
        Message message = new Message(this.springApplicationName, tags, keys, content.getBytes());
        message.getProperties().put("content", content);
        log.info("发送asyncSend消息，destination为：{}，keys为：{}， message为：{}, content为: {}", destination, keys,
                JSONObject.toJSONString(message), JSONObject.toJSONString(content));
        this.rocketMQTemplate.asyncSend(destination, message, sendCallback);
    }


    /**
     * asyncSendOrderly发送
     * TODO 始终第一次发送是乱序的 后面就是好的
     *
     * @param tags         tags
     * @param content      内容
     * @param sendCallback 回调事件
     */
    public void asyncSendOrderly(String tags, String content, SendCallback sendCallback) {
        String destination = String.format("%s:%s", this.springApplicationName, tags);
        Message message = new Message(this.springApplicationName, tags, content.getBytes());
        message.getProperties().put("content", content);
        log.info("发送asyncSendOrderly消息，destination为：{}，message为：{}, content为: {}", destination,
                JSONObject.toJSONString(message), JSONObject.toJSONString(content));
        this.rocketMQTemplate.asyncSendOrderly(destination, message, tags, sendCallback);
    }


}
