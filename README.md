# Coblan System Admin · Backend

**Coblan** es un sistema administrativo modular orientado a la **gestión de negocios que ofrecen servicios facturados por hora**. Está diseñado para estudios profesionales, consultoras, servicios tercerizados y cualquier organización que trabaje con empleados contratados por períodos de tiempo y necesite llevar un control integral.

Este sistema permite gestionar **clientes, empleados, contrataciones, servicios, horas trabajadas, pagos de salarios, gastos** y **reportes económicos**, todo desde una plataforma centralizada, eficiente y escalable.

Este repositorio contiene el backend de la aplicación, desarrollado con Java y Spring Boot, utilizando una arquitectura modular basada en principios de Domain-Driven Design (DDD).

> 🧠 **Documentación completa e interactiva disponible en [Deepwiki](https://deepwiki.com/MichaelIllescas/coblan-system-admin-backend/1-overview)**

---

## 🌐 Repositorios del sistema

- 🔙 Backend: **[Coblan System Admin - Backend (este repo)](https://github.com/MichaelIllescas/coblan-system-admin-backend)**
- 🔜 Frontend en React: **[Coblan System Admin - Frontend](https://github.com/MichaelIllescas/coblan-system-admin-fronted)**

---

## 📌 Características Principales

- ✅ Arquitectura modular limpia (DDD)
- 👤 Gestión de usuarios, clientes, empleados, servicios ofrecidos y contrataciones
- 🕒 Registro y confirmación de horas trabajadas por servicio
- 💸 Administración de pagos de salarios y gastos
- 📊 Reportes de ingresos, egresos, balances y ganancias mensuales/anuales
- 🔐 Seguridad con Spring Security 6.1 + JWT
- 🧾 API REST documentada con Swagger
- 🐳 Listo para despliegue con Docker

---

## ⚙️ Tecnologías

| Backend         | Seguridad           | Persistencia     | Herramientas         |
|----------------|---------------------|------------------|----------------------|
| Java 17        | Spring Security 6.1 | MySQL            | Maven                |
| Spring Boot 3  | JWT                 | Spring Data JPA  | Docker / Docker Hub  |
| REST API       | CORS Configurable   | Hibernate        | Swagger / OpenAPI    |

---

## 🚀 Instalación rápida (modo desarrollo)

```bash
# Clonar el proyecto
git clone https://github.com/MichaelIllescas/coblan-system-admin-backend.git
cd coblan-system-admin-backend

# Crear una base de datos MySQL y configurar credenciales en application.yml

# Ejecutar
./mvnw spring-boot:run
```

- 📄 Swagger: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## 📚 Índice de Documentación Técnica

La documentación completa e interactiva se encuentra disponible en Deepwiki, organizada por secciones:

- [System Architecture](https://deepwiki.com/MichaelIllescas/coblan-system-admin-backend/system-architecture) – Patrones de arquitectura y configuración
- [Core Domain Model](https://deepwiki.com/MichaelIllescas/coblan-system-admin-backend/core-domain-model) – Relaciones y estructura de entidades
- [Authentication and Security](https://deepwiki.com/MichaelIllescas/coblan-system-admin-backend/authentication-and-security) – Implementación de JWT y seguridad
- [Hiring and Scheduling System](https://deepwiki.com/MichaelIllescas/coblan-system-admin-backend/hiring-and-scheduling-system) – Contrataciones y horarios
- [Financial Management System](https://deepwiki.com/MichaelIllescas/coblan-system-admin-backend/financial-management-system) – Gastos, salarios y cálculos financieros
- [Reporting System](https://deepwiki.com/MichaelIllescas/coblan-system-admin-backend/reporting-system) – Reportes financieros y operativos

---

## 🧑‍💻 Desarrollador

**Michael Jonathan Illescas**  
Backend Developer | Full Stack | Arquitectura de Software  
📧 info@imperial-net.com  
🔗 [linkedin.com/in/jonathanillescas](https://linkedin.com/in/michael-jonathan-illescas)  

---

## 🪪 Licencia

Este proyecto es de código abierto bajo la licencia **MIT**.  
Podés usarlo, modificarlo y adaptarlo libremente respetando los términos de la licencia.
