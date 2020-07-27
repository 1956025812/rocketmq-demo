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
    SEND_ONE_WAY_WITH_KEYS("tags_send_one_way", "keys_send_one_way"),
    SYNC_SEND_WITH_KEYS("tags_sync_send", "keys_sync_send"),
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
