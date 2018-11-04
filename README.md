# FEM. Autenticación, persistencia y conexión con servicios remotos

Antiguamente, los marinos de guardia se encargaban de registrar en cuadernos las incidencias que sucedían durante los largos viajes en alta mar y sus coordenadas. Estos cuadernos se guardaban en la “bitácora”, un mueble empernado donde estaban resguardados de las inclemencias de alta mar. Los cuadernos de bitácora han ido evolucionando hasta nuestros días. En la actualidad los dispositivos móviles se utilizan en el campo de la logística para gestionar la trazabilidad de la paquetería, su localización, su hora de entrega, su temperatura, la conformidad de la entrega (firma) y otras variables que se pueden registrar mediante los sensores integrados en el propio teléfono (cámara de fotos, GPS, brújula, temperatura, biosensores, etc).

La presente tarea pretende abarcar los diferentes conceptos y técnicas estudiados en la asignatura. Para conseguir este objetivo se propone al alumno el desarrollo de un Cuaderno de Bitácora en Android.

Se pide realizar una aplicación móvil para una empresa de repartos (p.ej. deliveroo, la casa del libro, amazon, reparto de helados, etc…) que gestione la trazabilidad de sus productos, entregas y que cubra la siguiente funcionalidad:

#### Autentificación de usuario
Autenticar los repartidores de la empresa. Para ello se empleará el servicio Firebase Authentication.

#### Acceso a un servicio externo
Acceso a un servicio externo (api restful Web service) para la obtención de información relevante para la aplicación escogida (por ejemplo: lista de productos, lista de ciudades de reparto, etc…). Se sugiere utilizar ANY-API (https://any-api.com/) o Mashape (https://market.mashape.com/explore) para identificar apis sobre cualquier temática. Se recomienda utilizar las librerías Volley o Retrofit.

#### Gestión de repartos y trazabilidad de entregas.
Registrar fecha/hora de inicio del reparto, registrar las localizaciones intermedias del producto, fecha/hora de recogida/entrega, y registrar incidencias en la ruta. Para alojar esta información se utilizará Firebase Realtime Database. Adicionalmente se valorará positivamente el registro de imágenes en Firebase Storage: por ejemplo el código de barras (id paquete), fotografías para reportar sobre un paquete defectuoso, un accidente, etc.


> #### [Máster en Ingeniería Web por la Universidad Politécnica de Madrid (miw-upm)](http://miw.etsisi.upm.es)
> #### Asignatura: *Front-end para Móviles*

##### Autora: Vanesa Paniego Seco.
