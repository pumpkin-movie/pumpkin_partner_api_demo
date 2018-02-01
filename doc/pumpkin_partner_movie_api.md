# 南瓜电影 电影内容对接 API

编写者: 宋立君 

联系方式: `songlijun@vcinema.cn` / `17611590700`


## 获取内容列表 API

#### API 地址及校验信息: 

```
测试地址：http://dev.api.guoing.com:3505/movie/sync
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


#### 返回信息:

##### 成功

`HTTP Status Code`: `200`

```
{
  "message": "获取成功",
  "content": {
    "total_movie_number": 382,
    "data_time": "2018-01-29 15:01:30",
    "data_timestamp": "1517276352692",
    "movies": [
      {
        "movie_id": "10003",
        "movie_title": "两个女人一台戏   自我救赎的一百种方式",
        "movie_name": "赦免",
        "movie_name_english": "Absolution",
        "movie_alias": "Henkesi edestä ä",
        "movie_desc": "女主在医院看望出车祸的朋友，又发现朋友的隐情。故事将如何走向……",
        "movie_director": "Petri Kotwica",
        "movie_actor": "Jukka Hurme,Minna Hämäläinen,Stefan Karlsson,Elise Kouki,Mika Kujala,Jussi Kylmäniemi,Mirja Oksanen,Tom Petäjä",
        "movie_country": "芬兰",
        "movie_language": "芬兰语",
        "movie_year": "2015",
        "movie_type": "1",
        "movie_poster": null,
        "movie_duration": null,
        "episode_num": 1,
        "movie_image_list": [
          "http://movie.image.vcinema.com.cn/qqfGyZpljhz6rn5BIHHAGGri.png",
          "http://movie.image.vcinema.com.cn/svVQ3Jmc9Ti3luJRHzkQFycy.png"
        ]
      },
      {
        "movie_id": "10004",
        "movie_title": "遗失的宝藏  藏在每个人身上的宝藏",
        "movie_name": "失去同情",
        "movie_name_english": "Lost Solace",
        "movie_alias": "",
        "movie_desc": "Spence Cutler 是一个心理变态，没有感情和罪恶感，偶然的机会他服用了一种新药，让他重新认识现实，恢复良知。",
        "movie_director": "Chris Scheuerman",
        "movie_actor": "Andrew Jenkins,Melissa Roxburgh,Leah Gibson,Charlie Kerr,Michael Kopsa,Johannah Newmarch,Carmen Moore,Brittney Wilson,Jed Rees,Krista Magnusson",
        "movie_country": "加拿大",
        "movie_language": "英语",
        "movie_year": "2016",
        "movie_type": "1",
        "movie_poster": null,
        "movie_duration": null,
        "episode_num": 1,
        "movie_image_list": [
          "http://movie.image.vcinema.com.cn/am4yGORo0hs9Je0t5nswpgYq.png",
          "http://movie.image.vcinema.com.cn/8NkRpIbpd3thfwwMVyeEcuGE.png"
        ]
      }
      ]
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
  2  | data_time | string | 内容生成时间
  3  | data_timestamp |  long   | 内容生成时间戳
  4  | movies  |  list | 内容列表

根据内容生成时间,电影总数进行同步内容数据

##### 电影信息:

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
  11 | movie_type  |   string | 电影类型1: 电影 2: 电视剧
  12  | movie_duration  |   string | 电影市场
  13  | episode_num  |   string | 电影集数
  14  | movie_image_list  |   list | 电影海报 [0]: 竖版海报 [1]: 横版海报
