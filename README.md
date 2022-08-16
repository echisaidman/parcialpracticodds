# Parcial practico DDS

### Hecho por Ezequiel Saidman y Milagros Ramos 

**Link de OneDrive:** https://1drv.ms/u/s!Ah1kN8TWP3jdni34M2cR8W7Iq0U6?e=pFgiAd

**Script de la BD:** https://github.com/echisaidman/parcialpracticodds/blob/main/src/main/resources/db/parcialpracticodds.sql

### Notas
- Es necesario crear la BD `parcialpracticodds` antes de ejecutar el proyecto (las tablas despues las crea Hibernate automáticamente, pero necesita que la BD ya esté creada)
- Si al hacer una request devuelve "Forbidden" o algo similar, es porque primero hay que hacer una request a `api/auth/login` para obtener el JWT
