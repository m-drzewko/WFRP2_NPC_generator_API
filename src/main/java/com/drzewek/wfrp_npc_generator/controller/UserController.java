package com.drzewek.wfrp_npc_generator.controller;

import com.drzewek.wfrp_npc_generator.model.NpcDto;
import com.drzewek.wfrp_npc_generator.model.RegistrationDto;
import com.drzewek.wfrp_npc_generator.model.entity.Token;
import com.drzewek.wfrp_npc_generator.model.response.ResponseObject;
import com.drzewek.wfrp_npc_generator.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseObject<Token> registerNewUser(@RequestBody RegistrationDto dto,
                                                 @RequestHeader (HttpHeaders.ACCEPT_LANGUAGE) String language) {
        return userService.registerNewUser(dto, language);
    }

    @PatchMapping("/verify")
    public ResponseObject<Object> verifyUser(@RequestParam String token) {
        return userService.verifyUser(token);
    }

    @PostMapping("/auth/npc/save")
    public ResponseObject<Void> saveNpc(@RequestBody NpcDto npc, HttpServletRequest request) {
        return userService.saveNpc(npc, request);
    }

    @GetMapping("/auth/npc/getall")
    public ResponseObject<List<NpcDto>> getAllSavedNpcs(HttpServletRequest request, @RequestParam(defaultValue = "0") int page) {
        return userService.getAllSavedNpcs(request, page);
    }
}
