package top.yein.chaos.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import top.yein.chaos.common.EnumCode;

/**
 * Chaos Jackson 模块.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 * @date 2020-12-04 17:21
 */
public class ChaosModule extends SimpleModule {

  /** 构造 chaos jackson 模块. */
  public ChaosModule() {
    super("chaos");

    addSerializer(EnumCode.class, new EnumCodeJacksonSerializer());
  }
}
