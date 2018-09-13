package app.manager;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class ManejadorPermisos {
	private JdbcTemplate db;
	@Autowired
	public void setDataSource(DataSource dataSource){
		db = new JdbcTemplate(dataSource);
	}
	public boolean addOpcion(HttpServletRequest req){
		String sql="";
		int idRol=Integer.parseInt(req.getParameter("idRol"));
		int idMod=Integer.parseInt(req.getParameter("idMod"));
		int idProc=Integer.parseInt(req.getParameter("idProc"));
		int idOpc=Integer.parseInt(req.getParameter("idOpc"));
		try {
			sql="insert into permiso(idrol,idmod,idproc,idopc) values(?,?,?,?)";
			int a=this.db.update(sql,idRol,idMod,idProc,idOpc);
//			this.db.update(sql,id);
			System.out.println("sql_elimino: "+a);
			if (a==1) {
				return true;	
			} else {
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean removeOpcion(HttpServletRequest req){
		String sql="";
		int idRol=Integer.parseInt(req.getParameter("idRol"));
		int idMod=Integer.parseInt(req.getParameter("idMod"));
		int idProc=Integer.parseInt(req.getParameter("idProc"));
		int idOpc=Integer.parseInt(req.getParameter("idOpc"));
		try {
			sql="DELETE FROM permiso WHERE idrol=? and idmod=? and idproc=? and idopc=?";
			int a=this.db.update(sql,idRol,idMod,idProc,idOpc);

			System.out.println("sql_elimino: "+a);
			if (a==1) {
				return true;	
			} else {
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean removeProceso(HttpServletRequest req){
		String sql="";
		int idRol=Integer.parseInt(req.getParameter("idRol"));
		int idMod=Integer.parseInt(req.getParameter("idMod"));
		int idProc=Integer.parseInt(req.getParameter("idProc"));
		try {
			sql="DELETE FROM permiso WHERE idrol=? and idmod=? and idproc=?";
			int a=this.db.update(sql,idRol,idMod,idProc);

			System.out.println("sql_elimino: "+a);
		
			return true;	
		
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean removeModulo(HttpServletRequest req){
		String sql="";
		int idRol=Integer.parseInt(req.getParameter("idRol"));
		int idMod=Integer.parseInt(req.getParameter("idMod"));
		try {
			sql="DELETE FROM permiso WHERE idrol=? and idmod=?";
			int a=this.db.update(sql,idRol,idMod);
			System.out.println("sql_elimino: "+a);
			return true;	
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
