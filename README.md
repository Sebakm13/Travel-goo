# Travel Go
## 1. Caso elegido y alcance
- **Caso:** Turismo sustentable / agencia de viajes digital
- **Alcance EP3:** Diseño/UI, validaciones, navegación, estado, persistencia, recursos nativos(cámara/galeria), animaciones, consumo de API, almacenamiento local de datos y perfiles, integracion de  enpoints /me

Travel Go busca que el usuario pueda explorar multimples paquetes turísticos, reservar aventuras y experiencias más haya de tu imaginacion, realizar pagos para ahorarte tiempo, gestionar itinerarios y mantener un perfil limpio y actualizado, todo de una manera natural y con visuales atractivos.

## 2. Requisitos y ejecución
- **Stack:** Frontend: React(SPA) + React Router + React Query
UI: React-Bootsstrap
Animaciones: Framer Motion
Backend/API: Xano(Endpoints REST)
Persistencia local: LocalStorage
Autenticación:JWT
- **Instalación:** para instalarlo tiene que usar
- git clone
- cd TravelGo
- npm install
- **Ejecución:**
- para ejecutar use npm start
- los perfiles de prueba (por venir)
## 3. Arquitectura y flujo
- **Estructura carpetas** 
Travel Go 
├─app
  ├─src
    ├─androidTest/java/com/example/travelgo
      ├─ ExampleInstrumentedTest.kt
    ├─main
      ├─Java/com/example/travelgo
        ├─data
          ├─api
            ├─ApiClient.kt
            ├─TravelGoApi.kt
          ├─models
            ├─AuthResponse.kt
            ├─PaqueteTuristico.kt
            ├─Reserva.kt
            ├─User.kt
          ├─repository
            ├─TravelGoRepository.kt
        ├─ui
          ├─navigation
            ├─AppNavHost.kt
            ├─NavRoutes.kt
          ├─screens
            ├─DetallePaqueteScreen.kt
            ├─HomeScreen.kt
            ├─LoginScreen.kt
            ├─PerfilScreen.kt
            ├─ReservasScreen.kt
          ├─theme
           ├─Color.kt
           ├─Theme.kt
           ├─Type.kt
        ├─viewmodel
          ├─AuthViewModel.kt
          ├─PaquetesViewModel.kt
        ├─MainActivity.kt
      ├─res
        ├─drawable
          ├─ic-launcher_background.xml
          ├─ic_lounder_foreground.xml
        ├─minimap-antdpi-v26
          ├─ic_launcher.xml
          ├─ic_launcher_round.xml
        ├─minimap-hdpi
          ├─ic_launcer.webp
          ├─ic_launcer_round.webp
        ├─minimap-mdpi
          ├─ic_launcher.webp
          ├─ic_launcher_round.webp
        ├─minimap-xhdpi
          ├─ic_launcher.webp
          ├─ic_launcher_round.webp
        ├─minimap-xxhdpi
          ├─ic_launcher.webp
          ├─ic_launcher_round.webp
        ├─minimap-xxxhdpi
          ├─ic_launcher.webp
          ├─ic_launcher_round.webp
        ├─values
          ├─colors.xml
          ├─strings.xml
          ├─themes.xml
        ├─xml
          ├─backup_rules.xml
          ├─data_extraction_rules.xml
      ├─AndroidManifest.xml
      ├─ExampleInstrumentedTest.kt
    ├─test/java/com/example/travelgo
      ├─ExampleUnitTest.kt
  ├─build.gradle.kts
  ├─proguard-rules.pro
├─gradle
  ├─wrapper
    ├─gradle-wrapper.jar
    ├─gradle_wrapper.properties
  ├─libs.versions.toml
├─build.gradle.kts
├─gradle.properties
├─gradlew
├─gradlew.bat
├─settings.gradle.kts
- **Gestión de estado**: 
Local:estado de formularios y modales
Global: usuario autentificado, carrito de reservas, datos de paquetes
Flujo de datos:fetch - store global/local - UI reactiva - persistencia
- **Navegación**: 
Srack principal para pantallas: home - paquete - reserva - pago - itinerario
Tabs opcionales: home / mis reservas / perfil
deep link para acceso directo a itinerario o paquete
## 4. Funcionalidades
- Formulario validado: Registro de cliente y reservas con validacion de campos obligatorios, email, contraseñas y fechas.
- Navegación y backstack: soporte de volver a pantallas anteriores conservando estado y datos ingresados.
- Gestión de estado: manejo de carga/exito/error al consumir API y en formilarios.
- **Persistencia local** CRUD de reservas, itinerarios y perfil; almacenamiento de imagen de perfil con fallback si no hay cámara o galería.
**almacenamiento de imagen de perfil**:
- **Recursos nativos**: cámara y galería para subir foto de perfil, permisos gestionados con fallback.
- **Animaciones**: transición entre pantallas, carga de tarjetas de paquetes, feedback de botones
- **Consumo de API** endpints de autenticación (/auth/signup, /auth/login, /auth/me), paquetes turísticos, reservas y pago.
## 5. Endpoints
**Base URL:** `https://x8ki-letl-twmt.n7.xano.io/api:Rfm_61dW\`
| Método | Ruta          | Body                                        | Respuesta                                                           |
| ------ | ------------- | ------------------------------------------- | ------------------------------------------------------------------- |
| POST   | /auth/signup  | `{ email, password, name? }`                | `201 { authToken, user: { id, email, name, avatarUrl? } }`          |
| POST   | /auth/login   | `{ email, password }`                       | `200 { authToken, user: { id, email, name, avatarUrl? } }`          |
| GET    | /auth/me      | - (requiere header Authorization)           | `200 { id, email, name, avatarUrl?, reservas: [...] }`              |
| GET    | /packages     | -                                           | `200 [{ id, nombre, descripcion, precio, disponibilidad, imagen }]` |
| GET    | /packages/:id | -                                           | `200 { id, nombre, descripcion, precio, itinerario, imagen }`       |
| POST   | /reservations | `{ userId, packageId, fecha, numPersonas }` | `201 { id, estado, fecha, paquete }`                                |
| POST   | /payments     | `{ reservationId, metodo, monto }`          | `200 { id, estado, fecha, monto }`                                  |

## 6. User flows
- Flujo principal:
-  Usuario visita Home → Explora paquetes.
-  Selecciona paquete → Revisa itinerario y detalles → Click en “Reservar”.
-  Completa formulario de reserva → Confirma pago → Reserva registrada y persistida.
-  Perfil actualizado → Usuario puede ver itinerario y estado de reservas.
    *Casos de error:
      *Intentar reservar sin login → redirigir a /login.
      *Pago rechazado → mostrar mensaje de error y opción de reintentar.
      *Formulario incompleto → validar campos y mostrar alertas.
