package com.drzewek.wfrp_npc_generator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RegistrationDto {

    private String username;
    private String email;
    private String password;
}
