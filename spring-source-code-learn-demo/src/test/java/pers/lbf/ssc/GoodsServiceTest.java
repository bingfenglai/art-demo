package pers.lbf.ssc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest(classes = App.class)
class GoodsServiceTest {

    @Resource
    GoodsService goodsService;

    @Test
    public void test() {
        System.out.println(goodsService);
    }

}