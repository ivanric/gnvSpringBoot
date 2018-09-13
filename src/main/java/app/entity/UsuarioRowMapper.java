package app.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UsuarioRowMapper implements RowMapper<Usuario> {
	@Override
	public Usuario mapRow(ResultSet rs, int arg1) throws SQLException {
		Usuario u= new Usuario();
		u.setLogin(rs.getString("login"));
		u.setPassword(rs.getString("password"));
		u.setEstado(rs.getInt("estado"));
		u.setIdper(rs.getInt("idper"));
		return u;
    }
}
