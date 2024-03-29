package com.cintelink.message.controller;

import com.cintelink.message.model.Message;
import com.cintelink.message.model.User;
import com.cintelink.message.service.MessageService;
import com.cintelink.message.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
public class MessageController {
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
    private MessageService messageService;
    private UserService userService;

    @Autowired

    public MessageController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }


    @PostMapping("/messages")
    public ResponseEntity getMessageByUser(@RequestBody User requestUser) {
        logger.info("Busqueda de usuario {}", requestUser.getUsername());
        if (requestUser == null|| requestUser.getPassword() == null || requestUser.getUsername() == null) { //todo isEmpty()
            logger.error("No se ingreso un usuario correcto");
            return ResponseEntity.badRequest().body("Ingrese los datos requeridos");
        }

        User foundUser = userService.getExistingUser(requestUser.getUsername());
        if (foundUser == null) {
            logger.error("Ususario no encontrado {}", requestUser.getUsername());
            return ResponseEntity.badRequest().body("Contraseña o usuario equivocado");
        }
        if (!foundUser.getPassword().equals(requestUser.getPassword())) {
            logger.error("Contraseña erronea");
            return ResponseEntity.badRequest().body("Contraseña o usuario equivocado");
        }
        Iterable<Message> mensajes = messageService.getMessagesByUser(foundUser.getId());

        if (mensajes == null) {
            logger.error("No habian mensajes del usuario {}", requestUser.getUsername());
            return ResponseEntity.badRequest().body("No se encontraron mensajes para el usuario");
        }
        logger.info("exito en busqueda de usuario {}", requestUser.getUsername());
        return ResponseEntity.ok().body(mensajes);

    }

}
