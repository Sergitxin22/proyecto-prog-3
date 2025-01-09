# UsuarioDAO.java

La clase `UsuarioDAO` es responsable de gestionar las operaciones de la base de datos relacionadas con la entidad `Usuario`. Implementa la interfaz `UsuarioDAOInterface` y proporciona métodos para agregar, recuperar, actualizar y eliminar registros de usuarios en la base de datos.

## Constructor

- `UsuarioDAO()`
  - Inicializa `conexionBD` y `logger` utilizando la clase `Main`.

- `UsuarioDAO(Connection conexionBD, Logger logger)`
  - Inicializa `conexionBD` y `logger` con los parámetros proporcionados.

## Métodos

### addUsuario(Usuario usuario)
- Agrega un nuevo usuario a la base de datos.
- Inserta al usuario en la tabla `Usuario` y, según el tipo de usuario, en la tabla `Cliente` o `Admin`.
- Devuelve `true` si el usuario fue agregado con éxito, de lo contrario `false`.

### isUsuarioCorrecto(String dni, String password)
- Verifica si las credenciales del usuario son correctas.
- Devuelve `true` si el usuario existe y la contraseña coincide, de lo contrario `false`.

### getUsuario(String dni)
- Recupera un usuario por su DNI.
- Devuelve un objeto `UsuarioDTO` con los detalles del usuario.

### getDatosAdicionalesUsuario(UsuarioDTO usuario)
- Recupera datos adicionales de un usuario.
- Establece el rol del usuario (admin o cliente) y el número de advertencias si el usuario es un cliente.

### updatePassword(UsuarioDTO usuario, String password)
- Actualiza la contraseña del usuario.
- Devuelve `true` si la contraseña se actualizó con éxito, de lo contrario `false`.

### getLogAccionesByAdminDni(String dniAdmin)
- Recupera las acciones registradas realizadas por un administrador.
- Devuelve un `ArrayList` de objetos `LogAccion`.

### addLogAccion(LogAccion logAccion)
- Agrega una nueva acción al registro de la base de datos.
- Devuelve `true` si la acción fue agregada con éxito, de lo contrario `false`.

### borrarRegistros()
- Elimina todos los registros de las tablas `Usuario`, `Cliente`, `Admin` y `LogAccion`.

### pruebas()
- Un método para probar la funcionalidad de la clase `UsuarioDAO`.
- Realiza varias operaciones como agregar usuarios, recuperar usuarios, actualizar contraseñas y agregar acciones al registro.
