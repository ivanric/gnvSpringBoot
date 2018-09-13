package app.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import app.entity.Persona;
import app.entity.Usuario;
public interface IUsuarioDAO {
	public List<Persona> findAll(HttpServletRequest req);
	public Usuario getUsuarioById(int idper);
	public boolean save(HttpServletRequest req,String tel[],String nombreFoto);
	public boolean modify(HttpServletRequest req,String tel[],String nombreFoto);
	public Persona getPersonaById(int idper);
	public boolean changeStatus(HttpServletRequest req);
	public int generarIdPer();
	
}
