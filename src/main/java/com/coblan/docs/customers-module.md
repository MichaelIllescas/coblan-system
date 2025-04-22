# Módulo Customers

Este módulo es responsable de la **gestión de clientes** dentro del sistema Coblan. Su función principal es permitir el alta, modificación, consulta y baja lógica de los clientes, de manera desacoplada y preparada para integrarse con otros módulos como contrataciones y reportes.

---

## 🔍 Responsabilidades

- Registrar nuevos clientes.
- Obtener listado completo de clientes.
- Obtener un cliente por su ID.
- Actualizar información de un cliente existente.
- Eliminar un cliente si no está asociado a otros registros (baja definitiva).
- Validar campos de entrada con reglas de negocio.

---

## 📚 Estructura de paquetes

```
com.coblan.customers
├── controller
│   └── CustomerController.java
├── domain
│   └── Customer.java
├── dto
│   ├── CustomerRequestDTO.java
│   └── CustomerResponseDTO.java
├── exception
│   ├── CustomerNotFoundException.java
│   └── CustomerInUseException.java
├── mappers
│   └── CustomerMapper.java
├── repository
│   └── CustomerRepository.java
├── service
│   ├── CreateCustomerUseCaseImpl.java
│   ├── DeleteCustomerUseCaseImpl.java
│   ├── GetAllCustomersUseCaseImpl.java
│   ├── GetCustomerByIdUseCaseImpl.java
│   └── UpdateCustomerUseCaseImpl.java
└── usecase
    ├── CreateCustomerUseCase.java
    ├── DeleteCustomerUseCase.java
    ├── GetAllCustomersUseCase.java
    ├── GetCustomerByIdUseCase.java
    └── UpdateCustomerUseCase.java
```

---

## 🔧 Tecnologías utilizadas

- Spring Boot 3
- Spring Data JPA
- Bean Validation (Jakarta)
- Swagger/OpenAPI (Springdoc)
- Lombok

---

## 📃 Entidad `Customer`

Representa a un cliente en el sistema. Contiene:
- `id: Long`
- `firstName: String`
- `lastName: String`
- `documentNumber: String`
- `phone: String`
- `email: String`
- `active: boolean`

---

## ⚖️ Validaciones aplicadas

La entidad y los DTOs están validados mediante anotaciones como:
- `@NotBlank`
- `@Email`
- `@Pattern`
- `@Size`

---

## 📊 Endpoints expuestos

| Método | Ruta             | Descripción                             |
|--------|------------------|----------------------------------------|
| POST   | `/customers`     | Crear un nuevo cliente                 |
| GET    | `/customers`     | Listar todos los clientes              |
| GET    | `/customers/{id}`| Obtener cliente por ID                 |
| PUT    | `/customers/{id}`| Actualizar cliente por ID              |
| DELETE | `/customers/{id}`| Eliminar cliente (si no está en uso)   |

---

## ❌ Manejo de errores

Este módulo lanza excepciones controladas:
- `CustomerNotFoundException` → 404 Not Found
- `CustomerInUseException` → 409 Conflict
- Errores de validación → 400 Bad Request

Todos los errores se devuelven con el siguiente formato JSON:
```json
{
  "error": "Mensaje descriptivo del error"
}
```

---

## ✏️ Notas finales

Este módulo está diseñado siguiendo principios **SOLID** y **DDD**, asegurando escalabilidad, bajo acoplamiento y alta cohesión. Puede integrarse con módulos como contrataciones o reportes para operaciones cruzadas sin alterar su independencia.

