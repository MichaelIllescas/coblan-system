# Historias de usuario

Historias de usuario del sistema, organizadas por módulo. Sirvieron como guía de desarrollo y como base para la validación funcional.

## 1. Autenticación y registro (`auth`)

### HU-1.1

**Como** usuario, **quiero** iniciar sesión con mis credenciales, **para** acceder a las funcionalidades del sistema según mi rol.

Criterios de aceptación:

- El login debe validar email y contraseña.
- Si las credenciales son válidas, se debe redirigir al panel según el rol del usuario.
- Si el usuario está deshabilitado, se debe impedir el acceso con un mensaje informativo.
- Si las credenciales son incorrectas, debe mostrarse un error genérico sin indicar cuál campo falló.

### HU-1.2

**Como** administrador, **quiero** poder deshabilitar usuarios, **para** evitar que personas no autorizadas accedan al sistema.

Criterios de aceptación:

- El sistema debe mostrar una lista de usuarios con su estado actual (activo/inactivo).
- El administrador debe poder cambiar el estado a “deshabilitado” sin eliminar al usuario.
- Un usuario deshabilitado no podrá iniciar sesión.
- El cambio de estado debe quedar registrado con fecha y responsable.

### HU-1.3

**Como** sistema, **quiero** registrar automáticamente un usuario administrador al iniciar por primera vez, **para** garantizar que exista un usuario con privilegios completos desde el comienzo.

Criterios de aceptación:

- El usuario admin debe crearse con valores definidos en propiedades del sistema.
- Solo debe generarse si no existe ya un usuario con rol ADMIN.
- El usuario debe poder iniciar sesión inmediatamente y acceder a la gestión del sistema.

## 2. Clientes (`customers`)

### HU-2.1

**Como** usuario, **quiero** registrar nuevos clientes con sus datos personales y de contacto, **para** mantener organizada la información de quienes solicitan servicios.

Criterios de aceptación:

- El formulario debe validar campos obligatorios: nombre, apellido, número de documento, teléfono, dirección y email.
- El sistema debe mostrar mensajes específicos si algún campo no es válido.
- El cliente debe guardarse correctamente en la base de datos.
- Tras el registro, debe mostrarse una confirmación y reflejarse en la lista de clientes.

### HU-2.2

**Como** usuario, **quiero** consultar la lista de clientes registrados, **para** visualizar, buscar y acceder fácilmente a la información de contacto.

Criterios de aceptación:

- El listado debe mostrar los datos básicos: nombre, apellido, documento, teléfono y email.
- Debe incluir un campo de búsqueda por nombre o número de documento.
- El sistema debe permitir paginación o scroll si hay muchos registros.

### HU-2.3

**Como** usuario, **quiero** editar los datos de un cliente registrado, **para** mantener actualizada su información personal y de contacto.

Criterios de aceptación:

- El sistema debe permitir modificar nombre, apellido, dirección, teléfono, email y documento.
- La validación debe ser igual a la del registro.
- Al guardar los cambios, el sistema debe actualizar la información en el listado.

### HU-2.4

**Como** usuario, **quiero** poder deshabilitar o anular un cliente, **para** que ya no aparezca en los listados operativos si dejó de contratar servicios.

Criterios de aceptación:

- El sistema debe mostrar un botón para anular en el listado o detalle del cliente.
- Al anular un cliente, debe cambiar su estado a “inactivo” sin eliminarlo de la base de datos.
- El cliente anulado no debe mostrarse en los formularios de selección ni en las contrataciones nuevas.
- Se debe poder visualizar un listado completo que incluya también los clientes anulados (filtro opcional).
- El sistema debe permitir reactivar clientes anulados.

## 3. Empleados (`employees`)

### HU-3.1

**Como** usuario, **quiero** registrar nuevos empleados con sus datos personales y de contacto, **para** tenerlos disponibles para futuras asignaciones de servicios.

Criterios de aceptación:

- El formulario debe validar los siguientes campos obligatorios: nombre, apellido, número de documento, teléfono, dirección y email.
- El sistema debe mostrar mensajes claros si algún campo es inválido.
- El empleado debe guardarse correctamente en la base de datos.
- Tras el registro exitoso, debe mostrarse una confirmación y reflejarse en el listado.

### HU-3.2

