package com.example.fxdealswarehouse.util;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Response<T> {
    private String message;
    private T result;
    private List<T> errors;
    private Map<String, String> errorMap;
    private String error;

    public List<T> getErrors() {
        return errors;
    }

    public void setErrors(List<T> errors) {
        this.errors = errors;
    }

    public Map<String, String> getErrorMap() {
        return errorMap;
    }

    public void setErrorMap(Map<String, String> errorMap) {
        this.errorMap = errorMap;
    }
}