package com.drzewek.wfrp_npc_generator.service;

import com.drzewek.wfrp_npc_generator.mapper.NpcDtoMapper;
import com.drzewek.wfrp_npc_generator.model.Gender;
import com.drzewek.wfrp_npc_generator.model.NpcDto;
import com.drzewek.wfrp_npc_generator.model.entity.Npc;
import com.drzewek.wfrp_npc_generator.model.entity.Race;
import com.drzewek.wfrp_npc_generator.model.response.PageableResponseObject;
import com.drzewek.wfrp_npc_generator.model.response.ResponseObject;
import com.drzewek.wfrp_npc_generator.repository.NpcRepository;
import com.drzewek.wfrp_npc_generator.repository.RaceRepository;
import com.drzewek.wfrp_npc_generator.utility.NpcUtility;
import com.drzewek.wfrp_npc_generator.utility.RaceUtility;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;

@Service
@AllArgsConstructor
public class NpcService {

    private final RaceRepository raceRepository;
    private final NpcRepository npcRepository;
    private final ResourceBundle applicationMessages = ResourceBundle.getBundle("ApplicationMessages");
    private static final String RANDOM = "random";
    private RaceUtility raceUtility;
    private final NpcDtoMapper npcDtoMapper;
    private final int pageSize = 5;

    public NpcDto generateNpc(String lang, String raceName, String gender) {

        Npc generatedNpc;

        if (raceName.equals(RANDOM) && gender.equals(RANDOM)) {
            generatedNpc = NpcUtility.generateNpc(lang, null, raceUtility.generateRace());
        } else if (raceName.equals(RANDOM)) {
            if (gender.equals("male")) {
                generatedNpc = NpcUtility.generateNpc(lang, Gender.MALE, raceUtility.generateRace());
            } else if (gender.equals("female")) {
                generatedNpc = NpcUtility.generateNpc(lang, Gender.FEMALE, raceUtility.generateRace());
            } else throw new EntityNotFoundException("Gender " + gender + " does not exist!");
        } else if (gender.equals(RANDOM)) {
            String raceToFind = raceName.toLowerCase();
            raceToFind = StringUtils.capitalize(raceToFind);
            Race raceToAssign = raceRepository.findByName(raceToFind)
                    .orElseThrow(() -> new EntityNotFoundException("Race " + raceName + " does not exist!"));
            generatedNpc = NpcUtility.generateNpc(lang, null, raceToAssign);
        } else {
            String raceToFind = raceName.toLowerCase();
            raceToFind = StringUtils.capitalize(raceToFind);
            Race raceToAssign = raceRepository.findByName(raceToFind)
                    .orElseThrow(() -> new EntityNotFoundException("Race " + raceName + " does not exist!"));
            if (gender.equals("male")) {
                generatedNpc = NpcUtility.generateNpc(lang, Gender.MALE, raceToAssign);
            } else if (gender.equals("female")) {
                generatedNpc = NpcUtility.generateNpc(lang, Gender.FEMALE, raceToAssign);
            } else throw new EntityNotFoundException("Gender " + gender + " does not exist!");
        }

        return npcDtoMapper.npcToDto(generatedNpc);
    }

    public ResponseObject<NpcDto> updateSavedNpc(Long id, NpcDto update) {
        Npc updateNpc = npcDtoMapper.dtoToNpc(update);
        NpcDto updatedNpcDto = npcDtoMapper.npcToDto(updateNpc(id, updateNpc));
        return new ResponseObject<>(HttpStatus.OK, "Npc " + updatedNpcDto.getId() + " updated", updatedNpcDto);
    }

    public Npc updateNpc(Long id, Npc update) {
        Npc toUpdate = npcRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException(applicationMessages.getString("error.npc.no-such-npc")));

        toUpdate.setName(update.getName());
        toUpdate.setGender(update.getGender());
        toUpdate.setRace(update.getRace());
        toUpdate.setHeight(update.getHeight());
        toUpdate.setWeight(update.getWeight());
        toUpdate.setAge(update.getAge());
        toUpdate.setHairColor(update.getHairColor());
        toUpdate.setEyeColor(update.getEyeColor());
        toUpdate.setWeaponSkill(update.getWeaponSkill());
        toUpdate.setBallisticSkill(update.getBallisticSkill());
        toUpdate.setStrength(update.getStrength());
        toUpdate.setToughness(update.getToughness());
        toUpdate.setAgility(update.getAgility());
        toUpdate.setIntelligence(update.getIntelligence());
        toUpdate.setWillPower(update.getWillPower());
        toUpdate.setFellowship(update.getFellowship());
        toUpdate.setWounds(update.getWounds());
        toUpdate.setStrengthBonus(update.getStrengthBonus());
        toUpdate.setToughnessBonus(update.getToughnessBonus());
        toUpdate.setMovement(update.getMovement());

        return npcRepository.save(toUpdate);
    }

    public ResponseObject<Void> deleteSavedNpc(Long id) {
        npcRepository.deleteById(id);
        return new ResponseObject<>(HttpStatus.NO_CONTENT, "Deleted npc " + id, null);
    }

    public PageableResponseObject<List<NpcDto>> getNpcPage(Long userId, int page) {
        PageRequest request = PageRequest.of(page, pageSize);

        Page<Npc> npcPage = npcRepository.findByUserId(userId, request);

        return new PageableResponseObject<>(HttpStatus.OK,
                "",
                npcPage.map(npcDtoMapper::npcToDto).toList(), npcPage.getTotalPages());
    }
}
