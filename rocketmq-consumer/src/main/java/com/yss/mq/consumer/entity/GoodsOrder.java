package com.yss.mq.consumer.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author DuXueBo
 * @since 2020-07-24 20:21
 */
@Data
@Accessors(chain = true)
public class GoodsOrder {

    private Integer id;
    private Integer count;

    public GoodsOrder() {
    }

    public GoodsOrder(Integer id, Integer count) {
        this.id = id;
        this.count = count;
    }
}
