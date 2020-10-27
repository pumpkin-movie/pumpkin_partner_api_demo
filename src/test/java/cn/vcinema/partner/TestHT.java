package cn.vcinema.partner;

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.map.LinkedMap;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestHT {

    private static final String prefix_url = "https://dev.partner.vcinema.cn:3505";

    @Test
    public void getHTOrdersTest() throws Exception {

        String signatureNonce = Random.getRandom(10, Random.TYPE.LETTER_CAPITAL_NUMBER); // 获取10位随机数
        String action = "/pay/get_order_ht"; // 请求路径
        String pid = PartnerInfo.pid; // pid，第三方标识，分配获得
        String accessSecret = PartnerInfo.accessSecret; // 加密秘钥，分配获得
        String httpMethod = PartnerInfo.httpPostMethod; //请求方式
        long timestamp = System.currentTimeMillis(); // 时间戳
        String format = PartnerInfo.format; // 返回格式
        String phone = "130xxxxxxx1,131xxxxxxx2"; // 查询的手机号码，多个逗号分隔

        LinkedMap signParams = new LinkedMap();
        signParams.put("phone", phone);
        signParams.put("timestamp", timestamp + "");
        signParams.put("signature_nonce", signatureNonce);
        signParams.put("format", format);

        // 获取加密信息
        String signature = PartnersApiSignature.partnersApiSignature(httpMethod, action, format, pid, signatureNonce, accessSecret, timestamp, signParams);

        /**
         * 封装post参数
         */
        List<NameValuePair> parameter = new ArrayList<>();
        parameter.add(new BasicNameValuePair("phone", phone));
        parameter.add(new BasicNameValuePair("timestamp", String.valueOf(timestamp)));
        parameter.add(new BasicNameValuePair("signature_nonce", signatureNonce));
        parameter.add(new BasicNameValuePair("format", format));
        parameter.add(new BasicNameValuePair("sign", signature));

        /**
         * 获取信息
         */
        String url = prefix_url + "/" + action; // 请求地址
        String result = HttpClientUtil.doPost(url, parameter);
        System.out.println(result);

    }
}
