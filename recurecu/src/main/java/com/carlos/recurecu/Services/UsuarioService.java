package com.carlos.recurecu.Services;

import com.carlos.recurecu.Datos.BeanUsuario;
import com.carlos.recurecu.Datos.UsuarioDTO;
import com.carlos.recurecu.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository repositorio;

    public UsuarioService(UsuarioRepository repositorio) {
        this.repositorio = repositorio;
    }

    public BeanUsuario registrar(UsuarioDTO dto) {
        BeanUsuario u = new BeanUsuario();
        u.setNombre(dto.getNombre());
        u.setActivo(dto.isActivo());
        return repositorio.save(u);
    }

    public BeanUsuario desactivarUsuario(Long usuarioId) {
        BeanUsuario u = repositorio.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        u.setActivo(false);
        return repositorio.save(u);
    }
}