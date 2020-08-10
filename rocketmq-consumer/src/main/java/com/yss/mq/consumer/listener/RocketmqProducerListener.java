package com.yss.mq.consumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeReturnType;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
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
@RocketMQMessageListener(consumerGroup = "rocketmq-producer", topic = "rocketmq-producer2", consumeMode = ConsumeMode.ORDERLY)
public class RocketmqProducerListener implements RocketMQReplyListener<MessageExt, ConsumeReturnType> {

    @Override
    public ConsumeReturnType onMessage(MessageExt messageExt) {
//        if(messageExt.getReconsumeTimes() == 3) {
//            log.info("重试打到3次，不再重试, 自己做操作处理");
//            return;
//        }
//        System.out.println(1/0);
        log.info("收到的message对象为：{}， body为：{}", messageExt.toString(), new String(messageExt.getBody()));
        return ConsumeReturnType.SUCCESS;
    }
}
