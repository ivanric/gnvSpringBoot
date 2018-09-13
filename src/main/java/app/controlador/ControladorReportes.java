package app.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping({"/Reportes/"})
@Controller
public class ControladorReportes {

	@RequestMapping({"Gestion"})
	public String reporte(){
		System.out.println("llego reporte ajax");
		return "reportes/gestion";
	}
}
