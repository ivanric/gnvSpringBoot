package app.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Permisos/")
public class ControladorPermisos {
	
	@RequestMapping("Gestion")
	public String Gestion() {
		return "permisos/gestion";
	}
	
}
