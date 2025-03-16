package com.quiz.config;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                ServerHttpRequest request, ServerHttpResponse response) {
        
        // Si c'est déjà un ResponseEntity, on le retourne tel quel
        if (body instanceof ResponseEntity) {
            return body;
        }

        // Si c'est void ou null, on retourne un succès sans contenu
        if (body == null) {
            response.setStatusCode(HttpStatus.NO_CONTENT);
            return null;
        }

        // Pour tout autre cas, on enveloppe dans un ApiResponse
        return new ApiResponse<>(HttpStatus.OK.value(), "Success", body);
    }
} 