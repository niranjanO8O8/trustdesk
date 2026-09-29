package com.trustdesk.exception;

import com.trustdesk.response.ApiErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiErrorResponse> handleBusinessException(BusinessException ex, HttpServletRequest request) {
        HttpStatus errorStatus = ex.getHttpErrorStatus();
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(errorStatus.value(), errorStatus.getReasonPhrase(),
                ex.getMessage(), request.getRequestURI());

        return new ResponseEntity<>(apiErrorResponse, errorStatus);
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ApiErrorResponse> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request)
//    {
//        HashMap<String, String> validationErrors = new HashMap<>();
//
//        ex.getBindingResult().getFieldErrors().forEach((fieldError) -> {
//            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
//        });
//
//        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
//                HttpStatus.BAD_REQUEST.value(),
//                "Validation failed",
//                "Validation failed for one or more fields",
//                request.getRequestURI(),
//                validationErrors
//        );
//
//        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
//    }
//
//    // Handle Disabled/Deactivated Accounts during login (403 Forbidden)
//    @ExceptionHandler(DisabledException.class)
//    public ResponseEntity<ApiErrorResponse> handleDisabledResponse(DisabledException ex, HttpServletRequest request)
//    {
//        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
//                HttpStatus.FORBIDDEN.value(),
//                HttpStatus.FORBIDDEN.getReasonPhrase(),
//                "Your account  has been deactivated. Please contact support to reactivate your account.",
//                request.getRequestURI()
//        );
//
//        return new ResponseEntity<>(apiErrorResponse, HttpStatus.FORBIDDEN);
//    }
//
//    //
//    @ExceptionHandler(BadCredentialsException.class)
//    public ResponseEntity<ApiErrorResponse> handleBadCredentialsException(BadCredentialsException ex, HttpServletRequest request)
//    {
//        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
//                HttpStatus.UNAUTHORIZED.value(),
//                HttpStatus.UNAUTHORIZED.getReasonPhrase(),
//                "Invalid email or password. Please check your credentials and try again.",
//                request.getRequestURI()
//        );
//
//        return new ResponseEntity<>(apiErrorResponse, HttpStatus.UNAUTHORIZED);
//    }
//
//    // Generic Exception Handler in case unhandled exception comes and should not fuck up our server
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ApiErrorResponse> handleException(Exception ex, HttpServletRequest request)
//    {
//        logger.error("An unexpected error occurred", ex);
//        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
//                HttpStatus.INTERNAL_SERVER_ERROR.value(),
//                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
//                "An unexpected error occurred. Please try again later.",
//                request.getRequestURI()
//        );
//
//        return new ResponseEntity<>(apiErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
}
