package com.coblan.users.controller;

import com.coblan.users.dto.CreateUserRequest;
import com.coblan.users.dto.UserDTO;
import com.coblan.users.usecase.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final GetAllUsersUseCase getAllUsersUseCase;
    private final GetUserByIdUseCase getUserByIdUseCase;
    private final RegisterUserUseCase registerUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final ToggleStatusUserCase toggleStatusUserUseCase;

    @Operation(
            summary = "Obtener todos los usuarios",
            description = "Devuelve una lista con todos los usuarios registrados. Requiere rol ADMIN."
    )
    @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida correctamente")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAll")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = getAllUsersUseCase.execute();
        return ResponseEntity.ok(users);
    }

    @Operation(
            summary = "Obtener un usuario por ID",
            description = "Devuelve la información de un usuario específico por su ID. Requiere rol ADMIN."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getUser/{user_id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long user_id) {
        UserDTO user = getUserByIdUseCase.execute(user_id);
        return ResponseEntity.ok(user);
    }

    @Operation(
            summary = "Registrar un nuevo usuario",
            description = "Crea un nuevo usuario con los datos proporcionados. Requiere rol ADMIN."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuario registrado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "401", description = "No autorizado")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody @Valid CreateUserRequest request) {
        UserDTO createdUser = registerUserUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @Operation(
            summary = "Eliminar un usuario",
            description = "Elimina un usuario del sistema por su ID. Requiere rol ADMIN."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        deleteUserUseCase.execute(id);
        return ResponseEntity.ok("Usuario eliminado correctamente");
    }

    @Operation(
            summary = "Actualizar un usuario",
            description = "Modifica los datos de un usuario existente. Solo los campos no nulos serán actualizados. Requiere rol ADMIN."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<UserDTO> updateUser(@RequestBody @Valid UserDTO userDTO) {
        return ResponseEntity.ok(updateUserUseCase.execute(userDTO));
    }

    @Operation(
            summary = "Actualizar el estado de un usuario (habilitar/deshabilitar)",
            description = "Modifica el estado de un usuario existente. Si se encuentra habilitado, pasa a estar inhabilitado y bisceversa. Requiere rol ADMIN."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/toggleStatus/{id}")
    public ResponseEntity<?> togglestatsus(@PathVariable Long id ) {
        toggleStatusUserUseCase.execute(id);
        return ResponseEntity.ok("El estado ha sido cambiado correctamente");
    }




}
