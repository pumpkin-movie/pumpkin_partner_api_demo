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
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;

/**
 * 影片信息相关的接口调用DEMO
 * <p>
 * User: Xulin Zhuang
 * Date: 29/1/2018
 * Time: 8:44 PM
 */
public class TestMovie {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getMoviesSuccessful() throws Exception {
        String signatureNonce = Random.getRandom(10, Random.TYPE.LETTER_CAPITAL_NUMBER);
        long timestamp = System.currentTimeMillis();

        String pid = PartnerInfo.pid;
        String accessSecret = PartnerInfo.accessSecret;

        LinkedMap params = new LinkedMap();
        params.put("pid", pid);
        params.put("timestamp", timestamp + "");
        params.put("signature_nonce", signatureNonce);
        params.put("format", PartnerInfo.format);
        params.put("version", PartnerInfo.version);
        params.put("sign", PartnersApiSignature.partnersApiSignature(PartnerInfo.httpGetMethod, PartnerInfo.movie_action, PartnerInfo.format, pid, signatureNonce, accessSecret, timestamp, params));
        ResponseEntity<MovieResult> result = JSON.parseObject(HttpClientUtil.doGet("http://dev.api.guoing.com:3505/movie/sync", params), ResponseEntity.class);
        System.out.println(result);
        assertEquals(200, result.getStatusCode());
    }

    @Test
    public void getMovieSignFailure() throws IOException {
        String signatureNonce = Random.getRandom(10, Random.TYPE.LETTER_CAPITAL_NUMBER);
        long timestamp = System.currentTimeMillis();

        LinkedMap params = new LinkedMap();
        params.put("pid", PartnerInfo.pid);
        params.put("timestamp", timestamp + "");
        params.put("signature_nonce", signatureNonce);
        params.put("format", PartnerInfo.format);
        params.put("version", PartnerInfo.version);
        params.put("sign", "ERROR SIGN");
        PayResponseBean result = JSON.parseObject(HttpClientUtil.doGet("http://dev.api.guoing.com:3505/movie/sync", params), PayResponseBean.class);
        assertEquals("17006", result.getStatusCode());

    }

    @Test
    public void getMediaInfoSuccessful() throws Exception {
        String signatureNonce = Random.getRandom(10, Random.TYPE.LETTER_CAPITAL_NUMBER);
        long timestamp = System.currentTimeMillis();

        String pid = PartnerInfo.pid;
        String accessSecret = PartnerInfo.accessSecret;

        LinkedMap params = new LinkedMap();
        params.put("pid", pid);
        params.put("timestamp", timestamp);
        params.put("signature_nonce", signatureNonce);
        params.put("format", PartnerInfo.format);
        params.put("version", PartnerInfo.version);
        params.put("sign", PartnersApiSignature.partnersApiSignature(PartnerInfo.httpGetMethod, PartnerInfo.media_action, PartnerInfo.format, pid, signatureNonce, accessSecret, timestamp, params));
        /** 全量模式 **/
        params.put("mode", "full");

        ResponseEntity<MovieResult> result = JSON.parseObject(HttpClientUtil.doGet("http://dev.api.guoing.com:3505/media/sync", params), ResponseEntity.class);
        System.out.println(result);
        assertEquals(200, result.getStatusCode());
    }

    @Test
    public void getMediaInfoBatchSuccessful() throws Exception {
        String signatureNonce = Random.getRandom(10, Random.TYPE.LETTER_CAPITAL_NUMBER);
        /** 增量：此处以2019-05-09 11:30:00为例，获取在此前半小时内更新的数据 **/
        long timestamp = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse("2019-05-09 11:30:00", new ParsePosition(0)).getTime();

        String pid = PartnerInfo.pid;
        String accessSecret = PartnerInfo.accessSecret;

        LinkedMap params = new LinkedMap();
        params.put("pid", pid);
        params.put("timestamp", timestamp);
        params.put("signature_nonce", signatureNonce);
        params.put("format", PartnerInfo.format);
        params.put("version", PartnerInfo.version);
        params.put("sign", PartnersApiSignature.partnersApiSignature(PartnerInfo.httpGetMethod, PartnerInfo.media_action, PartnerInfo.format, pid, signatureNonce, accessSecret, timestamp, params));
        /** 增量模式 **/
        params.put("mode", "batch");

        ResponseEntity<MovieResult> result = JSON.parseObject(HttpClientUtil.doGet("http://dev.api.guoing.com:3505/media/sync", params), ResponseEntity.class);
        System.out.println(result);
        assertEquals(200, result.getStatusCode());
    }

    @Test
    public void getOfflineMediaSuccessful() throws Exception {
        String signatureNonce = Random.getRandom(10, Random.TYPE.LETTER_CAPITAL_NUMBER);
        /** 增量：此处以2019-05-09 19:30:00为例，获取在此前半小时内更新的数据 **/
        long timestamp = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse("2019-05-09 19:30:00", new ParsePosition(0)).getTime();

        String pid = PartnerInfo.pid;
        String accessSecret = PartnerInfo.accessSecret;

        LinkedMap params = new LinkedMap();
        params.put("pid", pid);
        params.put("timestamp", timestamp);
        params.put("signature_nonce", signatureNonce);
        params.put("format", PartnerInfo.format);
        params.put("version", PartnerInfo.version);
        params.put("sign", PartnersApiSignature.partnersApiSignature(PartnerInfo.httpGetMethod, PartnerInfo.media_offline_action, PartnerInfo.format, pid, signatureNonce, accessSecret, timestamp, params));

        ResponseEntity<MovieResult> result = JSON.parseObject(HttpClientUtil.doGet("http://dev.api.guoing.com:3505/media/offline_sync", params), ResponseEntity.class);
        System.out.println(result);
        assertEquals(200, result.getStatusCode());
    }
}
