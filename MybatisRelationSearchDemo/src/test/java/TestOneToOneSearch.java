import com.realationSearch.demo.dao.PersonMapper;
import com.realationSearch.demo.model.Person;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.util.List;

public class TestOneToOneSearch {
    private SqlSession getSession() throws Exception{
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis.xml"));
        return factory.openSession();
    }

    @Test
    public void testSelectManyTime() throws Exception{
        SqlSession session = this.getSession();
        PersonMapper mapper = session.getMapper(PersonMapper.class);
        List<Person> list = mapper.selectPersons();
        for(Person p : list)
            System.out.println(p);
        session.commit();
    }

    @Test
    public void testSelectOneTime() throws Exception{
        SqlSession session = this.getSession();
        PersonMapper mapper = session.getMapper(PersonMapper.class);
        List<Person> list = mapper.selectPersonsOneTime();
        for(Person p : list)
            System.out.println(p);
        session.commit();
    }
}
