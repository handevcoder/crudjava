//package com.example.crud.exception;
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.WebRequest;
//
//import java.util.Date;
//
//@ControllerAdvice
//public class GlobalEH {
//    @ExceptionHandler(ResourceNFE.class)
//    public ResponseEntity<?> resourceNFE(ResourceNFE ex, WebRequest request) {
//        ErrorDetails errorMsg = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
//
//        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), errorMsg);
//
//        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<?> globalEH(Exception ex, WebRequest request) {
//        ErrorDetails errorMsg = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
//
//        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), errorMsg);
//        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
//    }
//}
