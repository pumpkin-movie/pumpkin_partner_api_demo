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

/**
 * User: Xulin Zhuang
 * Date: 22/2/2018
 * Time: 4:48 PM
 */
public class TestCdn {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getCdnLog() throws Exception {
        String signatureNonce = Random.getRandom(10,Random.TYPE.LETTER_CAPITAL_NUMBER);
        long timestamp = System.currentTimeMillis();

        LinkedMap params = new LinkedMap();
        params.put("pid","");
        params.put("timestamp", timestamp+"");
        params.put("signature_nonce", signatureNonce);
        params.put("format", PartnerInfo.format);
        params.put("version", PartnerInfo.version);
        params.put("log_date","20180222");
        params.put("sign", PartnersApiSignature.partnersApiSignature(PartnerInfo.httpGetMethod,"/cdn/buffer_statistics",PartnerInfo.format,"pid",signatureNonce,"pid access secret",timestamp,params));
        String result = JSON.parseObject(HttpClientUtil.doGet("http://dev.api.guoing.com:3505/cdn/buffer_statistics",params),String.class);
        System.out.println(result);
        //System.out.println(result.getMessage());
        //assertEquals("200",result.getStatusCode());
    }


    @Test
    public void getCdnBufferStatistics() throws Exception {
        String signatureNonce = Random.getRandom(10,Random.TYPE.LETTER_CAPITAL_NUMBER);
        long timestamp = System.currentTimeMillis();

        LinkedMap params = new LinkedMap();
        params.put("pid","s1");
        params.put("timestamp", timestamp+"");
        params.put("signature_nonce", signatureNonce);
        params.put("format", PartnerInfo.format);
        params.put("version", PartnerInfo.version);
        params.put("log_date","20180202");
        params.put("sign", PartnersApiSignature.partnersApiSignature(PartnerInfo.httpGetMethod,"/cdn/buffer_statistics",PartnerInfo.format,"s1",signatureNonce,"k4LtF4JWay6TG8bD",timestamp,params));
        String result = JSON.parseObject(HttpClientUtil.doGet("http://dev.api.guoing.com:3505/cdn/buffer_statistics",params),String.class);
        System.out.println(result);
        //System.out.println(result.getMessage());
        //assertEquals("200",result.getStatusCode());
    }
}
