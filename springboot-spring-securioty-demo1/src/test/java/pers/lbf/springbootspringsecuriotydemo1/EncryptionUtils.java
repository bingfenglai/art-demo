package pers.lbf.springbootspringsecuriotydemo1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/8/30 10:20
 */
@SpringBootTest
public class EncryptionUtils {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void test1() {

        String user = passwordEncoder.encode("admin");
        System.out.println(user);

    }
}
