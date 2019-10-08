package dao;

import bean.Book;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface BookMapper {

    List<Book> getAll();

    Book findById(@Param("bookId") Integer bookId);

    void subStockById(@Param("stock") BigDecimal stock, @Param("bookId") Integer bookId);
}
