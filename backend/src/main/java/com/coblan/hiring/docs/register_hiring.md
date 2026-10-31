# Registro de Contratación - Coblan

## 🧩 Descripción general

La clase `RegisterHiringService` implementa el caso de uso `RegisterHiringUseCase`.  
Se encarga de registrar una nueva contratación (`Hiring`) y generar automáticamente las horas de trabajo (`WorkHour`) correspondientes al primer ciclo mensual.

---

## ✅ Flujo completo

### 1. Validación de entidades

Busca y valida las siguientes entidades por ID usando sus respectivos repositorios:

- `Customer` (cliente)
- `Service` (servicio contratado)
- `Employee` (empleado asignado)

Si alguna no existe, lanza una excepción.

---

### 2. Mapeo del DTO a entidad

Usa `HiringMapper.toEntity(...)` para construir la entidad `Hiring` a partir del `HiringRequestDTO`, asociando el cliente, servicio y empleado, e inicializando el estado como `ACTIVE`.

---

### 3. Generación automática de WorkHours

Llama al método `generateStandardWorkHours(Hiring)` que:

- Obtiene el `schedule` (días de la semana + horarios fijos)
- Calcula el total de minutos del servicio mensual (`monthlyHours * 60`)
- Recorre el calendario día por día a partir de la `startDate`
- Por cada coincidencia con el `schedule`, crea una `WorkHour`
- Continúa generando horas hasta cumplir con el total de minutos requeridos
- Cada `WorkHour` es:
  - De tipo `STANDARD`
  - Con duración fija (ej. 60 minutos)
  - Con precio proporcional
  - En estado `PENDING`
  - Asociada al `Employee` y posteriormente al `Hiring`

---

### 4. Persistencia

Guarda el `Hiring` con sus `WorkHours` en cascada (`cascade = ALL`), usando `HiringRepository`.

---

### 5. Respuesta

Devuelve un `HiringResponseDTO` construido desde la entidad `Hiring` persistida, utilizando el mapper.

---

## 🔄 Métodos principales

### `execute(HiringRequestDTO dto)`

- Punto de entrada del caso de uso.
- Orquesta la validación, mapeo, generación y guardado.

### `generateStandardWorkHours(Hiring hiring)`

- Lógica de generación de horas de trabajo automáticas para el primer ciclo mensual.
- Se asegura de cubrir la cantidad total de minutos contratados, incluso si eso implica pasar al mes siguiente.
