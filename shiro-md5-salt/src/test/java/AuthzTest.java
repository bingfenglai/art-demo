import com.sun.org.apache.bcel.internal.generic.GOTO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;
import pers.lbf.shiroauthcmysql.core.authc.CurrentSystemAuthenticator;

import java.util.Arrays;

/**
 * 访问控制测试
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/10/5 16:48
 */
public class AuthzTest {
    private CurrentSystemAuthenticator authenticator;

    @Before
    public void init() {
        this.authenticator = new CurrentSystemAuthenticator();
        //对于授权，只有主体通过认证后才能进行，所以需要先登录系统
        this.authenticator.authenticate("xiangbei", "123");
    }

    @Test
    public void testHasAllRoles() {
        Subject subject = SecurityUtils.getSubject();
        boolean b = subject.hasAllRoles(Arrays.asList("admin", "user"));
        if (b) {
            System.out.println(subject.getPrincipal() + " 具有访问权限");
        } else {
            System.out.println(subject.getPrincipal() + " 没有访问权限");
        }
    }

    @Test
    public void testHasRole() {
        Subject subject = SecurityUtils.getSubject();
        //主体具备某一角色即可访问
        if (subject.hasRole("admin")) {
            System.out.println(subject.getPrincipal() + " 具有 admin 角色");
        }

    }

    @Test
    public void testHasRoles() {
        Subject subject = SecurityUtils.getSubject();

        //适用于只要有其中一个角色即可的情况
        boolean[] booleans = subject.hasRoles(Arrays.asList("admin", "user"));
        for (boolean b : booleans) {
            if (b) {
                System.out.println(subject.getPrincipal() + " 具有访问权限");
                break;
            }
        }

    }

}
