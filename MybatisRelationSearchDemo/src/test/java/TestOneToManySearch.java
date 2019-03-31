import com.realationSearch.demo.dao.ClassMapper;
import com.realationSearch.demo.model.Classes;
import com.realationSearch.demo.model.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

public class TestOneToManySearch {
    private SqlSession getSession() throws Exception{
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis.xml"));
        return factory.openSession();
    }

    @Test
    public void testSelectManyTime() throws Exception{
        SqlSession session = this.getSession();
        ClassMapper mapper = session.getMapper(ClassMapper.class);
        Classes cls = mapper.selectClassManyTime(1);
        session.commit();
    }

    @Test
    public void testSelectOneTime() throws Exception{
        SqlSession session = this.getSession();
        ClassMapper mapper = session.getMapper(ClassMapper.class);
        Classes cls = mapper.selectClassOneTime(1);
        session.commit();
    }
}
