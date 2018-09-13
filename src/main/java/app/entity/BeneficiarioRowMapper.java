package app.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class BeneficiarioRowMapper implements RowMapper<Beneficiario>{
	@Override
	public Beneficiario mapRow(ResultSet rs, int arg1) throws SQLException {
		Beneficiario b= new Beneficiario();
		b.setIdben(rs.getInt("idben"));
		b.setInstitucion(rs.getString("institucion"));
		b.setDescripcion(rs.getString("descripcion"));
		b.setEstado(rs.getInt("estado"));
		b.setIdper(rs.getInt("idper"));
		return b;
    }
}
