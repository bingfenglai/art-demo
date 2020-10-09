package pers.lbf.springbootshiro.start;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author Ferryman
 */
@SpringBootApplication(scanBasePackages = "pers.lbf.springbootshiro")
@MapperScan("pers.lbf.springbootshiro.**.dao")
public class SpringbootShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootShiroApplication.class, args);
    }

}
