package app.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class DocumentoRowMapper implements RowMapper<Documento> {
	@Override
	public Documento mapRow(ResultSet rs, int arg1) throws SQLException {
		Documento d= new Documento();
		d.setIddocb(rs.getInt("iddocb"));
		d.setNombre(rs.getString("nombre"));
		d.setEstado(rs.getInt("estado"));
		return d;
    }
}