**Como** usuario, **quiero** ver la lista de empleados registrados, **para** identificar a quiénes están disponibles para trabajar.

Criterios de aceptación:

- El listado debe mostrar nombre, documento, teléfono y estado del empleado.
- Debe incluir paginación si la cantidad de registros lo requiere.
- Debe contar con un campo de búsqueda por nombre y filtro por estado (activo/inactivo).
- Solo los empleados activos deben mostrarse en formularios operativos (ej: asignación de horas).

### HU-3.3

**Como** usuario, **quiero** editar los datos de un empleado, **para** mantener actualizada su información laboral y de contacto.

Criterios de aceptación:

- El sistema debe permitir modificar nombre, apellido, documento, teléfono, dirección y email.
- Las mismas validaciones del registro deben aplicarse en la edición.
- Al guardar los cambios, se debe actualizar la información en el listado inmediatamente.

### HU-3.4

**Como** usuario, **quiero** poder deshabilitar empleados, **para** que no aparezcan en los listados operativos cuando ya no estén disponibles para trabajar.

Criterios de aceptación:

- El sistema debe permitir cambiar el estado de un empleado a “inactivo” sin eliminarlo.
- Los empleados inactivos no deben mostrarse en los formularios de asignación de horas o contrataciones.
- En el listado general debe poder filtrarse por estado (activo/inactivo/todos).
- El sistema debe permitir reactivar un empleado cuando sea necesario.
- El cambio de estado debe quedar registrado con fecha y responsable (opcional).

## 4. Gastos (`expenses`)

### HU-4.1

**Como** usuario, **quiero** registrar nuevos gastos con su nombre, monto, fecha y detalles, **para** llevar un control financiero detallado de la empresa.

Criterios de aceptación:

- El formulario debe validar los siguientes campos obligatorios:
- Nombre: requerido, máximo 100 caracteres.
- Descripción: opcional, hasta 255 caracteres.
- Monto: requerido, mayor a cero, con hasta dos decimales.
- Fecha: requerida, no puede ser futura.
- Observaciones: opcionales, hasta 500 caracteres.
- El gasto debe guardarse correctamente en la base de datos.
- Al registrar exitosamente, debe mostrarse un mensaje de confirmación.

### HU-4.2

**Como** usuario, **quiero** consultar los gastos registrados filtrando por mes y año, **para** analizar los costos operativos del período seleccionado.

Criterios de aceptación:

- El sistema debe permitir seleccionar un mes y un año.
- El listado debe mostrar los siguientes datos por gasto: nombre, monto, fecha, descripción y observaciones.
- Debe mostrarse el total acumulado de gastos del período consultado.
- El usuario debe poder ordenar los resultados por fecha o monto.

### HU-4.3

**Como** usuario, **quiero** editar un gasto registrado, **para** corregir errores o actualizar su información.

Criterios de aceptación:

- El sistema debe permitir modificar los siguientes campos: nombre, descripción, monto, fecha y observaciones.
- Las validaciones deben ser iguales a las del registro.
- Al guardar los cambios, se debe actualizar el gasto en la base de datos.
- Debe mostrarse un mensaje de confirmación tras la edición exitosa.

### HU-4.4

**Como** usuario, **quiero** eliminar un gasto registrado, **para** quitar del sistema aquellos que fueron cargados por error o ya no corresponden.

Criterios de aceptación:

- El sistema debe solicitar confirmación antes de eliminar el gasto.
- Una vez confirmado, el gasto debe eliminarse de la base de datos.
- El gasto eliminado no debe aparecer más en los listados ni en el total acumulado.
- Debe mostrarse un mensaje de eliminación exitosa.

## 5. Contrataciones (`hiring`)

### HU-5.1

**Como** usuario, **quiero** registrar una nueva contratación indicando el cliente, servicio, días de prestación, horario y empleado asignado, **para** automatizar la generación de horas de trabajo mensuales y asegurar que el empleado esté disponible en ese cronograma.

Criterios de aceptación:

- El formulario debe solicitar en este orden:
- Días de la semana de prestación (ej: lunes y miércoles)
- Horario fijo (ej: 10:00 hs)
- Cliente
- Servicio
- Fecha de inicio
- Al seleccionar un empleado, el sistema debe verificar nuevamente su disponibilidad en las fechas generadas desde la fecha de inicio.
- Si existe conflicto de horario con otra contratación, debe impedir el registro e indicar los días/hora en conflicto.
- Al completar el registro, deben generarse automáticamente las WorkHour del mes en curso, asociadas al empleado, contratación y servicio, de tipo STANDARD.
- Debe mostrarse un mensaje de confirmación si el registro fue exitoso.

