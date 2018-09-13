package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.ITelefonoDAO;
import app.entity.Telefono;

@Service
public class TelefonoService  implements ITelefonoService{
	@Autowired private ITelefonoDAO telefonoDAO;
	
	@Override
	public List<Telefono> getAllTelefonosById(int idper) {
		return telefonoDAO.getAllTelefonosById(idper);
		
	}

}
