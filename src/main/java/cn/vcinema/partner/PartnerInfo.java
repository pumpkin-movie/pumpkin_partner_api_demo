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

/**
 * Partner Info
 *
 * User: Xulin Zhuang
 * Date: 29/1/2018
 * Time: 12:55 PM
 */
public class PartnerInfo {
    final static String format = "JSON";
    final static String pay_action = "/pay/redeem_code";
    final static String pay_statistics_action = "/pay/redeem_code_statistics";
    final static String movie_action = "/movie/sync";
    final static String pay_order_action = "/pay/pay_order";
    final static String pay_order_operator_action = "/pay/pay_order_operator";
    final static String send_insufficient_balance_msg_action = "/send/insufficientBalance";
    final static String get_order_action = "/pay/get_order";
    final static String httpPostMethod = "POST";
    final static String httpGetMethod = "GET";
    final static String pid = "TEST_PID";
    final static String codeType = "m1";
    final static String productType = "m1";
    final static String accessSecret = "TEST_ACCESS_SECRET";
    final static String version = "v1";
}
