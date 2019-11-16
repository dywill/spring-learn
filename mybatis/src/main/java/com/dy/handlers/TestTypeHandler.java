package com.dy.handlers;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 自定义的类型处理器，即如何将java类型通过jdbc插入数据库
 */
public class TestTypeHandler implements TypeHandler<List> {

    public void setParameter(PreparedStatement preparedStatement, int i, List list, JdbcType jdbcType) throws SQLException {

    }

    public List getResult(ResultSet resultSet, String s) throws SQLException {
        return null;
    }

    public List getResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    public List getResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
