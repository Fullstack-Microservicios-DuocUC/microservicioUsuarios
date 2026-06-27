package cl.duoc.mineria.usuarios.service;

import cl.duoc.mineria.usuarios.dto.ActualizarRolUsuarioDTO;
import cl.duoc.mineria.usuarios.dto.RegistrarUsuarioDTO;
import cl.duoc.mineria.usuarios.exception.UsuarioDuplicadoException;
import cl.duoc.mineria.usuarios.exception.UsuarioNotFoundException;
import cl.duoc.mineria.usuarios.mapper.UsuarioMapper;
import cl.duoc.mineria.usuarios.model.Usuario;
import cl.duoc.mineria.usuarios.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceUsuario { // Nombre alineado con tus estándares

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Transactional
    public Usuario registrarUsuario(RegistrarUsuarioDTO dto) {
        String rutLimpio = dto.getRut().replace(".", "").toUpperCase();
        if (usuarioRepository.findByRut(rutLimpio).isPresent()) {
            throw new UsuarioDuplicadoException("El trabajador con RUT " + dto.getRut() + " ya se encuentra registrado.");
        }
        Usuario nuevoUsuario = usuarioMapper.toEntity(dto);
        return usuarioRepository.save(nuevoUsuario);
    }

    @Transactional(readOnly = true)
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException("El ID de usuario " + id + " no pertenece a ningún miembro del personal."));
    }

    @Transactional
    public Usuario actualizarRol(Long id, ActualizarRolUsuarioDTO dto) {
        Usuario usuario = obtenerPorId(id);
        usuario.setRol(dto.getRol());
        return usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)
    public boolean existeUsuario(Long id) {
        return usuarioRepository.existsById(id);
    }

    
}