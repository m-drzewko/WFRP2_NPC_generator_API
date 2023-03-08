package com.drzewek.wfrp_npc_generator.service;

import com.drzewek.wfrp_npc_generator.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
}
