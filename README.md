# **Chimchar - SkyCampus MVP**

- **Nombre:** Cristian Camilo Ortiz Sánchez
- **Carnet:** 1000105286
- **Correo:** `cristian.ortiz-s@mail.escuelaing.edu.co`
---

**Contexto:** La ECI tiene 5 drones del mismo modelo (DJI Mini 3). Solo transportan documentos (sobres y carpetas). Los destinos posibles son fijos: Bloque A, Bloque B, Bloque C, Bloque D y la Biblioteca. Un operador selecciona manualmente qué drone asignar a cada misión.

**Flujo básico:** El operador registra una solicitud de reparto (origen, destino, tipo de documento) → revisa qué drones están disponibles y con batería suficiente → asigna uno manualmente → el drone ejecuta la misión por ruta predefinida → el operador confirma la entrega.

**Los objetos del sistema:**

`Drone(id:String, modelo:String, bateria:int, disponible:boolean, ubicacion:String)`

`Mision(id:String, drone:Drone, origen:String, destino:String tipoCarga:TipoCarga, estado:EstadoMision)`

`TipoCarga: Enum(SOBRE, CARPETA, LIBRO)   EstadoMision: Enum(PENDIENTE EN_VUELO, ENTREGADA, FALLIDA)`

---

## 01 · STREAMS & LAMBDAS
### Filtrar y ordenar la flota de drones disponibles

#### 1. Lista de IDs de drones disponibles con batería ≥50%, ordenados de mayor a menor batería.

```java
List<String> primeraConsulta = flota.stream()
    .filter(d -> d.bateria() >= 50 && d.disponible())
    .sorted(Comparator.comparing(Drone::bateria).reversed())
    .map(Drone::id)
    .toList();
```

#### 2. ¿Hay algún drone disponible ubicado en "Bloque C"?
```java
boolean segundaConsulta = flota.stream()
    .anyMatch(p -> p.ubicacion().equals("Bloque C") && p.disponible());   
```
#### 3. Cuántos drones tienen batería crítica (menos del 20%), independientemente de su disponibilidad.
```java
long terceraConsulta = flota.stream()
    .filter(p -> p.bateria() < 20)
    .count();
```
#### 4. Lista con el ID y la batería de cada drone (formato "D-01: 85%"), para todos los drones.

```java
List<String> cuartaConsulta = flota.stream()
    .map(d -> d.id() + ": " + d.bateria() + "%")
    .toList();
```

### Respuesta del agente:

```
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
REVISIÓN — SkyCampus [Chimchar]
Reto: Reto 1 — 4 consultas sobre la flota
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

ESTADO: APROBADO ✅

Lo que está bien:

Corregiste la comparación de cadenas usando .equals() en lugar de ==, asegurando la evaluación por contenido.

El pipeline de Streams en cada consulta es correcto, conciso y utiliza las operaciones intermedias y terminales adecuadas.

Cumple estrictamente con el nivel Chimchar: sin bucles for/while, compila perfectamente y resuelve las 4 consultas solicitadas.

Antes de avanzar, responde estas preguntas sin mirar el código:

En la consulta 2, ¿por qué es computacionalmente más eficiente usar anyMatch() que hacer un filter() seguido de un findFirst().isPresent() o count() > 0?

Si intentáramos guardar el Stream<Drone> en una variable local y ejecutar la consulta 1 y la consulta 2 sobre esa misma variable, ¿qué excepción lanzaría Java en tiempo de ejecución y por qué?

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
```
---
