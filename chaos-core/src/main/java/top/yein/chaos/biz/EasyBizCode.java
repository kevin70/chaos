package top.yein.chaos.biz;

/**
 * 通用错误码对象.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 * @date 2020-11-09 16:03
 */
public final class EasyBizCode implements BizCode {

  private final int code;
  private final int status;
  private final String message;

  /**
   * 构造一个通用错误码对象.
   *
   * @param code {@link BizCode#getCode()}
   * @param status {@link BizCode#getStatus()}
   * @param message {@link BizCode#getMessage()}
   */
  public EasyBizCode(int code, int status, String message) {
    this.code = code;
    this.status = status;
    this.message = message;
  }

  @Override
  public int getCode() {
    return code;
  }

  @Override
  public int getStatus() {
    return status;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
