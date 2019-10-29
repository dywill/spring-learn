import dy.dao.BookMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import dy.service.BuyService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class MyTest {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BuyService buyService;

    @Test
    public void test01(){
        buyService.buyBook(1,1);
    }

    @Test
    public void test02(){
        buyService.findPrice(1);
    }

    @Test
    public void test03(){
        buyService.buyBookTest01(1,1);
    }
}
