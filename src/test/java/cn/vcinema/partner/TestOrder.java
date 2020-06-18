package cn.vcinema.partner;

import com.alibaba.fastjson.JSON;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by wangyong on 2018/11/16.
 */
public class TestOrder {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }


    /**
     * 成功创建一个同步订单的测试
     *
     * @throws Exception
     */
    @Test
    public void orderSyncSuccessful() throws Exception {
        String signatureNonce = Random.getRandom(10,Random.TYPE.LETTER_CAPITAL_NUMBER);
        long timestamp = System.currentTimeMillis();

        String order_no = "200014100016";
        Integer productPrice = 1000;
        String paySuccessTime = "2018-11-16 22:06:21";
        String payOrderAction = "/order/sync";
        String product_id = "test_product_id";
        String pay_type = "ALIPAY";
        String startTime = "2018-11-16 22:06:21";
        String endTime = "2018-12-16 22:06:21";
        String channel = "test_channel";
        String deviceId = "test_device_id";
        String userId = "test_userId";
        Integer platform = 1;
        String ip = "test_ip";

        Map<String,String> params = new HashMap<>();
        params.put("version",PartnerInfo.version);
        params.put("order_no",order_no);

        String sign = PartnersApiSignature.partnersApiSignature(PartnerInfo.httpPostMethod,
                payOrderAction,
                PartnerInfo.format,
                PartnerInfo.pid,
                signatureNonce,
                PartnerInfo.accessSecret,
                timestamp,
                params);

        List<NameValuePair> parameter = new ArrayList<>();
        parameter.add(new BasicNameValuePair("pid", PartnerInfo.pid));
        parameter.add(new BasicNameValuePair("order_no",order_no));
        parameter.add(new BasicNameValuePair("product_id",product_id));
        parameter.add(new BasicNameValuePair("product_price",productPrice +""));
        parameter.add(new BasicNameValuePair("timestamp", timestamp+""));
        parameter.add(new BasicNameValuePair("signature_nonce", signatureNonce));
        parameter.add(new BasicNameValuePair("format", PartnerInfo.format));
        parameter.add(new BasicNameValuePair("pay_type",pay_type));
        parameter.add(new BasicNameValuePair("order_status", "2"));
        parameter.add(new BasicNameValuePair("pay_success_time", paySuccessTime));
        parameter.add(new BasicNameValuePair("start_time",startTime));
        parameter.add(new BasicNameValuePair("end_time",endTime));
        parameter.add(new BasicNameValuePair("channel",channel));
        parameter.add(new BasicNameValuePair("device_id",deviceId));
        parameter.add(new BasicNameValuePair("user_id",userId));
        parameter.add(new BasicNameValuePair("platform",platform +""));
        parameter.add(new BasicNameValuePair("app_version", PartnerInfo.version));
        parameter.add(new BasicNameValuePair("ip",ip));
        parameter.add(new BasicNameValuePair("sign", sign));


        ResponseEntity result = JSON.parseObject(HttpClientUtil.doPost("https://dev.partner.vcinema.cn:3505"+payOrderAction,parameter),ResponseEntity.class);
        System.out.println(result);
        assertEquals(200,result.getStatusCode());
    }
}
