package app.utilidades;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Telefono;
import app.entity.Usuario;
import app.service.ITelefonoService;
import app.service.IUsuarioService;


@Service
public class OpcionesAdicionales {
	@Autowired private IUsuarioService iUsuarioService;
	@Autowired private ITelefonoService iTelefonoService;
	
	public List<Telefono> getTelefonosbyIdper(int idper) {
		return iTelefonoService.getAllTelefonosById(idper);
	}
	public Usuario getUserByIdper(int id) {
		return iUsuarioService.getUsuarioById(id);
	}
}
