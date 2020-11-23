package pers.lbf.spring.cloud.alibaba.demo.service.pojo.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0 @Description TODO
 * @date 2020/11/23 22:44
 */
public class RpcResultDTO<T> implements Serializable {

  /** 响应码 */
  private String code;

  /** 消息 */
  private String message;

  /** 响应数据 */
  private T data;

  public RpcResultDTO(String code, String message) {
    this.code = code;
    this.message = message;
  }

  public RpcResultDTO(T data) {
    this.data = data;
    this.code = "00000";
    this.message = "success";
  }

  @Override
  public String toString() {
    return "RpcResultDTO{"
        + "code='"
        + code
        + '\''
        + ", message='"
        + message
        + '\''
        + ", data="
        + data
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RpcResultDTO<?> that = (RpcResultDTO<?>) o;
    return Objects.equals(code, that.code)
        && Objects.equals(message, that.message)
        && Objects.equals(data, that.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, message, data);
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
