package pers.lbf.ssc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pers.lbf.ssc.service.UserService;

import javax.annotation.Resource;

@SpringBootTest
class UserServiceTest {

    @Resource
    UserService userService;

    @Test
    void test1() {
        userService.test();
    }
}