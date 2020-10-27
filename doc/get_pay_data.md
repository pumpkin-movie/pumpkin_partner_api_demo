
## get pay data API

## 根据手机号查询订单 API

#### API 地址及校验信息: 
(请联系后获取详细内容)

```
测试地址：
```

```
正式接口地址：
```

#### 请求方式: `POST`

#### 传入参数:

序号  | 字段名称 |   类型   | 备注
---- | ------- | ------ | -----
  1  |  phone    | string  | 手机号（可传多个，半角逗号隔开）
  2  | timestamp | long   | 时间戳
  3  | signature_nonce  | string | 随机数
  4  | format    | string |  返回类型 标准参数：JSON
  5  | sign | string | sign 签名，加密方式请查看DEMO

#### 返回参数:

序号  | 字段名称 |   类型   | 备注
---- | ------- | ------ | -----
  1  | phone    | string  | 手机号
  2  | user_status | boolean | 用户状态（是否存在订单）
  3  | price | string   | 价格（分）
  4  | products  | list | 订单产品信息



#### 返回信息:

`HTTP Status Code`: `200`

##### 成功

```
{
    "message": "成功",
    "content": {
        "order_infos": [
            {
                "phone": "134xxxxxxxx",
                "user_status": true,
                "products": [
                    {
                        "product_code": "a",
                        "product_price": 800,
                        "pay_success_time": "2020-10-27 12:00:12"
                    },
                    {
                        "product_code": "a",
                        "product_price": 800,
                        "pay_success_time": "2020-10-27 12:00:12"
                    }
                ]
            },
            {
                "phone": "139xxxxxxxx",
                "user_status": fasle,
                "products": [
                    
                ]
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
  "message": "该IP禁止访问",
  "content": {

  },
  "status_code": 17005,
  "timestamp": "1520218926861",
  "date": "2018-03-05 11:02:06"
}
```

#### 返回状态码定义

| 状态码  | 信息  |  
| :------------ |:---------------:| 
| 200      | 查询成功 | 
| 17005      | 该IP禁止访问        |
| 17006      | sign不正确        |
| 17007     | 参数错误        |


