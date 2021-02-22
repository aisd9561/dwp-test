package dwp.techtest.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientResponseException;


@ControllerAdvice
public class AppExceptionsHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleOtherExceptions(Exception ex) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorMessages.INTERNAL_SERVER_ERROR, ex);
        return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RestClientResponseException.class)
    public ResponseEntity handleHttpStatusCodeException(RestClientResponseException e) {
        System.out.println(e);
        ApiError apiError = new ApiError(e.getRawStatusCode(), ErrorMessages.USER_SERVICE_ERROR,e.getResponseBodyAsString());
        return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.resolve(e.getRawStatusCode()));
    }



}