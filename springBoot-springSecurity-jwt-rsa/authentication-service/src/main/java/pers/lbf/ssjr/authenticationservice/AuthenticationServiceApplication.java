package pers.lbf.ssjr.authenticationservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import pers.lbf.ssjr.authenticationservice.web.config.AuthServerRsaKeyProperties;

/**
 * @author Ferryman
 */
@SpringBootApplication
@MapperScan(value = "pers.lbf.ssjr.authenticationservice.dao")
@EnableConfigurationProperties(AuthServerRsaKeyProperties.class)
public class AuthenticationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthenticationServiceApplication.class, args);
    }

}
