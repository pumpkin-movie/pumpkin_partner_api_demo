# 南瓜电影 CDN Log API

编写者: 王永 

联系方式: `wangyong@vcinema.cn`


## CDN LOG API

### 获取获取CDN LOG下载地址 API

#### API 地址及校验信息: 

```
测试地址：http://dev.api.guoing.com:3505/cdn/log
测试PID：TEST_PID
测试PID access secret：TEST_ACCESS_SECRET
```

### 获取CDN buffer statistics下载地址

```
测试地址：http://dev.api.guoing.com:3505/cdn/buffer_statistics
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
  6  | sign | string | sign 签名，加密方式请查看DEMO
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
  
##### log 信息:

```
terminal_category STRING,   -- TV,IPHONE,IPAD,APH
  device_info STRING,     -- 设备唯一码
  device_type STRING,    -- 设备类型
  device_id STRING,     --设备唯一码
  os_version STRING,   --设备操作系统
  user_id STRING,   --用户ID
  app_version STRING,   --应用版本
  remote_addr STRING,   --用户IP
  network_environment STRING,   --网络类型
  cdn_ip STRING,     --CDN IP
  isp_name STRING,   --ISP Name
  movie_url STRING,   --影片地址
  p2p_status STRING,   --P2P状态
  decode_type STRING,   -- 解码模式
  operate_type STRING,   --操作类型，
  is_play_status STRING,  --播放状态
  buffer_start_time STRING,  -- 卡顿开始时间，只有操作类型是卡顿的时候才有值
  buffer_end_time STRING,   --卡顿结束时间，只有操作类型是卡顿的时候才有值
  start_position STRING,    --影片开始帧
  end_position STRING,      --影片结束帧
  play_total_time STRING,   --单次播放总时长
  log_record_timestamp STRING,   --日志记录时间的timestamp
  log_record_time STRING   --日志记录时间
```

卡顿数据是选取操作类型是BUFFER，并且卡顿时长大于1s的记录。播放总数是选取操作类型为START-PLAY的播放条数



#### Sign生成规则

加密方式: `HmacSHA1`

详情查看 [TestCdnDemo](https://github.com/pumpkin-movie/pumpkin_partner_api_demo/blob/master/src/test/java/cn/vcinema/partner/TestCdn.java) 文件
