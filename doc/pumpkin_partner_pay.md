# 南瓜电影 直充 API

编写者: 宋立君/王永 

联系方式: `wangyong@vcinema.cn` / `13466536112`


## 直充 API

#### API 地址及校验信息: 

```
测试地址：http://dev.api.guoing.com:3505/pay/pay_order
测试PID：TEST_PID
测试PID access secret：TEST_ACCESS_SECRET
测试PRODUCT ID: TEST_PUMPKIN_PRODUCT_ID
```

请联系后获取详细内容

```
正式接口地址：
PID：
PID access secret:
PRODUCT ID: 
```


#### 请求方式: `POST`

#### 传入参数:

序号  | 字段名称 |   类型   | 备注
---- | ------- | ------ | -----
  1  |  pid    | string  | 合作方ID
  2  | version | string | 接口版本，默认v1
  3  | timestamp | long   | 时间戳
  4  | signature_nonce  | string | 随机数
  5  | format    | string |  返回类型 标准参数：JSON
  6  | sign | string | sign 签名，加密方式请查看DEMO
  7  | product_id | string | 商品编码 
  8  | account | string | 充值账号(手机号)
  9  | amount | integer | 充值数量
  10  | shop | string | 第三方标识
  11  | order_number | string | 第三方订单号




#### 返回信息:

`HTTP Status Code`: `200`

##### 成功

```
{
  "date": "2018-02-22 19:06:38",
  "status_code": 200,
  "message": "充值成功",
  "content": "",
  "timestamp": "1519297598084"
}
```

##### 失败

```
{
  "date": "2018-02-22 19:06:38",
  "status_code": 17003,
  "message": "订单号重复",
  "content": "",
  "timestamp": "1519297598084"
}
```

#### 返回状态码定义

| 状态码  | 信息  |  
| :------------ |:---------------:| 
| 200      | 充值成功 | 
| 17003      | 订单号重复|
| 17005      | 该IP禁止访问        |
| 17006      | sign不正确        |
| 17007     | 参数错误        |
| 17008      | 商品不存在或无法购买        |


## 查询订单 API

```
测试地址：http://dev.api.guoing.com:3505/pay/get_order
测试PID：TEST_PID
测试PID access secret：TEST_ACCESS_SECRET
```

请联系后获取详细内容

```
正式接口地址：
PID：
PID access secret:
PRODUCT ID: 
```

#### 请求方式: `POST`

#### 传入参数:

序号  | 字段名称 |   类型   | 备注
---- | ------- | ------ | -----
  1  |  pid    | string  | 合作方ID
  2  | version | string | 接口版本，默认v1
  3  | timestamp | long   | 时间戳
  4  | signature_nonce  | string | 随机数
  5  | format    | string |  返回类型 标准参数：JSON
  6  | sign | string | sign 签名，加密方式请查看DEMO
  7  | shop | string | 第三方标识
  8  | order_number | string | 第三方订单号

#### 返回信息:

`HTTP Status Code`: `200`

##### 成功

```
{
  "message": "充值成功",
  "content": {
    "pay_date_time": "2018-03-02 18:03:05.0",
    "amount": "1",
    "account": "18502083318"
  },
  "status_code": 200,
  "timestamp": "1520218926861",
  "date": "2018-03-05 11:02:06"
}
```

##### 失败

```
{
  "message": "订单号不存在",
  "content": {

  },
  "status_code": 17003,
  "timestamp": "1520218926861",
  "date": "2018-03-05 11:02:06"
}
```

#### 返回状态码定义

| 状态码  | 信息  |  
| :------------ |:---------------:| 
| 200      | 充值成功 | 
| 17003      | 订单号不存在 |
| 17005      | 该IP禁止访问        |
| 17006      | sign不正确        |
| 17007     | 参数错误        |



#### Sign生成规则,以及测试 demo

加密方式: `HmacSHA1`

详情查看 [TestPay.java](https://github.com/pumpkin-movie/pumpkin_partner_api_demo/blob/master/src/test/java/cn/vcinema/partner/TestPay.java) 文件中`payOrderSuccessful`方法
