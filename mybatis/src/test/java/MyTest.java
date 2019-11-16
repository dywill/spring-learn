import com.dy.dao.BookDao;
import com.dy.entity.Book;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;


/**
 *  todo
 *  该junit测试，执行test方法，若为向mysql数据库插入数据，执行一次会插入两条数据
 *  且执行一个test也会执行其他test
 */
public class MyTest {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void init() throws IOException {

        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    }

    /**
     * 测试查询
     */
    @Test
    public void test01(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            BookDao mapper = sqlSession.getMapper(BookDao.class);
            Book book = mapper.getById(1);

            System.out.println(book);
        } finally {
            sqlSession.close();
        }

    }

    @Test
    public void test02(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);    // 获取到连接，并且设置为自动提交
        BookDao mapper = sqlSession.getMapper(BookDao.class);

        try {
            Book book = new Book(null,"math_book_001",new BigDecimal(10),100);
            int i = mapper.insertBook(book);
            Integer id = book.getId();
            System.out.println("主键id ====> " + id);
            System.out.println("result =====> " + i);
        } finally {
            sqlSession.close();
        }
    }


}
