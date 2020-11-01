package pers.lbf.spring.cloud.alibaba.demo.sentinel.demo.vo;

import java.io.Serializable;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/10/30 20:30
 */
public class ResultVO<T> implements Serializable {
    private String code;
    private String msg;
    private T data;


    public ResultVO(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public ResultVO(T data){
        this.data = data;
        this.msg = "操作成功";
        this.code = "0";
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultVO{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
