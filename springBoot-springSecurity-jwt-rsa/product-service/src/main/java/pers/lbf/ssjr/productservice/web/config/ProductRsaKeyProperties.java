package pers.lbf.ssjr.productservice.web.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import pers.lbf.ssjr.common.utils.RsaUtils;

import javax.annotation.PostConstruct;
import java.security.PublicKey;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/9/4 10:05
 */
@ConfigurationProperties(prefix = "lbf.key")
@ConstructorBinding
public class ProductRsaKeyProperties {

    private String publicKeyPath;
    private PublicKey publicKey;

    @PostConstruct
    public void loadKey() throws Exception {
        publicKey = RsaUtils.getPublicKey(publicKeyPath);
    }

    @Override
    public String toString() {
        return "ProductRsaKeyProperties{" +
                "pubKeyPath='" + publicKeyPath + '\'' +
                ", publicKey=" + publicKey +
                '}';
    }

    public String getPublicKeyPath() {
        return publicKeyPath;
    }

    public void setPublicKeyPath(String publicKeyPath) {
        this.publicKeyPath = publicKeyPath;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }
}
