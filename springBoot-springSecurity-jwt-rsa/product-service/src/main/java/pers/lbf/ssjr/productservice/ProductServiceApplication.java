package pers.lbf.ssjr.productservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import pers.lbf.ssjr.productservice.web.config.ProductRsaKeyProperties;

/**
 * @author Ferryman
 */
@SpringBootApplication
@MapperScan(basePackages = "pers.lbf.ssjr.productservice.dao")
@EnableConfigurationProperties(ProductRsaKeyProperties.class)
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

}
