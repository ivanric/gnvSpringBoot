package app.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.entity.Persona;
import app.entity.PersonaRowMapper;
import app.entity.Usuario;
import app.utilidades.ALGORITMO3DES_Ecript_Desencript;

@Transactional
@Repository
public class UsuarioDAO implements IUsuarioDAO {
	@Autowired
	private  JdbcTemplate db;
	
	@Override
	public List<Persona> findAll(HttpServletRequest req){
		String filtro=req.getParameter("filtro");
		int estado=Integer.parseInt(req.getParameter("estado"));
		String sql="select p.* from persona p join usuario u ON u.idper=p.idper where (concat(p.ap,' ',p.am,' ',p.nombres) LIKE ? or p.ci LIKE ?) and (p.estado=? or ?=-1) ORDER BY p.idper ASC";
		RowMapper<Persona> rowMapper=new PersonaRowMapper();
		return this.db.query(sql, rowMapper,"%"+filtro+"%","%"+filtro+"%",estado,estado);
	}

	@Override
	public Usuario getUsuarioById(int idper) {
		String sql="select * from usuario where idper=?";
		RowMapper<Usuario> rowMapper=new BeanPropertyRowMapper<Usuario>(Usuario.class);
		Usuario u=this.db.queryForObject(sql,rowMapper,idper);
		return u;
	}
	@Override
	public boolean save(HttpServletRequest req,String[] tel, String nombreFoto) {
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
	}

	@Override
	public int generarIdPer() {
		String sql="select COALESCE(max(idper),0)+1 as idper from persona";
		return db.queryForObject(sql, Integer.class);
		
	}

	@Override
	public Persona getPersonaById(int idper) {
		String sql="";
		Persona p=null;
		try {
			sql="SELECT p.* FROM persona p,usuario u WHERE u.idper=p.idper and p.idper=?";
			RowMapper<Persona> mapper=new BeanPropertyRowMapper<Persona>(Persona.class);
			p=this.db.queryForObject(sql,mapper,idper);
		} catch (Exception e) {
			p=null;
		}
		return p;
	}

	@Override
	public boolean modify(HttpServletRequest req, String[] tel, String nombreFoto) {
		String sql="";
		String ci=req.getParameter("ci");
		String ciCod=req.getParameter("ciCod");
		String nombres=req.getParameter("nombres");
		String ap=req.getParameter("ap");
		String am=req.getParameter("am");
		String genero=req.getParameter("genero");
		String direccion=req.getParameter("direccion");
		String email=req.getParameter("email");
		try {
			sql="UPDATE persona SET ci=?,ciCod=?,nombres=?,ap=?,am=?,genero=?,direccion=?,email=?,foto=? WHERE idper=?";
			this.db.update(sql,ci,ciCod,nombres.toUpperCase(),ap.toUpperCase(),am.toUpperCase(),genero,direccion.toUpperCase(),email,nombreFoto,req.getParameter("idper"));
			sql="delete from telefono where idper=?";
			this.db.update(sql,new Object[] {req.getParameter("idper")});
			sql="insert into telefono(numero,idper) values(?,?)";
			for (int i = 0; i < tel.length; i++) {
				if(!tel[i].equals("")) {
					this.db.update(sql,tel[i],req.getParameter("idper"));
				}
			}

			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean changeStatus(HttpServletRequest req){
		int idper=Integer.parseInt(req.getParameter("idper"));
		int estado=Integer.parseInt(req.getParameter("estado"));
		String sql="";
		int resp=0;
		try{
			if (estado==1) {
				sql="UPDATE persona SET estado=0 WHERE idper=?";	
				resp=this.db.update(sql,idper);
			}else{
				sql="UPDATE persona SET estado=1 WHERE idper=?";	
				resp=this.db.update(sql,idper);
			}

			if (resp==1) {
				return true;
			}else{
				return false;
			}

		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
