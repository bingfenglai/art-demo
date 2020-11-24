package pers.lbf.webflux.demo.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * @author laibf
 */
@SpringBootApplication(scanBasePackages = "pers.lbf.webflux.demo")
@EnableWebFlux
public class WebFluxDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(WebFluxDemoApplication.class, args);
  }
}
