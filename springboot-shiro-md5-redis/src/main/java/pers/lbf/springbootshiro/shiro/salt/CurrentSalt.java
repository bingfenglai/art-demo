package pers.lbf.springbootshiro.shiro.salt;

import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;

/**由于shiro当中的ByteSource没有实现序列化接口，缓存时会发生错误
 * 因此，我们需要通过自定义ByteSource的方式实现这个接口
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/10/8 16:17
 */
public class CurrentSalt extends SimpleByteSource implements Serializable {
    public CurrentSalt(String string) {
        super(string);
    }

    public CurrentSalt(byte[] bytes) {
        super(bytes);
    }

    public CurrentSalt(char[] chars) {
        super(chars);
    }

    public CurrentSalt(ByteSource source) {
        super(source);
    }

    public CurrentSalt(File file) {
        super(file);
    }

    public CurrentSalt(InputStream stream) {
        super(stream);
    }
}
