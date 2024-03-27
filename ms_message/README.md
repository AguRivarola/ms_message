# mensajeria_ms

DB: mysql 8
MS: Java 17 - Springboot
VM: VirtualBox
Conteiners: Docker 
Orquestation: Docker Swarm 
- choco install docker-machine

Crear VM manager
- docker-machine create -d virtualbox --virtualbox-no-vtx-check manager1
Crear VM workers
- docker-machine create -d virtualbox --virtualbox-no-vtx-check worker1
- docker-machine create -d virtualbox --virtualbox-no-vtx-check worker2
Obtenemos las ip:
- docker-machine ls
Iniciamos el swarm en el manager:
- docker swarm init --advertise-addr {ip_manager1}:2377
Copiamos el comando generado:
- docker swarm join --token {token} {ip_manager1}
Mediante ssh entramos en los workers y corremos el comando anterior dentro:
- docker-machine ssh worker{num} 

- docker network create -d overlay open_cluster

- docker stack deploy --compose-file monitoring/docker-compose.yml monitoring
- docker stack deploy --compose-file mensajeria_ms/docker-compose.yml mensajeria


docker-compose --env-file ./default.env up -d

//AWS

Lanzamos una instancia ec2, la llamamos manager:
seleccionamos amazon linux 