### HU-5.2

**Como** sistema, **quiero** renovar automáticamente las contrataciones activas al comenzar cada nuevo mes, **para** asegurar la continuidad de los servicios contratados sin necesidad de intervención manual.

Criterios de aceptación:

- El sistema debe generar nuevas WorkHour correspondientes al nuevo mes, replicando el patrón de días y horario configurado en la contratación.
- Solo deben renovarse contrataciones con estado “Activo”.
- No deben duplicarse horas ya generadas.
- Las nuevas horas deben vincularse correctamente a la contratación y al empleado asignado.

### HU-5.3

**Como** usuario, **quiero** cancelar una contratación vigente, **para** finalizar la prestación del servicio a solicitud del cliente.

Criterios de aceptación:

- Debe existir la opción de cancelar una contratación desde su vista detallada.
- El sistema debe permitir registrar un motivo de cancelación (opcional).
- La contratación debe pasar al estado “Cancelada” y sus horas futuras deben quedar anuladas o sin efecto.
- No debe ser posible editar o renovar una contratación cancelada.

### HU-5.4

**Como** usuario, **quiero** agregar horas de trabajo extra a una contratación existente, **para** cumplir con nuevas necesidades operativas solicitadas por el cliente.

Criterios de aceptación:

- Las horas extra deben poder agregarse manualmente desde la vista de detalle de la contratación.
- Cada hora extra debe tener: fecha, hora, duración y tipo = “EXTRA”.
- Deben distinguirse visualmente de las horas generadas automáticamente (tipo = “STANDARD”).
- Se debe permitir asignar un empleado a la hora extra o dejarla sin asignar para hacerlo luego.

### HU-5.5

**Como** usuario, **quiero** confirmar las horas trabajadas de una contratación y reasignar el empleado si es necesario, **para** reflejar correctamente la ejecución real del servicio.

Criterios de aceptación:

- El sistema debe permitir marcar una hora como “CONFIRMADA”.
- Al confirmar, el usuario debe ingresar manualmente la fecha y hora real en que se realizó la tarea.
- Se debe permitir cambiar el empleado asignado a la hora antes de confirmarla.
- Una vez confirmada, la hora no debe poder ser modificada ni eliminada.
- Debe existir un listado de horas pendientes por cada servicio con filtros por fecha, contratación o empleado.

## 6. Reportes (`reports`)

### HU-6.1

**Como** usuario, **quiero** generar reportes mensuales de contrataciones, **para** analizar la evolución de los servicios prestados.

Criterios de aceptación:

- El sistema debe permitir seleccionar un mes y año específicos.
- El reporte debe incluir contrataciones activas y canceladas en el período seleccionado.
- Debe mostrarse: cliente, servicio, estado, fecha de inicio, y fecha de cancelación si aplica.
- El reporte debe poder visualizarse y descargarse como PDF.

### HU-6.2

**Como** usuario, **quiero** visualizar las horas trabajadas por cada empleado por mes, **para** evaluar su carga laboral y desempeño.

Criterios de aceptación:

- El sistema debe permitir seleccionar un mes y año.
- Las horas deben agruparse por empleado.
- Se debe mostrar la cantidad total de horas por tipo: STANDARD y EXTRA.
- Debe ser posible ver el reporte en pantalla y descargarlo como PDF.

### HU-6.3

**Como** usuario, **quiero** generar reportes financieros mensuales y anuales, **para** evaluar ingresos reales, gastos, pagos de salarios y balance del negocio.

Criterios de aceptación:

- El sistema debe permitir seleccionar un mes o un año completo.
- El reporte debe incluir:
- Ingresos reales: calculados a partir de las horas confirmadas, tanto en contrataciones activas como canceladas.
- Para contrataciones canceladas, solo deben considerarse las horas que hayan sido confirmadas.
- El ingreso por hora debe calcularse dividiendo el valor mensual del servicio por la cantidad estándar de horas.
- Gastos registrados durante el período.
- Salarios pagados a empleados.
- Balance final: ingresos – (gastos + salarios).
- El reporte debe poder descargarse como PDF.

