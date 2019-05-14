# 南瓜电影 下线媒资同步 API

编写者: 宋立君/韩张钞 

联系方式: `hanzhangchao@vcinema.cn` / `15556927463`

合作方可以通过该接口同步下线媒资清单。该接口为增量接口，获取前12小时内的下线媒资清单。

## 获取内容列表 API

#### API 地址及校验信息: 

```
测试地址：http://dev.api.guoing.com:3505/media/offline_sync
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
  3  | timestamp | long   | 当前时间戳
  4  | signature_nonce  | string | 随机数
  5  | format    | string |  返回类型 标准参数：JSON
  6  | sign | string | sign 签名，加密方式请查看DEMO


### 返回信息:

##### 成功

`HTTP Status Code` : `200`

##### 响应示例
```
{
    message: 获取成功
    content: {
        "mediaList":[{
            "movie_name":"测试w",
            "movie_id":"11332",
            "delete_time":"2019-05-09 19:18:33",
            }],
        "total_movie_number":1,
        "data_generate_timestamp":"1555673163000",
        "data_generate_time":"2019-04-19 19:26:03"
        }
    timestamp: 1557816111972
    date: 2019-05-14 14:41:51
}
```

参数说明:

##### 内容信息:

序号  | 字段名称 |   类型   | 备注
---- | ------- | ------ | -----
  1  |  total_movie_number    | int  | 电影总数
  2  | data_generate_time | string | 内容生成时间
  3  | data_generate_timestamp |  String   | 内容生成时间戳
  4  | mediaList  |  list | 内容列表

根据内容生成时间,电影总数进行同步内容数据

##### 下线媒资信息（media）:

序号  | 字段名称 |   类型   | 备注
---- | ------- | ------ | -----
  1  |  movie_id | int  | 电影id
  2  | movie_name |   string   | 电影名称
  3  | delete_time | string | 下线时间

      
  ##### 异常示例
  ```
  {
     status_code: 17007
     message: pid错误或无此接口调用权限
     content: {}
     timestamp: 1557492848602
     date: 2019-05-10 20:54:08
   }
  ```
  
 ##### 业务错误码
 序号  | 错误码 |   错误描述   | 解决方案
 ---- | ------- | ------ | -----
   1  | 17007 | 参数错误  | 检查请求参数，修改后重新发起请求
   2  | 17006 | sign 不正确 | 传入的 sign 错误，请修正

  接口详情查看 [TestMovie.java](https://github.com/pumpkin-movie/pumpkin_partner_api_demo/blob/master/src/test/java/cn/vcinema/partner/TestMovie.java) 文件中`getOfflineMediaSuccessful`方法
