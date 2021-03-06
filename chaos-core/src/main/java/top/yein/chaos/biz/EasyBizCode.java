package top.yein.chaos.biz;

/**
 * 通用错误码对象.
 *
 * @author KK (kzou227@qq.com)
 */
public final class EasyBizCode implements BizCode {

  private final int code;
  private final int httpStatus;
  private final int grpcStatus;
  private final String message;

  /**
   * 构造一个通用错误码对象.
   *
   * @param code {@link BizCode#getCode()}
   * @param httpStatus {@link BizCode#getHttpStatus()}
   * @param grpcStatus {@link BizCode#getGrpcStatus()}
   * @param message {@link BizCode#getMessage()}
   */
  public EasyBizCode(int code, int httpStatus, int grpcStatus, String message) {
    this.code = code;
    this.httpStatus = httpStatus;
    this.grpcStatus = grpcStatus;
    this.message = message;
  }

  @Override
  public int getCode() {
    return code;
  }

  @Override
  public int getHttpStatus() {
    return httpStatus;
  }

  @Override
  public int getGrpcStatus() {
    return grpcStatus;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
