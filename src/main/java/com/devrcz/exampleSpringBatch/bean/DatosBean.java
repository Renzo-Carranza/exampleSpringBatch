/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devrcz.exampleSpringBatch.bean;

import java.io.Serializable;

/**
 *
 * @author renzo
 */
public class DatosBean implements Serializable {

    private Long id;

    private String dato_tipo;

    private String dato_sub_tipo;

    public DatosBean() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDato_tipo() {
        return dato_tipo;
    }

    public void setDato_tipo(String dato_tipo) {
        this.dato_tipo = dato_tipo;
    }

    public String getDato_sub_tipo() {
        return dato_sub_tipo;
    }

    public void setDato_sub_tipo(String dato_sub_tipo) {
        this.dato_sub_tipo = dato_sub_tipo;
    }

}
