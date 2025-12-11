package com.umc.sistemaonganimal.api.exceptionhandler;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiError {
    private LocalDateTime data;
    private String mensagem;
}
