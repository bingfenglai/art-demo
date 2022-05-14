package pers.lbf.springbootspringsecuriotydemo1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Ferryman
 */
@SpringBootApplication
@MapperScan("pers.lbf.springbootspringsecuriotydemo1.dao")
public class SpringbootSpringSecurityDemo1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSpringSecurityDemo1Application.class, args);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
