package com.devrcz.exampleSpringBatch.batch;

import com.devrcz.exampleSpringBatch.bean.AcuerdoFinanciamientoBean;
import com.devrcz.exampleSpringBatch.bean.DatosBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class BatchProcessor implements ItemProcessor<AcuerdoFinanciamientoBean, DatosBean> {

    private static final Logger log = LoggerFactory.getLogger(BatchProcessor.class);

    @Override
    public DatosBean process(AcuerdoFinanciamientoBean acuerdoFinanciamientoBean) throws Exception {

        DatosBean datos = new DatosBean();

        if (acuerdoFinanciamientoBean != null) {

            if (acuerdoFinanciamientoBean.getTipo() == null) {
                acuerdoFinanciamientoBean.setTipo("MK!");
            }
            if (acuerdoFinanciamientoBean.getSub_tipo() == null) {

                acuerdoFinanciamientoBean.setSub_tipo("BYTE_TBS");
            }
        }
        log.info("Acuerdo afectado : " + acuerdoFinanciamientoBean.getId());

        datos.setId(acuerdoFinanciamientoBean.getId());
        datos.setDato_tipo(acuerdoFinanciamientoBean.getTipo());
        datos.setDato_sub_tipo(acuerdoFinanciamientoBean.getSub_tipo());
        return datos;
    }
}
