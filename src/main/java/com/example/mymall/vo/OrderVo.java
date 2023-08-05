package com.example.mymall.vo;

import com.example.mymall.pojo.Shipping;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 黄烨轩
 * @version : 1.0
 * @Project : mymall
 * @Package : com.example.mymall.vo
 * @ClassName : OrderVo.java
 * @createTime : 2023/7/30 20:11
 */
@Data
public class OrderVo {

    private Long orderNo;

    private Integer shippingId;

    private Integer paymentType;

    private Integer postage;

    private Integer status;

    private Date paymentTime;

    private Date sendTime;

    private Date endTime;

    private Date closeTime;

    private Date createTime;

    private BigDecimal payment;

    private List<OrderItemVo> orderItemVoList;

    private Shipping shipping;
}
