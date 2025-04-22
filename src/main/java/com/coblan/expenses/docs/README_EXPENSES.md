# Módulo de Gastos (`expenses`)

## 📄 Descripción

El módulo de gastos permite registrar, consultar, actualizar y eliminar egresos asociados a la empresa. Está orientado a llevar un control contable general sobre los gastos realizados, incluyendo montos, fechas, conceptos y observaciones.

---

## 🧩 Funcionalidades principales

- Registrar un nuevo gasto.
- Listar todos los gastos registrados.
- Obtener un gasto por su ID.
- Actualizar un gasto existente.
- Eliminar un gasto.
- Validación de datos con mensajes de error detallados.
- Documentación Swagger para todos los endpoints.

---

## 🧠 Casos de uso

| Caso de Uso               | Descripción                                                 |
|--------------------------|-------------------------------------------------------------|
| `CreateExpenseUseCase`   | Registra un nuevo gasto en el sistema.                      |
| `GetAllExpensesUseCase`  | Devuelve la lista completa de gastos registrados.           |
| `GetExpenseByIdUseCase`  | Devuelve los detalles de un gasto específico por su ID.     |
| `UpdateExpenseUseCase`   | Actualiza los datos de un gasto existente.                  |
| `DeleteExpenseUseCase`   | Elimina un gasto existente por su ID.                       |

---

## 🗃️ Entidad principal

```java
@Entity
public class Expense {
    Long id;
    String name;
    String description;
    BigDecimal amount;
    LocalDate date;
    String notes;
}
```

---

## 📥 DTOs

- `ExpenseRequestDTO`: utilizado para crear y actualizar un gasto.
- `ExpenseResponseDTO`: utilizado para devolver datos de un gasto.

---

## 🔁 Mapper

- `ExpenseMapper`: encargado de transformar entre la entidad y los DTOs.

---

## 🌐 Endpoints

| Método | Ruta              | Descripción                  |
|--------|-------------------|------------------------------|
| GET    | `/expenses`       | Listar todos los gastos.     |
| GET    | `/expenses/{id}`  | Obtener un gasto por ID.     |
| POST   | `/expenses`       | Registrar un nuevo gasto.    |
| PUT    | `/expenses/{id}`  | Actualizar un gasto.         |
| DELETE | `/expenses/{id}`  | Eliminar un gasto por ID.    |

---

## 🚫 Manejo de errores

- Los errores de validación o entidad no encontrada se devuelven en formato `ErrorResponseDTO`, con estructura clara para el frontend.
- Se utilizan códigos de estado HTTP apropiados (`400`, `404`, `409`, etc.).
