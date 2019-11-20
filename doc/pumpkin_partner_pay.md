# 南瓜电影 直充 API

编写者: 宋立君/王永 

联系方式: `wangyong@vcinema.cn` / `13466536112`


## 直充 API

#### API 地址及校验信息: 

```
测试地址：http://dev.api.guoing.com:3505/pay/pay_order
测试pid：TEST_PID
测试pid access secret：TEST_ACCESS_SECRET
测试product_id: TEST_PUMPKIN_PRODUCT_ID
测试shop：standard
```

请联系后获取详细内容

```
正式接口地址：
pid：
pid access secret:
product_id: 
shop:
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
  10  | shop | string | 第三方标识（默认standard）
  11  | order_number | string | 第三方订单号
  12  | original_price | string | cp提供的原价
  13  | settle_price | string | 结算单价




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
测试pid：TEST_PID
测试pid access secret：TEST_ACCESS_SECRET
```

请联系后获取详细内容

```
正式接口地址：
pid：
pid access secret:
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


## 按日期查询订单 API

```
测试地址：http://dev.api.guoing.com:3505/pay/get_order_by_date
测试pid：TEST_PID
测试pid access secret：TEST_ACCESS_SECRET
```

请联系后获取详细内容

```
正式接口地址：
pid：
pid access secret:
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
  8  | query_date | string | 查询日期（定长8位，格式为"yyyyMMdd")

#### 返回参数:

序号  | 字段名称 |   类型   | 备注
---- | ------- | ------ | -----
  1  | product_id    | string  | 产品id
  2  | product_name | string | 产品名称
  3  | price | string   | 价格（分）
  4  | amount  | string | 购买数量
  5  | order_number    | string |  订单号
  6  | account | string | 账号
  7  | order_date | string |订购日期
  8  | created_time | string | 订购时间


#### 样例:
##### 成功

```
{
    "message": "成功",
    "content": {
        "order_infos": [
            {
                "product_id": "TEST_PUMPKIN_PRODUCT_ID",
                "product_name": "测试",
                "price": "0",
                "amount": "1",
                "order_number": "8ec2ba8abf5d4e6f95e72050580bcd89",
                "account": "18392117474",
                "order_date": "20191115",
                "created_time": "2019-11-15 09:38:16"
            },
            {
                "product_id": "TEST_PUMPKIN_PRODUCT_ID",
                "product_name": "测试",
                "price": "0",
                "amount": "1",
                "order_number": "d59f332cd97648bca541ab3eaeb5e4e1",
                "account": "13484672424",
                "order_date": "20191115",
                "created_time": "2019-11-15 17:37:03"
            }
        ]
    },
    "status_code": 200,
    "timestamp": "1574232834823",
    "date": "2019-11-20 14:53:54"
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
