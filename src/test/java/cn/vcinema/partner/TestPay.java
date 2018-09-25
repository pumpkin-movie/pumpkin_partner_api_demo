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

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.map.LinkedMap;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * 支付相关的接口调用DEMO
 *
 * User: Xulin Zhuang
 * Date: 29/1/2018
 * Time: 12:50 PM
 */
public class TestPay {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * 成功获取兑换码测试
     *
     * @throws Exception
     */
    @Test
    public void getRedeemCodeSuccessful() throws Exception {
        String signatureNonce = Random.getRandom(10,Random.TYPE.LETTER_CAPITAL_NUMBER);
        long timestamp = System.currentTimeMillis();

        Map<String,String> params = new HashMap<>();
        params.put("code_type",PartnerInfo.codeType);
        params.put("version",PartnerInfo.version);

        List<NameValuePair> parameter = new ArrayList<>();
        parameter.add(new BasicNameValuePair("pid", PartnerInfo.pid));
        parameter.add(new BasicNameValuePair("code_type", PartnerInfo.codeType));
        parameter.add(new BasicNameValuePair("timestamp", timestamp+""));
        parameter.add(new BasicNameValuePair("signature_nonce", signatureNonce));
        parameter.add(new BasicNameValuePair("format", PartnerInfo.format));
        parameter.add(new BasicNameValuePair("version", PartnerInfo.version));
        parameter.add(new BasicNameValuePair("sign", PartnersApiSignature.partnersApiSignature(PartnerInfo.httpPostMethod,PartnerInfo.pay_action,PartnerInfo.format,PartnerInfo.pid,signatureNonce,PartnerInfo.accessSecret,timestamp,params)));
        PayResponseBean result = JSON.parseObject(HttpClientUtil.doPost("http://dev.api.guoing.com:3505"+PartnerInfo.pay_action,parameter),PayResponseBean.class);
        assertEquals("200",result.getStatusCode());
    }

    /**
     * 签名错误测试
     *
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws UnsupportedEncodingException
     */
    @Test
    public void getRedeemCodeSignFailure() throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        String signatureNonce = Random.getRandom(10,Random.TYPE.LETTER_CAPITAL_NUMBER);
        long timestamp = System.currentTimeMillis();

