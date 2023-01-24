package com.drzewek.wfrp_npc_generator.service;

import com.drzewek.wfrp_npc_generator.model.Name;
import com.drzewek.wfrp_npc_generator.repository.NameRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UtilService {

    private final NameRepository nameRepository;


    @PostConstruct
    void run() {
        //Generating list of names -> you can even save it as json file and import to app with every start
        List<Name> names = List.of(new Name("Human", 1, "Ja"), new Name("Human", 1, "Je"), new Name("Human", 1, "Jo"), new Name("Human", 1, "Ji"), new Name("Human", 1, "Jai"), new Name("Human", 1, "Hem"), new Name("Human", 1, "Mo"), new Name("Human", 1, "De"),
                new Name("Human", 2, "Rem"), new Name("Human", 2, "Ko"), new Name("Human", 2, "Jo"), new Name("Human", 2, "Sem"), new Name("Human", 2, "Mik"),
                new Name("Elf", 1, "El"), new Name("Elf", 1, "Ko"), new Name("Elf", 1, "Joh"),
                new Name("Elf", 2, "Ri"), new Name("Elf", 2, "Re"), new Name("Elf", 2, "Ro"),
                new Name("Elf", 3, "Ko"), new Name("Elf", 3, "Ke"), new Name("Elf", 3, "Ky"));
        //Saving to the repo
        List<Name> namesList = nameRepository.saveAll(names);
        //Getting list of names only for Human race
        List<Name> humanList = namesList.stream().filter(name -> name.getRaceName().equals("Human")).collect(Collectors.toList());
        //Geting list of first prefix names for human
        List<Name> humanNameFirstPrefix = humanList.stream().filter(name -> name.getPrefix() == 1).collect(Collectors.toList());
        log.info("List of human name first prefix: " + humanNameFirstPrefix);
        //Geting list of second prefix names for human
        List<Name> humanNameSecondPrefix = humanList.stream().filter(name -> name.getPrefix() == 2).collect(Collectors.toList());
        log.info("List of human name second prefix: " + humanNameSecondPrefix);
        log.info("Generating random human name");
        //Random name
        Random random = new Random();
        String randomName = humanNameFirstPrefix.get(random.nextInt(humanNameFirstPrefix.size())).getPrefixValue() + " - "
                + humanNameSecondPrefix.get(random.nextInt(humanNameSecondPrefix.size())).getPrefixValue();
        log.info("Random human name: " + randomName);
    }
}
