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

import org.apache.commons.collections.map.LinkedMap;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * User: Xulin Zhuang
 * Date: 29/1/2018
 * Time: 1:01 PM
 */
public class HttpClientUtil {

    /**
     * post模拟浏览器请求
     *
     * @param url 请求的URL
     * @param parameter 请求参数
     * @return http访问返回值
     */
    public static String doPost(String url, List parameter) throws UnsupportedEncodingException {
        HttpPost post = new HttpPost(url);
        post.addHeader("Connection", "keep-alive");
        post.addHeader("Accept", "*/*");
        post.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

        post.setEntity(new StringEntity(url, "UTF-8"));

        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameter);
        post.setEntity(entity);
        String returnStr = null;
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();
            HttpResponse response = httpclient.execute(post);
            returnStr = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnStr;
    }

    /**
     * get模拟浏览器请求
     *
     * @param url 请求的URL
     * @param parameters 参数
     * @return http访问返回值
     */
    public static String doGet(String url,LinkedMap parameters) throws IOException {
        if (parameters != null && parameters.size() > 0) {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            for (int i = 0, len = parameters.size(); i < len; i++) {
                Object v = parameters.getValue(i);
                if (v == null)
                    continue;
                String value = v.toString();
                String key = parameters.get(i).toString();
                params.add(new BasicNameValuePair(key, value));
            }
            if (url.indexOf("?") > -1) {
                url += "&" + URLEncodedUtils.format(params, "UTF-8");
            } else {
                url += "?" + URLEncodedUtils.format(params, "UTF-8");
            }
        }
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("Content-Type", "text/html;charset=UTF-8");
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(httpGet);
        return EntityUtils.toString(response.getEntity(), "utf-8");
    }
}
