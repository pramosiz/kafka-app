Las tablas que autogenera Spring Batch las sitúa en el schema "public" por defecto.
No hay forma una propiedad que indique que las genere en otro schema.
La única posibilidad para ello es:
1. Escribir en un fichero SQL las mismas QUERIES que se usa para crear las tablas pero especificar en ellas el schema donde queremos crearlas.
1. Usar la propiedad spring.batch.jdbc.schema para indicar la ruta de ese fichero SQL