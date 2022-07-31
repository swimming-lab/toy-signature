package swm.toy.signature.base.common.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import swm.toy.signature.base.common.exception.ExceptionCode;

@Builder
@Getter
@ToString
public class ApiResult<T> {
  private String code;
  private String message;
  private T data;

  public static ApiResult<?> of(final ExceptionCode exceptionCode) {
    return ApiResult.of(exceptionCode, null);
  }

  public static <T> ApiResult<T> of(final ExceptionCode exceptionCode, final T data) {
    return ApiResult.<T>builder()
        .code(exceptionCode.getCode())
        .message(exceptionCode.getMessage())
        .data(data)
        .build();
  }
}
