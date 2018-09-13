package app.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping({"/ProgramaGNV/"})
@Controller
public class ControladorProgramaGNV {
	
	@RequestMapping({"Gestion"})
	public String gestion(){
		return "programaGNV/GestionGNV"; 
	}
}
