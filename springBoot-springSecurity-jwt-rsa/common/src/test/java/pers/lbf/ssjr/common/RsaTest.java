package pers.lbf.ssjr.common;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pers.lbf.ssjr.common.utils.RsaUtils;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/9/3 10:28
 */

public class RsaTest {
    private String publicFile = "D:\\Desktop\\rsa_key.pub";
    private String privateFile = "D:\\Desktop\\rsa_key";


    /**生成公钥和私钥
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @date 2020-09-03 10:32:16
     * @throws Exception
     * @version 1.0
     */
    @Test
    public void generateKey() throws Exception{

        RsaUtils.generateKey(publicFile,privateFile,"Java开发实践",2048);

    }

}
