package pers.lbf.webflux.demo;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/11/24 14:13
 */
public class SimpleFluxTest {

    @Test
    public void test1() {
        Flux.create(sink -> {

            for (int i = 0; i < 100000000; i++) {

                sink.next(i);

            }

            sink.complete();

        }).subscribe(System.out::println);
    }
}
