package com.dosgenerales.auth_service.repository;

import com.dosgenerales.auth_service.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> finByEmail(String email);
}
