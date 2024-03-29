package com.cintelink.message.repository;

import com.cintelink.message.model.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {

    @Query("SELECT m FROM message m WHERE m.idUsuario = ?1")
    Iterable<Message> findMessagesFromIdUser(Integer idUser);
}
