package com.drzewek.wfrp_npc_generator.model.response;

import lombok.*;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponseObject {

    private HttpStatus statusCode;

    private String message;

    private LocalDateTime date;
}
