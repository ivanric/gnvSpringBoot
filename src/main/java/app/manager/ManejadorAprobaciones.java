package app.manager;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class ManejadorAprobaciones {
	private JdbcTemplate db;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		db = new JdbcTemplate(dataSource);
	}
	
	public int getTipoFinal(){
		String sql="SELECT idTipoAp FROM tipoAprobador WHERE codigo='afin'";
		return this.db.queryForObject(sql,Integer.class);
	}
	
	public int insertarAprobacion(String login,int idSolt,String aprob[],int aprobarFinal){
		int estado=0;
		String sql="";
		try {
			sql="INSERT INTO aprobacion(login,idsolt,idTipoAp) VALUES(?,?,?)";
			for (int i = 0; i < aprob.length; i++) {
				estado=this.db.update(sql,login,idSolt,Integer.parseInt(aprob[i]));
			}
			if(aprobarFinal!=0) {
				sql="UPDATE solicitud SET aprobadoSiNo=? WHERE idsolt=?";
				estado=this.db.update(sql,1,idSolt);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			e.printStackTrace();
			estado=0;
		}
		return estado;
	}
	public int insertarPausaAprobacion(int idsolt,int idTipoAp,String descripcion,String login){
		int pEstado=0;
		String sql="";
		int codPrimary=generarIdpausa();
		try {
			sql="INSERT INTO pausaAprobacion(idsolt,idTipoAp,descripcion,login,idpaAp) VALUES(?,?,?,?,?)";
			pEstado=this.db.update(sql,idsolt,idTipoAp,descripcion,login,codPrimary);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			e.printStackTrace();
			pEstado=0;
		}
		return pEstado;
	}
	public int generarIdpausa(){
		String sql="select COALESCE(max(idpaAp),0)+1 as idpaAp from pausaAprobacion";
		return db.queryForObject(sql, Integer.class);
	}
}
