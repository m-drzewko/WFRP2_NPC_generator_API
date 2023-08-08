package com.drzewek.wfrp_npc_generator.model.response;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PageableResponseObject<T> extends ResponseObject<T>{

    private int pages;

    public PageableResponseObject(HttpStatus statusCode, String message, T object, int pages) {
        super(statusCode, message, object);
        this.pages = pages;
    }
}
