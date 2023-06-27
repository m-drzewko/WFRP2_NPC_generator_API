package com.drzewek.wfrp_npc_generator.controller;

import com.drzewek.wfrp_npc_generator.model.RegistrationDto;
import com.drzewek.wfrp_npc_generator.model.entity.Token;
import com.drzewek.wfrp_npc_generator.model.response.ResponseObject;
import com.drzewek.wfrp_npc_generator.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

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
}
