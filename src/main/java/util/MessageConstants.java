package util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MessageConstants {
    OK("Request successfully fulfilled"),
    
    ERR_BAD_REQUEST("Failed to fulfill the request: field constraints violated."),
    ERR_NOT_FOUND("Failed to fulfill the request: entity not found.");

    private String message;
}
