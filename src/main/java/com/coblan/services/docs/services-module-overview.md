# Módulo de Servicios (Coblan)

## 📌 Propósito

Este módulo representa el catálogo de **servicios que la empresa ofrece**, y forma parte de la arquitectura modular y escalable de Coblan. Está diseñado bajo principios de **Domain-Driven Design (DDD)** y **SOLID**, permitiendo que otros módulos (como contrataciones) lo reutilicen sin acoplamiento.

---

## 🧱 Entidad principal

### 🔹 `Service`

Es la **única entidad gestionada directamente por este módulo**, y representa un servicio que la empresa tiene disponible para ofrecer a sus clientes.

**Atributos:**
- `id`: identificador único del servicio
- `name`: nombre del servicio (ej: Limpieza mensual)
- `monthlyHours`: cantidad de horas mensuales que requiere el servicio
- `totalAmount`: precio total del servicio por mes
- `note`: observación opcional

> Este módulo **no genera horas ni contrataciones**, solo define los servicios disponibles.

---

## 🔁 Comportamiento del módulo

- Permite **registrar, listar y consultar** servicios ofrecidos por la empresa.
- **No contiene lógica de asignación, ejecución ni estado de horas**.
- Su responsabilidad termina en la definición del servicio como un producto/paquete ofertado.

---

## ✅ Responsabilidades

- Crear servicios con estructura consistente y reutilizable
- Servir como base para otros módulos (ej: contratación)
- Garantizar integridad de datos en la creación de servicios

---

## ❌ Fuera del alcance

Este módulo **no realiza ninguna de las siguientes acciones**:

- No genera instancias de `WorkHour`
- No cambia estado de ninguna hora
- No asigna empleados
- No gestiona clientes ni contrataciones

---

## 📎 Estructura del módulo

```
services/
├── controller/
│   └── ServicesController.java
├── domain/
│   └── Service.java
├── dto/
│   ├── ServiceRequestDTO.java
│   └── ServiceResponseDTO.java
├── mapper/
│   └── ServiceMapper.java
├── repository/
│   └── ServiceRepository.java
├── service/
│   └── (Lógica de aplicación)
├── usecase/
│   └── (Casos de uso)
├── docs/
│   └── services-module-overview.md
```

---

## 🔁 Formatos de entrada y salida (DTOs)

### 📤 `ServiceRequestDTO`
Usado para registrar un nuevo servicio:

- `name`: nombre del servicio
- `monthlyHours`: cantidad de horas por mes
- `totalAmount`: monto total mensual
- `note`: observación opcional

### 📥 `ServiceResponseDTO`
Devuelve la información completa del servicio registrado:

- `id`, `name`, `monthlyHours`, `totalAmount`, `note`

> No contiene horas ni estados, ya que no forman parte de este módulo.

---

## ✍️ Notas técnicas

- Este módulo está completamente **desacoplado** de cualquier contexto de ejecución o cliente.
- Toda lógica de asignación de horas, contratación o personal se desarrollará en un **composite posterior** bajo un módulo distinto (`contracts`, por ejemplo).
- Este diseño permite **escalar fácilmente**, manteniendo responsabilidades separadas y claridad estructural.
