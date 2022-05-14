package pers.lbf.ssjr.authenticationservice.web.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import pers.lbf.ssjr.common.utils.RsaUtils;

import javax.annotation.PostConstruct;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * 解析公钥和私钥的配置类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/9/3 10:42
 */
@ConfigurationProperties(prefix = "lbf.key")
@ConstructorBinding
public class AuthServerRsaKeyProperties {

    private String publicKeyPath;
    private String privateKeyPath;

    private PublicKey publicKey;
    private PrivateKey privateKey;


    /**
     * 加载文件当中的公钥、私钥
     * 被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，
     * 并且只会被服务器执行一次。PostConstruct在构造函数之后执行，
     * init（）方法之前执行。
     *
     * @throws Exception e
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @date 2020-09-03 12:07:35
     * @version 1.0
     */
    @PostConstruct
    public void loadKey() throws Exception {
        publicKey = RsaUtils.getPublicKey(publicKeyPath);
        privateKey = RsaUtils.getPrivateKey(privateKeyPath);

    }

    public String getPublicKeyPath() {
        return publicKeyPath;
    }

    public void setPublicKeyPath(String publicKeyPath) {
        this.publicKeyPath = publicKeyPath;
    }

    public String getPrivateKeyPath() {
        return privateKeyPath;
    }

    public void setPrivateKeyPath(String privateKeyPath) {
        this.privateKeyPath = privateKeyPath;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }
}
