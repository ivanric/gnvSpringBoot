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

import app.models.Opcion;

@Service
public class ManejadorOpciones{
	private JdbcTemplate db;
	@Autowired
	public void setDataSource(DataSource dataSource){
		db = new JdbcTemplate(dataSource);
	}
	public class objOpcion implements RowMapper<Opcion>{
		@Override
		public Opcion mapRow(ResultSet rs, int arg1) throws SQLException {
			Opcion o= new Opcion();
			o.setIdopc(rs.getInt("idopc"));
			o.setNombre(rs.getString("nombre"));
			o.setCodigo(rs.getString("codigo"));
			o.setEstado(rs.getInt("estado"));
			return o;
	    }
	}
	
	public  List<Opcion> getOpcionesByRolMenuProc(int idrol,int idmod,int idproc){
//		String sql = "select p.* from proceso p, modproc mp where mp.idmod =? AND mp.idproc = p.idproc";
		String sql = "select op.* from opcion op,rol r,modulo m,proceso p where r.idrol=? and m.idmod=? and p.idproc=? and op.idopc in (select pr.idopc from permiso pr where pr.idopc=op.idopc and r.idrol=pr.idrol and pr.idmod=m.idmod and pr.idproc=p.idproc)";
		return this.db.query(sql , new objOpcion(), new Object[]{idrol,idmod,idproc});
	}
}
