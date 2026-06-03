package cl.duoc.mineria.usuarios.dto;

import cl.duoc.mineria.usuarios.model.RolUsuario;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ActualizarRolUsuarioDTO {

    @NotNull(message = "El rol operativo del trabajador es obligatorio")
    private RolUsuario rol;
}
