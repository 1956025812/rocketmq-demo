package com.yss.mq.producer;

import com.yss.mq.producer.constant.MqEnum;
import com.yss.mq.producer.ordermanager.entity.Goods;
import com.yss.mq.producer.ordermanager.util.MqUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * <p>
 *
 * </p>
 *
 * @author DuXueBo
 * @since 2020-07-26 13:39
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MqProducerApplication.class)
@Slf4j
public class MqProducerTest {

    @Resource
    private RocketMQTemplate rocketMQTemplate;


    /**
     * sendOneWay方式； 不关心发送结果
     */
    @Test
    public void testSendOneWay() {
        Goods goods = new Goods(1, "goods001");
        this.rocketMQTemplate.sendOneWay(MqUtil.makeupDestination(MqEnum.BUY_GOODS.getTopic(), MqEnum.BUY_GOODS.getTags()), goods);
    }


    /**
     * 同步发送
     */
    @Test
    public void testSendSyncMsg() {

        // 同步发送： topic 如下俩种方式等价
        this.rocketMQTemplate.convertAndSend("topic1", "同步发送1-1,指定topic1");
        this.rocketMQTemplate.syncSend("topic1", MessageBuilder.withPayload("同步发送1-2,指定topic1").build());

        // 同步发送：  topic:tags
        this.rocketMQTemplate.syncSend(MqUtil.makeupDestination("topic1", "tags1"), "同步发送1-3,指定topic1和tags1");

        // 同步发送： topic:tags 和 keys
        Message<String> message = MessageBuilder.withPayload("同步发送1-4,指定topic1和tags1和keys1").setHeader(MqUtil.KEYS, "keys1").build();
        this.rocketMQTemplate.syncSend(MqUtil.makeupDestination("topic1", "tags1"), message);

        // 同步发送延时消息 TODO

        // 同步发送顺序消息： topic
        for (int i = 1; i <= 5; i++) {
            this.rocketMQTemplate.syncSendOrderly("topic1", String.format("同步发送顺序消息：%s,指定topic1", i), "orderMsg-topic1");
        }

    }


}
