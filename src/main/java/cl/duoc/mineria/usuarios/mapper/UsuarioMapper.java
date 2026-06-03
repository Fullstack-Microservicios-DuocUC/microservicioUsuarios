package cl.duoc.mineria.usuarios.mapper;

import cl.duoc.mineria.usuarios.dto.RegistrarUsuarioDTO;
import cl.duoc.mineria.usuarios.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario toEntity(RegistrarUsuarioDTO dto) {
        if (dto == null) return null;

        String rutEstandarizado = dto.getRut().replace(".", "").toUpperCase();

        Usuario usuario = new Usuario();
        usuario.setRut(rutEstandarizado);
        usuario.setNombreCompleto(dto.getNombreCompleto());
        usuario.setRol(dto.getRol());
        return usuario;
    }
}