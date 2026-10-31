# Arquitectura

Coblan System es una aplicación cliente-servidor: un frontend en React consume una API REST en Spring Boot, que persiste en MySQL.

```
┌──────────────────┐   HTTP + cookie JWT   ┌──────────────────────┐        ┌─────────┐
│ Frontend (React) │ ────────────────────▶ │ Backend (Spring Boot)│ ─────▶ │  MySQL  │
│ Vite · MUI       │ ◀──────────────────── │ API REST             │        └─────────┘
└──────────────────┘          JSON         │                      │ ─────▶ SMTP (emails)
                                           └──────────────────────┘
```

## Backend

### Organización por módulo

El código está organizado por contexto de negocio (`com.coblan.<módulo>`), no por capa técnica. Cada módulo contiene sus propias capas:

```
<modulo>/
├── controller/    endpoints REST
├── dto/           objetos de entrada y salida de la API
├── usecase/       interfaces de los casos de uso
├── service/       implementaciones de los casos de uso
├── mapper/        conversión entre entidades y DTOs
├── repository/    acceso a datos (Spring Data JPA)
├── domain/        entidades y enums del módulo
└── docs/          documentación específica del módulo
```

Cada operación de negocio es un caso de uso con su propia interfaz (por ejemplo, `CreateEmployeeUseCase`) y su implementación. Los controllers dependen de las interfaces, no de las implementaciones.

### Módulos

| Módulo | Ruta base | Responsabilidad |
|---|---|---|
| `auth` | `/auth` | Login, logout, recuperación y cambio de contraseña |
| `users` | `/users` | Usuarios del sistema y sus roles (`ADMIN`, `USER`) |
| `company` | `/companies` | Datos de la empresa que usa el sistema |
| `customers` | `/customers` | Clientes que contratan servicios |
| `employees` | `/employees` | Empleados que prestan los servicios |
| `services` | `/services` | Catálogo de servicios con horas y monto mensual |
| `hiring` | `/hirings`, `/work-hours` | Contrataciones, cronograma y horas de trabajo |
| `expenses` | `/expenses` | Gastos de la empresa |
| `salaryPayments` | `/salary-payments` | Pagos de sueldos a empleados |
| `reports` | `/reports` | Reportes financieros, de contrataciones y estadísticas del dashboard |

### Modelo central: contrataciones y horas

El núcleo del sistema es el módulo `hiring`:

- Una **contratación** (`Hiring`) vincula un cliente, un servicio y un empleado, con un cronograma semanal fijo (`ScheduleEntry`: días y horario).
- Al registrarla, el sistema genera las **horas de trabajo** (`WorkHour`) del mes, de tipo `STANDARD`.
- Se pueden agregar horas de tipo `EXTRA` manualmente.
- Cada hora pasa de pendiente a **confirmada** cuando se registra que el trabajo se realizó. Sólo las horas confirmadas cuentan como ingreso.
- Antes de asignar un empleado se valida que no tenga conflictos de horario con otras contrataciones.

Una tarea programada (`HiringRenewalTask`, diaria) renueva las contrataciones activas y genera las horas del mes siguiente sin duplicar las existentes.

### Reportes financieros

El balance de un período se calcula como:

```
ingresos (horas confirmadas × valor hora) − (gastos + sueldos pagados)
```

El valor hora surge de dividir el monto mensual del servicio por sus horas mensuales previstas.

### Seguridad

- Autenticación con **JWT almacenado en una cookie HttpOnly** (`JwtCookieAuthenticationFilter`), lo que evita exponer el token a JavaScript.
- Rutas públicas configurables (`security.whitelist`) y CORS por propiedades.
- Contraseñas cifradas con `PasswordEncoder` (BCrypt).
- Recuperación de contraseña con token de un solo uso que vence a los 30 minutos y se envía por email. El flujo completo está en [`backend/.../auth/docs/flujo_reset_password_coblan.md`](../backend/src/main/java/com/coblan/auth/docs/flujo_reset_password_coblan.md).
- Al iniciar por primera vez se crea un usuario administrador si no existe ninguno (`AdminUserInitializer`).

### Manejo de errores

Las excepciones de dominio se traducen a respuestas HTTP en un manejador global (`config/exception`), de modo que los controllers no tienen bloques `try/catch`.

### Documentación de la API

La API se documenta con springdoc-openapi. Con el backend corriendo, la interfaz de Swagger está en `/swagger-ui.html`.

## Frontend

Aplicación de una sola página creada con Vite y React 19.

```
src/
├── Features/      una carpeta por módulo de negocio (Customer, Employee, Hirings, Reports…)
├── Components/    componentes reutilizables (tablas, modales, filtros, selects, PDF)
├── Layouts/       estructura de página (sidebar, navbar)
├── Routes/        definición de rutas y protección por sesión
├── Context/       estado global de autenticación
├── Services/      cliente HTTP (axios) configurado contra la API
├── hooks/         hooks propios
└── utils/         utilidades
```

- **UI:** Material UI y React Bootstrap.
- **Formularios:** React Hook Form con validación Yup.
- **Gráficos:** Chart.js y Recharts para el dashboard y los reportes.
- **PDF:** los comprobantes mensuales y reportes se generan en el navegador con jsPDF y react-pdf.

## Documentación por módulo

Cada módulo del backend tiene su propia documentación junto al código:

- [Autenticación](../backend/src/main/java/com/coblan/auth/docs/auth-module.md)
- [Clientes](../backend/src/main/java/com/coblan/docs/customers-module.md)
- [Empleados](../backend/src/main/java/com/coblan/employees/docs/employee-module-full.md)
- [Gastos](../backend/src/main/java/com/coblan/expenses/docs/README_EXPENSES.md)
- [Registro de contrataciones](../backend/src/main/java/com/coblan/hiring/docs/register_hiring.md)
- [Renovación automática de contrataciones](../backend/src/main/java/com/coblan/hiring/docs/HiringRenewalTask_Documentacion.md)
- [Pagos de sueldos](../backend/src/main/java/com/coblan/salaryPayments/docs/README_SALARY_PAYMENTS.md)
- [Servicios](../backend/src/main/java/com/coblan/services/docs/services-module-overview.md)
