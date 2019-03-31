import com.demo.dao.UsersMapper;
import com.demo.model.Users;
import com.demo.model.UsersExample;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.util.List;

public class TestMyBatis {

    @Test
    public void testUpdate() throws Exception{
        SqlSession session = this.getSession();

        UsersMapper mapper = session.getMapper(UsersMapper.class);

        Users users = new Users();
        users.setId(2);
        users.setName("2update");
        users.setPassword("123456");
        // 主键更新全部字段
        mapper.updateByPrimaryKey(users);
        // 主键更新非默认值数据字段
        mapper.updateByPrimaryKeySelective(users);

        // 模拟Hibernate中的QBE定义的条件。Example类型，每个实体对应一个Example.每个Example中有一个CCriteria内部类
        // Criteria内部类用于定于具体条件。约束是只能做and多条件。所有的条件定义方式为：Criteria.and字段名条件
        // 如：criteria.andIdEqualTo;criteria.andNameLike;criteria.andIdBetween等
        UsersExample example = new UsersExample();
        UsersExample.Criteria c =example.createCriteria();
        c.andIdEqualTo(2);
        // 根据example条件更新全部字段
        mapper.updateByExample(users, example);
        // 根据example条件更新非默认值数据字段
        mapper.updateByExampleSelective(users, example);

        session.commit();

    }

    @Test
    public void testInsert() throws  Exception{
        SqlSession session = this.getSession();
        UsersMapper mapper = session.getMapper(UsersMapper.class);

        Users users = new Users();
        users.setId(2);
        users.setName("2");
        users.setPassword("123456");
        mapper.insert(users);

        session.commit();
    }

    @Test
    public void testSelect() throws Exception{
        SqlSession session = this.getSession();
        UsersMapper mapper = session.getMapper(UsersMapper.class);
        // 主键查询
        mapper.selectByPrimaryKey(1);

        UsersExample example = new UsersExample();
        // 查询结果根据id降序
        example.setOrderByClause("id desc");

        // 根据example条件查询多数据
        List<Users> list = mapper.selectByExample(example);
        session.commit();
        System.out.println(list);
        session.close();
    }

    private SqlSession getSession() throws Exception{
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis.xml"));
        return factory.openSession();
    }
}
