package com.drzewek.wfrp_npc_generator.model;

import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {

    private String username;
    private String email;
    private Set<NpcDto> savedNpcs;
}
