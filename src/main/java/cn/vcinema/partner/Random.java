/*
 * Copyright (c) 2017-present, Pumpkin Movie, Inc. All rights reserved.
 *
 * You are hereby granted a non-exclusive, worldwide, royalty-free license to use,
 * copy, modify, and distribute this software in source code or binary form for use
 * in connection with the web services and APIs provided by Pumpkin Movie.
 *
 * As with any software that integrates with the pumpkin movie platform, your use of
 * this software is subject to the Pumpkin Movie Developer Principles and Policies
 * [http://developers.vcinema.com/policy/]. This copyright notice shall be
 * included in all copies or substantial portions of the software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package cn.vcinema.partner;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 随机数生成
 *
 * User: Xulin Zhuang
 * Date: 29/1/2018
 * Time: 9:42 PM
 *
 */
public class Random {

    /**
     * 密码类型枚举
     */
    public enum TYPE {
        /**
         * 字符型
         */
        LETTER,
        /**
         * 大写字符型
         */
        CAPITAL,
        /**
         * 数字型
         */
        NUMBER,
        /**
         * 符号型
         */
        SIGN,
        /**
         * 大+小字符 型
         */
        LETTER_CAPITAL,
        /**
         * 小字符+数字 型
         */
        LETTER_NUMBER,
        /**
         * 大+小字符+数字 型
         */
        LETTER_CAPITAL_NUMBER,
        /**
         * 大+小字符+数字+符号 型
         */
        LETTER_CAPITAL_NUMBER_SIGN
    }

    private static String[] lowercase = {
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
            "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

    private static String[] capital = {
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
            "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    private static String[] number = {
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};

    private static String[] sign = {
            "~", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "_", "+", "`", "-", "=",
            "{", "}", "|", ":", "\"", "<", ">", "?",
            "[", "]", "\\", ";", "'", ",", ".", "/"};

    /**
     * 静态随机数
     */
    private static java.util.Random random = new java.util.Random();

    /**
     * 获取随机组合码
     *
     * @param num  位数
     * @param type 类型
     * <br>字符型 LETTER,
     * <br>大写字符型 CAPITAL,
     * <br>数字型 NUMBER,
     * <br>符号型 SIGN,
     * <br>大+小字符 型 LETTER_CAPITAL,
     * <br>小字符+数字 型 LETTER_NUMBER,
     * <br>大+小字符+数字 型 LETTER_CAPITAL_NUMBER,
     * <br>大+小字符+数字+符号 型 LETTER_CAPITAL_NUMBER_SIGN
     * @return 随机数
     */
    public static String getRandom(int num, TYPE type) {
        ArrayList<String> temp = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        if (type == TYPE.LETTER) {
            temp.addAll(Arrays.asList(lowercase));
        } else if (type == TYPE.CAPITAL) {
            temp.addAll(Arrays.asList(capital));
        } else if (type == TYPE.NUMBER) {
            temp.addAll(Arrays.asList(number));
        } else if (type == TYPE.SIGN) {
            temp.addAll(Arrays.asList(sign));
        } else if (type == TYPE.LETTER_CAPITAL) {
            temp.addAll(Arrays.asList(lowercase));
            temp.addAll(Arrays.asList(capital));
        } else if (type == TYPE.LETTER_NUMBER) {
            temp.addAll(Arrays.asList(lowercase));
            temp.addAll(Arrays.asList(number));
        } else if (type == TYPE.LETTER_CAPITAL_NUMBER) {
            temp.addAll(Arrays.asList(lowercase));
            temp.addAll(Arrays.asList(capital));
            temp.addAll(Arrays.asList(number));
        } else if (type == TYPE.LETTER_CAPITAL_NUMBER_SIGN) {
            temp.addAll(Arrays.asList(lowercase));
            temp.addAll(Arrays.asList(capital));
            temp.addAll(Arrays.asList(number));
            temp.addAll(Arrays.asList(sign));
        }
        for (int i = 0; i < num; i++) {
            sb.append(temp.get(random.nextInt(temp.size())));
        }
        return sb.toString();
    }
}
