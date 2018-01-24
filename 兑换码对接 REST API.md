# 兑换码对接 REST API

编写者: 宋立君 

联系方式: `songlijun@vcinema.cn` / `17611590700`

## 生成兑换码API

#### API 地址: 

测试地址: ``

正式地址: ``

#### 请求方式: `POST`

请求类型: `application/x-www-form-urlencoded`

#### 传入参数:

`必填` `string` `pid`: 合作方 PID

`必填` `string` `code_type` : 兑换码类型

`必填` `string` `timestamp` : 当前时间戳

`必填` `string` `accessSecret`: sign 签名

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

#### `status_code` 状态码

| 状态码  | 信息  |  
| :------------ |:---------------:| 
| 200      | 生成成功 |
| 17006      | sign不正确        |

#### Sign生成规则

`key`: `sing-key` (邮件告知)

加密方式: `HmacSHA1`

详情查看 [PartnersApiSignature.java](https://github.com/pumpkin-movie/pumpkin_partner_api_demo/blob/master/PartnersApiSignature.java) 文件


