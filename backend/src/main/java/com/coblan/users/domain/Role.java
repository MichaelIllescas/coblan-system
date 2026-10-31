package com.coblan.users.domain;

/**
 * Enum que representa los roles de los usuarios en el sistema.
 * Los roles determinan los permisos y la autoridad de los usuarios dentro de la aplicación.
 *
 * Los roles disponibles son:
 * - USER: Rol estándar para los usuarios normales, con acceso restringido a ciertas funcionalidades.
 * - ADMIN: Rol para los administradores, con permisos completos para gestionar la aplicación.
 */
public enum Role {
    USER,  // Rol para usuarios estándar con permisos limitados.
    ADMIN  // Rol para administradores con permisos completos.
}
