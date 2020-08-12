package com.yss.mq.producer.manager.controller;

import com.alibaba.fastjson.JSONObject;
import com.yss.mq.producer.manager.entity.Goods;
import com.yss.mq.producer.manager.util.MqUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *
 * </p>
 *
 * @author DuXueBo
 * @since 2020-07-29 20:20
 */
@RequestMapping("/async")
@RestController
@Slf4j
public class AsyncController {

    @Resource
    private MqUtil mqUtil;


    /**
     * 测试路径： http://localhost:8888/rocketmq-producer/async/sendAsyncSend
     */
    @RequestMapping("/sendAsyncSend")
    public void sendAsyncSend() {
        this.mqUtil.asyncSend("tags_async_send", "keys_async_send", new Goods(1, "商品名称1"), new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("发送异步消息成功，sendResult: {}", JSONObject.toJSONString(sendResult));
            }

            @Override
            public void onException(Throwable throwable) {
                log.error("发送异步消息失败", throwable);
            }
        });
    }


    /**
     * 测试路径： http://localhost:8888/rocketmq-producer/async/sendAsyncSendOrderly
     * TODO 异步的顺序消息有问题
     */
    @RequestMapping("/sendAsyncSendOrderly")
    public void sendAsyncSendOrderly() {
        for (int i = 1; i <= 5; i++) {
            this.mqUtil.asyncSendOrderly("tags_async_send_orderly", "keys_async_send_orderly", "该批次统一的hashKey", new Goods(i, String.format("商品名称-%s", i)), new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    log.info("发送异步顺序消息成功，sendResult: {}", JSONObject.toJSONString(sendResult));
                }

                @Override
                public void onException(Throwable throwable) {
                    log.error("发送异步顺序消息失败", throwable);
                }
            });
        }
    }


    /**
     * 测试路径： http://localhost:8888/rocketmq-producer/async/sendAsyncDelay
     */
    @RequestMapping("/sendAsyncDelay")
    public void sendAsyncDelay() {
        this.mqUtil.asyncSendDelay("tags_async_send_delay", "keys_async_send_delay", new Goods(1, "商品名称1"), new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("发送异步延时消息成功，sendResult: {}", JSONObject.toJSONString(sendResult));
            }

            @Override
            public void onException(Throwable throwable) {
                log.error("发送异步延时消息失败", throwable);
            }
        }, 500, 3);
    }


}
