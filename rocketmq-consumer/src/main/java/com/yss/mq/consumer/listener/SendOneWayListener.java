package com.yss.mq.consumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 监听TOPIC为：rocketmq-producer 下的TAGS为：tags_send_one_way 下的消息
 * </p>
 *
 * @author DuXueBo
 * @since 2020-07-24 20:38
 */
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "tagsSendOneWayListener", topic = "rocketmq-producer", selectorExpression = "tags_send_one_way")
public class SendOneWayListener implements RocketMQListener<MessageExt> {

    @Override
    public void onMessage(MessageExt messageExt) {
        log.info("监听TAGS为：tags_send_one_way 发送过来的message对象为：{}， body为：{}", messageExt.toString(), new String(messageExt.getBody()));
    }
}
