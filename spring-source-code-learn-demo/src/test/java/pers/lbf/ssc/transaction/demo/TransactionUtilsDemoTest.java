package pers.lbf.ssc.transaction.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pers.lbf.ssc.App;

import javax.annotation.Resource;

@SpringBootTest(classes = App.class)
class TransactionUtilsDemoTest {

    @Resource
    TransactionUtilsDemo transactionUtilsDemo;

    @Test
    void doSomething() {

        transactionUtilsDemo.doSomething();
        System.out.println("调用之后");
    }
}