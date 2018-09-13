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

import app.models.Proceso;
@Service
public class ManejadorProcesos {
	private JdbcTemplate db;
	@Autowired
	public void setDataSource(DataSource dataSource){
		db = new JdbcTemplate(dataSource);
	}
	public class objProceso implements RowMapper<Proceso>{
		@Override
		public Proceso mapRow(ResultSet rs, int arg1) throws SQLException {
			Proceso p= new Proceso();
			p.setIdproc(rs.getInt("idproc"));
			p.setNombre(rs.getString("nombre"));
			p.setEnlace(rs.getString("enlace"));
			p.setIcono(rs.getString("icono"));
			p.setEstado(rs.getInt("estado"));
			return p;
	    }
	}
	
	public  List<Proceso> getProcesosByMenu(Integer idmod,int idrol){
//		String sql = "select p.* from proceso p, modproc mp where mp.idmod =? AND mp.idproc = p.idproc";
		String sql = "select p.* from proceso p,rol r,modulo m where m.idmod=? and r.idrol=? and p.idproc in (select pr.idproc from permiso pr where pr.idproc=p.idproc and r.idrol=pr.idrol and m.idmod=pr.idmod)";
		return this.db.query(sql , new objProceso(), new Object[]{idmod,idrol});
	}
}
