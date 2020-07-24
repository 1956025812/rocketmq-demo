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
     * 购买商品
     */
    BUY_GOODS("topic_buy_goods", "tags_buy_goods", "keys_buy_goods"),
    ;


    private String topic;
    private String tags;
    private String keys;

    MqEnum(String topic, String tags, String keys) {
        this.topic = topic;
        this.tags = tags;
        this.keys = keys;
    }

    public String getTopic() {
        return topic;
    }

    public String getTags() {
        return tags;
    }

    public String getKeys() {
        return keys;
    }
}
