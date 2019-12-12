/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  renzo
 * Created: 12/12/2019
 */

CREATE TABLE CFM_DATOS (
"CFM_ID" NUMBER(15,0) PRIMARY KEY, 
"CFM_TIPO" VARCHAR2(50), 
"CFM_SUB_TIPO" VARCHAR2(50)
);
    
CREATE SEQUENCE CFM_DATOS_SEQ MINVALUE 1 MAXVALUE 999999999 START WITH 1 INCREMENT BY 1 CACHE 20;

--Obtener el valor actual de la secuencia
--select CFM_DATOS_SEQ.currval from dual;

--Sumar 1 a la secuencia
--select CFM_DATOS_SEQ.nextval from dual;

