### 配置文件加载顺序
```java
加载优先级从高到低：
        > application-default.properties 
        > application-default.yml 
        > application.properties
        > application.yml
```

> 优先加载操作系统层面的配置、命令行
> 由jar包外向jar包内进行寻找，优先查找config目录。
> 优先加载带profile（application-{profile}.yml）的，后加载不带profile（application.yml）的
> 高优先级的配置覆盖低优先级的配置，所有的配置会形成互补配置

```java
nacos:默认远程配置优先级最高
```