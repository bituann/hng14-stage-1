package com.bituan.hng14_stage_1.exception;

import com.bituan.hng14_stage_1.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BadGateway.class)
    public ResponseEntity<ErrorResponse> handleBadGatewayException (BadGateway ex) {
        ErrorResponse response = new ErrorResponse("error", ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException (BadRequest ex) {
        ErrorResponse response = new ErrorResponse("error", ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExternalApiException.class)
    public ResponseEntity<ErrorResponse> handleExternalApiException (ExternalApiException ex) {
        ErrorResponse response = new ErrorResponse("" + ex.getCode(), ex.getMessage());

        return new ResponseEntity<>(response, HttpStatusCode.valueOf(ex.getCode()));
    }

    @ExceptionHandler(NotFound.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException (NotFound ex) {
        ErrorResponse response = new ErrorResponse("error", ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ServerException.class)
    public ResponseEntity<ErrorResponse> handleServerException (ServerException ex) {
        ErrorResponse response = new ErrorResponse("error", ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnprocessableEntity.class)
    public ResponseEntity<ErrorResponse> handleUnprocessableEntityException (UnprocessableEntity ex) {
        ErrorResponse response = new ErrorResponse("error", ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
