package cl.duoc.mineria.usuarios.controller;

import cl.duoc.mineria.usuarios.dto.ActualizarRolUsuarioDTO;
import cl.duoc.mineria.usuarios.dto.RegistrarUsuarioDTO;
import cl.duoc.mineria.usuarios.exception.UsuarioNotFoundException;
import cl.duoc.mineria.usuarios.model.RolUsuario;
import cl.duoc.mineria.usuarios.model.Usuario;
import cl.duoc.mineria.usuarios.service.ServiceUsuario;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final ServiceUsuario usuarioService;

    @PostMapping("/registrar")
    public ResponseEntity<Usuario> registrar(@Valid @RequestBody RegistrarUsuarioDTO dto) {
        return new ResponseEntity<>(usuarioService.registrarUsuario(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.obtenerPorId(id));
    }

    @PutMapping("/{id}/rol")
    public ResponseEntity<Usuario> actualizarRolUsuario(
            @PathVariable Long id,
            @Valid @RequestBody ActualizarRolUsuarioDTO dto) {
        return ResponseEntity.ok(usuarioService.actualizarRol(id, dto));
    }

    @GetMapping("/existe/{id}")
    public ResponseEntity<Boolean> verificarExiste(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.existeUsuario(id));
    }

    @GetMapping("/paleros/{id}")
    public ResponseEntity<Boolean> verificarEsPaleroActivo(@PathVariable Long id) {
        try {
            // 1. Obtienes el usuario directamente (si no existe, saltará al catch)
            Usuario usuario = usuarioService.obtenerPorId(id);
            
            // 2. Comparas el rol directamente usando el objeto obtenido
            boolean esPaleroValido = usuario.getRol() == RolUsuario.OPERADOR_PALA;
            
            return ResponseEntity.ok(esPaleroValido);
            
        } catch (UsuarioNotFoundException e) {
            // Si el service lanzó la excepción porque el ID no existe, retornamos false
            System.out.println("⚠️ [Usuarios] El ID " + id + " no existe en el sistema.");
            return ResponseEntity.ok(false);
        }
    }
}