# 南瓜电影 订单同步 API

编写者: 王永 

联系方式: `wangyong@vcinema.cn` / `13466536112`


## 订单同步 API

#### API 地址及校验信息: 

请联系后获取详细内容

```
测试地址：
测试pid：
测试PID access secret：
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
  1  | pid    | string  | 合作方ID | N
  2  | order_no | string | 第三方订单号 | N
  3  | product_id | string | 商品编码  | Y
  4  | product_price | integer | 产品价格，单位分  | N
  5  | timestamp | long   | 时间戳 | N
  6  | signature_nonce  | string | 随机数 | N
  7  | format    | string |  返回类型 标准参数：JSON | N
  8  | sign | string | sign 签名，加密方式请查看DEMO | N
  9  | pay_type | string | 支付方式：ALIPAY,WXPAY  | Y
  10  | order_status | integer | 订单状态：1:处理中 2：处理成功 | Y
  11  | pay_success_time | string | 支付成功时间，格式：yyyy-MM-dd HH:mm:ss  | N
  12  | start_time | string | 订单开始时间，格式：yyyy-MM-dd HH:mm:ss  | Y
  13  | end_time | string | 订单结束时间，格式：yyyy-MM-dd HH:mm:ss  | Y
  14  | channel | string | 渠道号 | Y
  15  | deveice_id | string | 设备编号  | Y
  16  | user_id | string | 用户编号 | Y
  17  | platform | string | 平台号 | Y
  18  | app_version | string | 接口版本，默认v1 | Y
  19  | ip | string | ip地址 | Y
  
  
  
  
 




#### 返回信息:

`HTTP Status Code`: `200`

##### 成功

```
{
  "date": "2018-02-22 19:06:38",
  "status_code": 200,
  "message": "同步成功",
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
| 200      | 同步成功 | 
| 17003      | 订单号重复|
| 17005      | 该IP禁止访问        |
| 17006      | sign不正确        |
| 17007     | 参数错误        |



#### Sign生成规则,以及测试 demo
加密方式: HmacSHA1

详情查看 demo
