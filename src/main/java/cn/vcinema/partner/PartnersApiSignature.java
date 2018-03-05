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

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.crypto.dsig.SignatureMethod;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Pumpkin Partners API 安全签名机制
 *
 * Update by songlijun on 24/01/2018 3:47 PM.
 * Version: v1.0
 * Update by songlijun on 24/01/2018 3:47 PM.
 */
public class PartnersApiSignature {

    private final static String ENCODE_TYPE = "utf-8";


    public static void main(String[] args) throws Exception {
        String httpMethod = "POST";
        String action = "getA";
        String format = "JSON";
        String pid = "101010";
        String signatureNonce = Random.getRandom(30,Random.TYPE.LETTER_CAPITAL_NUMBER);
        String accessSecret = "sign_key";
        long timestamp = System.currentTimeMillis();
        Map params = new HashMap();
        params.put("code_type","1m");
        String sign = partnersApiSignature(httpMethod,action,format,pid,signatureNonce,accessSecret,timestamp,params);
        System.out.println(sign);
    }

    /**
     * API签名
     * @param httpMethod http请求方式，GET，POST，DELETE
     * @param action 调用的方法名称
     * @param format 返回值格式化，JSON or XML
     * @param pid 合作伙伴ID
     * @param signatureNonce 签名随机数
     * @param accessSecret 签名对应的秘钥
     * @param timestamp 时间戳
     * @param params 方法其他参数MAP值
     * @return 对应的签名值
     * @throws NoSuchAlgorithmException NoSuchAlgorithmException
     * @throws InvalidKeyException InvalidKeyException
     * @throws UnsupportedEncodingException UnsupportedEncodingException
     */
    public static String partnersApiSignature(String httpMethod,String action,String format,String pid,String signatureNonce,String accessSecret,long timestamp, Map<String, String> params) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        if (params == null){
            params = new HashMap<>();
        }
        if(httpMethod == null || "".equalsIgnoreCase(httpMethod)
                || action == null || "".equalsIgnoreCase(action)
                || format == null || "".equalsIgnoreCase(format)
                || pid == null || "".equalsIgnoreCase(pid)
                || signatureNonce == null || "".equalsIgnoreCase(signatureNonce)
                || accessSecret == null || "".equalsIgnoreCase(accessSecret)){
            return "";
        }
        params.put("pid",pid);
        params.put("action",action);
        params.put("format",format);
        params.put("signature_nonce",signatureNonce);
        params.put("timestamp",timestamp+"");
        return signatureByHmacSHA1(httpMethod,accessSecret,params);
    }

    /**
     * HmacSHA1 模式签名
     *
     * @param method 调用的HTTP方法类型，GET／POST／DELETE
     * @param accessSecret 签名秘钥
     * @param param HTTP参数
     * @return 对应的签名值
     * @throws NoSuchAlgorithmException NoSuchAlgorithmException
     * @throws UnsupportedEncodingException UnsupportedEncodingException
     * @throws InvalidKeyException InvalidKeyException
     */
    private static String signatureByHmacSHA1(String method,String accessSecret, Map<String, String> param) throws NoSuchAlgorithmException,
            UnsupportedEncodingException,
            InvalidKeyException {
        List<String> keys = new ArrayList<>(param.keySet());

        // Step 1: canonicalize
        Collections.sort(keys);
        StringBuilder sb = new StringBuilder();
        String SEPARATOR = "&";
        String EQUAL = "=";
        for (String key : keys) {
            String value = param.get(key);
            sb.append(SEPARATOR).append(percentEncode(key)).append(EQUAL).append(percentEncode(value));
        }
        String canonicalizedQueryString = sb.substring(1);

        // Step 2: prepare string to sign
        StringBuilder signedData = new StringBuilder();
        signedData.append(percentEncode(method));
        signedData.append(SEPARATOR);
        signedData.append(percentEncode("/"));
        signedData.append(SEPARATOR);
        signedData.append(percentEncode(canonicalizedQueryString));
        String stringToSign = signedData.toString();
//
//        stringToSign = "POST%26%2f%26account%3d18502083318%26action%3d%252fpay%252fpay_order%26amount%3d1%26format%3dJSON%26order_number%3d100014%26pid%3dTEST_PID%26product_id%3dTEST_PUMPKIN_PRODUCT_ID%26shop%3dpumpkin_film%26signature_nonce%3d8396d7c8019e4ce5a1516080ccbfd6%26timestamp%3d1519983480%26version%3dv1";
        // Step 3: sign
        SecretKey key = new SecretKeySpec((accessSecret + SEPARATOR).getBytes(ENCODE_TYPE), SignatureMethod.HMAC_SHA1);
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(key);
        byte[] hmacValue = mac.doFinal(stringToSign.getBytes(ENCODE_TYPE));

        // Step 4: base64 encode
        return Base64.getEncoder().encodeToString(hmacValue);


    }

     /**
     *  i.对于字符A-Z、a-z、0-9以及字符"-"、"_"、"."、"~"不编码;
     *  ii. 对于其它字符编码成"%XY"的格式，其中XY是字符对应ASCII码的16进制表示。比如英文的双引号(")对应的编码就是%22
     *  iii.对于扩展的UTF-8字符，编码成"%XY%ZA…"的格式；
     *  iv. 需要说明的是英文空格（ ）要被编码是%20，而不是加号（+）。
     * 一般支持URL编码的库（比如Java中的java.net.URLEncoder）都是按照”application/x-www-form-urlencoded”的MIME类型的规则进行编码的。
     * 实现时可以直接使用这类方式进行编码，把编码后的字符串中加号（+）替换成%20、星号（*）替换成%2A、%7E替换回波浪号(~)，即可得到上述规则描述的编码字符串。
     * @param s 待编码内容
     * @return 编码后的内容
     * @throws UnsupportedEncodingException 不支持的编码转换异常
     */
     public static String percentEncode(String s) throws UnsupportedEncodingException {
         return s == null?null: URLEncoder.encode(s, "utf-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
     }
}
