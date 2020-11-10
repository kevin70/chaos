package top.yein.chaos.util;

import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * IP 工具类.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 * @date 2020-11-09 16:53
 */
public final class IpUtils {

  private IpUtils() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * 返回 IPv4 的 {@link Integer} 数值.
   *
   * @param address IPv4 地址
   * @return IPv4 {@link Integer} 数值
   * @throws IllegalIpAddressException 非法的 IPv4 地址
   */
  public static int ipv4ToInt(@NonNull String address) throws IllegalIpAddressException {
    String[] parts = address.split("\\.");
    int rs = 0;
    for (int i = 0; i < 4; i++) {
      try {
        rs = rs | Integer.parseInt(parts[i]) << 8 * i;
      } catch (RuntimeException e) {
        throw new IllegalIpAddressException(address, "非法的 IPv4 地址 \"" + address + "\"");
      }
    }
    return rs;
  }
}
