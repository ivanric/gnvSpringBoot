package app.manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import app.models.Persona;
import app.models.Usuario;
import app.utilidades.ALGORITMO3DES_Ecript_Desencript;
//@Service indica que la clase es un bean de la capa de negocio
@Service
public class ManejadorUsuarios {
	
	private JdbcTemplate db;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		db = new JdbcTemplate(dataSource);
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
			return p;
	    }
	}

	public List<Persona> Lista(HttpServletRequest req){
		String filtro=req.getParameter("filtro");
		int estado=Integer.parseInt(req.getParameter("estado"));
		String sql="select p.* from persona p join usuario u ON u.idper=p.idper where (concat(p.ap,' ',p.am,' ',p.nombres) LIKE ? or p.ci LIKE ?) and (p.estado=? or ?=-1) ORDER BY p.idper ASC";
		return this.db.query(sql, new objPersona(),"%"+filtro+"%","%"+filtro+"%",estado,estado);
	}
	
	
	public Usuario obtenerUsuario(int codper){
		return this.db.queryForObject("select * from usuario where idper=?", new objUsuario(),codper);
	}
	public Persona iniciarSession(String xlogin, String xpassword){
		System.out.println("entro consulta--> "+"xlogin: "+xlogin+ " xpassword:"+xpassword);
		String xsql="";
		try {
			xsql="select p.*,u.* FROM usuario u, persona p WHERE u.login=? and u.password=? and u.idper=p.idper and u.estado=1";
			return this.db.queryForObject(xsql, new objPersona() ,xlogin,xpassword);
		} catch (Exception e) {
			System.out.println("error iniciar_sesion="+e.toString());
			return null;
		}	
	}
//	public Persona iniciarSession(String xlogin, String xpassword){
//		System.out.println("entro consulta--> "+"xlogin: "+xlogin+ " xpassword:"+xpassword);
//		ALGORITMO3DES_Ecript_Desencript des=new ALGORITMO3DES_Ecript_Desencript();
//		String xsql="";
//		Persona p=null;
//		try {
//			Usuario user=this.db.queryForObject("select * from usuario where login=?", new objUsuario() ,xlogin);
//			if(user!=null) {
//				System.out.println("contraseña: "+des.Desencriptar(user.getPassword()));
//				if(des.Desencriptar(user.getPassword()).equals(xpassword)) {
//					xsql="select p.*,u.* FROM usuario u, persona p WHERE u.login=? and u.idper=p.idper and u.estado=1";
//					p=this.db.queryForObject(xsql, new objPersona() ,xlogin);
//					
//				}
//			}
//		
//
//		} catch (Exception e) {
//			System.out.println("error iniciar_sesion="+e.toString());
//		}	
//		return p;
//	}
	
	public boolean registrar(HttpServletRequest req,String tel[],String nombreFoto){
		ALGORITMO3DES_Ecript_Desencript des=new ALGORITMO3DES_Ecript_Desencript();
		int idper= generarIdPer();
		String ci=req.getParameter("ci");
		String ciCod=req.getParameter("ciCod");
		String nombres=req.getParameter("nombres");
		String ap=req.getParameter("ap");
		String am=req.getParameter("am");
		String genero=req.getParameter("genero");
		String direccion=req.getParameter("direccion");
		String email=req.getParameter("email");
		String usuario=req.getParameter("usuario");
		String password=req.getParameter("password");
		String sql="";
		try {
			sql="INSERT INTO persona(idper,ci,ciCod,nombres,ap,am,genero,direccion,email,foto,estado) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
			this.db.update(sql,idper,des.Encriptar(ci),ciCod,nombres.toUpperCase(),ap.toUpperCase(),am.toUpperCase(),genero,direccion.toUpperCase(),email,nombreFoto,1);
			sql="insert into telefono(numero,idper) values(?,?)";
			for (int i = 0; i < tel.length; i++) {
				if(!tel[i].equals("")) {
					this.db.update(sql,tel[i],idper);
				}
			}
			
			sql="insert into usuario(login,password,idper,estado) values(?,?,?,?)";
			this.db.update(sql,usuario,des.Encriptar(password),idper,1);
			
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	public String getUserByIdper(int idper) {
		return db.queryForObject("select u.login from usuario u,persona p where p.idper=u.idper and u.idper=?",String.class,idper);	
	}
	public int generarIdPer(){
		String sql="select COALESCE(max(idper),0)+1 as idper from persona";
		return db.queryForObject(sql, Integer.class);
	}

}