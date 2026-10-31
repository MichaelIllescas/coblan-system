# Coblan System · Frontend

Panel administrativo del sistema, desarrollado con React 19 y Vite.

Para la descripción general, el stack completo y cómo ejecutar todo el sistema, ver el [README principal](../README.md).

## Ejecución

```bash
npm install
npm run dev
```

El panel queda en `http://localhost:5173`. La URL de la API se configura en [`src/Services/apiClient.js`](src/Services/apiClient.js).

## Scripts

| Comando | Descripción |
|---|---|
| `npm run dev` | Servidor de desarrollo |
| `npm run build` | Build de producción en `dist/` |
| `npm run preview` | Sirve el build localmente |
| `npm run lint` | Análisis estático con ESLint |

## Estructura

```
src/
├── Features/      una carpeta por módulo de negocio
├── Components/    componentes reutilizables (tablas, modales, filtros, PDF)
├── Layouts/       sidebar, navbar y estructura de página
├── Routes/        rutas y protección por sesión
├── Context/       estado de autenticación
├── Services/      cliente HTTP contra la API
├── hooks/
└── utils/
```
