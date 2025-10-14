package com.makara.phoneshop.exception;

import com.makara.phoneshop.baseApi.ErrorApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleApiException(ApiException e){
        ErrorResponse errorResponse = new ErrorResponse(e.getStatus(), e.getMessage());
        return ResponseEntity
                .status(e.getStatus())
                .body(errorResponse);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ResponseStatusException.class)
    public ErrorApi<?> handleServiceException(Exception e){
        Map<String, Object> body = new HashMap<>();
        return ErrorApi.builder()
                .status(false)
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Something went wrong, please check in error detail!")
                .timestamp(LocalDateTime.now())
                .errors(body)
                .build();

    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorApi<?> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException e){
        String messageError = e.getMessage();
        String unrecognizedValue = extractUnrecognizedValueException(messageError);
        String  acceptValue = extractAcceptedValueException(messageError);

        //Building customized response message
        String customMessage = String.format("Enum %s Not found %s",
                unrecognizedValue,acceptValue);

        Map<String ,String> erros = new HashMap<>();
        erros.put("status","NOT_FOUND");
        erros.put("message",customMessage);
        return ErrorApi.builder()
                .status(false)
                .code(HttpStatus.NOT_FOUND.value())
                .message("Something went wrong, please check in error detail!")
                .timestamp(LocalDateTime.now())
                .errors(erros)
                .build();
    }
    private String extractUnrecognizedValueException(String message){
        Pattern pattern = Pattern.compile("from string \"(.*?)\"");
        Matcher matcher = pattern.matcher(message);
        if(matcher.find()){
            return matcher.group();
        }
        return "UnKnow";
    }
    private String extractAcceptedValueException(String  message){
        Pattern pattern = Pattern.compile("accepted Enum class: \"(.*?)\"");
        Matcher matcher = pattern.matcher(message);

        if(matcher.find()){
            return matcher.group();
        }
        return "[]";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ErrorApi<?> handleValidationException(MethodArgumentNotValidException ex){

        List<Map<String, String>> errors =  new ArrayList<>();
        
        for(FieldError fieldError : ex.getFieldErrors()){
            Map<String, String> error = new HashMap<>();
            error.put("field", fieldError.getField());
            error.put("detail", fieldError.getDefaultMessage());
            errors.add(error);
        }
        
        return ErrorApi.builder()
                        .status(false)
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message("Something went wrong, please check in error detail!")
                        .timestamp(LocalDateTime.now())
                        .errors(errors)
                        .build();
    }

}
