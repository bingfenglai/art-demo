package pers.lbf.spring.cloud.alibaba.demo.service.gateway.util;

import java.util.TimeZone;

/**
 * 格式化时间生成工具类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0 @Description TODO
 * @date 2020/11/23 17:14
 */
public class FormatTimeUtil {

  private FormatTimeUtil() {}

  public static void generateTime() {
    TimeZone tz = TimeZone.getDefault();
    System.out.println(tz.toString());
  }

  public static void main(String[] args) {
    System.out.println(System.nanoTime());
    generateTime();
  }
}
