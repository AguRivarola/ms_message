package com.cintelink.message.model;

import jakarta.persistence.*;


@Entity(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @Column(name = "id_usuario")
    private Integer idUsuario;
    private String asunto;
    private String mensaje;
//    private Boolean leido;

    public Message() {
    }

    public Message(Integer idUsuario, String asunto, String mensaje) {
        this.idUsuario = idUsuario;
        this.asunto = asunto;
        this.mensaje = mensaje;
    }

    public Integer getId() {
        return id;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
