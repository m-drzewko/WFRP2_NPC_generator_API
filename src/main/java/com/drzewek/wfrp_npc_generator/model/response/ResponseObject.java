package com.drzewek.wfrp_npc_generator.model.response;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ResponseObject<T> {

    private HttpStatus statusCode;

    private String message;

    private T object;
}
