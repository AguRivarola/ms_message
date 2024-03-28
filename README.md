# ms_message
Services:

DB:
- Mysql(RDS)
Monitoring (Swarm Stack):
- Prometheus
- Grafana
- Loki
- Tempo
Messaging (Swarm service): 
- Spring Boot API
Reverse Proxy (Swarm service):
- Nginx

Metodo de testeo:
- Postman Collection


Para levantar la app de mensajeria, deberiamos tener una cuenta de AWS para levantar al menos 2 instancias de EC2.

Una vez dentro de nuestra cuenta, iremos a lanzar instancias de EC2, seleccionaremos la cantidad deseada (recomiendo 3: 1 manager y 2 workers).

- Seleccionaremos la "OS image" de Amazon Linux
- Generaremos o seleccionaremos el KeyPair deseado para ingresar
- En network crearemos un Security group, chequeando los los checkbox (ssh,https y http).
- En avanced details, abriremos el menu y en la seccion de "User data" (Al final del menu), arrastraremos el archivo "ec2_init.sh" ubicado en la carpeta "aws" o copiaremos su contenido manualmente. (validar que se cargue correctamente en caso de hacerlo a mano).
- Lanzar instancias seleccionadas y esperar a que se levanten.

Una vez levantadas:
- Seleccionar todas las instancias creadas desde la pagina "Instances" 
- En el menu "Instance state" seleccionar "Reboot Instance" para que se apliquen los cambios del script ec2_init.sh
- Cambiar los nombres de las instancias a "worker1", "worker2" y "manager" (es indiferente cual a que instancia, todas son iguales en este punto)

Cuando vuelvan a iniciar, iremos a la instancia que llamamos "manager". Nos conectaremos mediante ssh y correremos los siguientes comandos:
- docker swarm init 
Esto nos devolvera un comando parecido a:
- docker swarm join --token SWMTKN-1-xxxx xxx.xxx.xxx.xxx:2377
Deberiamos copiar el comando y (conectandonos mediante ssh en las instancias "workerX") correrlo.

Una vez realizado el paso anterior, deberiamos correr el siguiente comando en la instancia "manager":
- git clone https://github.com/AguRivarola/ms_message.git
Esto nos va a traer los archivos necesarios para poder levantar los servicios.

Una vez clonado, deberiamos tener una carpeta en el directorio donde nos encontramos. Movernos dentro con:
- cd ms_message/

# Networking
Crearemos la network que va a conectar los servicios:
- docker network create -d overlay msg-net

# Monitoring
Primeros deberiamos levantar las intancias de monitoreo:
 ### Falta el networking ? docker stack deploy --compose-file docker-compose.yml --network msg-net monitoring
- docker stack deploy --compose-file monitoring/docker-compose.yml monitoring
Una vez temine de correr el comando y las instancias esten deployadas, deberiamos poder verificar con lo siguiente si estan ok:
- docker service ls
o 
- docker stack ps monitoring

# Api

Luego de que se levante el servicio anterior deberiamos poder levantar la api:

- docker service create --name mensajeria --publish published=7654,target=7654 --network msg-net bondiolino/java_app


# Nginx
Luego deberiamos levantar el proxy reverso para la redireccion:

- docker service create --name proxy-reverso --publish published=80,target=80 --network msg-net --mount type=bind,src=./nginx/nginx.conf,dst=/etc/nginx/nginx.conf,readonly nginx