### HU-6.4 (generado en el frontend)

**Como** usuario, **quiero** descargar un comprobante mensual en PDF con los detalles de una contratación, **para** registrar o entregar al cliente un resumen del servicio prestado en ese mes.

Criterios de aceptación:

- Desde la vista de detalle de una contratación, debe poder seleccionarse un mes para generar su comprobante.
- El comprobante debe incluir:
- Datos del cliente y del servicio.
- Empleado asignado.
- Horas confirmadas durante ese mes (fecha, hora, duración, observaciones si las hay).
- Total de horas ejecutadas y valor correspondiente.
- Si la contratación fue cancelada, solo deben mostrarse las horas confirmadas hasta la fecha de cancelación.
- El documento debe estar diseñado con un formato profesional tipo factura y descargarse en PDF.

## 7. Pagos de sueldos (`salaryPayments`)

### HU-7.1

**Como** usuario, **quiero** registrar pagos de salarios a empleados, **para** dejar constancia formal de la remuneración correspondiente a cada mes, incluso si se paga en varias partes.

Criterios de aceptación:

- El formulario debe requerir:
- Empleado
- Monto del pago
- Mes y año al que corresponde
- Fecha de pago, seleccionada manualmente por el usuario
- El sistema debe permitir registrar múltiples pagos para el mismo empleado y mes.
- Cada pago debe guardarse con su fecha, monto y período correspondiente.
- El sistema debe mostrar un mensaje de confirmación tras el registro exitoso.

### HU-7.2

**Como** usuario, **quiero** consultar los pagos de salarios realizados, **para** verificar el cumplimiento de las obligaciones mensuales con los empleados.

Criterios de aceptación:

- Debe poder filtrarse por empleado, mes y año.
- El listado debe mostrar:
- Empleado
- Monto pagado
- Mes y año correspondiente
- Fecha exacta de cada pago
- El sistema debe calcular y mostrar el total acumulado del mes por empleado.
- Debe incluirse la opción de descargar el listado como PDF para respaldo o auditoría.

### HU-7.3

**Como** usuario, **quiero** poder editar un pago de salario registrado, **para** corregir errores en el monto, fecha o período declarado.

Criterios de aceptación:

- El sistema debe permitir modificar los siguientes campos de un pago existente:
- Monto
- Mes y año correspondiente
- Fecha de pago
- Las mismas validaciones del registro deben aplicarse al editar.
- Al guardar los cambios, debe actualizarse la información en los reportes y listados.
- El sistema debe registrar internamente la modificación con fecha de edición.

### HU-7.4

**Como** usuario, **quiero** poder eliminar un pago de salario registrado, **para** quitar del sistema aquellos que fueron cargados por error o anulados internamente.

Criterios de aceptación:

- El sistema debe solicitar confirmación antes de eliminar un pago.
- Una vez eliminado, el pago debe desaparecer de los listados y cálculos acumulados.
- No debe quedar rastro visual en reportes, pero puede conservarse internamente si se desea trazabilidad (opcional).
- El sistema debe mostrar un mensaje de eliminación exitosa.

## 8. Servicios (`services`)

### HU-8.1

**Como** usuario, **quiero** registrar los servicios que ofrece la empresa, **para** poder asociarlos a futuras contrataciones con sus datos correctamente definidos.

Criterios de aceptación:

- El formulario debe requerir los siguientes campos:
- name: nombre del servicio (obligatorio)
- monthlyHours: cantidad de horas mensuales previstas (obligatorio, numérico positivo)
- totalAmount: monto total mensual del servicio (obligatorio, mayor a cero)
- note: observaciones o nota aclaratoria (opcional)
- Todos los campos obligatorios deben validarse correctamente.
- El servicio debe guardarse en la base de datos y quedar disponible para nuevas contrataciones.
- Debe mostrarse un mensaje de confirmación tras el registro exitoso.

### HU-8.2

**Como** usuario, **quiero** consultar la lista de servicios disponibles, **para** poder elegir uno al momento de registrar una contratación.

Criterios de aceptación:

- El sistema debe mostrar una tabla/listado con: nombre del servicio, cantidad de horas mensuales y monto total.
- Debe incluir un buscador por nombre.
- El listado debe ser ordenable y filtrable para facilitar su selección.

