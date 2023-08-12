package com.drzewek.wfrp_npc_generator.service;

import com.drzewek.wfrp_npc_generator.mapper.NpcDtoMapper;
import com.drzewek.wfrp_npc_generator.model.*;
import com.drzewek.wfrp_npc_generator.model.entity.Npc;
import com.drzewek.wfrp_npc_generator.model.entity.Token;
import com.drzewek.wfrp_npc_generator.model.entity.User;
import com.drzewek.wfrp_npc_generator.model.response.PageableResponseObject;
import com.drzewek.wfrp_npc_generator.model.response.ResponseObject;
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

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    private final NpcDtoMapper npcDtoMapper;
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final PasswordEncoder encoder;
    private final EmailSenderService emailService;
    private final JwtService jwtService;
    private final NpcService npcService;

    private ResourceBundle applicationMessages = ResourceBundle.getBundle("ApplicationMessages");

    public UserService(UserRepository userRepository, TokenService tokenService,
                       @Lazy PasswordEncoder encoder, EmailSenderService emailService,
                       JwtService jwtService, NpcDtoMapper npcDtoMapper, NpcService npcService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.encoder = encoder;
        this.emailService = emailService;
        this.jwtService = jwtService;
        this.npcDtoMapper = npcDtoMapper;
        this.npcService = npcService;
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

    public User decodeUserFromJwt(HttpServletRequest request) {
        String username = jwtService.decodeUsername(request.getHeader("Authorization"));
        return userRepository.findByUsername(username).orElseThrow(
                () -> new NoSuchElementException(
                        applicationMessages.getString("error.user.no-such-user")));
    }

    @Transactional
    public ResponseObject<Void> saveNpc(NpcDto npc, HttpServletRequest request) {
        User user = decodeUserFromJwt(request);
        Npc newNpc = npcDtoMapper.dtoToNpc(npc);
        user.getSavedNpcs().add(newNpc);
        newNpc.setUser(user);
        userRepository.save(user);
        return new ResponseObject<>(HttpStatus.ACCEPTED, "Npc " + newNpc.getName() + " saved for user " + user.getUsername(), null);
    }

    public ResponseObject<List<NpcDto>> getAllSavedNpcs(HttpServletRequest request, int page) {
        User user = decodeUserFromJwt(request);

        PageableResponseObject<List<NpcDto>> responseObject = npcService.getNpcPage(user.getId(), page);

        responseObject.setMessage("Returning page " + page + " of NPCs for user " + user.getUsername());

        return responseObject;
    }
}
