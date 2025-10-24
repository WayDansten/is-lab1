package util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MessageConstants {
    OK("Request successfully fulfilled"),
    
    ERR_BAD_REQUEST("Failed to fulfill the request: field constraints violated."),
    ERR_NOT_FOUND("Failed to fulfill the request: entity not found."),
    ERR_PERSISTENCE("Failed to fulfill the request: names for Disciplines, People and Locations need to be unique.");

    private String message;
}
