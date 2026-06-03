package cl.duoc.mineria.usuarios.dto;

import cl.duoc.mineria.usuarios.model.RolUsuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistrarUsuarioDTO {

    @NotBlank(message = "El RUT del trabajador es obligatorio")
    @Pattern(
        regexp = "^[0-9]{1,2}\\.?[0-9]{3}\\.?[0-9]{3}-[0-9kK]{1}$", 
        message = "El formato del RUT no es válido (ejemplo correcto: 12.345.678-9 o 12345678-9)"
    )
    private String rut;

    @NotBlank(message = "El nombre completo del usuario no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    private String nombreCompleto;

    @NotNull(message = "El rol operativo del trabajador es obligatorio")
    private RolUsuario rol;
}