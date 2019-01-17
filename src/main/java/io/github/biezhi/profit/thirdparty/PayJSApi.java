package io.github.biezhi.profit.thirdparty;

import com.alibaba.fastjson.JSONObject;
import io.github.biezhi.profit.bootstrap.Bootstrap;
import io.github.biezhi.profit.bootstrap.Constant;
import io.github.biezhi.profit.entities.request.CreateOrderRequest;
import io.github.biezhi.profit.entities.response.PayOrderResponse;
import io.github.biezhi.profit.enums.Platform;
import io.github.biezhi.profit.thirdparty.payjs.SignUtil;
import io.github.biezhi.profit.thirdparty.payjs.response.Order;
import io.github.biezhi.profit.thirdparty.payjs.response.ScanResult;
import io.github.biezhi.request.Request;

import java.util.HashMap;
import java.util.Map;

/**
 * PAYJS API 实现
 *
 * @author biezhi
 * @date 2018/9/29
 */
public class PayJSApi implements PayApi {

    private final String url_base = "https://payjs.cn/api";
    /**
     * 扫码
     */
    private final String url_scan = "/native";
    /**
     * 客户端
     */
    private final String url_client = "/cashier";
    /**
     * 订单查询
     */
    private final String url_order = "/check";

    @Override
    public PayOrderResponse createQrCode(CreateOrderRequest createOrderRequest) {
        Map<String, String> map = new HashMap<>();
        map.put("mchid", Bootstrap.getGlobalConfig().get(Constant.SAFE_PAYJS_MCHID));
        map.put("total_fee", createOrderRequest.getPrice().toString());
        map.put("out_trade_no", createOrderRequest.getOrderNo());
        map.put("body", createOrderRequest.getQrName());
        map.put("attach", createOrderRequest.getOrderNo());
//        map.put("notify_url", notifyUrl);// 回调地址
        map.put("sign", SignUtil.sign(map, Bootstrap.getGlobalConfig().get(Constant.SAFE_PAYJS_SECRET)).toUpperCase());

        map.put("body", createOrderRequest.getQrName());
//        map.put("notify_url", map.get("notify_url"));
        String json = Request.post(url_base + url_scan).form(map).body();
        ScanResult result = JSONObject.parseObject(json, ScanResult.class);

        PayOrderResponse response = new PayOrderResponse();
        response.setQrImg(result.getQrcode());
        response.setQrUrl(result.getCode_url());
        response.setTradeNo(result.getPayjs_order_id());
        return response;
    }

    @Override
    public boolean orderPaySuccess(String tradeNo) {
        Map<String, String> map = new HashMap<>();
        map.put("payjs_order_id", tradeNo);
        map.put("sign", SignUtil.sign(map, Bootstrap.getGlobalConfig().get(Constant.SAFE_PAYJS_SECRET)).toUpperCase());
        String body = Request.post(url_base + url_order).form(map).body();
        Order order = JSONObject.parseObject(body, Order.class);
        return order.getStatus() == 1;
    }

    @Override
    public Platform getPayPlatform() {
        return Platform.PAYJS;
    }
}
