# 南瓜电影 第三方支付回调 API

编写者: 王永 

联系方式: `wangyong@vcinema.cn` / `13466536112`


## 第三方支付回调 API

#### API 地址及校验信息: 

请联系后获取详细内容

```
测试地址：http://dev.doras.guoing.com:80/pay/third/callback
```

```
正式接口地址：
pid：
PID access secret:
```


#### 请求方式: `POST`

#### 传入参数:

序号  | 字段名称 |   类型   | 备注  | 是否可空
---- | ------- | ------ | ----- | -----
  1  | tpid    | string  | 合作方ID | N
  2  | orderNumber | string | 南瓜电影订单号 | N
  3  | thirdOrderNumber | string | 第三方订单号 | N
  4  | signatureNonce  | string | 随机数 | N
  5  | format    | string |  返回类型 标准参数：JSON | N
  6  | timestamp | long   | 时间戳 | N
  7  | orderStatus | string | 订单支付状态：2-支付成功，3-支付失败  | N
  8  | productPrice  | string | 产品价格，单位分 | N
  9  | payType  | string | 支付类型（接口方分配） | N
  10  | notifyType  | string | 通知类型（接口方分配） | N
  11  | sign | string | sign 签名，加密方式请查看DEMO | N
  


#### 返回信息:

`HTTP Status Code`: `200`

##### 成功

```
{
  "date": "2018-02-22 19:06:38",
  "status_code": 200,
  "message": "回调成功",
  "content": "",
  "timestamp": "1519297598084"
}
```

##### 失败

```
{
  "date": "2018-02-22 19:06:38",
  "status_code": 17007,
  "message": "参数错误",
  "content": "",
  "timestamp": "1519297598084"
}
```

#### 返回状态码定义

| 状态码  | 信息  |  
| :------------ |:---------------:| 
| 200      | 同步成功 | 
| 17000      | 内部错误|
| 17005      | 该IP禁止访问        |
| 17006      | sign不正确        |
| 17007     | 参数错误        |



#### Sign生成规则,以及测试 demo
加密方式: HmacSHA1

详情查看 [TestPay.java](https://github.com/pumpkin-movie/pumpkin_partner_api_demo/blob/master/src/test/java/cn/vcinema/partner/TestThirdPayNotify.java)文件中`testThirdPayNotify`方法

