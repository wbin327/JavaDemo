## SpringTransactionDemo

### Demo说明

1.SpringBoot整合mybatis

2.启用spring事务

### 什么是事务属性

![](/1.jpg)

### Spring事务隔离级别

TransactionDefinition.ISOLATION_DEFAULT: 使用后端数据库默认的隔离级别，Mysql 默认采用的 REPEATABLE_READ隔离级别 Oracle 默认采用的 READ_COMMITTED隔离级别.

TransactionDefinition.ISOLATION_READ_UNCOMMITTED: 最低的隔离级别，允许读取尚未提交的数据变更，可能会导致脏读、幻读或不可重复读

TransactionDefinition.ISOLATION_READ_COMMITTED: 允许读取并发事务已经提交的数据，可以阻止脏读，但是幻读或不可重复读仍有可能发生

TransactionDefinition.ISOLATION_REPEATABLE_READ: 对同一字段的多次读取结果都是一致的，除非数据是被本身事务自己所修改，可以阻止脏读和不可重复读，但幻读仍有可能发生。

TransactionDefinition.ISOLATION_SERIALIZABLE: 最高的隔离级别，完全服从ACID的隔离级别。所有的事务依次逐个执行，这样事务之间就完全不可能产生干扰，也就是说，该级别可以防止脏读、不可重复读以及幻读。但是这将严重影响程序的性能。通常情况下也不会用到该级别。

### Spring事务传播行为（为了解决业务层方法之间互相调用的事务问题）

假设现在Service中有两个方法A和B，A调用B。如果方法A开启了事务，那么B是否也要在事务中运行呢？这个问题就是事务的传播属性的问题，Spring中有七种不同的事务传播属性

支持当前事务的情况：

- PROPAGATION_REQUIRED： 
  - spring默认的事务传播，方式如果当前存在事务，沿用当前事务，不存在事务，开启一个事务
  - 怎么理解上面这句话？“当前”是指被调用的函数B，并且B开启了事务，还设置事务传播属性为REQUIRED
- PROPAGATION_SUPPORTS： 
  - 以当前事务方式运行，当前没事务，不开启新的事务
- PROPAGATION_MANDATORY： 
  - 强制要有事务，以当前事务方式运行，当前不存在事务，抛出异常

不支持当前事务的情况：

- PROPAGATION_REQUIRES_NEW： 
  - 总是开启一个新的事务，当前存在事务，将当前事务挂起
  - 怎么理解上面这句话？A和B都开启了事务，这两个事务相互独立，互不影响，挂起的意思是指执行完B事务后，在继续执行A事务
- PROPAGATION_NOT_SUPPORTED： 
  - 以非事务方式运行，如存在事务，将当前事务挂起
  - 怎么理解上面这句话？A开启，B开启，A事务生效，B事务失效，挂起的意思不是指不执行A事务，而是执行完B后再执行A事务。
- PROPAGATION_NEVER： 
  - 以非事务方式运行，如果当前存在事务，则抛出异常

其他情况：

- PROPAGATION_NESTED： 
- 嵌套事务，如果当前存在事务，则在嵌套事务中执行，如果没事务，则与REQUIRED相同

### 事务回滚

事务只有遇到运行期异常（即RuntimeException及其子类）时才会回滚，而在遇到检查型异常时不会回滚。

事务不回滚常见的原因：

- 声明式事务配置切入点表达式写错了，没切中Service中的方法
- Service方法中，把异常给try catch了，但catch里面只是打印了异常信息，没有手动抛出RuntimeException异常
- Service方法中，抛出的异常不属于运行时异常（如IO异常），因为Spring默认情况下是捕获到运行时异常就回滚

### 事务是否只读

事务的只读属性是指，对事务性资源进行只读操作或者是读写操作。所谓事务性资源就是指那些被事务管理的资源，比如数据源、 JMS 资源，以及自定义的事务性资源等等。如果确定只对事务性资源进行只读操作，那么我们可以将事务标志为只读的，以提高事务处理的性能。在 TransactionDefinition 中以 boolean 类型来表示该事务是否只读。

### 事务超时(一个事务允许执行的最长时间)

所谓事务超时，就是指一个事务所允许执行的最长时间，如果超过该时间限制但事务还没有完成，则自动回滚事务。在 TransactionDefinition 中以 int 的值来表示超时时间，其单位是秒。

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