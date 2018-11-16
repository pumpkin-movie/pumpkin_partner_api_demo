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

        String order_no = "200014100015";
        Integer productPrice = 1000;
        String paySuccessTime = "2018-11-16 22:06:21";
        String payOrderAction = "/order/sync";

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
        parameter.add(new BasicNameValuePair("product_price",productPrice +""));
        parameter.add(new BasicNameValuePair("timestamp", timestamp+""));
        parameter.add(new BasicNameValuePair("signature_nonce", signatureNonce));
        parameter.add(new BasicNameValuePair("format", PartnerInfo.format));
        parameter.add(new BasicNameValuePair("version", PartnerInfo.version));
        parameter.add(new BasicNameValuePair("pay_success_time", paySuccessTime));
        parameter.add(new BasicNameValuePair("order_status", "2"));

        parameter.add(new BasicNameValuePair("sign", sign));


        ResponseEntity result = JSON.parseObject(HttpClientUtil.doPost("http://dev.api.guoing.com:3505"+payOrderAction,parameter),ResponseEntity.class);
        System.out.println(result);
        assertEquals(200,result.getStatusCode());
    }
}
