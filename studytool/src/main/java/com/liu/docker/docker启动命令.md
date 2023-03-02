### 参数说明

> -d：后台运行
>
> -e：设置环境变量，如账号密码
>
> --name：设置容器别名
>
> --restart=always：自动重启

### MySQL

``` shell
sudo docker run \
	-d \
	--restart=always \
	--privileged=true  \
	--name mysql8 \
	-p 3306:3306 \
	-v /Users/xinliu/docker/mysql/conf/:/etc/mysql/conf.d/ \
	-v /Users/xinliu/docker/mysql/data/:/var/log/mysql/ \
	-v /Users/xinliu/docker/mysql/data/:/var/lib/mysql/ \
	-e MYSQL_ROOT_PASSWORD=123456 \
	-v /etc/localtime:/etc/localtime:ro \
	-v /etc/timezone:/etc/timezone:ro \
	mysql:8.0
```
#### `Redis`

```shell
docker run \
	-p 6379:6379 \
	--name=redis \
	-d --restart=always \
	-v /mydata/redis/data:/data \
	-v /mydata/redis/conf/redis.conf:/etc/redis/redis.conf \
	-v /etc/localtime:/etc/localtime:ro \
	-v /etc/timezone:/etc/timezone:ro \
	redis
```

### Tomcat

```shell
sudo docker run -d \
	--restart=always \
	--privileged=true \
 	-p 8085:8080 \
 	--name tomcat9 \
 	-v /Users/xinliu/docker/tomcat/webapps/:/usr/local/tomcat/webapps/ \
 	-v /Users/xinliu/docker/tomcat/logs/:/usr/local/tomcat/logs/ \
 	tomcat:9
```
### Minio
```shell
docker run 
	-p 9000:9000 \
	-p 9001:9001 \
	--name minio \
	-d --restart=always \
  	-e "MINIO_ACCESS_KEY=root" \
  	-e "MINIO_SECRET_KEY=minio@123" \
  	-v /mydata/minio/data:/data \
	-v /mydata/minio/config:/root/.minio \
	-v /etc/localtime:/etc/localtime:ro \
	-v /etc/timezone:/etc/timezone:ro \
  	minio/minio:RELEASE.2021-08-05T22-01-19Z server /data \
	--console-address ":9001"
	
注：
	1. minio/minio:RELEASE.2021-08-05T22-01-19Z server /data 是minio的启动命令

（minio/minio 是镜像名字、 /data:数据存储位置）
	2. --console-address ":9001" 是指定控制台的端口映射，并且和minio server 是不同的
	3minio 控制台和minio server 需要不同的端口：所以要有两个端口，一个是供调用的，一个是可视化的控制台
```
