package wise.filters;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

import wise.common.RequestContext;
import wise.utils.constant.Constant;

import java.util.UUID;

@Component
public class MyInterceptor implements HandlerInterceptor {

    // private static final Logger logger = LoggerFactory.getLogger(MyInterceptor.class);

    @Override
    public boolean preHandle(@SuppressWarnings("null") HttpServletRequest request, @SuppressWarnings("null") HttpServletResponse response, @SuppressWarnings("null") Object handler) throws Exception {

        // Set request time for time taken calculation
        RequestContext.set("request_time", System.currentTimeMillis());

        // Set platform parsed from request path
        String requestPath = request.getRequestURI();

        String platform = "WEB";
        // if (requestPath.startsWith(Constant.KIOSK_BASE_API)) {
        //     platform = "KIOSK";
        // } else if (requestPath.startsWith(Constant.MOBILE_BASE_API)) {
        //     platform = "MOBILE";
        // }
        RequestContext.set("platform", platform);


        // Generate or retrieve traceId
        String traceId = request.getHeader("traceId");
        if (traceId == null || traceId.isEmpty()) {
            traceId = UUID.randomUUID().toString();
        }

        // Generate spanId
        String spanId = UUID.randomUUID().toString();

        // Set traceId and spanId in MDC
        MDC.put("traceId", traceId);
        MDC.put("spanId", spanId);

        // Optionally, add traceId and spanId to response headers
        response.addHeader("traceId", traceId);
        response.addHeader("spanId", spanId);

        return true;
    }

    @Override
    public void afterCompletion(@SuppressWarnings("null") HttpServletRequest request, @SuppressWarnings("null") HttpServletResponse response,
                                @SuppressWarnings("null") Object handler, @SuppressWarnings("null") Exception ex) throws Exception {
        // Clean up MDC to prevent memory leaks
        // print all MDC values to log
        // MDC.getCopyOfContextMap().forEach((key, value) -> logger.info("MDC Key: {}, Value: {}", key, value));

        MDC.remove("traceId");
        MDC.remove("spanId");
    }
}
