# 南瓜电影 新版直充 API

编写者: 杨露/孙博 

联系方式: `yanglu@vcinema.cn` 

### 接口介绍
新版直充接口操作文档

### 接口地址
	测试环境 ：https://dev-environmental.vcinema.cn:3505/pay/new_pay_order
	正式环境 ：https://partner-api.vcinema.cn/pay/new_pay_order

#### 请联系后获取详细内容
	合作方id（pid）
	加密秘钥（access_secret）
	第三方标识（shop）
	产品码（product_code）

### 接口请求方式
POST

### 接口描述

#### 接口请求参数
**注意：使用query传参时，value需进行encode，编码格式为UTF-8。**

|参数名|必选|类型|说明|
|:----    |:---|:----- |-----   |
|signature_nonce |是  |string |接口随机数  |
|version |是  |string |接口版本，默认v1   |
|format |是  |string | 返回值类型，默认JSON|
|timestamp     |是  |string | 当前时间戳，单位：毫秒|
|sign |是  |string |sign签名，加密方式HMacSha1,加密流程参考加密说明   |
|pid |是  |string | 合作方id，联系相关工作人员获取|
|product_code     |是  |string | 商品编号，联系相关工作人员获取    |
|account |是  |string |充值账号/手机号|
|amount |是  |integer | 充值数量,目前只支持充值数量为1    |
|shop     |是  |string | 第三方标识，联系相关工作人员获取    |
|order_number |是  |string |第三方商户订单号|
|origin_price |否  |string |第三方商户提供的原价    |
|settle_price     |否  |string | 结算单价|


###加密说明
#### 加密参数
|参数名|必选|类型|说明|
|:----    |:---|:----- |-----   |
|signature_nonce |是  |string |接口随机数  |
|version |是  |string |接口版本，默认v1   |
|format |是  |string | 返回值类型。默认JSON    |
|timestamp     |是  |string | 当前时间戳 单位：毫秒    |
|pid |是  |string | 合作方id，联系相关工作人员获取    |
|product_code     |是  |string |  商品编号，联系相关工作人员获取    |
|account |是  |string |充值账号/手机号|
|amount |是  |integer | 充值数量,目前只支持充值数量为1    |
|shop     |是  |string | 第三方标识    |
|order_number |是  |string |第三方订单号   |
|action |是  |string |接口路径 示例：/pay/new_pay_order   |
|method |是  |string |接口请求方式，示例：POST   |



####加密流程

    所有加密参数及值都需要URLEncoding,编码格式：UTF-8，其中method不参与排序，直接放在需要进行加密的字符串首位。
    排序规则：字典升序
    //升序后字符串：
    sortStr =  "account=value&action=value&amount=value&format=value&order_number=value&pid=value &product_id=value& shop=value&signature_nonce=value&timestamp=value & version = value"
    //拼接后字符串(也就是准备进行加密的字符串)
    appenedStr =  percentEncode(method) + "&" +  percentEncode("/") + "&" + percentEncode(sortStr);
    //其中percentEncode方式如下：
    String percentEncode(String s) throws UnsupportedEncodingException {
          return s == null ? null : URLEncoder.encode(s, "utf-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
    }

**注意：其中accessSecret作为秘钥进行加密。可联系相关工作人员获取。具体加密流程如下**
##### 加密方式：HmacSHA1
    // accessSecret：秘钥
    SecretKey key = new SecretKeySpec((accessSecret +"&").getBytes("utf-8"), "http://www.w3.org/2000/09/xmldsig#hmacsha1");
    Mac mac = Mac.getInstance("HmacSHA1");
    mac.init(key);
    //对拼接后字符串进行加密
    byte[] hmacValue = mac.doFinal(appenedStr.getBytes("utf-8"));
    //获取签名
    String sign = BaseEncoding.base64().encode(hmacValue);




###响应参数说明

|参数名|类型|说明|
|:----    |:---|----- |
|message     |String |响应信息 |
|content     |Object | 操作结果，充值成功返回pumpkin_order_num(南瓜电影订单号)，否则为空字符 |
|status_code |String |响应状态码 |
|timestamp   |String | 当前时间戳 |
|date        |String | 当前日期 |

#### 状态码说明

|状态码|说明|
|:--------|---   |
|10999|充值失败|
|200|成功|
|17001|pid不能为空|
|17002|没有此商户pid商户|
|17003|查不到该IP|
|17004|该IP禁止访问|
|17005|商户状态失效|
|17006|shop不能为空|
|17007|产品码（product_code）为空|
|17008|format不能为空|
|17009|timestamp不能为空|
|17010|phone输入错误|
|17011|sign 不能为空|
|17012|amount参数目前只支持1|
|17013|订单号重复|
|17014|领取失败 您已参与过该活动|
|17015|秘钥有误|
|17017|sign不正确|
|17018|合作信息有误或该合作已失效，请检查pid,productCode,shop关系及合作状态|
|17019|pid错误或没有此接口调用权限|
|17020|合作未开始|
|17021|合作已到期|
|17022|该合作用户类型有误|
|17023|合作次数已达上限|
|17024|不可重复领取|


### 示例
#### 请求示例:
```
https://dev-environmental.vcinema.cn:3505/pay/new_pay_order?signature_nonce=2021111****2820889&version=v1&format=JSON&timestamp=163696***8343&pid=MC******LB&product_code=ng-***-m-nm01&account=1***********&amount=1&shop=lan_pay&order_number=2021****16402820889&sign=xepaPW3Cgpirgs0EpLjOqq%2F2etY%3D
```
#### 返回示例:

##### 成功

```
{
"message": "充值成功",
"content": {
"pumpkin_order_num": "2021081719481073187015******"
},
"status_code": 200,
"timestamp": "1629200891743",
"date": "2021-08-17 19:48:11"
}
```

##### 失败

```
{
"message": "错误信息",
"content": "",
"status_code": 错误状态码,
"timestamp": "1629194384687",
"date": "2021-08-17 17:59:44"
}
```


#### Sign生成规则,以及测试 Demo

加密方式: `HmacSHA1`

详情查看 [TestNewPay.java](https://github.com/pumpkin-movie/pumpkin_partner_api_demo/blob/master/src/test/java/cn/vcinema/partner/TestNewPay.java) 文件中`pay`方法
