# Renovación Automática de Contrataciones (`HiringRenewalTask`)

## Descripción General

Esta clase tiene como propósito verificar periódicamente si las contrataciones activas (`Hiring`) están listas para ser renovadas automáticamente. La renovación solo ocurre si se cumplieron las horas mensuales pactadas del servicio contratado.

## Ubicación

`package com.coblan.hiring.task`

## Dependencias

- `HiringRepository`: Para obtener las contrataciones activas.
- `RenewHiringUseCase`: Caso de uso que realiza la renovación propiamente dicha.

## Lógica Principal

```java
for (Hiring hiring : activas) {
    int totalMinutes = hiring.getService().getMonthlyHours() * 60;
    int generatedMinutes = hiring.getWorkHours().stream()
        .filter(w -> w.getType() == WorkHourType.STANDARD)
        .mapToInt(WorkHour::getDuration)
        .sum();

    if (generatedMinutes < totalMinutes) {
        System.out.printf("No se renovó Hiring ID %d: aún le faltan %d minutos para completar su ciclo.%n",
                hiring.getId(), totalMinutes - generatedMinutes);
        continue;
    }

    // Si se cumplió el mínimo requerido de minutos, se procede a renovar:
    renewHiringUseCase.execute(hiring.getId());
}
```

### Explicación Paso a Paso

1. **Iterar sobre contrataciones activas:**
   - Se busca renovar solo aquellas que están en estado `ACTIVA`.

2. **Cálculo del total de minutos requeridos:**
   - Cada servicio tiene una cantidad de horas mensuales configuradas (`monthlyHours`).
   - Se convierte a minutos multiplicando por 60.

3. **Cálculo de minutos trabajados reales:**
   - Se suman los minutos de las horas de trabajo (`WorkHour`) del tipo `STANDARD`.
   - Se ignoran otros tipos como extras o canceladas.

4. **Validación:**
   - Si no se alcanzó el total de minutos requeridos, se omite la renovación de esa contratación.
   - Se imprime un mensaje indicando cuántos minutos faltan.

5. **Renovación:**
   - Si se alcanzó el tiempo requerido, se ejecuta la renovación mediante el `RenewHiringUseCase`.

## Programación del Proceso

Este proceso puede estar anotado con `@Scheduled` para ejecutarse automáticamente, por ejemplo, una vez al mes.

```java
@Scheduled(cron = "0 0 0 1 * ?") // Ejecuta el 1º de cada mes a la medianoche
public void renovarContrataciones() {
    ...
}
```

## Consideraciones

- Esta validación evita renovar contrataciones que aún no cumplieron su ciclo de trabajo.
