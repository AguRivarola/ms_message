DELETE FROM message;
DELETE FROM message_user;
INSERT INTO message_user (username, password) values ("agustin98", "1234");
INSERT INTO message_user (username, password) values ("root", "root");
INSERT INTO message (asunto, id_usuario, mensaje) VALUES ("asunto 1", (SELECT u.id FROM message_user u where u.username = "agustin98"), "Mensaje 1");
INSERT INTO message (asunto, id_usuario, mensaje) VALUES ("asunto 2", (SELECT u.id FROM message_user u where u.username = "agustin98"), "Mensaje 2");
INSERT INTO message (asunto, id_usuario, mensaje) VALUES ("asunto 3", (SELECT u.id FROM message_user u where u.username = "root"), "Mensaje 3");
INSERT INTO message (asunto, id_usuario, mensaje) VALUES ("asunto 4", (SELECT u.id FROM message_user u where u.username = "root"), "Mensaje 4");
