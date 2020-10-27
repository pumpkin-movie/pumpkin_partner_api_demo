
# get pay data

## get pay data API

### 根据手机号，查询订单详情

#### API 地址及校验信息: 

```
测试地址：联系相关人员获取
```

## 根据手机号查询订单 API

```
测试地址：
```

```
正式接口地址：
```
请联系后获取详细内容

#### 请求方式: `POST`

#### 传入参数:

序号  | 字段名称 |   类型   | 备注
---- | ------- | ------ | -----
  1  |  phone    | string  | 手机号（可传多个，半角逗号隔开）
  2  | timestamp | long   | 时间戳
  3  | signature_nonce  | string | 随机数
  4  | format    | string |  返回类型 标准参数：JSON
  5  | sign | string | sign 签名，加密方式请查看DEMO

#### 返回信息:

`HTTP Status Code`: `200`

##### 成功

```
{
    "message": "成功",
    "content": {
        "order_infos": [
            {
                "phone": "12345678910",
                "products": [
                    {
                        "product_code": "a",
                        "product_price": 800,
                        "pay_success_time": "2020-10-27 12:00:12"
                    },
                    {
                        "product_code": "a",
                        "product_price": 800,
                        "pay_time": "2020-10-27 12:00:12"
                    }
                ]
            },
            {
                "phone": "12345678911",
                "products": [
                    {
                        "product_code": "a",
                        "product_price": 800,
                        "pay_success_time": "2020-10-27 12:00:12"
                    },
                    {
                        "product_code": "a",
                        "product_price": 800,
                        "pay_time": "2020-10-27 12:00:12"
                    }
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
  "message": "手机号不存在",
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
| 200      | 查询成功 | 
| 17003      | 手机号不存在 |
| 17005      | 该IP禁止访问        |
| 17006      | sign不正确        |
| 17007     | 参数错误        |


