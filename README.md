# Coblan System

Sistema administrativo para empresas que prestan servicios facturados por hora: limpieza, mantenimiento, cuidado de personas y rubros similares.

Centraliza clientes, empleados, servicios y contrataciones, genera automáticamente las horas de trabajo de cada mes y calcula el balance real del negocio a partir de las horas efectivamente realizadas.

Desarrollado originalmente para una empresa de servicios de Casbas (Buenos Aires), que lo usó en producción.

## Funcionalidades

- **Contrataciones con cronograma.** Cada contratación define días y horario fijos; el sistema genera las horas del mes y valida que el empleado no tenga superposiciones.
- **Renovación automática.** Una tarea programada genera las horas del mes siguiente para las contrataciones activas.
- **Confirmación de horas.** Las horas se confirman cuando el trabajo se realizó, se pueden reasignar a otro empleado y se admiten horas extra.
- **Gestión de clientes, empleados y servicios**, con baja lógica y reactivación.
- **Gastos y pagos de sueldos**, incluidos pagos parciales del mismo mes.
- **Reportes financieros** mensuales y anuales: ingresos por horas confirmadas, gastos, sueldos y balance.
- **Comprobantes mensuales en PDF** por contratación, con formato de factura.
- **Usuarios con roles** (`ADMIN`, `USER`) y recuperación de contraseña por email.

## Stack

| Capa | Tecnologías |
|---|---|
| Backend | Java 17, Spring Boot 3.4, Spring Security, Spring Data JPA, JWT, springdoc-openapi |
| Base de datos | MySQL 8 |
| Frontend | React 19, Vite, Material UI, React Bootstrap, React Hook Form + Yup |
| Reportes | Chart.js, Recharts, jsPDF, react-pdf |

## Estructura del repositorio

```
coblan-system/
├── backend/     API REST en Spring Boot
├── frontend/    panel administrativo en React
└── docs/        arquitectura e historias de usuario
```

## Arquitectura

El backend está organizado por módulo de negocio (`auth`, `customers`, `employees`, `services`, `hiring`, `expenses`, `salaryPayments`, `reports`, `users`, `company`). Cada módulo tiene sus propias capas (controller, casos de uso, servicios, repositorio, dominio) y cada operación es un caso de uso con su interfaz.

La autenticación usa JWT en una cookie HttpOnly. Los errores se manejan en un único manejador global.

El detalle está en [docs/arquitectura.md](docs/arquitectura.md) y los requerimientos en [docs/historias-de-usuario.md](docs/historias-de-usuario.md).

## Cómo ejecutarlo

### Requisitos

- JDK 17
- Maven 3.9 o superior
- Node.js 18 o superior
- MySQL 8

### Backend

```bash
cd backend
cp src/main/resources/application-example.properties src/main/resources/application.properties
```

Completar en `application.properties` (o con variables de entorno) los datos de la base, el secreto JWT y el servidor de correo. Después:

```bash
mvn spring-boot:run
```

La API queda en `http://localhost:8080` y la documentación interactiva en `http://localhost:8080/swagger-ui.html`.

Al iniciar por primera vez se crea un usuario administrador:

| Email | Contraseña |
|---|---|
| `admin@admin.com` | `admin.123` |

### Frontend

```bash
cd frontend
npm install
npm run dev
```

El panel queda en `http://localhost:5173` y apunta a la API local.

## Licencia

Distribuido bajo licencia MIT. Ver [LICENSE](LICENSE).

## Autor

Jonathan Illescas · [GitHub](https://github.com/MichaelIllescas)
