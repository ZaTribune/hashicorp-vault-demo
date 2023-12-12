package com.tribune.demo.vault.error;


import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.JDBCException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;


@Slf4j
@RestControllerAdvice
public class MyAdvice {


    @ExceptionHandler(value = JDBCException.class)
    public ObjectNode handleException(JDBCException e, HttpServletResponse response) {
        log.error("JDBC Exception: {}", e.getMessage());
        response.setStatus(HttpStatus.BAD_REQUEST.value());


        String reason = "Database Error";
        String[] attrs = e.getSQLException().getMessage().split(":");
        if (attrs.length > 1) {
            reason = attrs[0];
        }

        return buildResponse(844, "Database Error", reason);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ObjectNode handleException(MethodArgumentNotValidException e, HttpServletResponse response) {
        log.error("INPUT Exception: {}", e.getMessage());
        response.setStatus(HttpStatus.BAD_REQUEST.value());


        String[] reasons = e.getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
                .toArray(String[]::new);

        return buildResponse(744, "Validation Error", reasons);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ObjectNode handleException(HttpMessageNotReadableException e, HttpServletResponse response) {
        log.error("INPUT Exception: {}", e.getMessage());
        response.setStatus(HttpStatus.BAD_REQUEST.value());

        String reason = "Input Error";
        String[] attrs = e.getMostSpecificCause().getMessage().split(":");
        if (attrs.length > 1) {
            reason = attrs[0];
        }


        return buildResponse(744, "Input Error", reason);
    }


    public ObjectNode buildResponse(Integer code, String message, String... reason) {
        ObjectNode objectNode = new ObjectNode(JsonNodeFactory.instance);
        objectNode.set("code", TextNode.valueOf(String.valueOf(code)));
        objectNode.set("message", TextNode.valueOf(message));

        ArrayNode arrayNode = new ArrayNode(JsonNodeFactory.instance);
        for (String r : reason) {
            arrayNode.add(r);
        }
        objectNode.set("reason", arrayNode);

        return objectNode;
    }
}
