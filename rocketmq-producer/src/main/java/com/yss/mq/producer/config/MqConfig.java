package com.yss.mq.producer.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * MQ生产配置类
 * </p>
 *
 * @author DuXueBo
 * @since 2020-07-24 12:04
 */
@Configuration
@Data
public class MqConfig {

    @Value("${mq.topic.name}")
    private String mqTopicName;

    @Value("${mq.topic.tag}")
    private String mqTopicTag;

    @Value("${mq.topic.keys}")
    private String mqTopicKeys;



}