### HU-8.3

**Como** usuario, **quiero** poder editar un servicio registrado, **para** corregir o actualizar sus datos antes de ser usado en nuevas contrataciones.

Criterios de aceptación:

- El sistema debe permitir modificar los siguientes campos:
- Nombre del servicio
- Cantidad de horas mensuales
- Monto total mensual
- Nota u observaciones
- El sistema debe validar que los datos editados sean consistentes.
- Los cambios deben guardarse correctamente y actualizarse en los listados.
- Si el servicio ya fue usado en contrataciones, las ediciones no deben afectar registros pasados.

### HU-8.4

**Como** usuario, **quiero** poder eliminar un servicio registrado, **para** remover del sistema aquellos que ya no se utilizarán y no estén en uso.

Criterios de aceptación:

- El sistema debe permitir eliminar un servicio solo si no está vinculado a ninguna contratación existente.
- Al intentar eliminar un servicio en uso, debe mostrarse un mensaje indicando que no puede ser borrado.
- El sistema debe solicitar confirmación antes de eliminar definitivamente un servicio.
- Una vez eliminado, el servicio debe desaparecer de los listados y no estar disponible para nuevas contrataciones.

## 9. Usuarios (`users`)

### HU-9.1

**Como** administrador, **quiero** registrar nuevos usuarios con sus datos personales y rol, **para** otorgarles acceso al sistema según su función.

Criterios de aceptación:

- El formulario debe requerir:
- Nombre
- Apellido
- Número de documento
- Teléfono
- Dirección
- Email
- Contraseña (mínimo 6 caracteres)
- Rol (ADMIN, USER, etc.)
- Todos los campos deben validarse correctamente.
- El usuario debe guardarse en la base de datos y estar habilitado para iniciar sesión.
- Debe mostrarse un mensaje de confirmación tras el registro exitoso.

### HU-9.2

**Como** administrador, **quiero** ver y editar la información de los usuarios registrados, **para** mantener actualizados sus datos y roles dentro del sistema.

Criterios de aceptación:

- Se debe permitir editar: nombre, apellido, teléfono, dirección, rol y email.
- Las modificaciones deben validarse correctamente.
- Las ediciones no deben afectar el historial ni las asociaciones previas del usuario.
- Debe mostrarse una confirmación de éxito tras la actualización.

### HU-9.3

**Como** administrador, **quiero** deshabilitar usuarios en lugar de eliminarlos, **para** preservar su historial y evitar accesos no autorizados.

Criterios de aceptación:

- El estado del usuario debe poder cambiarse a “deshabilitado”.
- Un usuario deshabilitado no podrá iniciar sesión.
- Su historial debe seguir accesible en reportes o auditorías.
- El sistema debe permitir reactivarlo si se necesita.
- El cambio de estado debe ser visible en el listado de usuarios.

### HU-9.4

**Como** usuario, **quiero** poder actualizar mis propios datos personales, **para** mantener mi información actualizada dentro del sistema.

Criterios de aceptación:

- El sistema debe permitir modificar nombre, apellido, teléfono, dirección y contraseña.
- Email y documento deben estar protegidos (no editables por el propio usuario).
- Las validaciones deben aplicarse igual que en el registro.
- Debe mostrarse un mensaje de confirmación tras guardar.

### HU-9.5

**Como** administrador, **quiero** poder restablecer la contraseña de un usuario registrado, **para** resolver problemas de acceso o realizar cambios administrativos.

Criterios de aceptación:

- El sistema debe permitir al administrador definir una nueva contraseña para cualquier usuario.
- Debe validarse la longitud mínima y formato seguro de la nueva contraseña.
- El usuario debe poder iniciar sesión con la nueva contraseña inmediatamente.
- Puede notificarse por correo (opcional).

### HU-9.6

**Como** usuario, **quiero** recuperar mi contraseña mediante un enlace enviado a mi email, **para** poder acceder al sistema si la olvidé.

Criterios de aceptación:

- El formulario de recuperación debe solicitar el email registrado.
- El sistema debe enviar un enlace seguro y único al correo del usuario.
- El enlace debe permitir establecer una nueva contraseña y tener una validez limitada.
- Al finalizar, debe mostrarse un mensaje de éxito y permitir iniciar sesión.
