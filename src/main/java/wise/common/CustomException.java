package wise.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import io.micrometer.common.lang.Nullable;

public class CustomException extends RuntimeException {

    private String code;
    private Object[] args;
    HttpStatusCode status;

    // Constructor with code only

    public CustomException(String code, @Nullable  Object[] args, HttpStatusCode status, Throwable cause) {
        super(cause);
        this.code = code;
        this.args = args;
        this.status = status;
    }

    public CustomException(String code, @Nullable Object[] args, @Nullable HttpStatusCode status) {
        this.code = code;
        this.args = args;
        this.status = status;
    }

    public CustomException(String code, HttpStatusCode status) {
        this.code = code;
        this.args = null;
        this.status = status;
    }

    public CustomException(String code) {
        this.code = code;
        this.args = null;
    }

    public String getCode() {
        return code;
    }

    public Object[] getArgs() {
        return args;
    }

    public HttpStatusCode getStatus() {
        if (status == null) {
            return HttpStatus.BAD_REQUEST;
        }
        return status;
    }
}
