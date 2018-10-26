# SpringX-learning
learning...
----
# SprinngBoot
### [1.SpringBoot简介](https://github.com/Letitmiss/SpringX-learning/blob/master/blog/01.springboot1.md)
### [2.SpringBoot项目配置](https://github.com/Letitmiss/SpringX-learning/blob/master/blog/01.springboot2.md)
### [3.Controller的配置](https://github.com/Letitmiss/SpringX-learning/blob/master/blog/01.springboot3.md)

https://docs.servicecomb.io/java-chassis/zh_CN/edge/by-servicecomb-sdk.html



ak：T7TD91OULQFEFSB7RU6I sk ： 792fdRxayIiMwdkDWCJI979PiWKYn4MOq5ssQ06M 


# SprinngCloud 

HAHA

1. 加入eureka的客户端,配置cloud , eureka-client
2. 加入@enableEurekaClient
3. 配置服务的名称和 eureka.server-url.defaultZone=http://localhost:8671/eureka
4. ribbon引入 , 在 RestTemplate 方法 @LoadBalanced, 调用的时候: uri:http://{服务名}/{uri}
5. 启动访问的

注册中心高可用集群
互相注册
1. eureka1 注册中心
eureka.client.server-url.dofaultZone=http://{eureka2}:8672/eureka

2. 提供者需要两个注册地址
用,号隔开

自我保护 
服务端配置:
eureka.server.enable-self-preservation=false #禁用自我保护机制
客户端配置:
隔几秒
eureka.instance.lease-renewal-interval-in-seconds=2
多少秒之后没故障,就提出我这个服务
eureka.instance.lease-expiration-duration-in-seconds=10
