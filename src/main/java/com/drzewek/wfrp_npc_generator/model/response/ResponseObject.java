package com.drzewek.wfrp_npc_generator.model.response;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseObject<T> {

    protected HttpStatus statusCode;

    protected String message;

    protected T object;
}
