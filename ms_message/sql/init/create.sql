
CREATE DATABASE mensajeria;


CREATE TABLE mensajeria.message
(
id INT(20) AUTO_INCREMENT,
asunto VARCHAR(20),
id_usuario INT(20),
mensaje VARCHAR(20),
PRIMARY KEY (id)
);

CREATE TABLE mensajeria.message_user
(
id INT(20) AUTO_INCREMENT,
username VARCHAR(20),
password VARCHAR(20),
PRIMARY KEY (id)
);