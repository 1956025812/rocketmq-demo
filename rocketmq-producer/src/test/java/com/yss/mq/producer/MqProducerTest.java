package com.yss.mq.producer;

import com.alibaba.fastjson.JSONObject;
import com.yss.mq.producer.constant.MqEnum;
import com.yss.mq.producer.manager.entity.Goods;
import com.yss.mq.producer.manager.util.MqUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 消息生产者测试类
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
    private MqUtil mqUtil;


    /**
     * sendOneWay方式； 不关心发送结果
     */
    @Test
    public void testSendOneWay() {
        this.mqUtil.sendOneWay(MqEnum.SEND_ONE_WAY_WITH_KEYS.getTags(), MqEnum.SEND_ONE_WAY_WITH_KEYS.getKeys(), "sendOneWay消息：使用默认topic并指定topic和keys");
        this.mqUtil.sendOneWay(MqEnum.SEND_ONE_WAY_WITH_KEYS.getTags(), MqEnum.SEND_ONE_WAY_WITH_KEYS.getKeys(), new Goods(1, "商品名称1"));
    }


    /**
     * syncSend方式: 同步发送单条
     */
    @Test
    public void testSyncSendSingle() {
        SendResult sendResult = this.mqUtil.syncSend("tags_sync_send_single", "keys_sync_send_single", new Goods(2, "商品名称2"));
        log.info("sendResult: {}", JSONObject.toJSONString(sendResult));
    }


    /**
     * syncSend方式：同步发送多条
     */
    @Test
    public void testSyncSendBatch() {
        List<Object> goodsList = new ArrayList<>(3);
        goodsList.add(new Goods(1, "商品名称1"));
        goodsList.add(new Goods(2, "商品名称2"));
        goodsList.add(new Goods(3, "商品名称3"));
        SendResult sendResult = this.mqUtil.syncSendBatch("tags_sync_send_batch", "keys_sync_send_batch", goodsList);
        log.info("sendResult: {}", JSONObject.toJSONString(sendResult));
    }


    /**
     * syncSend方式：同步顺序发送
     */
    @Test
    public void testSyncSendOrderly() {
        for (int i = 1; i <= 5; i++) {
            Goods goods = new Goods(i, String.format("商品名称-%s", i));
            SendResult sendResult = this.mqUtil.syncSendOrderly("tags_sync_send_orderly", "keys_sync_send_orderly", "该批次统一的hashKey", goods);
            log.info("sendResult: {}", JSONObject.toJSONString(sendResult));
        }
    }


    /**
     * 发送延迟消息
     */
    @Test
    public void testSyncSendDelay() {
        Goods goods = new Goods(1, "商品名称1");
        SendResult sendResult = this.mqUtil.syncSendDelay("tags_sync_send_delay", "keys_sync_send_delay", goods, 500, 4);
        log.info("sendResult: {}", JSONObject.toJSONString(sendResult));
    }


}
