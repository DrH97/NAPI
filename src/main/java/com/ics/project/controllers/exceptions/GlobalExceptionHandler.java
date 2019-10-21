package com.ics.project.controllers.exceptions;

import com.ics.project.controllers.errors.ApiError;
import com.ics.project.controllers.errors.MovieApiError;
import com.ics.project.utils.Utils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import java.util.Collections;
import java.util.List;

/**
 * Catch all errors that are thrown by the API at runtime and handle them accordingly
 *
 * @author Dr H
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ResourceNotFoundException.class, UserNotFoundException.class, ResourceExistsException.class, UserExistsException.class})
    public final ResponseEntity<ApiError> handleException(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();

        Utils.print(ex.toString());

        if (ex instanceof UserNotFoundException) {

            HttpStatus status = HttpStatus.NOT_FOUND;
            UserNotFoundException unfe = (UserNotFoundException) ex;

            return handleUserNotFoundException(unfe, headers, status, request);
        } else if (ex instanceof ResourceNotFoundException) {

            HttpStatus status = HttpStatus.NOT_FOUND;
            ResourceNotFoundException rnfe = (ResourceNotFoundException) ex;

            return handleResourceNotFoundException(rnfe, headers, status, request);
        } else if (ex instanceof ResourceExistsException) {

            HttpStatus status = HttpStatus.FOUND;
            ResourceExistsException ree = (ResourceExistsException) ex;

            return handleResourceExistsException(ree, headers, status, request);
        } else if (ex instanceof UserExistsException) {

            HttpStatus status = HttpStatus.FOUND;
            UserExistsException uee = (UserExistsException) ex;

            return handleUserExistsException(uee, headers, status, request);
        } else {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleExceptionInternal(ex, null, headers, status, request);
        }
    }

    private ResponseEntity<ApiError> handleResourceExistsException(ResourceExistsException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionInternal(ex, new MovieApiError(errors, ex.getMovie()), headers, status, request);
    }

    private ResponseEntity<ApiError> handleUserExistsException(UserExistsException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionInternal(ex, new ApiError(errors), headers, status, request);
    }

    private ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionInternal(ex, new ApiError(errors), headers, status, request);
    }

    protected ResponseEntity<ApiError> handleUserNotFoundException(UserNotFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionInternal(ex, new ApiError(errors), headers, status, request);
    }

    protected ResponseEntity<ApiError> handleExceptionInternal(Exception ex, ApiError body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        return new ResponseEntity<>(body, headers, status);
    }

}
