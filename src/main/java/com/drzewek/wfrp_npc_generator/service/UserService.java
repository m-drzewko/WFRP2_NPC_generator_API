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
import java.util.ResourceBundle;
import java.util.UUID;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final TokenService tokenService;

    private final PasswordEncoder encoder;

    private ResourceBundle applicationMessages = ResourceBundle.getBundle("ApplicationMessages");

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
        User foundUser;

        if (username.contains("@")) {
            foundUser = userRepository.findByEmail(username)
                    .orElseThrow(() -> new EntityNotFoundException(applicationMessages.getString("error.user.no-such-user")));
        } else {
            foundUser = userRepository.findByUsername(username)
                    .orElseThrow(() -> new EntityNotFoundException(applicationMessages.getString("error.user.no-such-user")));
        }

        if (!foundUser.isConfirmed()) {
            throw new EntityNotFoundException(applicationMessages.getString("error.user.not-verified"));
        }

        return new MyUserPrincipal(foundUser);
    }

    @Transactional
    public ResponseObject<Token> registerNewUser(RegistrationDto dto) throws EntityExistsException {
        if (userRepository.existsByUsernameOrEmail(dto.getUsername(), dto.getEmail())) {
            throw new EntityExistsException(applicationMessages.getString("error.user.already-exists"));
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
            if (savedUser == null) {
                return null;
            } else {
                return new ResponseObject<>(HttpStatus.CREATED, applicationMessages.getString("user.registered"), verificationToken);
            }
        }
    }

    @Transactional
    public ResponseObject<Object> verifyUser(String token) throws SecurityException {

        Token checkToken = tokenService.findToken(token);
        User userToVerify = userRepository.findByEmail(checkToken.getEmail())
                .orElseThrow(() -> new EntityNotFoundException(applicationMessages.getString("error.user.no-such-user")));

        if (userToVerify.isConfirmed()) {
            throw new SecurityException(applicationMessages.getString("error.user.already-confirmed"));
        }

        tokenService.validateToken(checkToken, TokenType.VERIFY_ACCOUNT);

        checkToken.setUsed(true);
        userToVerify.setConfirmed(true);

        tokenService.save(checkToken);
        saveUser(userToVerify);

        return new ResponseObject<>(HttpStatus.ACCEPTED,
                applicationMessages.getString("user.verified"), null);
    }
}
