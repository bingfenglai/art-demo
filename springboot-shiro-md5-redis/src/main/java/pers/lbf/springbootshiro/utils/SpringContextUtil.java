package pers.lbf.springbootshiro.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Spring上下文工具类
 * <p>@author 赖柄沣 laibingf_dev@outlook.com</p>
 * <p>@date 2020/8/15 15:03</p>
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {
    private static ApplicationContext context;


    /**
     * Spring在bean初始化后会判断是不是ApplicationContextAware的子类
     * 如果该类是,setApplicationContext()方法,会将容器中ApplicationContext作为参数传入进去
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    /**
     * 通过Name返回指定的Bean
     *
     * @param beanClass
     * @param <T>
     * @return bean
     */
    public static <T> T getBean(Class<T> beanClass) {

        return context.getBean(beanClass);
    }

}
