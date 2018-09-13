package app.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class TelefonoRowMapper implements RowMapper<Telefono>{
	@Override
	public Telefono mapRow(ResultSet rs, int arg1) throws SQLException {
		Telefono t= new Telefono();
		t.setIdper(rs.getInt("idper"));
		t.setNumero(rs.getString("numero"));
		return t;
    }
}
