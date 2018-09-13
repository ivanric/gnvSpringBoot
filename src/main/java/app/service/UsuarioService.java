package app.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.IUsuarioDAO;
import app.entity.Persona;
import app.entity.Usuario;
@Service
public class UsuarioService implements IUsuarioService {
	@Autowired
	private IUsuarioDAO usuarioDAO;
	
	@Override
	public List<Persona> findAll(HttpServletRequest req) {
		return usuarioDAO.findAll(req);
	}

	@Override
	public Usuario getUsuarioById(int idper) {
		Usuario u=usuarioDAO.getUsuarioById(idper);
		return u;
	}

	@Override
	public boolean save(HttpServletRequest req,String[] tel, String nombreFoto) {
		boolean status=usuarioDAO.save(req,tel, nombreFoto);
		return status;
	}

	@Override
	public int generarIdPer() {	
		return usuarioDAO.generarIdPer();
	}

	@Override
	public Persona getPersonaById(int idper) {
		Persona p=usuarioDAO.getPersonaById(idper);
		return p;
	}

	@Override
	public boolean modify(HttpServletRequest req, String[] tel, String nombreFoto) {
		boolean status=usuarioDAO.modify(req,tel, nombreFoto);
		return status;
	}

	@Override
	public boolean changeStatus(HttpServletRequest req) {
		boolean status=usuarioDAO.changeStatus(req);
		return status;
	}

}
