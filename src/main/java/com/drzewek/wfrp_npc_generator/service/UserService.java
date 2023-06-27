package com.drzewek.wfrp_npc_generator.service;

import com.drzewek.wfrp_npc_generator.mapper.NpcDtoMapper;
import com.drzewek.wfrp_npc_generator.mapper.NpcDtoMapperImpl;
import com.drzewek.wfrp_npc_generator.model.*;
import com.drzewek.wfrp_npc_generator.model.entity.Npc;
import com.drzewek.wfrp_npc_generator.model.entity.Token;
import com.drzewek.wfrp_npc_generator.model.entity.User;
import com.drzewek.wfrp_npc_generator.model.response.ResponseObject;
import com.drzewek.wfrp_npc_generator.repository.NpcRepository;
import com.drzewek.wfrp_npc_generator.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
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
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.UUID;

@Service
@Slf4j
public class UserService implements UserDetailsService {
    private final NpcRepository npcRepository;
    private final NpcDtoMapper npcDtoMapper;

    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final PasswordEncoder encoder;
    private final EmailSenderService emailService;
    private final JwtService jwtService;

    private ResourceBundle applicationMessages = ResourceBundle.getBundle("ApplicationMessages");

    public UserService(UserRepository userRepository, TokenService tokenService, @Lazy PasswordEncoder encoder, EmailSenderService emailService, JwtService jwtService,
                       NpcDtoMapper npcDtoMapper,
                       NpcRepository npcRepository) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.encoder = encoder;
        this.emailService = emailService;
        this.jwtService = jwtService;
        this.npcDtoMapper = npcDtoMapper;
        this.npcRepository = npcRepository;
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
    public ResponseObject<Token> registerNewUser(RegistrationDto dto, String language) throws EntityExistsException {
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
            log.info("User: " + newUser.getUsername() + "\n" + "Verification token = " + verificationToken.getToken());
            tokenService.save(verificationToken);

            User savedUser = saveUser(newUser);

            if (savedUser == null) {
                throw new UnsupportedOperationException(applicationMessages.getString("error.general"));
            } else {
                emailService.sendVerificationEmail(savedUser.getUsername(), savedUser.getEmail(), verificationToken.getToken(), language);
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
        userToVerify.addRole(Role.CONFIRMED_USER);

        tokenService.save(checkToken);
        saveUser(userToVerify);

        return new ResponseObject<>(HttpStatus.ACCEPTED,
                applicationMessages.getString("user.verified"), null);
    }

    @Transactional
    public ResponseObject<String> saveNpc(NpcDto npc, HttpServletRequest request) {
        String username = jwtService.decodeUsername(request.getHeader("Authorization"));
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new NoSuchElementException(applicationMessages
                        .getString("error.user.no-such-user")));
        Npc newNpc = npcDtoMapper.dtoToNpc(npc);
        user.getSavedNpcs().add(newNpc);
        userRepository.save(user);
        return new ResponseObject<>(HttpStatus.ACCEPTED, "Npc saved for user " + user.getUsername(), "");
    }
}
