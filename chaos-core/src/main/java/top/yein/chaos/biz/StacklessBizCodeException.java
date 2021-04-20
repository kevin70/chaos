package top.yein.chaos.biz;

import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * 无详细线程堆栈的业务错误码异常.
 *
 * @author KK (kzou227@qq.com)
 */
public final class StacklessBizCodeException extends BizCodeException {

  /**
   * 使用业务错误码构建异常.
   *
   * @param bizCode 业务错误码
   */
  public StacklessBizCodeException(@NonNull final BizCode bizCode) {
    super(bizCode);
  }

  /**
   * 使用业务错误码与原因构建异常.
   *
   * @param bizCode 业务错误码
   * @param cause 原因
   */
  public StacklessBizCodeException(@NonNull final BizCode bizCode, @NonNull final Throwable cause) {
    super(bizCode, cause);
  }

  /**
   * 使用业务错误码与描述构建异常.
   *
   * @param bizCode 业务错误码
   * @param message 描述
   */
  public StacklessBizCodeException(@NonNull final BizCode bizCode, @NonNull final String message) {
    super(bizCode, message);
  }

  /**
   * 使用业务错误码, 描述与原因构建异常.
   *
   * @param bizCode 业务错误码
   * @param message 描述
   * @param cause 原因
   */
  public StacklessBizCodeException(
      @NonNull final BizCode bizCode,
      @NonNull final String message,
      @NonNull final Throwable cause) {
    super(bizCode, message, cause);
  }

  @Override
  public Throwable fillInStackTrace() {
    // 重写 fillInStackTrace()，这样我们就不会通过本机调用填充回溯，从而泄漏类加载器。
    return this;
  }
}
