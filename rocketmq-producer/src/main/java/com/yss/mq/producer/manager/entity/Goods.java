package com.yss.mq.producer.manager.entity;

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
public class Goods {

    private Integer goodsId;
    private String goodsName;

    public Goods() {
    }

    public Goods(Integer goodsId, String goodsName) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
    }
}
