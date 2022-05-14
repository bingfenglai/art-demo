package pers.lbf.webflux.demo.pojo;

import java.io.Serializable;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/11/24 13:08
 */
public class ResultVO implements Serializable {

    private String code;
    private String msg;

    public ResultVO(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResultVO ok() {
        return new ResultVO("00000", "success");
    }

    @Override
    public String toString() {
        return "ResultVO{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
