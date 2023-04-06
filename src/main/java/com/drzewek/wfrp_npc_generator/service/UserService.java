package com.drzewek.wfrp_npc_generator.service;

import com.drzewek.wfrp_npc_generator.model.Role;
import com.drzewek.wfrp_npc_generator.model.TokenType;
import com.drzewek.wfrp_npc_generator.model.entity.Token;
import com.drzewek.wfrp_npc_generator.model.entity.User;
import com.drzewek.wfrp_npc_generator.repository.TokenRepository;
import com.drzewek.wfrp_npc_generator.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final TokenRepository tokenRepository;


    private final PasswordEncoder encoder;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    @Transactional
    public User registerNewUser(User user) {
        if (userRepository.existsByUsernameOrEmail(user.getUsername(), user.getEmail())) {
            throw new EntityExistsException("There already exists a user with this username and email!");
        } else {
            user.addRole(Role.USER);
            user.setPassword(encoder.encode(user.getPassword()));
            String token = UUID.randomUUID().toString();
            Token verificationToken = new Token(token, TokenType.VERIFY_ACCOUNT, user.getEmail());
            log.info("verification token = " + verificationToken.getToken());
            tokenRepository.save(verificationToken);
            //TODO change return type to ResponseObject to use on the frontend
            return saveUser(user);
        }
    }
}
