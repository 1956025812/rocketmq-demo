package com.yss.mq.producer.constant;

/**
 * <p>
 * MQ枚举
 * </p>
 *
 * @author DuXueBo
 * @since 2020-07-24 21:12
 */
public enum MqEnum {

    /**
     *
     */
    SEND_ONE_WAY("send_one_way"),
    SEND_ONE_WAY_WITH_KEYS("send_one_way", "send_one_way_keys"),
    ;


    private String tags;
    private String keys;

    MqEnum(String tags) {
        this.tags = tags;
    }

    MqEnum(String tags, String keys) {
        this.tags = tags;
        this.keys = keys;
    }


    public String getTags() {
        return tags;
    }

    public String getKeys() {
        return keys;
    }
}
