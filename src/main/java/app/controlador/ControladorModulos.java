package app.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping({"/Modulos/"})
@Controller
public class ControladorModulos {
	@RequestMapping({"Gestion"})
	public String gestion(){
		return "modulos/gestion";
	}
}
