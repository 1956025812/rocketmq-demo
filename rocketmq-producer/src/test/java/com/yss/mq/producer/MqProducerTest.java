package com.yss.mq.producer;

import com.yss.mq.producer.constant.MqEnum;
import com.yss.mq.producer.ordermanager.util.MqUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
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
    private MqUtil mqUtil;


    /**
     * sendOneWay方式； 不关心发送结果
     */
    @Test
    public void testSendOneWay() {
        this.mqUtil.sendOneWay("sendOneWay消息：使用默认topic");
        this.mqUtil.sendOneWay(MqEnum.SEND_ONE_WAY.getTags(), "sendOneWay消息：使用默认topic并指定tags");
        this.mqUtil.sendOneWay(MqEnum.SEND_ONE_WAY_WITH_KEYS.getTags(), MqEnum.SEND_ONE_WAY_WITH_KEYS.getKeys(), "sendOneWay消息：使用默认topic并指定topic和keys");
    }


}
