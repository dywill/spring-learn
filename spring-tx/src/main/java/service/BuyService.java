package service;

import bean.Book;
import bean.Customer;
import dao.BookMapper;
import dao.CustomerMappler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class BuyService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private CustomerMappler customerMapper;


    /**
     * 添加事务注解的方法，若使用spring创建的对象进行调用即可生效
     * 若使用自己创建的静态方法则不会生效，类中对自己方法进行引用也不会生效（方法上没有注解，引用了有注解的方法）
     * @param customerId
     * @param bookId
     */
    @Transactional
    public void buyBook(Integer customerId, Integer bookId){

        Book book = bookMapper.findById(bookId);
        Customer customer = customerMapper.findById(customerId);

        customerMapper.subBalanceById(book.getPrice(),customerId);
        bookMapper.subStockById(BigDecimal.ONE,bookId);
    }



}
