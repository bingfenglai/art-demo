import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

import java.util.UUID;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/10/4 21:37
 */
public class Md5Test {

    @Test
    public void testMd5() {
        //三个参数分别对应密码明文、盐值、散列次数
        String salt = UUID.randomUUID().toString();
        Md5Hash md5Hash = new Md5Hash("123", salt, 1024);
        System.out.println("密文：" + md5Hash.toHex());
        System.out.println("盐值：" + salt);
    }
}
