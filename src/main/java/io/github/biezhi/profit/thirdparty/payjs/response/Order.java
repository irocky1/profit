package io.github.biezhi.profit.thirdparty.payjs.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Order implements Serializable {
    private static final long serialVersionUID = 797773404737408396L;
    /**
     * 用户自定义数据
     */
    private String attach;
    /**
     * PAYJS 平台商户号
     */
    private String mchid;
    /**
     * 用户 OPENID
     */
    private String openid;
    /**
     * 用户端订单号
     */
    private String out_trade_no;
    /**
     * 订单支付时间
     */
    private String paid_time;
    /**
     * PAYJS 订单号
     */
    private String payjs_order_id;
    /**
     * 1:请求成功 0:请求失败
     */
    private int return_code;
    /**
     * 0：未支付，1：支付成功
     */
    private int status;
    /**
     * 微信显示订单号
     */
    private String transaction_id;
    private String sign;
}