# 南瓜电影 新版直充 API

编写者: 杨露/孙博 

联系方式: `yanglu@vcinema.cn` / `13598866342`


## 新版直充 API

#### API 地址: 

```
测试环境地址：https://dev-environmental.vcinema.cn:3505/pay/new_pay_order
正式环境地质：https://partner-api.vcinema.cn/pay/new_pay_order
```

请联系后获取详细内容
```
pid：
pid access secret:
product_code: 
shop:
```

#### 请求方式: `POST`

#### 传入参数说明:

序号  | 字段名称 |   类型   | 备注
---- | ------- | ------ | -----
  1  |  pid    | string  | 合作方ID
  2  | version | string | 接口版本，默认v1
  3  | timestamp | long   | 时间戳
  4  | signature_nonce  | string | 随机数
  5  | format    | string |  返回类型 标准参数：JSON
  6  | sign | string | sign 签名，加密方式请查看DEMO
  7  | product_code | string | 商品编码 
  8  | account | string | 充值账号(手机号)
  9  | amount | integer | 充值数量
  10  | shop | string | 第三方标识（默认standard）
  11  | order_number | string | 第三方订单号
  12  | original_price | string | cp提供的原价(单位分)
  13  | settle_price | string | 结算单价(单位分)


#### 返回参数说明
|参数名|必选|类型|说明|
|:----    |:---|:----- |-----   |
|code  |是  |string |响应状态码   |
|content |否  |Object | 操作结果    |
|error     |否  |string | 错误信息    |
|msg     |是  |string | 操作信息    |

#### 返回示例:


##### 成功

```
{
  "timestamp": "2021-05-26T09:02:51.366Z",
  "status": 200,
  "error": ”",
  "message": "成功",
  "path": "/pay/new_pay_order"
}
```

##### 失败

```
{
  "timestamp": "2021-05-26T09:02:51.366Z",
  "status": "错误码",
  "error": "",
  "message": "提示语",
  "path": "/pay/new_pay_order"
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


#### Sign生成规则,以及测试 demo

加密方式: `HmacSHA1`

详情查看 [TestNewPay.java](https://github.com/pumpkin-movie/pumpkin_partner_api_demo/blob/master/src/test/java/cn/vcinema/partner/TestNewPay.java) 文件中`pay`方法
