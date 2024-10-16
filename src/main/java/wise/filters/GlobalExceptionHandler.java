package wise.filters;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException.NotImplemented;

import wise.common.ApiResponseModel;
import wise.common.CustomException;
import wise.services.app.ResponseService;

import org.springframework.context.MessageSource;



import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private ResponseService responseService;

    @Autowired
    private MessageSource messageSource;

    // Handle general runtime exceptions
    // @ExceptionHandler(RuntimeException.class)
    // public ResponseEntity<ApiResponseModel> handleRuntimeException(RuntimeException exception) {
    //     return new ResponseEntity<ApiResponseModel>(responseService.errorResponse(500, "response.internalServerError", null), HttpStatus.INTERNAL_SERVER_ERROR);
    // }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseModel> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = messageSource.getMessage(error, Locale.getDefault());
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<ApiResponseModel>(responseService.errorResponse(100, "response.validationError", errors), HttpStatus.BAD_REQUEST);
    }



    // Handle CustomException
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponseModel> handleCustomException(CustomException ex) {
        return new ResponseEntity<ApiResponseModel>(responseService.errorResponse(101, ex.getCode(), ex.getArgs(), null), ex.getStatus());
    }






    // Handle Hibernate's ConstraintViolationException
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponseModel> handleConstraintViolationException(ConstraintViolationException ex) {
        String constraintName = ex.getConstraintName();
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("constraint", constraintName);
        errorDetails.put("message", "A database constraint was violated.");

        return new ResponseEntity<ApiResponseModel>(responseService.errorResponse("response.internalServerError", errorDetails), HttpStatus.INTERNAL_SERVER_ERROR);
    }







    // Handle Spring's DataIntegrityViolationException
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponseModel> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("message", "Data integrity violation occurred.");
        errorDetails.put("error", ex.getMostSpecificCause().getMessage());

        return new ResponseEntity<ApiResponseModel>(responseService.errorResponse("response.internalServerError", errorDetails), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    // Handle JPA's Exception
    @ExceptionHandler(JpaSystemException.class)
    public ResponseEntity<ApiResponseModel> handleJPAException(JpaSystemException ex) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("message", "Data integrity violation occurred.");
        errorDetails.put("error", ex.getMostSpecificCause().getMessage());

        return new ResponseEntity<ApiResponseModel>(responseService.errorResponse("response.internalServerError", errorDetails), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(NotImplemented.class)
    public ResponseEntity<ApiResponseModel> handleNotImplementedException(NotImplemented exception) {
        return new ResponseEntity<ApiResponseModel>(responseService.errorResponse(404, "response.notFound", null), HttpStatus.NOT_FOUND);
    }

    // @ExceptionHandler(Exception.class)
    // public ResponseEntity<ApiResponseModel> handleException(Exception exception) {
    //     return new ResponseEntity<ApiResponseModel>(responseService.errorResponse(500, "response.internalServerError", null), HttpStatus.INTERNAL_SERVER_ERROR);
    // }
}
