package com.carlos.recurecu.Controllers;

import com.carlos.recurecu.Datos.BeanUsuario;
import com.carlos.recurecu.Datos.UsuarioDTO;
import com.carlos.recurecu.Services.UsuarioService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public BeanUsuario registrar(@RequestBody UsuarioDTO dto) {
        return service.registrar(dto);
    }

    @PutMapping("/{id}/desactivar")
    public BeanUsuario desactivar(@PathVariable Long id) {
        return service.desactivarUsuario(id);
    }
}