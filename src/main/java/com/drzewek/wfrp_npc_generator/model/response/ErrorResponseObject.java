package com.drzewek.wfrp_npc_generator.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseObject {

    private HttpStatus statusCode;

    private String message;

    private LocalDateTime date;
}
