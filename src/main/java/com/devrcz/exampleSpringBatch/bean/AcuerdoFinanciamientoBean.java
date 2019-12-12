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
public class AcuerdoFinanciamientoBean implements Serializable {

    private Long id;

    private String acuerdo_tipo;

    private String acuerdo_sub_tipo;

    private String tipo;

    private String sub_tipo;

    public AcuerdoFinanciamientoBean() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAcuerdo_tipo() {
        return acuerdo_tipo;
    }

    public void setAcuerdo_tipo(String acuerdo_tipo) {
        this.acuerdo_tipo = acuerdo_tipo;
    }

    public String getAcuerdo_sub_tipo() {
        return acuerdo_sub_tipo;
    }

    public void setAcuerdo_sub_tipo(String acuerdo_sub_tipo) {
        this.acuerdo_sub_tipo = acuerdo_sub_tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSub_tipo() {
        return sub_tipo;
    }

    public void setSub_tipo(String sub_tipo) {
        this.sub_tipo = sub_tipo;
    }

}
