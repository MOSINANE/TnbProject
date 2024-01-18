package com.example.authservice.controller;

import com.example.authservice.dto.AuthenticationResponse;
import com.example.authservice.dto.LoginRequest;
import com.example.authservice.dto.RegisterRequest;
import com.example.authservice.model.User;
import com.example.authservice.repository.TokenRepository;
import com.example.authservice.repository.UserRepository;
import com.example.authservice.service.AuthenticationService;
import com.example.authservice.token.Token;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/user")
@Transactional
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {

        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthenticationResponse> refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return authenticationService.refreshToken(request, response);
    }

    @GetMapping("/token/{token}")
    public Optional<Token> findByToken(@PathVariable String token) {
        return tokenRepository.findByToken(token);
    }

    @GetMapping("/findByEmail/{email}")
    public Optional<User> findByEmail(@PathVariable String email) {
        return userRepository.findByEmail(email);
    }

    @GetMapping("/check-token-validity/{token}")
    public ResponseEntity<Map<String, String>> checkTokenValidity(@PathVariable String token) {
        return authenticationService.checkTokenValidity(token);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(userRepository.findById(id).orElseThrow());
    }

    @PostMapping("/updateProfile")
    @ResponseBody
    public ResponseEntity<String> updateProfile(@RequestPart("user") User user , @RequestParam("multipartFile") MultipartFile multipartFile) throws IOException {
        return authenticationService.updateUserImage(user,multipartFile);
    }
}