# RideSmart V2 🚲⚡  
### Plataforma modular para gestión de reserva, uso y pago de bicicletas inteligentes

RideSmart V2 es un sistema diseñado bajo arquitectura modular (Spring Modulith) que gestiona usuarios, bicicletas, estaciones, reservas, viajes y pagos.  
El proyecto integra envío de correos, verificación de usuarios, cálculo de costos, redistribución de bicicletas y pagos vía Stripe.

---

## 🧱 Arquitectura general

El sistema se organiza en módulos independientes, cada uno con responsabilidades claras:

### 🔐 User Module
- Registro y gestión de usuarios  
- Generación de tokens de verificación  
- Publicación del evento `UserCreatedEvent`  
- Estados de cuenta (PENDING, ACTIVE)

### 📧 Email Module
- Envío de correos usando `JavaMailSender`

### 🚲 Bicicleta Module
- CRUD de bicicletas  
- Cambios de estado: *DISPONIBLE, NO DISPONIBLE, EN USO*  
- Reubicación y bloqueo/desbloqueo  
- Actualización de batería  

### 🏙 Estación Module
- Gestión de estaciones  
- Estados y redistribución de bicicletas

### 📅 Reserva Module
- Creación inteligente de reservas  
- Prevención de múltiples reservas activas  
- Asignación automática de bicicletas disponibles  
- Expiración, cancelación y cumplimiento

### 🧭 Viaje Module
- Inicio y finalización del viaje  
- Cálculo de duración  
- Cálculo de costo según tipo: *LARGO*, *ULT. MILLA*  
- Habilitación/bloqueo de bicicleta  
- Registro en base de datos

### 💳 Pago Module
- Manejo de pagos en COP y USD  
- Integración con Stripe  
- Validación y almacenamiento del pago

---

## 🛠️ Tecnologías usadas

- Java 21  
- Spring Boot 
- Spring Modulith  
- H2  
- Hibernate / JPA  
- Stripe Java SDK  
- JavaMailSender  
- Lombok  
-  ModelMapper  
- JUnit 5 + Mockito

---
Este proyecto fue desarrollado por Moisés Guerra, David Rondon y Felipe Marquez
