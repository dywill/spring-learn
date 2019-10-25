package service;

import bean.Book;
import bean.Customer;
import dao.BookMapper;
import dao.CustomerMappler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
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


    /**
     * readOnly为设置事务为只读事务（默认为false）， 此时若进行数据库修改操作则会报错
     * timeout为设置事务的超时时间，单位为秒，若超过该时间则会报错
     * @param bookId
     */
    @Transactional(readOnly = true,timeout = 2)
    public void findPrice(Integer bookId){
        Book book = bookMapper.findById(bookId);

        /**
         * 若在只读事务中添加 数据库的修改，添加，删除语句，则该方法会报错
         *
         * dy.springframework.dao.TransientDataAccessResourceException:
         * ### Error updating database.  Cause: java.sql.SQLException: Connection is read-only. Queries leading to data modification are not allowed
         *
         */
//        bookMapper.subStockById(BigDecimal.ONE,bookId);
        System.out.println(book.getPrice());
    }

    /**
     * 设置超时时间，若事务方法执行时间超过设定值，则会抛出异常
     * dy.springframework.transaction.TransactionTimedOutException: Transaction timed out: deadline was Tue Oct 08 23:40:58 CST 2019
     * 实验结果：事务虽然超时，但依然会正常提交
     *
     * spring默认是运行时异常回滚，编译时异常（受查异常）提交
     * 实验结果：添加事务回滚异常后。超时异常抛出后回滚
     *
     * rollbackFor              class数组， 指定发生什么异常进行事务回滚
     * rollbackForClassName     异常类名，string数组（明显使用上一种较为方便）
     *
     * noRollbackFor                        指定发生什么异常不进行事务回滚
     * noRollbackForClassName
     *
     * @param customerId
     * @param bookId
     */
    @Transactional(timeout = 2,rollbackFor = RuntimeException.class)
    public void buyBookTest01(Integer customerId, Integer bookId){

        Book book = bookMapper.findById(bookId);

        customerMapper.subBalanceById(book.getPrice(),customerId);

        System.out.println("---------------------------");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("---------------------------");
        bookMapper.subStockById(BigDecimal.ONE,bookId);
    }

    /**
     * isolation: Isolation.READ_COMMITTED(枚举)
     * 指定该事务的隔离级别
     *
     * 数据库sql
     * （事务管理，其本质即为动态代理，在标志了事务注解的方法执行前，先通过获取到的数据库连接，设置 start transaction
     *   然后执行目标方法，若未发生异常，则执行commit， 若发生异常，则执行rollback
     *  ）
     * 开启事务： start transaction（及设置事务不自动提交）
     * dml语句集合
     * 提交事务： commit
     * 回滚事务： rollback
     *
     * 事务隔离级别
     *      READ_UNCOMMITTED    读未提交    不能避免脏读，故一般不适用
     *      READ_COMMITTED      读已提交     仅可解决脏读
     *      REPEATABLE_READ     可重复读    可解决脏读/不可重复读     （mysqk 默认为此隔离级别）
     *      SERIALIZABLE        串行化      能解决所有问题，但会变为单线程查询，故一般不使用
     *
     * 脏读： 即该事务a中读取到其他事务b还未提交的数据，若b事务因异常回滚，则a事务读取数据为脏数据
     * 不可重复读： 事务a读取数据，此时事务b开始修改数据并进行提交，然后a事务再次读取数据，造成两次读取不一致
     * 幻读： 事务a在查询数据时，事务b向数据库进行了插入/删除数据操作，导致事务a其后的读取时，查询数据条数不一致
     *
     *
     * ################################################################################################################
     * propagation
     * 事务的传播行为
     * REQUIRED         当前方法需要事务，若已有事务，则使用该事务，若无事务则新建事务
     * REQUIRED_NEW     当前方法需要事务，但不论什么情况，都会新启用一个事务
     *
     * 故 当有三个事务方法 a, b, c 其方法的隔离级别都为 REQUIRED ，且调用为 a 方法调用 b, c 两个方法，则 a, b, c 三个方法相当于处于一个事务中，若有一个方法
     *    发生异常，则a, b, c 三个都会执行的sql都会进行回滚
     *
     *    当有三个方法 a, b, c 其中 a, b 方法的事务隔离级别为 REQUIRED ， c 为 REQUIRED_NEW ， 在 a 方法中执行 b， c 两个方法 其中 b 方法发生异常， c 方法正常
     *    执行， 则 a， b 两个方法会回滚， 但 c 方法由于时新建的事务，则会成功提交
     *
     * @param customerId
     * @param bookId
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public void buyBookTest02(Integer customerId, Integer bookId){
        Book book = bookMapper.findById(bookId);
        customerMapper.subBalanceById(book.getPrice(),customerId);
        bookMapper.subStockById(BigDecimal.ONE,bookId);
    }
}
