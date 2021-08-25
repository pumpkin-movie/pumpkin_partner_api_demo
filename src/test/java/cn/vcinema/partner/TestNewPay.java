/*
 *  Copyright (c) 2017-present, Pumpkin Movie, Inc. All rights reserved.
 *
 *  You are hereby granted a non-exclusive, worldwide, royalty-free license to use,
 *  copy, modify, and distribute this software in source code or binary form for use
 *  in connection with the web services and APIs provided by Pumpkin Movie.
 *
 *  As with any software that integrates with the pumpkin movie platform, your use of
 *  this software is subject to the Pumpkin Movie Developer Principles and Policies
 *  [http://developers.vcinema.com/policy/]. This copyright notice shall be
 *  included in all copies or substantial portions of the software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 *  FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 *  COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 *  IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 *  CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package cn.vcinema.partner;


import com.google.common.collect.Maps;
import com.google.common.io.BaseEncoding;
import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.*;

/**
 * 新版会员直充接口调用DEMO
 * <p>
 * User: yanglu
 * Date: 16/8/2021
 */
public class TestNewPay {

    /**
     * 普通会员直充新版接口测试
     *
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws UnsupportedEncodingException
     */
    @Test
    public void pay() throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("+8"));
        Long timestamp = now.toEpochSecond(ZoneOffset.of("+8")) * 1000;
        String action = "/pay/new_pay_order";
        String format = "JSON";
        String methodName = "POST";
        String orderNumber = "2021062815391624872097944"; // 订单编号 唯⼀ 不可重复
        String pid = "PID"; //商户PID
        String accessSecret = "accessSecret"; //商户密钥
        String signatureNonce = "signatureNonce"; // 随机数 不可重复
        String productCode = "ng-3rd-m-nd07"; // 产品ID
        String account = "18339****25"; // 充值⼿机号
        String amount = "1"; // 充值数量 ⽬前只⽀持 1
        String shop = "shop"; // 渠道
        LinkedHashMap<String, String> queryMap = Maps.newLinkedHashMap();
        queryMap.put("signature_nonce", signatureNonce);
        queryMap.put("version", "v1");
        queryMap.put("format", format);
        queryMap.put("timestamp", timestamp + "");
        queryMap.put("pid", pid);
        queryMap.put("product_code", productCode);
        queryMap.put("account", account);
        queryMap.put("amount", amount);
        queryMap.put("shop", shop);
        queryMap.put("order_number", orderNumber);
        Map<String, String> params = new HashMap<>();
        params.put("version", "v1");
        params.put("account", account);
        params.put("amount", amount);
        params.put("shop", shop);
        params.put("order_number", orderNumber);
        params.put("product_code", productCode);
        params.put("pid", pid);
        params.put("action", action);
        params.put("format", format);
        params.put("signature_nonce", signatureNonce);
        params.put("timestamp", timestamp + "");
        // 获取签名 sign
        String signature = signatureByHmacSHA1(methodName, accessSecret, params);
        queryMap.put("sign", signature);
        //请求参数value需使⽤URLEncode进⾏编码
        StringBuilder builder = new StringBuilder("?");
        for (String key : queryMap.keySet()) {
            String value = queryMap.get(key);
            builder.append(key).append("=").append(URLEncoder.encode(value, "utf-8")).append("&");
        }
        //测试环境接口地址
        UriComponentsBuilder uriComponentsBuilder =
                UriComponentsBuilder.fromHttpUrl("https://dev-environmental.vcinema.cn:3505/pay/new_pay_order" + builder);
        URI uri = uriComponentsBuilder.build(true).toUri();
        MultiValueMap<String, Object> requestParam = new LinkedMultiValueMap<>();
        RestTemplate restTemplate = new RestTemplate();
        Map result = restTemplate.postForObject(uri, requestParam, Map.class);
    }

    /**
     * 获取签名
     * @param method
     * @param accessSecret
     * @param param
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws InvalidKeyException
     */
    private static String signatureByHmacSHA1(String method, String accessSecret, Map<String, String> param) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
        List keys = new ArrayList<>(param.keySet());
        Collections.sort(keys);
        StringBuilder sb = new StringBuilder();
        String SEPARATOR = "&";
        String EQUAL = "=";
        Iterator var7 = keys.iterator();
        String stringToSign;
        while (var7.hasNext()) {
            stringToSign = (String) var7.next();
            String value = (String) param.get(stringToSign);
            sb.append(SEPARATOR).append(percentEncode(stringToSign)).append(EQUAL).append(percentEncode(value));
        }
        String canonicalizedQueryString = sb.substring(1);
        stringToSign = percentEncode(method) + SEPARATOR +
                percentEncode("/") + SEPARATOR + percentEncode(canonicalizedQueryString);
        SecretKey key = new SecretKeySpec((accessSecret +
                SEPARATOR).getBytes("utf-8"), "http://www.w3.org/2000/09/xmldsig#hmacsha1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(key);
        byte[] hmacValue = mac.doFinal(stringToSign.getBytes("utf-8"));
        return BaseEncoding.base64().encode(hmacValue);
    }

    /**
     * URL编码
     * @param s
     * @return
     * @throws UnsupportedEncodingException
     */
    private static String percentEncode(String s) throws UnsupportedEncodingException {
        return s == null ? null : URLEncoder.encode(s, "utf-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
    }

}

