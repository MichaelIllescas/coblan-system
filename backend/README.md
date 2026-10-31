# Coblan System · Backend

API REST del sistema, desarrollada con Spring Boot 3.4 y Java 17.

Para la descripción general, el stack completo y cómo ejecutar todo el sistema, ver el [README principal](../README.md).

## Ejecución

```bash
cp src/main/resources/application-example.properties src/main/resources/application.properties
mvn spring-boot:run
```

- API: `http://localhost:8080`
- Swagger: `http://localhost:8080/swagger-ui.html`

## Configuración

Todas las propiedades sensibles se leen de variables de entorno. Ver [`application-example.properties`](src/main/resources/application-example.properties).

| Variable | Descripción |
|---|---|
| `DB_URL`, `DB_USERNAME`, `DB_PASSWORD` | Conexión a MySQL |
| `JWT_SECRET` | Clave de firma de los tokens |
| `CORS_ORIGINS` | Orígenes permitidos para el frontend |
| `MAIL_HOST`, `MAIL_PORT`, `MAIL_USERNAME`, `MAIL_PASSWORD` | Servidor SMTP para recuperación de contraseña |
| `RESET_PASSWORD_URL` | URL del frontend a la que apunta el email de recuperación |

## Documentación por módulo

- [Autenticación](src/main/java/com/coblan/auth/docs/auth-module.md)
- [Flujo de recuperación de contraseña](src/main/java/com/coblan/auth/docs/flujo_reset_password_coblan.md)
- [Clientes](src/main/java/com/coblan/docs/customers-module.md)
- [Empleados](src/main/java/com/coblan/employees/docs/employee-module-full.md)
- [Gastos](src/main/java/com/coblan/expenses/docs/README_EXPENSES.md)
- [Registro de contrataciones](src/main/java/com/coblan/hiring/docs/register_hiring.md)
- [Renovación automática de contrataciones](src/main/java/com/coblan/hiring/docs/HiringRenewalTask_Documentacion.md)
- [Pagos de sueldos](src/main/java/com/coblan/salaryPayments/docs/README_SALARY_PAYMENTS.md)
- [Servicios](src/main/java/com/coblan/services/docs/services-module-overview.md)
