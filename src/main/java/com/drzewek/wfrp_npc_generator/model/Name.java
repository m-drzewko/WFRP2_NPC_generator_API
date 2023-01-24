package com.drzewek.wfrp_npc_generator.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Name {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String raceName;
    private int prefix;
    private String prefixValue;

    public Name(String raceName, int prefix, String prefixValue) {
        this.raceName = raceName;
        this.prefix = prefix;
        this.prefixValue = prefixValue;
    }
}
