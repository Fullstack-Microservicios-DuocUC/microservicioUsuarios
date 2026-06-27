package cl.duoc.mineria.usuarios.exception;

public class UsuarioDuplicadoException extends RuntimeException {
    public UsuarioDuplicadoException(String mensaje) {
        super(mensaje);
    }
}