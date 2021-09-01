# 南瓜电影 新版直充 API

编写者: 杨露/孙博 

联系方式: `yanglu@vcinema.cn` 


## 新版直充 API

#### API 地址: 

```
测试环境地址：https://dev-environmental.vcinema.cn:3505/pay/new_pay_order
正式环境地址：https://partner-api.vcinema.cn/pay/new_pay_order
```

请联系后获取详细内容
```
pid：
PrimarySecret:
product_code: 
shop:
```

#### 请求方式: `POST`

#### 传入参数说明:

序号  | 字段名称         |   类型  | 是否必选| 备注|
---- | -------         | ------ | ----- | -----
  1  |  pid            | String |是| 合作方PID
  2  | version         | String |是| 接口版本，默认v1
  3  | timestamp       | long   |是| 时间戳 单位：毫秒
  4  | signature_nonce | String |是| 接口随机数
  5  | format          | String |是| 返回数据类型 标准参数：JSON
  6  | sign            | String |是| sign 签名，加密方式请查看DEMO
  7  | product_code    | String |是| 商品编码 
  8  | account         | String |是| 充值账号(手机号)
  9  | amount          | Integer|是| 充值数量，目前只支持充值数量为1
  10  | shop           | String |是| 第三方标识
  11  | order_number   | String |是| 第三方订单号
  12  | original_price | String |否| cp提供的原价(单位分)
  13  | settle_price   | String |否| 结算单价(单位分)


#### 返回参数说明
|参数名|类型|说明|
|:----    |:----- |-----   |
|message     |String |响应信息 |
|content     |Object | 操作结果，充值成功返回pumpkin_order_num(南瓜电影订单号)，否则为空字符 |
|status_code |String |响应状态码 |
|timestamp   |String | 当前时间戳 |
|date        |String | 当前日期 |

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

#### 返回状态码定义

| 状态码  | 信息  |  
| :------------ |:---------------:| 
|10999|充值失败|
|200|成功|
|17001|pid不能为空|
|17002|没有此商户pid商户|
|17003|查不到该IP|
|17004|该IP禁止访问|
|17005|商户状态失效|
|17006|shop不能为空|
|17007|产品码（product_code）不能为空|
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


#### Sign生成规则,以及测试 demo

加密方式: `HmacSHA1`

详情查看 [TestNewPay.java](https://github.com/pumpkin-movie/pumpkin_partner_api_demo/blob/master/src/test/java/cn/vcinema/partner/TestNewPay.java) 文件中`pay`方法
