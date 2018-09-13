package app.manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import app.manager.ManejadorRoles.objRol;
import app.models.Persona;
import app.models.Rol;
import app.models.Taller;
import app.models.Telefono;
import app.models.Usuario;
@Service
public class ManejadorTalleres {
	private JdbcTemplate db;
	@Autowired
	public void setDataSource(DataSource dataSource){
		db = new JdbcTemplate(dataSource);
	}
	
	public class objTaller implements RowMapper<Taller>{
		@Override
		public Taller mapRow(ResultSet rs, int arg1) throws SQLException {
			Taller t= new Taller();
			t.setIdtall(rs.getInt("idtall"));
			t.setNombretall(rs.getString("nombretall"));
			t.setDireccion(rs.getString("direccion"));
			t.setFecha_registro(rs.getString("fecha_registro"));
			t.setEstado(rs.getInt("estado"));
			try {
				t.setPersona(metPersona(rs.getInt("idper")));
			}catch (Exception e){
				t.setPersona(null);
			}
			return t;
	    }
	}
	public class objPersona implements RowMapper<Persona>{
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
			try {
				p.setUsuario(obtenerUsuario(rs.getInt("idper")));
			} catch (Exception e) {
				p.setUsuario(null);
			}
			try {
				p.setListaTelf(metListaTelf(rs.getInt("idper")));
			} catch (Exception e) {
				// TODO: handle exception
				p.setListaTelf(null);
			}
			return p;
	    }
	}
	public class objUsuario implements RowMapper<Usuario>{
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
	public class objTelefono implements RowMapper<Telefono>{
		@Override
		public Telefono mapRow(ResultSet rs, int arg1) throws SQLException {
			Telefono t= new Telefono();
			t.setNumero(rs.getString("numero"));	
			return t;
	    }
	}
	public Persona metPersona(int idper){
		String sql="SELECT p.* FROM persona p JOIN taller t ON t.idper=p.idper and p.idper=?";
		return this.db.queryForObject(sql,new objPersona(),idper);
	}
	public Usuario obtenerUsuario(int codper){
		return this.db.queryForObject("select * from usuario where idper=?", new objUsuario(),codper);
	}
	public List<Telefono> metListaTelf(int idper){
		return this.db.query("select * from telefono where idper=?", new objTelefono(),idper);
	}
	public int generarIdPer(){
		String sql="select COALESCE(max(idper),0)+1 as idper from persona";
		return db.queryForObject(sql, Integer.class);
	}
	public int generarIdTall(){
		String sql="select COALESCE(max(idtall),0)+1 as idtall from taller";
		return db.queryForObject(sql, Integer.class);
	}
	//REST_TALLERES
	public List<Taller> ListarTalleres(HttpServletRequest req){
		String filtro=req.getParameter("filtro");
		int estado=Integer.parseInt(req.getParameter("estado"));
		String sql="SELECT t.* FROM taller t where (t.nombretall LIKE ?) and (t.estado=? or ?=-1) ORDER BY t.idtall ASC";
		return this.db.query(sql, new objTaller(),"%"+filtro+"%",estado,estado);
	}
	public boolean registrar(HttpServletRequest req,Persona p){
		int idper= generarIdPer();
		int idtall= generarIdTall();
		String[] tel=req.getParameterValues("telefono[]");
		String sql="";
		try {
			sql="INSERT INTO persona(idper,ci,ciCod,nombres,ap,am,genero,direccion,email,estado) VALUES(?,?,?,?,?,?,?,?,?,?)";
			this.db.update(sql,idper,p.getCi(),p.getCiCod(),p.getNombres().toUpperCase(),p.getAp().toUpperCase(),p.getAm().toUpperCase(),p.getGenero(),p.getDireccion().toUpperCase(),p.getEmail(),1);
			sql="insert into telefono(numero,idper) values(?,?)";
			for (int i = 0; i < tel.length; i++) {
				if(!tel[i].equals("")) {
					this.db.update(sql,tel[i],idper);
				}
			}	
			sql="INSERT INTO taller(idtall,nombretall,direccion,estado,idper) VALUES(?,?,?,?,?)";
			this.db.update(sql,idtall,req.getParameter("nombretall").toUpperCase(),req.getParameter("direcciontall").toUpperCase(),1,idper);
			
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	public Taller datosModificar(HttpServletRequest req){
		String sql="";
		Taller t=null;
		int idtall=Integer.parseInt(req.getParameter("idtall"));
		System.out.println("idtall: "+idtall);
		try {
			sql="SELECT t.* FROM taller t WHERE t.idtall=?";
			t=this.db.queryForObject(sql,new objTaller(),idtall);
		} catch (Exception e) {
			t=null;
		}
		return t;
	}
	public boolean modificar(HttpServletRequest req,Persona p,String [] tel){
		String sql="";
		try {
			sql="UPDATE persona SET nombres=?,ap=?,am=?,genero=?,direccion=?,email=? WHERE idper=?";
			this.db.update(sql,p.getNombres().toUpperCase(),p.getAp().toUpperCase(),p.getAm().toUpperCase(),p.getGenero(),p.getDireccion().toUpperCase(),p.getEmail(),req.getParameter("idper"));
			
			sql="delete from telefono where idper=?";
			this.db.update(sql,new Object[] {req.getParameter("idper")});
			
			sql="insert into telefono(numero,idper) values(?,?)";
			for (int i = 0; i < tel.length; i++) {
				if(!tel[i].equals("")) {
					this.db.update(sql,tel[i],req.getParameter("idper"));
				}
			}
			sql="UPDATE taller SET nombretall=?,direccion=? WHERE idtall=?";
			this.db.update(sql,req.getParameter("nombretall").toUpperCase(),req.getParameter("direcciontall").toUpperCase(),req.getParameter("idtall"));
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	public boolean eliminar(Integer id){
		String sql="";
		try {
			sql="UPDATE taller  SET estado=0 WHERE idtall=?";
			int a=this.db.update(sql,id);
			this.db.update(sql,id);
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
	public boolean habilitar(Integer id){
		String sql="";
		try {
			sql="UPDATE taller SET estado=1 WHERE idtall=?";
			int a=this.db.update(sql,id);
			this.db.update(sql,id);
			System.out.println("sql_habilito: "+a);
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
}
