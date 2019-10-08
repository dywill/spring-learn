package dao;

import bean.Customer;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

public interface CustomerMappler {

    Customer findById(@Param("customerId") Integer customerId);

    void subBalanceById(@Param("price") BigDecimal price, @Param("customerId") Integer customerId);
}
