# Módulo de Pagos de Salarios (`salaryPayments`)

## 📄 Descripción

Este módulo permite registrar, consultar, actualizar y eliminar pagos de salarios realizados a empleados. Ofrece una trazabilidad clara del monto, fecha, periodo y observaciones, con validaciones estrictas y documentación Swagger.

---

## 🧩 Funcionalidades principales

- Registrar un nuevo pago de salario.
- Listar todos los pagos registrados.
- Obtener detalles de un pago por su ID.
- Actualizar un pago existente.
- Eliminar un pago.
- Relación directa con la entidad `Employee`.
- Validaciones y manejo de errores detallados.
- Soporte para periodos mensuales con `YearMonth`.

---

## 🧠 Casos de uso

| Caso de Uso                         | Descripción                                                   |
|------------------------------------|---------------------------------------------------------------|
| `CreateSalaryPaymentUseCase`       | Registra un nuevo pago de salario para un empleado.           |
| `GetAllSalaryPaymentsUseCase`      | Devuelve todos los pagos registrados.                         |
| `GetSalaryPaymentByIdUseCase`      | Obtiene un pago específico por ID.                            |
| `UpdateSalaryPaymentUseCase`       | Actualiza los datos de un pago existente.                     |
| `DeleteSalaryPaymentUseCase`       | Elimina un pago por su ID.                                    |

---

## 🗃️ Entidad principal

```java
@Entity
public class SalaryPayment {
    Long id;
    Employee employee;
    BigDecimal amount;
    LocalDate paymentDate;
    YearMonth period;
    String note;
}
```

---

## 📥 DTOs

- `SalaryPaymentRequestDTO`: para crear y actualizar un pago.
- `SalaryPaymentResponseDTO`: para devolver datos de un pago.

---

## 🔁 Mapper

- `SalaryPaymentMapper`: transforma entre entidad y DTOs.

---

## 🌐 Endpoints

| Método | Ruta                      | Descripción                          |
|--------|---------------------------|--------------------------------------|
| GET    | `/salary-payments`       | Listar todos los pagos.              |
| GET    | `/salary-payments/{id}`  | Obtener pago por ID.                 |
| POST   | `/salary-payments`       | Registrar un nuevo pago.             |
| PUT    | `/salary-payments/{id}`  | Actualizar un pago existente.        |
| DELETE | `/salary-payments/{id}`  | Eliminar un pago por ID.             |

---

## 🚫 Manejo de errores

- Se utiliza `ErrorResponseDTO` para devolver errores estructurados.
- Codificación de errores con HTTP (`400`, `404`, etc.) según contexto.
