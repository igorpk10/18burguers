package br.com.eighteenburguers.configuration;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class HttpExceptionHandler extends ResponseEntityExceptionHandler {
    
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
            HttpStatusCode statusCode, WebRequest request) {

        ErrorResponses response = new ErrorResponses();

        if(ex.getClass().equals(MethodArgumentNotValidException.class)) {
            MethodArgumentNotValidException e = (MethodArgumentNotValidException) ex;

            for(ObjectError error: e.getAllErrors()) {
                String code = error.getCodes()[0];
                String field = Stream.of(code.split("\\.")).skip(2).collect(Collectors.joining("."));

                ErrorResponse err = new ErrorResponse("INVREQ", String.format("%s: %s", field, error.getDefaultMessage()));
                response.add(err);
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
 
    }
}
