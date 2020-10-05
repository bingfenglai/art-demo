import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;
import pers.lbf.shiroauthcmysql.core.authc.CurrentSystemAuthenticator;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/10/5 17:23
 */
public class AuthzTest2 {
    private CurrentSystemAuthenticator authenticator;
    @Before
    public void init() {
        this.authenticator = new CurrentSystemAuthenticator();
        //对于授权，只有主体通过认证后才能进行，所以需要先登录系统
        this.authenticator.authenticate("xiangbei","123");
    }

    @Test
    public void testIsPermittedAll() {
        Subject subject = SecurityUtils.getSubject();
        boolean b = subject.isPermittedAll("product:*");
        if (b) {
            System.out.println(subject.getPrincipal() +" 具备 product 模块的所有权限");
        }else {
            System.out.println(subject.getPrincipal() +" 没有 product 模块的访问权限");
        }
    }

    @Test
    public void testIsPermission() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isPermitted("user:create")){
            System.out.println(subject.getPrincipal() + " 具有 用户创建权限");

        }
    }


    @Test
    public void testIsPermitted(){
        Subject subject = SecurityUtils.getSubject();
        boolean[] permitted = subject.isPermitted("user:create", "user:find");
        for (boolean b : permitted) {
            if (b) {
                System.out.println(subject.getPrincipal() +" 具有访问权限");
                break;
            }
        }
    }
}
