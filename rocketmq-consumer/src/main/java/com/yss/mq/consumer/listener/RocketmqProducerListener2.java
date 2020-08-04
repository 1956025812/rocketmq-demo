package com.yss.mq.consumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.ConsumeReturnType;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQReplyListener;
import org.springframework.stereotype.Component;

/**
 * <p>
 * rocketMq-producer项目的监听器
 * 官方建议： 一个group对应一个topic  子业务用tags和keys做区分
 * </p>
 *
 * @author DuXueBo
 * @since 2020-07-24 20:38
 */
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "rocketmq-producer2", topic = "rocketmq-producer2", consumeMode = ConsumeMode.ORDERLY)
public class RocketmqProducerListener2 implements RocketMQReplyListener<MessageExt, ConsumeReturnType> {

    @Override
    public ConsumeReturnType onMessage(MessageExt messageExt) {
        return ConsumeReturnType.SUCCESS;
    }
}
