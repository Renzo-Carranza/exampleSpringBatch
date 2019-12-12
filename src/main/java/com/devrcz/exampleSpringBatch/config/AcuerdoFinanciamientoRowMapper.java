package com.devrcz.exampleSpringBatch.config;

import com.devrcz.exampleSpringBatch.bean.AcuerdoFinanciamientoBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class AcuerdoFinanciamientoRowMapper implements RowMapper<AcuerdoFinanciamientoBean> {

    @Override
    public AcuerdoFinanciamientoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
        AcuerdoFinanciamientoBean acuerdoFinanciamiento = new AcuerdoFinanciamientoBean();
        acuerdoFinanciamiento.setId(rs.getLong("id_acuerdo"));
        acuerdoFinanciamiento.setTipo(rs.getString("tipo"));
        acuerdoFinanciamiento.setSub_tipo(rs.getString("subtipo"));
        return acuerdoFinanciamiento;
    }
}
