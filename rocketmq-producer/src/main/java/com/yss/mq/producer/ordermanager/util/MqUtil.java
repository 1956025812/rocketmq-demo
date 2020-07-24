package com.yss.mq.producer.ordermanager.util;

import org.springframework.stereotype.Component;

/**
 * <p>
 * rocketMq工具类
 * </p>
 *
 * @author DuXueBo
 * @since 2020-07-24 19:58
 */
@Component
public class MqUtil {

    public static final String KEYS = "KEYS";


    /**
     * 构造Destination
     *
     * @param topic 主题
     * @param tags  标签
     * @return 主题:标签
     */
    public static String makeupDestination(String topic, String tags) {
        return String.format("%s:%s", topic, tags);
    }



}
