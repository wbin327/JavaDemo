## SpringTransactionDemo

### Demo说明

1.SpringBoot整合mybatis

2.启用spring事务

### SpringBoot整合Mybatis

1.maven依赖项配置，具体请查看pom文件

2.配置数据源datasource和mybatis，具体请查看resources文件夹下的application.yml文件

### Spring事务（注解方式）

demo模拟转账流程，转账流程的所有操作视为一个事务，启用事务使用@Transactional注解

### Spring事务（XML配置方式）

1.配置事务管理器

~~~

<bean id="transactionManager" class="org.springfarmework.jdbc.datasource.DataSourceTran
sactionManager">

    <!-- 注入dataSource -->
    <property name="dataSource" ref="dataSource"></property>

</bean>

~~~

2.配置事务增强(代理）

~~~
<tx:advice id="txadvice" transaction-manager="transactionManager">

<!-- 匹配符合相应规则的方法,下例中匹配所有以account开头的方法 -->
<tx:method name="account*" propagation="REQUIRED">

</tx:advice>
~~~

3.配置切面

~~~
<aop:config>
    <!-- 切入点 -->
    <aop:pointcut expression="execution(* com.transaction.demo.service.AccountService.transferAccounts(..))" id="pointcut1">
    <!-- 切面 -->
    <aop:advisor advice-ref="txadvice" pointcut-ref="pointcut1"/>
</aop:config>
~~~