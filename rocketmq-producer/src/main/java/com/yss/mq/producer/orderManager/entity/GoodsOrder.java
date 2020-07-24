package com.yss.mq.producer.orderManager.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author DuXueBo
 * @since 2020-07-24 12:09
 */
@Data
@Accessors(chain = true)
public class GoodsOrder {

    private Integer orderId;
    private String orderUuid;

    public GoodsOrder() {
    }

    public GoodsOrder(Integer orderId, String orderUuid) {
        this.orderId = orderId;
        this.orderUuid = orderUuid;
    }
}
