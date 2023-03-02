```
mongo 查询语句
> show dbs; #显示所有的数据库
> use dmp_app_event; #切换数据库，如果没有，则创建之
> show collections;
> db.events.findOne();
> db.events.find().sort({"TIMESTAMP_COLLECTED_BY_SERVER":-1})
> db.fx_ads_platform_http_abc_efg_vvv.find().sort({"TIMESTAMP_COLLECTED_BY_SERVER":-1})
> db.fx_ads_platform_http_abc_efg_vvv.find().sort({"TIMESTAMP_COLLECTED_BY_SERVER":-1}).limit(3)
> use sdk_api_log;
```