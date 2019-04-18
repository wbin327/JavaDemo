## Spring Aop Demo

### AOP常见术语


Aspect(切面)：把增强用到切入点的过程，这个过程称之为切面。也就是代理的过程

advice(增强/通知)：增强的概念就是指在原方法的基础上增加新的功能，与装饰器的概念相同，比如增加一个日志的功能。通知分为

- 前置通知：在原方法执行前

- 后置通知：在原方法执行后

- 异常通知：原方法出现异常时

- 最终通知：在finally语句块中执行

- 环绕通知：在原方法执行前后

Pointcut(切入点）：指我们要对哪些方法进行增强的声明

Target(目标对象）:代理的目标对象

Weaving(织入):把增强(advice)应用到目标对象(target)的过程

### Aspectj切入点语法

execution语法：execution(<函数修饰符>?<函数返回类型><函数名称>(<参数>)<异常>?) ，问号表示可选参数

定义切入点表达式execution(* com.sample.service.impl..*.*(..))

execution()是最常用的切入点函数，语法如下：

- 1、execution(): 表达式主体。

- 2、第一个*号：表示返回类型，*号表示所有的类型。

- 3、包名：表示需要拦截的包名，后面的两个句点表示当前包和当前包的所有子包，com.sample.service.impl包、子孙包下所有类的方法。

- 4、第二个*号：表示类名，*号表示所有的类。

- 5、*(..):最后这个星号表示方法名，*号表示所有的方法，后面括弧里面表示方法的参数，两个句点表示任何参数。

常见例子：

- execution(public * *(..))

匹配所有目标类的public方法，但不匹配SmartSeller和protected voidshowGoods()方法。第一个*代表返回类型，第二个*代表方法名，而..代表任意入参的方法；

-  execution(* *To(..))

匹配目标类所有以To为后缀的方法。它匹配NaiveWaiter和NaughtyWaiter的greetTo()和serveTo()方法。第一个*代表返回类型，而*To代表任意以To为后缀的方法；

- execution(*com.baobaotao.Waiter.*(..))

匹配Waiter接口的所有方法。第一个*代表返回任意类型，com.baobaotao.Waiter.*代表Waiter接口中的所有方法；

- execution(*com.baobaotao.Waiter+.*(..))

匹配Waiter接口及其所有实现类的方法。

- execution(* com.baobaotao.*(..))

匹配com.baobaotao包下所有类的所有方法，“.*”表示包下的所有类，而“..*”表示包、子孙包下的所有类。

- execution(* com..*.*Dao.find*(..))

匹配包名前缀为com的任何包下类名后缀为Dao的方法，方法名必须以find为前缀。

### 基于注解实现spring aop流程

1.为代理对象添加@Component注解，交由spring管理对象，控制反转

2.为代理对象添加@Aspect注解，启用spring aop

3.在代理对象的具体方法前添加通知类型，通知类型包括以下五种

- @Before，前置通知

- @AfterReturning,后置通知

- @Around，环绕通知

- @AfterThrowing，异常通知

- @After,finally通知

4.配置切面