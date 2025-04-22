
# 👤 Módulo de Empleados

El módulo de empleados forma parte del sistema Coblan y se encarga de la administración de los datos personales y de contacto del personal registrado en la organización.  
Su principal responsabilidad es permitir el registro, la modificación, la consulta y el control de estado (activo/inactivo) de los empleados, sin intervenir en tareas operativas como asignación de servicios o seguimiento de horas trabajadas, las cuales son tratadas en módulos específicos como **Contrataciones** y **Reportes**.

---

## 🚀 Endpoints disponibles

Todos los endpoints se encuentran bajo el path base `/employees`.

### 📌 Registrar un nuevo empleado
- **POST** `/employees/create`
- **Descripción**: Registra un nuevo empleado con los datos básicos.
- **Códigos de respuesta**: `200 OK` si el empleado fue creado correctamente.

#### Body de ejemplo:
```json
{
  "firstName": "Juan",
  "lastName": "Pérez",
  "documentNumber": "30123456",
  "email": "juan.perez@mail.com",
  "phone": "1122334455",
  "cuit": "20345678901",
  "address": "Av. Siempre Viva 123"
}
```

---

### 📌 Obtener todos los empleados
- **GET** `/employees/getAll`
- **Parámetro opcional**: `active=true` para filtrar solo empleados activos.
- **Descripción**: Devuelve todos los empleados registrados o solo los activos según se indique.

---

### 📌 Obtener un empleado por ID
- **GET** `/employees/{id}`
- **Descripción**: Recupera los datos completos de un empleado específico según su identificador único.

---

### 📌 Actualizar los datos de un empleado
- **PUT** `/employees/update/{id}`
- **Descripción**: Modifica la información personal de un empleado.
- **Códigos de respuesta**: `200 OK` con los datos actualizados.

---

### 📌 Activar o desactivar un empleado
- **PATCH** `/employees/toggle-status/{id}`
- **Descripción**: Cambia el estado del empleado (activo/inactivo).
- **Códigos de respuesta**: `204 No Content` si la operación fue exitosa.

---

## 📄 Estructura de la entidad `Employee`

```java
Long id;
String firstName;
String lastName;
String documentNumber;
String email;
String phone;
String cuit;        // opcional
String address;     // opcional
Boolean active;
```

---

## 🧱 Estructura de DTOs

### `EmployeeRequestDTO`
Se utiliza para operaciones de creación y actualización.

### `EmployeeResponseDTO`
Se utiliza para retornar datos completos al cliente.

---

## 📌 Casos de uso implementados

| Caso de uso                     | Descripción                                      |
|----------------------------------|--------------------------------------------------|
| `CreateEmployeeUseCase`         | Registro de nuevos empleados.                   |
| `GetAllEmployeesUseCase`        | Consulta de empleados filtrando por estado.     |
| `GetEmployeeByIdUseCase`        | Consulta detallada por ID.                      |
| `UpdateEmployeeUseCase`         | Modificación de datos de un empleado.           |
| `ToggleEmployeeStatusUseCase`   | Habilitación o desactivación lógica del estado. |

---

## 🟢 Consideraciones

- Las validaciones se realizan mediante anotaciones de Bean Validation (`jakarta.validation`).
- Los campos `documentNumber`, `email` y `cuit` son únicos.
- La eliminación de empleados no es física; se gestiona mediante el campo `active`.

---

## 🔗 Relación con otros módulos

- **Contrataciones**: Maneja la asignación de servicios a empleados.
- **Reportes**: Registra y consulta horas trabajadas.

---

## 📌 Estado del módulo
Implementado y operativo. Cumple con las funcionalidades esperadas para la administración del personal sin acoplarse a procesos operativos.

