package top.yein.chaos.util;

/**
 * 非法的 IP 地址异常.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 * @date 2020-11-09 17:28
 */
public final class IllegalIpAddressException extends IllegalArgumentException {

  public final String address;

  /**
   * @param address IP 地址
   * @param message 错误描述
   */
  public IllegalIpAddressException(String address, String message) {
    super(message);
    this.address = address;
  }

  /**
   * 返回 IP 地址.
   *
   * @return IP 地址
   */
  public String getAddress() {
    return address;
  }
}
