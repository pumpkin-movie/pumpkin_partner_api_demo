# 南瓜电影 CDN Log API

编写者: 宋立君 

联系方式: `songlijun@vcinema.cn` / `17611590700`


## 获取内容列表 API

#### API 地址及校验信息: 

```
测试地址：http://dev.api.guoing.com:3505/cdn/log
测试PID：TEST_PID
测试PID access secret：TEST_ACCESS_SECRET
```

请联系后获取详细内容

```
正式接口地址：
PID：
PID access secret:
```


#### 请求方式: `GET`

#### 传入参数:

序号  | 字段名称 |   类型   | 备注
---- | ------- | ------ | -----
  1  |  pid    | string  | 合作方ID
  2  | version | string | 接口版本，默认v1
  3  | timestamp | long   | 时间戳
  4  | signature_nonce  | string | 随机数
  5  | format    | string |  返回类型 标准参数：JSON
  6  | access_secret | string | sign 签名，加密方式请查看DEMO
  7  | log_date | string | 日志所属日期 如: 20180222



#### 返回信息:

##### 成功

`HTTP Status Code`: `200`

```
{
  "date": "2018-02-22 19:06:38",
  "status_code": 200,
  "message": "CDN LOG 获取成功",
  "content": "http://pumpkin-log.oss-cn-beijing.aliyuncs.com/cdn_log/s1/20180201__b5b8bdd710fc463889e1559ebc9e1dcf?Expires=1519301198&OSSAccessKeyId=LTAIkJ14NWOp9qjn&Signature=V0DxQi8IEbdmN6Dh16%2FYYXI7%2FLQ%3D",
  "timestamp": "1519297598084"
}
```

参数说明:

##### 内容信息:

序号  | 字段名称 |   类型   | 备注
---- | ------- | ------ | -----
  1  |  content | string  | 日志内容下载链接
  2  | data_time | string | 内容生成时间
  3  | data_timestamp |  long   | 内容生成时间戳

#### Sign生成规则

加密方式: `HmacSHA1`

详情查看 [TestCdnDemo](https://github.com/pumpkin-movie/pumpkin_partner_api_demo/blob/master/src/test/java/cn/vcinema/partner/TestCdn.java) 文件