        List<NameValuePair> parameter = new ArrayList<>();
        parameter.add(new BasicNameValuePair("pid", PartnerInfo.pid));
        parameter.add(new BasicNameValuePair("code_type", PartnerInfo.codeType));
        parameter.add(new BasicNameValuePair("timestamp", timestamp+""));
        parameter.add(new BasicNameValuePair("signature_nonce", signatureNonce));
        parameter.add(new BasicNameValuePair("format", PartnerInfo.format));
        parameter.add(new BasicNameValuePair("version", PartnerInfo.version));
        parameter.add(new BasicNameValuePair("sign", "error sign info"));
        PayResponseBean result = JSON.parseObject(HttpClientUtil.doPost("http://dev.api.guoing.com:3505"+PartnerInfo.pay_action,parameter),PayResponseBean.class);
        assertEquals("17006",result.getStatusCode());
    }

    /**
     * PID错误测试
     *
     * @throws UnsupportedEncodingException
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void getRedeemCodePidError() throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException {
        String signatureNonce = Random.getRandom(10,Random.TYPE.LETTER_CAPITAL_NUMBER);
        long timestamp = System.currentTimeMillis();

        Map<String,String> params = new HashMap<>();
        params.put("code_type",PartnerInfo.codeType);
        params.put("version",PartnerInfo.version);

        List<NameValuePair> parameter = new ArrayList<>();
        parameter.add(new BasicNameValuePair("pid", "ERROR_PID"));
        parameter.add(new BasicNameValuePair("code_type", PartnerInfo.codeType));
        parameter.add(new BasicNameValuePair("timestamp", timestamp+""));
        parameter.add(new BasicNameValuePair("signature_nonce", signatureNonce));
        parameter.add(new BasicNameValuePair("format", PartnerInfo.format));
        parameter.add(new BasicNameValuePair("version", PartnerInfo.version));
        parameter.add(new BasicNameValuePair("sign", PartnersApiSignature.partnersApiSignature(PartnerInfo.httpPostMethod,PartnerInfo.pay_action,PartnerInfo.format,"ERROR_PID",signatureNonce,PartnerInfo.accessSecret,timestamp,params)));
        PayResponseBean result = JSON.parseObject(HttpClientUtil.doPost("http://dev.api.guoing.com:3505"+PartnerInfo.pay_action,parameter),PayResponseBean.class);
        assertEquals("17007",result.getStatusCode());
    }

    /**
     * 获取兑换码统计成功测试
     *
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws IOException
     */
    @Test
    public void getPayStatisticsSuccessful() throws NoSuchAlgorithmException, InvalidKeyException, IOException {
        String signatureNonce = Random.getRandom(10,Random.TYPE.LETTER_CAPITAL_NUMBER);
        long timestamp = System.currentTimeMillis();
        LinkedMap params = new LinkedMap();
        params.put("version", PartnerInfo.version);
        params.put("start_date","2018-01-01");
        params.put("end_date","2018-01-30");
        params.put("pid",PartnerInfo.pid);
        params.put("timestamp", timestamp+"");
        params.put("signature_nonce", signatureNonce);
        params.put("format", PartnerInfo.format);
        params.put("sign", PartnersApiSignature.partnersApiSignature(PartnerInfo.httpGetMethod,PartnerInfo.pay_statistics_action,PartnerInfo.format,PartnerInfo.pid,signatureNonce,PartnerInfo.accessSecret,timestamp,params));

        PayStatisticsResponseBean result = JSON.parseObject(HttpClientUtil.doGet("http://dev.api.guoing.com:3505"+PartnerInfo.pay_statistics_action,params),PayStatisticsResponseBean.class);
        assertEquals("200",result.getStatusCode());
    }


    /**
     * 成功创建一个订单测试
     *
     * @throws Exception
     */
    @Test
    public void payOrderSuccessful() throws Exception {
        String signatureNonce = Random.getRandom(10,Random.TYPE.LETTER_CAPITAL_NUMBER);
        long timestamp = System.currentTimeMillis();

        Map<String,String> params = new HashMap<>();
        params.put("version",PartnerInfo.version);

        params.put("product_id","TEST_PUMPKIN_PRODUCT_ID");
        params.put("account","18502083318");
        params.put("amount","1");
        params.put("shop","pumpkin_film");
        params.put("order_number","100014");

        List<NameValuePair> parameter = new ArrayList<>();
        parameter.add(new BasicNameValuePair("pid", PartnerInfo.pid));
        parameter.add(new BasicNameValuePair("timestamp", timestamp+""));
        parameter.add(new BasicNameValuePair("signature_nonce", signatureNonce));
        parameter.add(new BasicNameValuePair("format", PartnerInfo.format));
        parameter.add(new BasicNameValuePair("version", PartnerInfo.version));

        parameter.add(new BasicNameValuePair("product_id","TEST_PUMPKIN_PRODUCT_ID"));
        parameter.add(new BasicNameValuePair("account","18502083318"));
        parameter.add(new BasicNameValuePair("amount","1"));
        parameter.add(new BasicNameValuePair("shop","pumpkin_film"));
        parameter.add(new BasicNameValuePair("order_number","100014"));


        parameter.add(new BasicNameValuePair("sign", PartnersApiSignature.partnersApiSignature(PartnerInfo.httpPostMethod,PartnerInfo.pay_order_action,PartnerInfo.format,PartnerInfo.pid,signatureNonce,PartnerInfo.accessSecret,timestamp,params)));


        PayResponseBean result = JSON.parseObject(HttpClientUtil.doPost("http://dev.api.guoing.com:3505"+PartnerInfo.pay_order_action,parameter),PayResponseBean.class);
        System.out.println(result);
        assertEquals("200",result.getStatusCode());
    }

    /**
     * 成功创建一个运营商订购订单测试
     *
     * @throws Exception
     */
    @Test
    public void payOrderOperatorSuccessful() throws Exception {
        String signatureNonce = Random.getRandom(10,Random.TYPE.LETTER_CAPITAL_NUMBER);
        long timestamp = System.currentTimeMillis();

        String order_number = "10001410001";
        String productId = "TEST_PUMPKIN_PRODUCT_ID";
        String account = "13466536112";
        String amount = "1";
        String shop = "testShop";
        String updateType = "1";


        Map<String,String> params = new HashMap<>();
        params.put("version",PartnerInfo.version);
        params.put("pid", PartnerInfo.pid);
        params.put("product_id",productId);
        params.put("account",account);
        params.put("amount",amount);
        params.put("shop",shop);
        params.put("order_number",order_number);

        List<NameValuePair> parameter = new ArrayList<>();
        parameter.add(new BasicNameValuePair("pid", PartnerInfo.pid));
        parameter.add(new BasicNameValuePair("timeStamp", timestamp+""));
        parameter.add(new BasicNameValuePair("signatureNonce", signatureNonce));
        parameter.add(new BasicNameValuePair("format", PartnerInfo.format));
        parameter.add(new BasicNameValuePair("version", PartnerInfo.version));

        parameter.add(new BasicNameValuePair("productId",productId));
        parameter.add(new BasicNameValuePair("account",account));
        parameter.add(new BasicNameValuePair("amount",amount));
        parameter.add(new BasicNameValuePair("shop",shop));
        parameter.add(new BasicNameValuePair("orderNumber",order_number));

        parameter.add(new BasicNameValuePair("sign", PartnersApiSignature.partnersApiSignature(PartnerInfo.httpPostMethod,PartnerInfo.pay_order_operator_action,PartnerInfo.format,PartnerInfo.pid,signatureNonce,PartnerInfo.accessSecret,timestamp,params)));
        parameter.add(new BasicNameValuePair("updateType",updateType));

        HNUnicomRspEntity result = JSON.parseObject(HttpClientUtil.doPost("http://dev.api.guoing.com:3505"+PartnerInfo.pay_order_operator_action,parameter),HNUnicomRspEntity.class);

        System.out.println(result.getResultCode());
        System.out.println(result.getMessage());
        assertEquals("0",result.getResultCode().toString());
    }


    /**
     * 成功创建一个订单测试
     *
     * @throws Exception
     */
    @Test
    public void getOrder() throws Exception {
        String signatureNonce = Random.getRandom(10,Random.TYPE.LETTER_CAPITAL_NUMBER);
        long timestamp = System.currentTimeMillis();

        Map<String,String> params = new HashMap<>();
        params.put("version",PartnerInfo.version);

        params.put("shop","pumpkin_film");
        params.put("order_number","100014");

        List<NameValuePair> parameter = new ArrayList<>();
        parameter.add(new BasicNameValuePair("pid", PartnerInfo.pid));
        parameter.add(new BasicNameValuePair("timestamp", timestamp+""));
        parameter.add(new BasicNameValuePair("signature_nonce", signatureNonce));
        parameter.add(new BasicNameValuePair("format", PartnerInfo.format));
        parameter.add(new BasicNameValuePair("version", PartnerInfo.version));

        parameter.add(new BasicNameValuePair("shop","pumpkin_film"));
        parameter.add(new BasicNameValuePair("order_number","100014"));


        parameter.add(new BasicNameValuePair("sign", PartnersApiSignature.partnersApiSignature(PartnerInfo.httpPostMethod,PartnerInfo.get_order_action,PartnerInfo.format,PartnerInfo.pid,signatureNonce,PartnerInfo.accessSecret,timestamp,params)));


        PayResponseBean result = JSON.parseObject(HttpClientUtil.doPost("http://dev.api.guoing.com:3505"+PartnerInfo.get_order_action,parameter),PayResponseBean.class);
        System.out.println(result);
        assertEquals("200",result.getStatusCode());
    }

    /**
     * 请求发送余额不足提醒短信的测试
     *
     * @throws Exception
     */
    @Test
    public void sendInsufficientBalanceSMSSuccessful() throws Exception {
        String signatureNonce = Random.getRandom(10,Random.TYPE.LETTER_CAPITAL_NUMBER);
        long timestamp = System.currentTimeMillis();

        String account = ""; //TODO to init the account by phone number
        String shop = "testShop";


        Map<String,String> params = new HashMap<>();
        params.put("version",PartnerInfo.version);
        params.put("pid", PartnerInfo.pid);
        params.put("account",account);
        params.put("shop",shop);

        List<NameValuePair> parameter = new ArrayList<>();
        parameter.add(new BasicNameValuePair("pid", PartnerInfo.pid));
        parameter.add(new BasicNameValuePair("timeStamp", timestamp+""));
        parameter.add(new BasicNameValuePair("signatureNonce", signatureNonce));
        parameter.add(new BasicNameValuePair("format", PartnerInfo.format));
        parameter.add(new BasicNameValuePair("version", PartnerInfo.version));

        parameter.add(new BasicNameValuePair("account",account));
        parameter.add(new BasicNameValuePair("shop",shop));

        parameter.add(new BasicNameValuePair("sign", PartnersApiSignature.partnersApiSignature(PartnerInfo.httpPostMethod,PartnerInfo.send_insufficient_balance_msg_action,PartnerInfo.format,PartnerInfo.pid,signatureNonce,PartnerInfo.accessSecret,timestamp,params)));

        SendSMSRepEntity result = JSON.parseObject(HttpClientUtil.doPost("http://dev.api.guoing.com:3505"+PartnerInfo.send_insufficient_balance_msg_action,parameter),SendSMSRepEntity.class);

        System.out.println(result.getResultCode());
        System.out.println(result.getMessage());
        assertEquals("0",result.getResultCode().toString());
    }

}

