package app.manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import app.models.Modulo;
@Service
public class ManejadorModulos {
	private JdbcTemplate db;
	@Autowired
	public void setDataSource(DataSource dataSource){
		db = new JdbcTemplate(dataSource);
	}
	public class objModulo implements RowMapper<Modulo>{
		@Override
		public Modulo mapRow(ResultSet rs, int arg1) throws SQLException {
			Modulo m= new Modulo();
			m.setIdmod(rs.getInt("idmod"));
			m.setNombre(rs.getString("nombre"));
			m.setIcono(rs.getString("icono"));
			m.setEstado(rs.getInt("estado"));
			return m;
	    }
	}
	
	public List<Modulo> menusRol(int idrol){
//		String xsql="select m.* from usuario u,modulo m,rol r,rolusu ru where ru.idrol=r.idrol and ru.login=u.login and r.idrol=? and ru.login=? and m.idmod in (select p.idmod from permiso p where p.idmod=m.idmod) ORDER BY m.idmod ASC";
		String xsql="select m.* from modulo m,rol r where r.idrol=? and m.idmod in (select pm.idmod from permiso pm where pm.idmod=m.idmod and pm.idrol=r.idrol) ORDER BY m.idmod ASC";
		return this.db.query(xsql,  new objModulo(),idrol);
	}
	public List<Modulo> LisRolmenus(int idrol){
//		String xsql="select m.* from modulo m,rol r,rolmod rm where rm.idrol=r.idrol and m.idmod=rm.idmod and r.idrol = ?  ORDER BY r.idrol ASC";
		String xsql="select m.* from modulo m,rol r where r.idrol=? and m.idmod in (select pm.idmod from permiso pm where pm.idmod=m.idmod and pm.idrol=r.idrol) ORDER BY m.idmod ASC";
		return this.db.query(xsql, new objModulo(),idrol);
	}
}
