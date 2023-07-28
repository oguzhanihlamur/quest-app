package dev.antozy.questapp.responses;

import lombok.Data;

@Data
public class AuthResponse {
    String message;
    Long userId;
}
