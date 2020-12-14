package top.yein.chaos.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 请求追踪基础日志.
 *
 * @author KK (kzou227@qq.com)
 * @date 2020-12-14 14:52
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqTracing {

  /**
   * (Optional) 请求 ID.
   *
   * <p>参数采用 HTTP Header 传递, Header 名称为 {@code x-request-id}.
   */
  private String requestId;
  /**
   * (Required) 请求的客户端 IP 地址.
   *
   * <p>由服务端自动获取客户端的请求 IP.
   *
   * <p>由于可能会经过代理, 负载均衡, 反向代理等多个中间服务器, 取值严格按照以下顺序获取.
   *
   * <ul>
   *   <li>HTTP Header: {@code x-real-ip}
   *   <li>HTTP Header: {@code x-forwarded-for}
   *   <li>{@code ServletRequest.getRemoteAddr()}
   * </ul>
   */
  private String clientIp;

  /**
   * (Optional) 设备 ID.
   *
   * <p>终端设备唯一 ID, 一般仅在有唯一标识的设备上传入该参数. 大多数时候都会在本地移动设备中出现, 浏览器中可忽略此参数.
   */
  private String deviceId;
}
