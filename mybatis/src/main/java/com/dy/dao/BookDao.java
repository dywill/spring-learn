package com.dy.dao;

import com.dy.entity.Book;
import org.apache.ibatis.annotations.Param;

public interface BookDao {


    /*
        一. mybatis传参:
        1. 单个基本类型参数
            单个参数#{xx}，可以随意填入能够自动获取
        2. 多个参数
            例如：Book getByIdAndName(Integer id， String name);
            可以使用 #{1}，#{2} 或 #{param1}，#{param2}获取参数值
            原因在于 mybatis 会封装参数到一个map中，mybatis默认使用参数所在的索引位置作为map的key

            解决方法：可以通过@Param参数指定map的key值
         3. pojo类
            #{pojo的属性名}

         二. 传参的封装
         #{xx}和${xx}的区别
            1. #{xx} 为PrepareStatement 预编译后设值的
            2. ${xx} 为直接通过拼接字符串的方式拼接sql
            --- 实际使用场景 ---
                1. 在where条件中封装参数使用 #{} 预编译参数，防止sql注入
                2. 在动态传入表名等使用场景下，动态sql需要通过 ${xx} 动态拼接 sql， 此处无法通过 #{xx} 预编译（会报错）

         三. 获取结果的封装
            1. 直接查询对应实体类，resultType直接填写实体类即可
                1).单个查询，多个查询 resultType 都直接填写实体类即可
                2).或者使用 resultMap 封装实体类
            2. 查询后封装为map
                1).单个查询查询出的 map 为 k(列名) - v (值) 方式得出一个map对象。(resultMap填写map)
                2).多个查询会封装为 <key,Entity> 的map 其中key 通过在方法名上使用 @MapKey("列名") 的方式指定map的key
                   其中resultMap填写map entity则封装为map，填写全限定性类名时，则会封装为一个实体类
            3. 封装结果为实体类
                1).开启驼峰命名映射规则 例如 数据库: emp_name 对应 java实体类: empName
                2).查询sql起别名来对应java实体类的属性名（即 select emp_name as ename）
                3).使用自定义的resultMap来封装自定义的 数据库列名 与 java实体类的属性 映射关系
                    --- resultMap标签的详细用法 ---
                        见 bookDao.xml
    */
    Book getById(@Param("id") Integer id);

    int insertBook(Book book);
}
