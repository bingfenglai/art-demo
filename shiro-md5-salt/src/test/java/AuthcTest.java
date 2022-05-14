import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Before;
import org.junit.Test;
import pers.lbf.shiroauthcmysql.core.authc.CurrentSystemAuthenticator;

import java.util.UUID;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/10/4 11:20
 */
public class AuthcTest {
    private CurrentSystemAuthenticator authenticator;

    @Before
    public void init() {
        this.authenticator = new CurrentSystemAuthenticator();
    }

    @Test
    public void testAuthc() {
        this.authenticator.authenticate("xiangbei", "123");
    }


}
