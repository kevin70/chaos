package top.yein.chaos.web.spring;

import javax.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import top.yein.chaos.domain.ReqTracing;

/**
 * {@link ReqTracing} SpringMVC 参数处理.
 *
 * @author KK (kzou227@qq.com)
 * @date 2020-12-14 15:09
 */
@Log4j2
public class ReqTracingMethodArgumentResolver implements HandlerMethodArgumentResolver {

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return ReqTracing.class.isAssignableFrom(parameter.getParameterType());
  }

  @Override
  public Object resolveArgument(
      MethodParameter parameter,
      ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest,
      WebDataBinderFactory binderFactory)
      throws Exception {
    var request = webRequest.getNativeRequest(HttpServletRequest.class);
    var xRequestId = request.getHeader("x-request-id");

    // 客户端请求 IP
    var clientIp = request.getHeader("x-real-ip");
    if (clientIp == null || clientIp.isEmpty()) {
      clientIp = request.getHeader("x-forwarded-for");
      if (clientIp != null && !clientIp.isEmpty()) {
        try {
          clientIp = clientIp.split(",")[0];
        } catch (IndexOutOfBoundsException e) {
          log.warn("解析请求中的 [x-forwarded-for: {}] 头出现异常", clientIp, e);
          clientIp = null;
        }
      }
      if (clientIp == null) {
        clientIp = request.getRemoteAddr();
      }
    }

    var xDeviceId = request.getHeader("x-device-id");
    var tracing = new ReqTracing(xRequestId, clientIp, xDeviceId);
    return tracing;
  }
}
