## Dubbo Spring Boot Demo

### 搭建之前必须的理解dubbo的流程图

![](dubbo.png)

- Provider 暴露服务的服务提供方
- Consumer 调用远程服务的服务消费方
- Registry 服务注册与发现的注册中心
- Monitor 统计服务的调用次数和调用时间的监控中心
- Container 服务运行容器
- register 注册
- subscribe 订阅
- notify 通知
- invoke 引用

### GitHub原文地址：https://github.com/apache/incubator-dubbo-spring-boot-project/blob/master/README_CN.md

根据原文来，并不能成功搭建，所以我这里做了一些修改，并搭建成功

### 1.编写provider项目（DubboSpringBootDemo），提供给系统内部使用的接口

- 1.配置pom文件,详情查看pom文件

- 2.编写provider的接口（提供给系统内部调用的接口）代码，详见com.dubbo.demo.service

- 这里需要注意的点，@Service注解使用的是org.apache.dubbo.config.annotation.Service。
而不是spring的Service注解，这两者是不一样的，如果使用了spring的Service注解，会导致项目启动时dubbo提示找不到相应的服务

- 3.编写项目启动入口，DubboSpringBootDemoApplication.java

- 这里需要注意的地方有使用@EnableDubbo注解，而不是@SpringBootApplication

- 4.编写配置文件，详见resources/application.properties


### 2.编写Consumer(消费者,DubboConsumerSpringBootDemo)项目，提供对用户的接口

这里需要注意的点有：

- idea无法编译项目，需要去file -> setting -> Build,Execution,Deployment -> compiler -> java compiler，修改target bytecode version与jdk保持一致

- 使用@Reference注入provider的service类时，例如Provider中接口名称为com.dubbo.demo.service.ProviderService。使用@Reference注解时，必须保证Comsumer中该接口名称与Provider中一致。因为注册中心中已注册的服务是以包名+接口名的形式来命名（如:com.dubbo.demo.service.ProviderService)，如果不一致会导致消费者订阅不到相应的接口。详情参考本项目com文件夹

- 编写项目启动文件时，使用的是@SpringBootApplication注解，表示启动的是一个spring web项目，提供对用户的接口

- 其他流程与编写Provider项目相似

### 3.安装zookeeper

我这里使用的是kitematic(docker的一款可视化管理工具）安装的zookeeper镜像。镜像启动后，修改上面两个项目的注册中心配置dubbo.registry.address=zookeeper://127.0.0.1:32770,上面只是个例子，具体请根据实际情况进行配置。

## Dubbo Admin,Dubbo控制台

github地址：https://github.com/apache/incubator-dubbo-admin/blob/develop/README_ZH.md

使用maven下载该项目依赖项时巨慢(已经使用了国内的阿里云镜像加速），没找到好的解决方法

## Dubbo官方文档

http://dubbo.apache.org/zh-cn/docs/user/references/xml/dubbo-service.html