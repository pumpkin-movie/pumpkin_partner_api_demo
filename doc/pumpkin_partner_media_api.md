# 南瓜电影 媒资同步 API

编写者: 宋立君/韩张钞 

联系方式: `hanzhangchao@vcinema.cn` / `15556927463`

可以通过该接口同步媒资信息，接口有全量和增量两种模式可选。其中增量接口为获取前半小时内新增更新的媒资。

## 获取内容列表 API

#### API 地址及校验信息: 

```
测试地址：http://dev.api.guoing.com:3505/media/sync
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
  7  | mode | string | 选择接口模式, "full"为全量接口, "batch"为增量接口


### 返回信息:

##### 成功

`HTTP Status Code` : `200`

##### 响应示例
```
{
  "message": "获取成功",
  "content": {
    "total_movie_number": 533,
    "data_generate_time": "2018-01-29 15:01:30",
    "data_generate_timestamp": "1517276352692",
    "movies": [
        {
            "movie_country":"英国",
            "movie_name":"不可遗忘",
            "episode_num":2,
            "movie_id":"10371",
            "movie_director":"Andy Wilson",
            "movie_title":"法网恢恢 疏而不漏",
            "movie_desc":"是以调查陈年旧案为主题的英国罪案剧。",
            "movie_actor":"Tim Faraday,Matthew Wilson,Nicola Walker,Sanjeev Bhaskar,Aasiya Shah,Lorraine Ashbourne,Will Brown,Jodie Tyack,Rosie Cavaliero,Louiza Patikas,Carolina Main,Bryony Hannah,Peter Egan,Douglas Hodge,Alex Lowe,Jonathan Harden,Tom Courtenay,Gemma Jones,Dominic Power,Adam Astill",
            "media_seasons":[
                {
                    "movie_country":"英国",
                    "total_parts":7,
                    "movie_name":"不可遗忘第2季",
                    "movie_id":"10372",
                    "movie_director":"Andy Wilson",
                    "movie_title":"陈年死尸调查出惊天秘密",
                    "movie_desc":"伦敦的利河里打捞起一个旅行箱，里面蜷成一团的尸骨已有近三十年。从遗留的手表和Call机开始追查，几个拥有各自成功人生的角色浮出水面...",
                    "movie_actor":"Tim Faraday,atthew Wilson,Nicola Walker,Sanjeev Bhaskar,Aasiya Shah,Lorraine Ashbourne,Will Brown,Jodie Tyack,Rosie Cavaliero,Louiza Patikas,Carolina Main,Bryony Hannah,Peter Egan,Douglas Hodge,Alex Lowe",
                    "movie_name_english":"Unforgotten Season 2",
                    "movie_language":"英语",
                    "season_parts":[
                        {"movie_number":"1","movie_id":"10373"},
                        {"movie_number":"2","movie_id":"10374"},
                        {"movie_number":"3","movie_id":"10375"},
                        {"movie_number":"4","movie_id":"10376"},
                        {"movie_number":"5","movie_id":"10377"},
                        {"movie_number":"6","movie_id":"10378"},
                        {"movie_number":"7","movie_id":"10769"}],
                    "movie_year":"2017",
                    "movie_alias":"未遗忘的事 第二季",
                    "movie_type":"3"
                    }],
            "movie_name_english":"Unforgotten",
            "movie_language":"英语",
            "movie_image_list":[
                "http://movie.image.vcinema.com.cn/rU1xCnhV4bBnT4XmuGAwKwnQ.png",
                "http://movie.image.vcinema.com.cn/VVtPefbhBDW6NKAwfUN6RKky.png"],
            "movie_year":"2015",
            "movie_alias":"未遗忘的事",
            "movie_type":"3"
        }]
    },
    "status_code": 200,
    "timestamp": "1517453525849",
    "date": "2018-02-01 10:52:05"
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

##### 媒资信息（media）:

序号  | 字段名称 |   类型   | 备注
---- | ------- | ------ | -----
  1  |  movie_id | int  | 电影id
  2  | movie_title | string | 电影标题
  3  | movie_name |   string   | 电影名称
  4  | movie_alias  |   string | 电影别名
  5  | movie_desc  |   string | 电影描述
  6  | movie_director  |   string | 电影导演
  7  | movie_actor  |   string | 电影主演
  8  | movie_country  |   string | 电影国家
  9  | movie_language  |   string | 电影语言
  10  | movie_year  |   string | 电影年份
  11 | movie_type  |   string | 电影类型，1: 电影 3：季播剧
  12  | episode_num  |   string | 集数/季数（movie_type为3是季数）
  13  | vertical_pic  |   string | 竖版海报
  14  | horizontal_pic  |   string | 横版海报
  15  | media_seasons | list | 如果其是季播剧，则包含该剧的全部季集合
  16 | video | list | 如果其是电影，则包含视频

  ##### 季信息（season）:

序号  | 字段名称 |   类型   | 备注
---- | ------- | ------ | -----
  1  |  movie_id | int  | 电影id
  2  | movie_title | string | 电影标题
  3  | movie_name |   string   | 电影名称
  4  | movie_alias  |   string | 电影别名
  5  | movie_desc  |  string | 电影描述
  6  | movie_director  |  string | 电影导演
  7  | movie_actor  |  string | 电影主演
  8  | movie_country  |  string | 电影国家
  9  | movie_language  |  string | 电影语言
  10  | movie_year  |  string | 电影年份
  11 | movie_type  |  string | 电影类型1: 电影 3：季播剧
  12  | total_parts  |   int | 当季的总集数
  13  | season_parts | list | 当季全集

  ##### 集信息（part）:

序号  | 字段名称 |   类型   | 备注
---- | ------- | ------ | -----
  1  | movie_id | int  | 电影id
  2  | movie_number | string | 当前的集数
  
  注：剧 - 季 - 集三者同名字段相互独立，值不同
  
  ##### 视频信息（video）:
  序号  | 字段名称 |   类型   | 备注
  ---- | ------- | ------   | -----
   1  | video_id | int  | 视频id，以"v_"为前缀，之后的值与媒资的movie_id相同
   2  | video_number | string | 当前的集数
   3  | video_name | string | 名称
      
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

  全量接口详情查看 [TestMovie.java](https://github.com/pumpkin-movie/pumpkin_partner_api_demo/blob/master/src/test/java/cn/vcinema/partner/TestMovie.java) 文件中`getMediaInfoSuccessful`方法

  增量接口详情查看 [TestMovie.java](https://github.com/pumpkin-movie/pumpkin_partner_api_demo/blob/master/src/test/java/cn/vcinema/partner/TestMovie.java) 文件中`getMediaInfoBatchSuccessful`方法