
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
##### 用户信息
序号  | 字段名称 |   类型   | 备注
---- | ------- | ------ | -----
  1  | phone    | string  | 手机号
  2  | user_status | boolean | 用户状态（用户是否注册）
  3  | register_time | string | 注册时间
  4  | orders  | list | 订单产品信息

##### 订单信息 order
序号  | 字段名称 |   类型   | 备注
---- | ------- | ------ | -----
  1  | order_code    | string  | 订单code码
  2  | order_price | int | 订单价格(单位分)
  3  | order_pay_time | string | 订单支付成功时间


#### 返回信息:

`HTTP Status Code`: `200`

##### 成功

```
{
    "message": "成功",
    "content": {
        "users": [
            {
                "phone": "134xxxxxxxx",
                "user_status": true,
                "register_time": "2020-10-27 12:00:12",
                "orders": [
                    {
                        "order_code": "a",
                        "order_price": 800,
                        "order_success_time": "2020-10-27 12:00:12"
                    },
                    {
                        "order_code": "a",
                        "order_price": 800,
                        "order_success_time": "2020-10-27 12:00:12"
                    }
                ]
            },
            {
                "phone": "139xxxxxxxx",
                "user_status": fasle,
                "register_time": null,
                "orders": []
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


