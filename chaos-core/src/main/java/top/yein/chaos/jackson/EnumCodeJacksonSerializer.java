package top.yein.chaos.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import top.yein.chaos.common.EnumCode;

/**
 * {@link EnumCode} jackson 序列化.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 * @date 2020-12-04 17:13
 */
public class EnumCodeJacksonSerializer extends StdSerializer<EnumCode> {

  /** 构造 EnumCodeJacksonSerializer 实例. */
  public EnumCodeJacksonSerializer() {
    super(EnumCode.class);
  }

  @Override
  public void serialize(EnumCode value, JsonGenerator gen, SerializerProvider provider)
      throws IOException {
    gen.writeNumber(value.code());
  }
}
