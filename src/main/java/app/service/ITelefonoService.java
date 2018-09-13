package app.service;

import java.util.List;

import app.entity.Telefono;

public interface ITelefonoService {
	List<Telefono> getAllTelefonosById(int idper);
}
