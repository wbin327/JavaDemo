## Maven下使用MyBatista Generator生成文件

### 1.修改pom文件，添加plugin,详见pom.xml文件

### 2.在src/main/resorces下创建generatorConfig.xml文件，用于配置MyBatis Generator，详见generatorConfig.xml文件，有中文解释。

### 3.使用Maven命令mvn mybatis-generator:generate执行程序

IDEA如何执行：点击Run-->edit Configurations打开配置窗口，点击左上角"+"号找到Maven，指定项目的路径以及Maven命令（mybatis-generator:generate）。配置完成后即可点击三角号运行

### 4.使用junit和mybatis测试一下生成的接口能否使用，

测试过程中遇到一个异常：mybatis错误——java.io.IOException: Could not find resource com/xxx/xxxMapper.xml

解决方法可以参考以下两篇文章（这个问题的原因有两个，一个是idea不会编译src的java目录的xml文件，另一个是多级目录的问题，路径应该使用com/demo/mapper/UsersMapper.xml，不应使用com.demo.mapper.UsersMapper.xml）

文章链接：

https://blog.csdn.net/u010648555/article/details/70880425

https://bbs.csdn.net/topics/391037150

### 5.使用MybatistaGenerator自动生成代码的优缺点

优点：
 
 - 方便，Sql规范，快捷，维护成本低

缺点：

 - 只能单表操作，不支持多表链接查询
 - 如果表格中有Text,Blob字段，查询和更新的时候，需要注意，默认的查询、更新方法不查询和更新Text、Blob字段，必须调用selectByExampleWithBlobs或updateByExampleWithBlobs方法