package wise.services;

import wise.dto.ApiResponseModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import io.micrometer.common.lang.Nullable;

@Service
public class ResponseService {

    @Autowired
    private MessageSource messageSource;


    public ApiResponseModel successResponse(@Nullable int code, @Nullable String msgcode, @Nullable Object[] args, @Nullable Object data) {
        code = (code == 0) ? 200 : code;
        msgcode = (msgcode == null) ? "response.success" : msgcode;

        String message = messageSource.getMessage(msgcode, args, LocaleContextHolder.getLocale());
        return new ApiResponseModel(true, code, data, message);
    }

    public ApiResponseModel successResponse(@Nullable int code, @Nullable String msgcode, @Nullable Object data) {
        return successResponse(code, msgcode, null, data);
    }

    public ApiResponseModel successResponse(@Nullable String msgcode, @Nullable Object data) {
        return successResponse(200, msgcode, null, data);
    }

    public ApiResponseModel successResponse(@Nullable Object data) {
        return successResponse(200, null, null, data);
    }

    public ApiResponseModel successResponse() {
        return successResponse(200, null, null, null);
    }




    public ApiResponseModel errorResponse(@Nullable int code, @Nullable String msgcode, @Nullable Object[] args, @Nullable Object data) {
        code = (code == 0 || code == 200) ? 500 : code;
        msgcode = (msgcode == null) ? "response.failed" : msgcode;

        String message = messageSource.getMessage(msgcode, args, LocaleContextHolder.getLocale());
        return new ApiResponseModel(false, code, data, message);
    }

    public ApiResponseModel errorResponse(@Nullable int code, @Nullable String msgcode, @Nullable Object data) {
        return errorResponse(code, msgcode, null, data);
    }

    public ApiResponseModel errorResponse(@Nullable String msgcode, @Nullable Object data) {
        return errorResponse(500, msgcode, null, data);
    }

    public ApiResponseModel errorResponse(@Nullable Object data) {
        return errorResponse(500, null, null, data);
    }

    public ApiResponseModel errorResponse() {
        return errorResponse(500, null, null, null);
    }
}
