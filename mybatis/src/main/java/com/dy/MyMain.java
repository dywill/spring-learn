package com.dy;

import com.dy.dao.BookDao;
import com.dy.entity.Book;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * mybatis ：
 *  四大组件：
 *  1. Executor             用于执行sql语句
 *  2. ParameterHandler     解析执行sql时需要的参数设置
 *  3. ResultSetHandler     封装sql执行完成后的参数封装
 *  4. StatementHandler     替代PerpareStatment/Statment来预编译参数
 *
 */

public class MyMain {

    public static void main(String[] args) throws IOException {

        SqlSessionFactory sqlSessionFactory;
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BookDao mapper = sqlSession.getMapper(BookDao.class);

        try {
            Book book = new Book(null,"math_book_001",new BigDecimal(10),100);
            int i = mapper.insertBook(book);
            System.out.println("result =====> " + i);
        } finally {
            sqlSession.close();
        }
    }

    private static void testrJdbc() {
        Connection connection = null;
        try {
            // 初始化驱动类
            Class.forName("com.mysql.jdbc.Driver");

            // 获取数据库连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dytest", "root", "123");

            String sql = "insert into ttt (a,b) values (1,2)";

            Statement statement = connection.createStatement();
            connection.setAutoCommit(false);


            boolean execute = statement.execute(sql);
            connection.commit();
        } catch (Exception e) {
            System.out.println("====> " + e);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
