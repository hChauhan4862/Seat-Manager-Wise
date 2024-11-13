package wise.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import wise.common.RequestContext;


@ControllerAdvice
public class TimeTakenAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(@SuppressWarnings("null") MethodParameter returnType, @SuppressWarnings({ "null", "rawtypes" }) Class converterType) {
        return true;  // Apply to all responses
    }

    @Override
    public Object beforeBodyWrite(@SuppressWarnings("null") Object body, @SuppressWarnings("null") MethodParameter returnType, @SuppressWarnings("null") MediaType selectedContentType,
                                  @SuppressWarnings({ "rawtypes", "null" }) Class selectedConverterType, @SuppressWarnings("null") ServerHttpRequest request, @SuppressWarnings("null") ServerHttpResponse response) {
        Long requestTime = (Long) RequestContext.get("request_time");
        // Long platform = (Long) RequestContext.get("platform");
        if (requestTime != null) {
            long timeTaken = System.currentTimeMillis() - requestTime;
            response.getHeaders().add("X-Time-Taken", String.valueOf(timeTaken));
            // response.getHeaders().add("X-Platform", String.valueOf(platform));
        }
        return body;
    }
}
