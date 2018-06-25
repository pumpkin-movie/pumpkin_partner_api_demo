# 兑换码对接 REST API

编写者: 宋立君 

## 生成兑换码API

#### API 地址: 

测试地址: `http://dev.api.guoing.com:3505/pay/redeem_code`
=======
#### API 地址及校验信息: 
```
测试地址：http://dev.api.guoing.com:3505
测试PID：TEST_PID
测试PID access secret：TEST_ACCESS_SECRET
```

请联系后获取详细内容
```
正式接口地址：
PID：
PID access secret:
```

#### 请求方式: `POST`

请求类型: `application/x-www-form-urlencoded`

#### Form 传入参数:

序号  | 字段名称 |   类型   | 备注
---- | ------- | ------ | -----
  1  |  pid    | string  | 合作方ID
  2  | code_type | string | 兑换码类型
  3  | timestamp | long   | 时间戳
  4  | signature_nonce  | string | 随机数
  5  | format    | string |  返回类型 标准参数：JSON
  6  | sign | string | sign 签名，加密方式请查看DEMO
  7  | version | string | 接口版本，默认v1
```
兑换码会员类型: 
m1=1个月 
m3=3个月 
m6=6个月
m12=12个月
当前仅支持按月
```

#### 返回信息:

##### 成功

`HTTP Status Code`: `200`

```
{
  "message": "生成成功",
  "content": {
    "code": "T7VWBKSDIP3AE57",
    "code_type": 1
  },
  "status_code": 200,
  "timestamp": "1516772509420",
  "date": "2018-01-24 13:41:49"
}
```


参数说明:

`code`: 兑换码

`code_type`: 会员类型 

##### 失败:

sign错误:

```
{
  "message": "sign不正确",
  "content": {
  },
  "status_code": 17006,
  "timestamp": "1516772509420",
  "date": "2018-01-24 13:41:49"
}
```

生成失败:

```
{
  "message": "生成失败,请稍后重试.",
  "content": {
  },
  "status_code": 17999,
  "timestamp": "1516772509420",
  "date": "2018-01-24 13:41:49"
}
```

#### `status_code` 状态码

| 状态码  | 信息  |  
| :------------ |:---------------:| 
| 200      | 生成成功 |
| 17005      | 兑换码类型不正确       |
| 17006      | sign不正确        |
| 17999      | 生成失败,请稍后重试.       |

#### Sign生成规则

`key`: `sing-key` (邮件告知)

加密方式: `HmacSHA1`

详情查看 [PartnersApiSignature.java](https://github.com/pumpkin-movie/pumpkin_partner_api_demo/blob/master/src/main/java/cn/vcinema/partner/PartnersApiSignature.java) 文件


