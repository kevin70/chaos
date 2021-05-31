package top.yein.chaos.biz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * 业务异常与 {@link BizCode} 联合使用.
 *
 * @author KK (kzou227@qq.com)
 */
public class BizCodeException extends RuntimeException {

  private final transient BizCode bizCode;
  private final transient List<ContextValue> contextEntries = new ArrayList<>();

  /**
   * 使用业务错误码构建异常.
   *
   * @param bizCode 业务错误码
   */
  public BizCodeException(@NonNull final BizCode bizCode) {
    this.bizCode = bizCode;
  }

  /**
   * 使用业务错误码与原因构建异常.
   *
   * @param bizCode 业务错误码
   * @param cause 原因
   */
  public BizCodeException(@NonNull final BizCode bizCode, @NonNull final Throwable cause) {
    super(cause);
    this.bizCode = bizCode;
  }

  /**
   * 使用业务错误码与描述构建异常.
   *
   * @param bizCode 业务错误码
   * @param message 描述
   */
  public BizCodeException(@NonNull final BizCode bizCode, @NonNull final String message) {
    super(message);
    this.bizCode = bizCode;
  }

  /**
   * 使用业务错误码, 描述与原因构建异常.
   *
   * @param bizCode 业务错误码
   * @param message 描述
   * @param cause 原因
   */
  public BizCodeException(
      @NonNull final BizCode bizCode,
      @NonNull final String message,
      @NonNull final Throwable cause) {
    super(message, cause);
    this.bizCode = bizCode;
  }

  /**
   * 返回业务错误码.
   *
   * @return 业务错误码
   */
  public BizCode getBizCode() {
    return bizCode;
  }

  /**
   * 添加错误上下文属性.
   *
   * @param label 上下文属性标签
   * @param value 上下文属性值
   * @return 当前实例
   */
  public BizCodeException addContextValue(@NonNull final String label, Object value) {
    contextEntries.add(new ContextValue(label, value));
    return this;
  }

  /**
   * 返回错误上下文属性.
   *
   * @return 错误上下文属性
   */
  public List<ContextValue> getContextEntries() {
    return Collections.unmodifiableList(contextEntries);
  }

  /**
   * 返回原始的错误描述.
   *
   * @return 错误描述
   */
  public String getRawMessage() {
    return super.getMessage();
  }

  /**
   * 返回格式化的错误描述.
   *
   * @return 错误描述
   */
  @Override
  public String getMessage() {
    return getFormattedMessage(super.getMessage());
  }

  /**
   * 返回格式化的错误描述.
   *
   * @return 错误描述
   */
  @Override
  public String toString() {
    return getFormattedMessage(super.getMessage());
  }

  private String getFormattedMessage(final String baseMessage) {
    final StringBuilder builder = new StringBuilder(64);
    if (baseMessage != null) {
      builder.append(baseMessage);
    }
    builder
        .append("\n\t[code=")
        .append(this.bizCode.getCode())
        .append(", message=")
        .append(this.bizCode.getMessage())
        .append(']');

    if (!contextEntries.isEmpty()) {
      builder.append("\nContext:\n");

      int i = 0;
      for (final ContextValue cv : contextEntries) {
        builder.append("\t[");
        builder.append(++i);
        builder.append(':');
        builder.append(cv.getLabel());
        builder.append("=");
        final Object value = cv.getValue();
        if (value == null) {
          builder.append("null");
        } else {
          try {
            builder.append(value.toString());
          } catch (final Exception e) {
            builder.append("Exception thrown on toString(): ").append(e.getMessage());
          }
        }
        builder.append("]\n");
      }
      builder.append("---------------------------------");
    }
    return builder.toString();
  }

  /** 错误上下文属性. */
  public static class ContextValue {

    private final String label;
    private final Object value;

    private ContextValue(final String label, final Object value) {
      this.label = label;
      this.value = value;
    }

    /**
     * 返回上下文属性标签.
     *
     * @return 上下文属性标签
     */
    public String getLabel() {
      return label;
    }

    /**
     * 返回上下文属性值.
     *
     * @return 上下文属性值
     */
    public Object getValue() {
      return value;
    }
  }
}
