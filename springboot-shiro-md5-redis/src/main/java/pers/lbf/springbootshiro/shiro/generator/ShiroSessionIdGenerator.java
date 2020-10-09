package pers.lbf.springbootshiro.shiro.generator;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

import java.io.Serializable;

/**SessionId生成器
 * <p>@author 赖柄沣 laibingf_dev@outlook.com</p>
 * <p>@date 2020/8/15 15:19</p>
 */
public class ShiroSessionIdGenerator implements SessionIdGenerator {

    /**
     *实现SessionId生成
     * @param session
     * @return
     */
    @Override
    public Serializable generateId(Session session) {
        Serializable sessionId = new JavaUuidSessionIdGenerator().generateId(session);
        return String.format("login_token_%s", sessionId);
    }
}
