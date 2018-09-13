package app.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PersonaRowMapper implements RowMapper<Persona>{
	@Override
	public Persona mapRow(ResultSet rs, int arg1) throws SQLException {
		Persona p= new Persona();
		p.setIdper(rs.getInt("idper"));
		p.setCi(rs.getString("ci"));
		p.setCiCod(rs.getString("ciCod"));
		p.setNombres(rs.getString("nombres"));
		p.setAp(rs.getString("ap"));
		p.setAm(rs.getString("am"));
		p.setGenero(rs.getString("genero"));
		p.setDireccion(rs.getString("direccion"));
		p.setEmail(rs.getString("email"));
		p.setFoto(rs.getString("foto"));
		p.setEstado(rs.getInt("estado"));
		return p;
    }
	
}
