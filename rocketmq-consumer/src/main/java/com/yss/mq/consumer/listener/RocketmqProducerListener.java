package com.yss.mq.consumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
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
@RocketMQMessageListener(consumerGroup = "rocketmq-producer", topic = "rocketmq-producer", consumeMode = ConsumeMode.ORDERLY)
public class RocketmqProducerListener implements RocketMQListener<MessageExt> {

    @Override
    public void onMessage(MessageExt messageExt) {
//        if(messageExt.getReconsumeTimes() == 3) {
//            log.info("重试打到3次，不再重试, 自己做操作处理");
//            return;
//        }
//        System.out.println(1/0);
        log.info("收到的message对象为：{}， body为：{}", messageExt.toString(), new String(messageExt.getBody()));
    }


}
