package io.github.biezhi.profit.thirdparty.payjs.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ScanResult {
    /**
     * 可将该参数生成二维码展示出来进行扫码支付
     */
    private String code_url;
    /**
     * 用户生成的订单号原样返回
     */
    private String out_trade_no;
    /**
     * PAYJS 平台订单号
     */
    private String payjs_order_id;
    /**
     * 二维码图片地址
     */
    private String qrcode;
    /**
     * 1:请求成功，0:请求失败
     */
    private int return_code;
    /**
     * 返回消息
     */
    private String return_msg;
    /**
     * 金额。单位：分
     */
    private String total_fee;
    @JSONField(serialize = false)
    private String sign;

    public String getReturn_msg() {
        return return_msg;
    }
}