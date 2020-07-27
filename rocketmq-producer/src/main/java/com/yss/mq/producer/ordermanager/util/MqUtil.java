package com.yss.mq.producer.ordermanager.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

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

    private static final String KEYS = "KEYS";

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
        Message message = new Message(this.springApplicationName, tags, keys, content.getBytes());
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
        String destination = String.format("%s:%s", this.springApplicationName, tags);
        log.info("发送syncSend消息，destination为：{}，keys为：{}， message为：{}, content: {}", destination, keys, JSONObject.toJSONString(message), content);
        return this.rocketMQTemplate.syncSend(destination, message);
    }


}
