package pers.lbf.ssjr.authenticationservice.web.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pers.lbf.ssjr.authenticationservice.pojo.vo.UserAuthVO;
import pers.lbf.ssjr.authenticationservice.pojo.vo.UserLoginVo;
import pers.lbf.ssjr.authenticationservice.web.config.AuthServerRsaKeyProperties;
import pers.lbf.ssjr.common.utils.JwtUtils;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**自定义认证过滤器
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/9/3 12:11
 */
public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {

    /**
     * 认证管理器
     */

    private AuthenticationManager authenticationManager;

    private AuthServerRsaKeyProperties prop;

    /**构造注入
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @date 2020-09-03 12:17:54
     * @param authenticationManager spring security的认证管理器
     * @param prop 公钥 私钥 配置类
     * @version 1.0
     */
    public TokenLoginFilter(AuthenticationManager authenticationManager, AuthServerRsaKeyProperties prop) {
        this.authenticationManager = authenticationManager;
        this.prop = prop;

    }


    /**接收并解析用户凭证，并返回json数据
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @date 2020-09-03 12:19:29
     * @param request req
     * @param response resp
     * @return Authentication
     * @version 1.0
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response){

        //判断请求是否为POST,禁用GET请求提交数据
        if (!"POST".equals(request.getMethod())) {
            throw new AuthenticationServiceException(
                    "只支持POST请求方式");
        }


        //将json数据转换为java bean对象
        try {
            UserLoginVo user = new ObjectMapper().readValue(request.getInputStream(), UserLoginVo.class);

            if (user.getUsername()==null){
                user.setUsername("");
            }

            if (user.getPassword() == null) {
                user.setPassword("");
            }
            user.getUsername().trim();

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword()));
        }
        catch (IOException e){
            e.printStackTrace();

        }
        catch (BadCredentialsException e) {
            //用户名或密码错误返回json数据提示，实际项目中这里可能是重定向到
            try {
                //生成消息
                Map<String, Object> map = new HashMap<>();
                map.put("code", HttpServletResponse.SC_OK);
                map.put("msg", "用户名或密码错误");
                //响应数据
                response.setContentType("application/json;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_OK);
                PrintWriter writer = response.getWriter();
                writer.write(new ObjectMapper().writeValueAsString(map));
                writer.flush();
                writer.close();
            } catch (Exception e1) {
                throw new RuntimeException(e1);
            }
            throw new RuntimeException(e);
        }
        throw new RuntimeException("未知错误");
        }



    /**这个方法会在验证成功时被调用
     *用户登录成功后，生成token,并且返回json数据给前端
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @date 2020-09-03 13:00:23
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @version 1.0
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response, FilterChain chain, Authentication authResult) {
        //获取当前登录对象
        UserAuthVO user = new UserAuthVO();
        user.setUsername(authResult.getName());
        user.setAuthorities((List<SimpleGrantedAuthority>) authResult.getAuthorities());

        //使用jwt创建一个token，私钥加密
        String token = JwtUtils.generateTokenExpireInMinutes(user,prop.getPrivateKey(),15);

        //返回token
       response.addHeader("Authorization","Bearer"+token);

       //登录成功返回json数据提示，实际项目中这里可能是重定向到
        try {
            //生成消息
            Map<String, Object> map = new HashMap<>();
            map.put("code",HttpServletResponse.SC_OK);
            map.put("msg","登录成功");
            //响应数据
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            PrintWriter writer = response.getWriter();
            writer.write(new ObjectMapper().writeValueAsString(map));
            writer.flush();
            writer.close();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
