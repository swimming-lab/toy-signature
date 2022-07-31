package swm.toy.signature.base.common.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

@UtilityClass
public class LogUtils {
  public String getStackTrace(final Throwable e) {
    return Arrays.stream(ExceptionUtils.getStackFrames(e))
        .filter(item -> item.startsWith("\tat com.cubewiz") || !item.startsWith("\tat"))
        .collect(Collectors.joining("\n"));
  }
}
