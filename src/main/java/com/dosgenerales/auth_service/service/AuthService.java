package com.dosgenerales.auth_service.service;

import com.dosgenerales.auth_service.dto.AuthResponseDTO;
import com.dosgenerales.auth_service.dto.LoginRequestDTO;
import com.dosgenerales.auth_service.dto.RegisterRequestDTO;
import com.dosgenerales.auth_service.model.Role;
import com.dosgenerales.auth_service.model.Usuario;
import com.dosgenerales.auth_service.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponseDTO login(LoginRequestDTO request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        Usuario usuario = usuarioRepository.finByEmail(request.getEmail()).orElseThrow();
        String token = jwtService.generateToken(usuario);
        return AuthResponseDTO.builder().token(token).build();
    }

    public AuthResponseDTO register(RegisterRequestDTO request) {
        Usuario usuario = Usuario.builder()
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        usuarioRepository.save(usuario);

        return AuthResponseDTO.builder()
                .token(jwtService.generateToken(usuario))
                .build();
    }
}
