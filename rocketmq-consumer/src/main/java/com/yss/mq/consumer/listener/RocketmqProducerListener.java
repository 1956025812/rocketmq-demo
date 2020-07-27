package com.yss.mq.consumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * <p>
 * rocketmq-producer项目的监听器
 * </p>
 *
 * @author DuXueBo
 * @since 2020-07-24 20:38
 */
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "rocketmq-producer", topic = "rocketmq-producer")
public class RocketmqProducerListener implements RocketMQListener<Message> {

    @Override
    public void onMessage(Message message) {
        log.info("收到的message对象为：{}， body为：{}", message.toString(), new String(message.getBody()));
    }
}
