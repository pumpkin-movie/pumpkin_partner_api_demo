package cn.vcinema.partner.demo;

import cn.vcinema.partner.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.map.LinkedMap;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Han ZC
 * @date 2020-11-24 15:29
 */
public class YouKuMediaSyncDemo {
    private static final String PREFIX_URL = "https://dev-environmental.vcinema.cn:3505";

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getChangeListSuccessful() throws Exception {
        String signatureNonce = Random.getRandom(10, Random.TYPE.LETTER_CAPITAL_NUMBER);
        long timestamp = System.currentTimeMillis();

        String pid = PartnerInfo.pid;
        String accessSecret = PartnerInfo.accessSecret;
        String action = "/movie/change_list";

        LinkedMap params = new LinkedMap();
        params.put("pid", pid);
        params.put("timestamp", Long.toString(timestamp));
        params.put("signature_nonce", signatureNonce);
        params.put("format", PartnerInfo.format);
        params.put("version", PartnerInfo.version);
        params.put("sign", PartnersApiSignature.partnersApiSignature(PartnerInfo.httpGetMethod, action, PartnerInfo.format, pid, signatureNonce, accessSecret, timestamp, params));
        JSONObject result = JSON.parseObject(HttpClientUtil.doGet(PREFIX_URL + action, params));
        System.out.println(result);
        Assert.assertEquals(200L, result.getLongValue("status_code"));
    }

    @Test
    public void getRedirectLinkSuccessful() throws Exception {
        String signatureNonce = Random.getRandom(10, Random.TYPE.LETTER_CAPITAL_NUMBER);
        long timestamp = System.currentTimeMillis();

        String pid = PartnerInfo.pid;
        String accessSecret = PartnerInfo.accessSecret;
        String action = "/movie/redirect_link";

        LinkedMap params = new LinkedMap();
        params.put("movie_type", "1");
        params.put("movie_id", "30414");
        params.put("pid", pid);
        params.put("timestamp",  Long.toString(timestamp));
        params.put("signature_nonce", signatureNonce);
        params.put("format", PartnerInfo.format);
        params.put("version", PartnerInfo.version);
        params.put("sign", PartnersApiSignature.partnersApiSignature(PartnerInfo.httpGetMethod, action, PartnerInfo.format, pid, signatureNonce, accessSecret, timestamp, params));
        JSONObject result = JSON.parseObject(HttpClientUtil.doGet(PREFIX_URL + action, params));
        System.out.println(result);
        Assert.assertEquals(200L, result.getLongValue("status_code"));
    }
}
