package com.example.fxdealswarehouse.exception;

import com.example.fxdealswarehouse.util.ErrorMessage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ValidationException extends RuntimeException {
    private List<ErrorMessage> errorMessages;

    public ValidationException(List<ErrorMessage> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public List<ErrorMessage> getErrorMessages() {
        return errorMessages;
    }
}
