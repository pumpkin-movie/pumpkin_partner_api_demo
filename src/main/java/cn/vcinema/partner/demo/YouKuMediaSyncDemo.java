package cn.vcinema.partner.demo;

import cn.vcinema.partner.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.map.LinkedMap;

/**
 * @author Han ZC
 * @date 2020-11-24 15:29
 */
public class YouKuMediaSyncDemo {
    private static final String PREFIX_URL = "https://dev-environmental.vcinema.cn:3505";

    /**
     * 获取媒资内容变动列表
     *
     * @throws Exception
     */
    public static JSONObject getChangeListSuccessful() throws Exception {
        String signatureNonce = Random.getRandom(10, Random.TYPE.LETTER_CAPITAL_NUMBER);
        long timestamp = System.currentTimeMillis();

        String pid = PartnerInfo.pid;
        String accessSecret = PartnerInfo.accessSecret;
        String action = "/movie/change_list";

        // 封装加密参数
        LinkedMap params = new LinkedMap();
        params.put("pid", pid);
        params.put("timestamp", Long.toString(timestamp));
        params.put("signature_nonce", signatureNonce);
        params.put("format", PartnerInfo.format);
        params.put("version", PartnerInfo.version);
        params.put("sign", PartnersApiSignature.partnersApiSignature(PartnerInfo.httpGetMethod, action, PartnerInfo.format, pid, signatureNonce, accessSecret, timestamp, params));
        JSONObject responseJSON = JSON.parseObject(HttpClientUtil.doGet(PREFIX_URL + action, params));
        if (200 == responseJSON.getInteger("status_code")) {
            return responseJSON.getJSONObject("content");
        } else {
            return null;
        }
    }

    /**
     * 获取跳转链接
     *
     * @throws Exception
     */
    public static String getRedirectLinkSuccessful() throws Exception {
        String signatureNonce = Random.getRandom(10, Random.TYPE.LETTER_CAPITAL_NUMBER);
        long timestamp = System.currentTimeMillis();

        String pid = PartnerInfo.pid;
        String accessSecret = PartnerInfo.accessSecret;
        String action = "/movie/redirect_link";

        LinkedMap params = new LinkedMap();
        params.put("movie_type", "1");
        params.put("movie_id", "30414");
        params.put("pid", pid);
        params.put("timestamp", Long.toString(timestamp));
        params.put("signature_nonce", signatureNonce);
        params.put("format", PartnerInfo.format);
        params.put("version", PartnerInfo.version);
        params.put("sign", PartnersApiSignature.partnersApiSignature(PartnerInfo.httpGetMethod, action, PartnerInfo.format, pid, signatureNonce, accessSecret, timestamp, params));
        JSONObject responseJSON = JSON.parseObject(HttpClientUtil.doGet(PREFIX_URL + action, params));
        if (200 == responseJSON.getInteger("status_code")) {
            return responseJSON.getJSONObject("content").getString("full_url");
        } else {
            return null;
        }
    }


    public static void main(String[] args) {
        try {
            JSONObject changelist = getChangeListSuccessful();
            System.out.println(changelist);
            String fullUrl = getRedirectLinkSuccessful();
            System.out.println(fullUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
