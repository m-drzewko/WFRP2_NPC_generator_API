package com.drzewek.wfrp_npc_generator.mapper;

import com.drzewek.wfrp_npc_generator.model.Gender;
import com.drzewek.wfrp_npc_generator.model.NpcDto;
import com.drzewek.wfrp_npc_generator.model.entity.Npc;
import com.drzewek.wfrp_npc_generator.service.RaceService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public abstract class NpcDtoMapper {

    @Autowired
    private RaceService raceService;

    @Mapping(target = "raceName", source = "race.name")
    public abstract NpcDto npcToDto (Npc npc);

    public Npc dtoToNpc (NpcDto dto) {
        return Npc.builder()
                .name(dto.getName())
                .gender(Gender.valueOf(dto.getGender()))
                .race(raceService.getRaceByName(dto.getName()))
                .height(dto.getHeight())
                .weight(dto.getWeight())
                .age(dto.getAge())
                .hairColor(dto.getHairColor())
                .eyeColor(dto.getEyeColor())
                .weaponSkill(dto.getWeaponSkill())
                .ballisticSkill(dto.getBallisticSkill())
                .strength(dto.getStrength())
                .toughness(dto.getToughness())
                .agility(dto.getAgility())
                .intelligence(dto.getIntelligence())
                .willPower(dto.getWillPower())
                .fellowship(dto.getFellowship())
                .wounds(dto.getWounds())
                .movement(dto.getMovement())
                .build();
    }
}
