# 📦 Sistema de Inventario - Java Desktop

Aplicación de escritorio desarrollada en Java con Swing para la gestión de inventario, ventas y control de productos en pequeños negocios.

---

## 🚀 Descripción

Este proyecto es un sistema de inventario modular diseñado para facilitar el control de productos, ventas y movimientos dentro de un negocio.

Está pensado para ser escalable por fases, permitiendo agregar nuevas funcionalidades sin afectar las existentes.

---

## 🧩 Características actuales

### 📦 Inventario

* Registro de productos
* Edición de productos
* Eliminación de productos
* Visualización en tarjetas (estilo moderno)
* Buscador en tiempo real

### 🔍 Búsqueda

* Filtrado dinámico mientras se escribe
* Actualización automática de resultados

### ✏️ Edición

* Formulario de edición interactivo
* Actualización directa en base de datos

### ❌ Eliminación

* Eliminación con confirmación
* Menú contextual (clic derecho)

---

## 🏗️ Arquitectura

El proyecto sigue una estructura por capas:

```
com.inventario
│
├── modelo       → Clases (Producto, etc.)
├── dao          → Acceso a datos (ProductoDAO)
├── database     → Conexión a base de datos
├── vista        → Interfaces gráficas (Swing)
```

---

## 🛠️ Tecnologías utilizadas

* Java (JDK)
* Swing (Interfaz gráfica)
* SQLite (Base de datos local)
* NetBeans (IDE recomendado)

---

## 💾 Base de datos

* Motor: SQLite
* Base de datos local por tienda
* Preparado para múltiples tiendas (cambio dinámico)

---

## 👥 Roles del sistema

* 👑 Dueño → Control total del sistema
* 🧑‍💼 Administrador → Gestión de ventas e inventario

---

## 📌 Estado del proyecto

✔ Inventario funcional
✔ CRUD completo (Crear, Leer, Actualizar, Eliminar)
✔ Buscador en tiempo real

🚧 En desarrollo:

* Ventas (POS)
* Reportes
* Estadísticas
* Catálogo PDF
* Notificaciones

---

## 📈 Futuras mejoras

* Sistema de ventas completo
* Generación de reportes en PDF
* Estadísticas por periodo
* Control de empleados
* Sistema de cotizaciones
* Notificaciones inteligentes

---

## ▶️ Cómo ejecutar

1. Clonar el repositorio:

```
git clone https://github.com/tu-usuario/tu-repo.git
```

2. Abrir en NetBeans

3. Ejecutar el proyecto

---

## 📷 Interfaz

Diseño inspirado en aplicaciones modernas, con enfoque en simplicidad y usabilidad.

* Color principal: Azul
* Diseño basado en tarjetas
* Navegación lateral

---

## 🤝 Contribución

Este proyecto es colaborativo.
Se recomienda trabajar por módulos o fases para evitar conflictos.

---

## 📄 Licencia

Proyecto de uso educativo y desarrollo interno.

---

## 💡 Autor

Desarrollado como sistema de gestión para negocio real, enfocado en eficiencia, simplicidad y escalabilidad.
