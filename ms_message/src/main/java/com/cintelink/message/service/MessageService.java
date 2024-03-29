package com.cintelink.message.service;

import com.cintelink.message.model.Message;
import com.cintelink.message.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class MessageService {

    private static final Logger log = LoggerFactory.getLogger(MessageService.class);
    private MessageRepository repository;
    @Autowired
    public MessageService(MessageRepository repository) {
        this.repository = repository;
    }

    public Iterable<Message> getMessagesByUser(Integer idUser){
        return repository.findMessagesFromIdUser(idUser);
    }
}
