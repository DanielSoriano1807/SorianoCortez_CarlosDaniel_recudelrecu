package com.carlos.recurecu.Repository;

import com.carlos.recurecu.Datos.BeanUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<BeanUsuario, Long> {
}