package pers.lbf.ssc.conditiondemo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;

@SpringBootTest
class ConditionDemoBeanTest {

    @Resource
    ApplicationContext ctx;

    @Test
    public void test() {
        ConditionDemoBean bean = ctx.getBean(ConditionDemoBean.class);

        Assertions.assertNotNull(bean);

    }

}