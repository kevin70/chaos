package top.yein.chaos.web.spring;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import org.zalando.problem.spring.web.advice.AdviceTrait;
import top.yein.chaos.biz.BizCodeException;

/**
 * {@link BizCodeException} Spring 异常处理器.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 * @date 2020-11-24 15:38
 */
@ControllerAdvice
public class BizCodeExceptionHandler implements AdviceTrait {

  @ExceptionHandler
  public ResponseEntity<Problem> handleBizCode(
      final BizCodeException exception, final NativeWebRequest request) {
    var bc = exception.getBizCode();
    var builder =
        Problem.builder()
            .with("code", bc.getCode())
            .withStatus(Status.valueOf(bc.getHttpStatus()))
            .withTitle(bc.getMessage())
            .withDetail(exception.getRawMessage());
    var contextEntries = exception.getContextEntries();
    if (!contextEntries.isEmpty()) {
      builder.with("ex-context-entries", contextEntries);
    }

    var problem = builder.build();
    problem.setStackTrace(createStackTrace(exception));
    return create(exception, problem, request);
  }
}
