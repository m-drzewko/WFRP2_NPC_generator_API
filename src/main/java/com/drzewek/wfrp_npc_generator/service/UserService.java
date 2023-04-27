package com.drzewek.wfrp_npc_generator.service;

import com.drzewek.wfrp_npc_generator.model.MyUserPrincipal;
import com.drzewek.wfrp_npc_generator.model.RegistrationDto;
import com.drzewek.wfrp_npc_generator.model.Role;
import com.drzewek.wfrp_npc_generator.model.TokenType;
import com.drzewek.wfrp_npc_generator.model.entity.Token;
import com.drzewek.wfrp_npc_generator.model.entity.User;
import com.drzewek.wfrp_npc_generator.model.response.ResponseObject;
import com.drzewek.wfrp_npc_generator.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.UUID;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final TokenService tokenService;

    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, TokenService tokenService, @Lazy PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.encoder = encoder;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User foundUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("No such user exists!"));

        if (!foundUser.isConfirmed()) {
            throw new EntityNotFoundException("User not verified!");
        }

        return new MyUserPrincipal(foundUser);
    }

    @Transactional
    public ResponseObject<Token> registerNewUser(RegistrationDto dto) {
        if (userRepository.existsByUsernameOrEmail(dto.getUsername(), dto.getEmail())) {
            throw new EntityExistsException("There already exists a user with this username and email!");
        } else {
            User newUser = User.builder()
                    .email(dto.getEmail())
                    .username(dto.getUsername())
                    .password(encoder.encode(dto.getPassword()))
                    .roles(new HashSet<>())
                    .build();
            newUser.addRole(Role.USER);

            String token = UUID.randomUUID().toString();
            Token verificationToken = new Token(token, TokenType.VERIFY_ACCOUNT, newUser.getEmail());
            log.info("verification token = " + verificationToken.getToken());
            tokenService.save(verificationToken);

            User savedUser = saveUser(newUser);
            if (savedUser.equals(null)) {
                return null;
            } else {
                return new ResponseObject<>(HttpStatus.CREATED, "USER_REGISTERED", verificationToken);
            }
        }
    }

    @Transactional
    public ResponseObject<Object> verifyUser(String token) throws SecurityException {

        Token checkToken = tokenService.findToken(token);
        User userToVerify = userRepository.findByEmail(checkToken.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("NO SUCH USER EXISTS!"));

        if (userToVerify.isConfirmed()) {
            throw new SecurityException("USER ALREADY CONFIRMED!");
        }

        if (tokenService.isExpired(checkToken)) {
            throw new SecurityException("TOKEN EXPIRED!");
        }

        if (checkToken.getTokenType() != TokenType.VERIFY_ACCOUNT) {
            throw new SecurityException("INVALID TOKEN TYPE!");
        }

        if (checkToken.isUsed()) {
            throw new SecurityException("TOKEN ALREADY USED!");
        }

        checkToken.setUsed(true);
        userToVerify.setConfirmed(true);

        tokenService.save(checkToken);
        saveUser(userToVerify);

        return new ResponseObject<>(HttpStatus.ACCEPTED,
                "User verified", null);
    }
}
