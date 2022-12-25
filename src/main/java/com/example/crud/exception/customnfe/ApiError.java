//package com.example.crud.exception;
//
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.http.HttpStatus;
//import java.util.Arrays;
//import java.util.List;
//
//@Getter
//@Setter
//public class ApiError {
//    private HttpStatus status;
//    private String message;
//    private List<ErrorDetails> errors;
//    public ApiError() {
//        super();
//    }
//    public ApiError(final HttpStatus status, final String message, final List<ErrorDetails> errors) {
//        super();
//        this.status = status;
//        this.message = message;
//        this.errors = errors;
//    }
//
//    public ApiError(final HttpStatus status, final String message, final ErrorDetails error) {
//        super();
//        this.status = status;
//        this.message = message;
//        errors = Arrays.asList(error);
//    }
//}
