package dev.antozy.questapp.controllers;

import dev.antozy.questapp.entities.User;
import dev.antozy.questapp.requests.UserRequest;
import dev.antozy.questapp.responses.AuthResponse;
import dev.antozy.questapp.security.JwtTokenProvider;
import dev.antozy.questapp.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LogManager.getLogger(AuthController.class);

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody UserRequest loginRequest) {
        logger.info("Login method started.");
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userService.getUserByUserName(loginRequest.getUserName());
        AuthResponse authResponse = new AuthResponse();
        authResponse.setMessage("Bearer " + jwtTokenProvider.generateJwtToken(authentication));
        authResponse.setUserId(user.getId());
        logger.info("Login method finished.");
        return authResponse;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserRequest userRequest) {
        logger.info("Register method started.");
        AuthResponse authResponse = new AuthResponse();
        if (userService.getUserByUserName(userRequest.getUserName()) != null) {
            authResponse.setMessage("Username already in use.");
            return new ResponseEntity<>(authResponse, HttpStatus.BAD_REQUEST);
        } else {
            User user = new User();
            user.setUserName(userRequest.getUserName());
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            userService.saveOneUser(user);
            authResponse.setMessage("User successfully created.");
            authResponse.setUserId(user.getId());
            logger.info("Register method finished.");
            return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
        }
    }

}
