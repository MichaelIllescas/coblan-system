package com.coblan.auth.controller;




import com.coblan.auth.dto.AuthenticationRequest;
import com.coblan.auth.dto.AuthenticationResponse;
import com.coblan.auth.exceptions.DisabledUserException;
import com.coblan.auth.exceptions.InvalidCredentialsException;
import com.coblan.auth.security.JwtService;
import com.coblan.auth.service.AuthenticationService;
import com.coblan.auth.service.CookieService;
import com.coblan.users.dto.UserDTO;
import com.coblan.users.service.UserMapper;
import com.coblan.users.usecase.GetAuthenticatedUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Controlador REST encargado de manejar la autenticación de usuarios,
 * incluyendo inicio y cierre de sesión, validación de sesión activa y
 * funcionalidades para recuperación de contraseña.
 */


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final AuthenticationService authenticationService;
    private final CookieService cookieService;
    private final GetAuthenticatedUserUseCase getAuthenticatedUserUseCase;
    private final UserMapper userMapper;

    /**
     * Endpoint para iniciar sesión. Autentica al usuario, genera un JWT
     * y lo almacena en una cookie.
     *
     * @param request  credenciales de autenticación
     * @param response objeto HttpServletResponse donde se setea la cookie
     * @return UserDTO del usuario autenticado o error en caso de falla
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody AuthenticationRequest request,
            HttpServletResponse response
    ) {
        try {
            AuthenticationResponse authResponse = authenticationService.login(request);
            ResponseCookie cookie = cookieService.createAuthCookie(authResponse.getJwt());
            response.addHeader("Set-Cookie", cookie.toString());
            UserDTO userDto = userMapper.toDTO(authenticationService.getUserByEmail(request.getEmail()).get());
            return ResponseEntity.ok(userDto);
        } catch (InvalidCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", e.getMessage()));
        } catch (DisabledUserException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Error al conectarse al servidor."));
        }
    }

    /**
     * Endpoint para cerrar sesión. Elimina la cookie con el JWT.
     *
     * @param response objeto HttpServletResponse donde se elimina la cookie
     * @return mensaje de confirmación
     */
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("authToken", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return ResponseEntity.ok("Logout exitoso");
    }

    /**
     * Endpoint para obtener información de la sesión actual.
     *
     * @param jwtToken token JWT extraído de la cookie
     * @param request  petición HTTP que contiene la cookie
     * @return información del usuario autenticado o mensaje de error
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    public ResponseEntity<?> getSession(@CookieValue(name = "authToken", required = false) String jwtToken,
                                        HttpServletRequest request) {
        if (jwtToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No hay sesión activa");
        }

        try {
            UserDTO user = getAuthenticatedUserUseCase.execute(request);

            Map<String, Object> response = new HashMap<>();
            response.put("username", user.getEmail());
            response.put("roles", List.of(user.getRole()));  // Asumiendo un solo rol
            response.put("name", user.getFirstName());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Sesión inválida");
        }
    }


    /**
     * Endpoint para solicitar el token de recuperación de contraseña.
     *
     * @param request objeto con el email del usuario
     * @return mensaje de confirmación

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody EmailRequest request) {
        userService.sendResetToken(request.getEmail());
        return ResponseEntity.ok("Si el correo está registrado, se envió el enlace.");
    }
*/
    /**
     * Endpoint para restablecer la contraseña usando un token.
     *
     * @param request objeto con el token y la nueva contraseña
     * @return mensaje de confirmación

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {
        userService.resetPassword(request.getToken(), request.getNewPassword());
        return ResponseEntity.ok("Contraseña actualizada con éxito.");
    } */
}