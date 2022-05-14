package shirodemo;

import org.junit.Before;
import org.junit.Test;
import pers.lbf.shirodemo.core.Authenticator;

/**
 * 测试认证
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/9/21 0:49
 */
public class TestAuthenticator {
    private Authenticator authenticator = null;

    @Before
    public void init() {
        authenticator = new Authenticator();
    }

    @Test
    public void testAuth() {

        authenticator.authenticate("xiangbei", "123");
    }
}
