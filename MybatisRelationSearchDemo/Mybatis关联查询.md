## Mybatis关联查询demo

### Mybatis中，关联查询分为一次查询和N+1次查询。

一次查询：
 
- 是使用多表联合查询sql语法实现，例如“select * from person left join person_card on person.id = person_card.personId.

多次查询：

 - 是使用多条查询语句实现。例如上面说的一次查询的sql，使用多次查询的话，它的执行结果是：首先select * from person，然后遍历返回的结果，再次查询，select * from person_card where person_card.personId = id。可以看出这样的查询效率肯定是很低的，所以生产环境不能使用

### 一对一关联查询

首先讲下两个数据库表的结构

person表：

|id|name|sex|age
|--:|--:|--:|--:

person_card表：

|card_id|person_id|
|-------|---------|


首先我们编写entity代码时，需要在原有的属性基础上增加一个属性，用于保存关联数据。代码如下：

~~~
public class Person {

    private Integer id;
    private String name;
    private String sex;
    private Integer age;
    //关系属性，一个人对应一张卡
    private PersonCard personCard;
	......以下代码省略
}

public class PersonCard {

    private Integer cardId;
    private Integer personId;
    // 关系属性，一张卡对应一个人
    private Person person;
	......以下代码省略
}
~~~

--------------------------------------------------------多次查询---------------------------------------------------------------------------------------

mapper.xml文件：

~~~

  <!-- 多次访问数据库，查询所有Person -->
  <resultMap id="PersonMap1" type="com.realationSearch.demo.model.Person">
    <id column="id" jdbcType="INTEGER" property="id" />
    <result column="name" jdbcType="VARCHAR" property="name" />
    <result column="sex" jdbcType="VARCHAR" property="sex" />
    <result column="age" jdbcType="INTEGER" property="age" />
    <!-- 关系数据使用association描述。代表关系数据是一个应用对象
         property - 是当前类型中关系对象的属性名称
         javaType - 关系对象的具体类型
         select - 当前关联对象数据使用什么sql语句查询数据。
         column - 执行查询关联数据对象sql时，传递的参数。如果传递一个参数可以直接定义当前查询的字段名。
                  如果传递的是多个参数，使用map的String格式定义如：{参数名称=字段名称， personId=id}
         -->
    <association property="personCard" javaType="com.realationSearch.demo.model.PersonCard"
                 select="com.realationSearch.demo.dao.PersonMapper.selectPersonCardByPerson"
                 column="{personId=id}"/>
  </resultMap>
  <resultMap id="PersonCardMap1" type="com.realationSearch.demo.model.PersonCard">
    <id column="card_id" jdbcType="INTEGER" property="cardId" />
    <result column="person_id" jdbcType="INTEGER" property="personId" />
  </resultMap>

  <select id="selectPersons" resultMap="PersonMap1">
    select id, `name`, sex, age from person
  </select>
  <!-- 根据外键查询卡号 -->
  <select id="selectPersonCardByPerson" resultMap="PersonCardMap1">
    select card_id, person_id from person_card where person_id = #{personId}
  </select>

~~~

----------------------------------------------一次查询-----------------------------------------------------------------------------------------

mapper.xml文件：

~~~
  <!-- 一次访问数据库，查询所有Person -->
  <resultMap id="PersonMap2" type="com.realationSearch.demo.model.Person">
  <id column="id" jdbcType="INTEGER" property="id" />
  <result column="name" jdbcType="VARCHAR" property="name" />
  <result column="sex" jdbcType="VARCHAR" property="sex" />
  <result column="age" jdbcType="INTEGER" property="age" />
  <association property="personCard" javaType="com.realationSearch.demo.model.PersonCard">
    <id column="card_id" property="card_id"></id>
    <result column="person_id" property="person_id"/>
  </association>
  </resultMap>
  <select id="selectPersonsOneTime" resultMap="PersonMap2">
    select
        id, `name`, sex, age, card_id as cardId, person_id as personId
    from
        person left join  person_card
        on
        person.id = person_card.person_id
  </select>
~~~

### 一对多和多对多查询

创建student和class表

student表结构如下：

|id|name|sex|age|class_id
|--:|--:|--:|--:|--:

class表结构如下:

|id|name
|--:|--:

具体去查看class和student代码
