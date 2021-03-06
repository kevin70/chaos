package top.yein.chaos.biz;

/**
 * 业务错误码接口.
 *
 * <p>错误码规范:
 *
 * <ul>
 *   <li>采用大写 {@code C} 字母开头;
 *   <li>错误码必须为一个数字;
 *   <li>在同一业务中错误码要保证唯一性;
 *   <li>同一错误码可在多个业务中使用，但是错误类型必须相同.
 * </ul>
 *
 * 错误码目标:
 *
 * <ul>
 *   <li>客户端能通过错误码快速识别错误原因;
 *   <li>开发人员可以通过错误码快速识别错误原因;
 *   <li>开发人员可以通过错误码快速解决问题.
 * </ul>
 *
 * <b>服务端未捕获或未知异常统一返回 {@link #C0} 错误类型, 同时需要在日志中记录详细的错误描述及输入参数, 便于开发人员后期排查问题.</b>
 *
 * @author KK (kzou227@qq.com)
 */
public interface BizCode {

  // ====================================== 公共错误码定义 ======================================

  /** 未定义错误. */
  BizCode C0 = new EasyBizCode(0, 500, 2, "未定义错误");
  /**
   * 错误的请求.
   *
   * <p>通常是指缺少参数, 或者参数不符合接口要求.
   */
  BizCode C400 = new EasyBizCode(400, 400, 3, "错误的请求");
  /** 未认证或认证失效访问资源需要认证校验. */
  BizCode C401 = new EasyBizCode(401, 401, 16, "未认证或认证失效");
  /** 拒绝访问没有资源访问权限. */
  BizCode C403 = new EasyBizCode(403, 403, 7, "拒绝访问");
  /** 未找到指定的资源. */
  BizCode C404 = new EasyBizCode(404, 404, 5, "未找到资源");
  /** Not Acceptable. */
  BizCode C406 = new EasyBizCode(406, 406, 9, "Not Acceptable");
  /** 资源冲突. */
  BizCode C409 = new EasyBizCode(409, 409, 10, "资源冲突");
  /**
   * 唯一冲突.
   *
   * <p>一般指数据库存储的唯一索引冲突.
   */
  BizCode C810 = new EasyBizCode(810, 409, 6, "唯一冲突");
  /**
   * 数据更新异常.
   *
   * <p>受影响的行记录数不正确.
   *
   * <p>示例: 预期修改 1 条数据记录实际修改了 3 条记录.
   */
  BizCode C811 = new EasyBizCode(811, 500, 9, "数据修改异常受影响的行记录数不正确");
  /** 数据格式不正确. */
  BizCode C910 = new EasyBizCode(910, 400, 3, "数据格式不正确");
  /** 数据类型不正确. */
  BizCode C911 = new EasyBizCode(911, 400, 3, "数据类型不正确");
  /** 缺少必选参数. */
  BizCode C912 = new EasyBizCode(912, 400, 3, "缺少参数");

  // ====================================== 公共错误码定义 ======================================

  /**
   * 错误码.
   *
   * <p>通过业务码客户及开发人员能够快速识别错误原因的<b>标识</b>.
   *
   * <p>{@code 1000} 以内为保留错误码, 在扩展时避免使用 {@code 1000} 以内的错误码, 避免冲突.
   *
   * @return 错误码
   */
  int getCode();

  /**
   * HTTP 状态码 <a
   * href="https://httpstatus.io/http-status-codes">https://httpstatus.io/http-status-codes</a>.
   *
   * @return HTTP 状态码
   */
  int getHttpStatus();

  /**
   * gRPC 状态码 <a
   * href="https://github.com/grpc/grpc/blob/master/doc/statuscodes.md">https://github.com/grpc/grpc/blob/master/doc/statuscodes.md</a>.
   *
   * @return gRPC 状态码
   */
  int getGrpcStatus();

  /**
   * 业务错误码描述.
   *
   * @return 错误码描述
   */
  String getMessage();
}
