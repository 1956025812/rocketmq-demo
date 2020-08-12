package com.yss.mq.producer.constant;

/**
 * <p>
 * 延时级别： 1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
 * 延迟级别下标从1开始
 * 默认是0 不延时
 * </p>
 *
 * @author DuXueBo
 * @since 2020-08-12 15:08
 */
public enum DelayLevel {

    /**
     * 1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
     */
    NO_DELAY(0, "默认不延时"),
    ONE_SECOND(1, "1s"),
    FIVE_SECOND(2, "5s"),
    TEN_SECOND(3, "10s"),
    THIRTY_SECOND(4, "30s"),
    ONE_MINUTE(5, "1m"),
    TWQ_MINUTE(6, "2m"),
    THREE_MINUTE(7, "3m"),
    FOUR_MINUTE(8, "4m"),
    FIVE_MINUTE(9, "5m"),
    SIX_MINUTE(10, "6m"),
    SEVEN_MINUTE(11, "7m"),
    EIGHT_MINUTE(12, "8m"),
    NINE_MINUTE(13, "9m"),
    TEN_MINUTE(14, "10m"),
    TWENTY_MINUTE(15, "20m"),
    THIRTY_MINUTE(16, "30m"),
    ONE_HOUR(17, "1h"),
    TWO_HOUR(18, "2h"),

    ;

    private Integer key;
    private String value;

    DelayLevel(Integer key, String value) {
        this.key = key;
        this.value = value;
    }


    public Integer getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
