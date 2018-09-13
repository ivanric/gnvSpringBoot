package app.dao;
import java.util.List;

import app.entity.Telefono;
public interface ITelefonoDAO {
	List<Telefono> getAllTelefonosById(int idper);
}
