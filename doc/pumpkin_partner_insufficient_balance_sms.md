# 南瓜电影 余额不足提醒 API

编写者: 王永 

联系方式: `wangyong@vcinema.cn` / `13466536112`


## 余额不足提醒API

#### API 地址及校验信息: 

```
测试地址：http://dev.api.guoing.com:3505/send/insufficientBalance
测试PID：TEST_PID
测试PID access secret：TEST_ACCESS_SECRET
```

正式环境请联系后获取详细内容

```
正式接口地址：
PID：
PID access secret:
```


#### 请求方式: `POST`

#### 传入参数:

序号  | 字段名称 |   类型   | 备注
---- | ------- | ------ | -----
  1  |  pid    | string  | 合作方ID
  2  | version | string | 接口版本，默认v1
  3  | timeStamp | long   | 时间戳
  4  | signatureNonce  | string | 随机数
  5  | format    | string |  返回类型 标准参数：JSON
  6  | sign | string | sign 签名，加密方式请查看DEMO
  7  | account | string | 充值账号(手机号)
  8  | shop | string | 第三方标识




#### 返回信息:

`HTTP Status Code`: 0

##### 成功

```
{
  "resultCode ": 0,
  "message": "发送余额不足提醒短信完成",
  "phone": "13445678900"
}

```

##### 失败

```
{
  "resultCode ": 1,
  "message": "pid不能为空",
  "phone": "13445678900"
}

```

#### 返回状态码定义

| 状态码  | 信息  |  
| :------------ |:---------------:| 
| 0      | 发送提醒短信成功 | 
| 1      | 发送提醒短信失败|



#### Sign生成规则,以及测试 demo

加密方式: `HmacSHA1`

详情查看 [TestPay.java](https://github.com/pumpkin-movie/pumpkin_partner_api_demo/blob/master/src/test/java/cn/vcinema/partner/TestPay.java) 文件中`sendInsufficientBalanceSMSSuccessful`方法
