package app.controlador;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import app.models.Persona;


//@RequestMapping("/principal/")
@Controller
public class Controlador {

	
	@RequestMapping(value = "/")
	public String init(Model model) {
		return "login";
	}
	
	@RequestMapping(value = "/index")
	public String index(Model model) {
		return "login";
	}
	
	
	@RequestMapping(value = "/inicio")
	public String inicio(HttpServletRequest request, Model model) {
		
		HttpSession sesion=request.getSession(true);
		Persona xuser=(Persona) sesion.getAttribute("xusuario");
		try {
			if (xuser==null){
				return "redirect:/index";
			}else{
				model.addAttribute("msg","Bienvenido "+xuser.getNombres()+" "+xuser.getAp()+" "+xuser.getAm());
				model.addAttribute("usuario",xuser);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "inicio";
	
	}
	
	@RequestMapping({"/alerta"})
	public String alerta(Model m,HttpServletRequest req,HttpServletResponse res){
		m.addAttribute("mensaje","Usted desea salir del Sistema..?");
		return "principal/desconectar";
	}
	@RequestMapping({"/desconectar"})
	public String desc(Model m,HttpServletRequest req,HttpServletResponse res){
		HttpSession sesion=req.getSession(true);
		sesion.removeAttribute("xusuario");
		return "redirect:index";
	}
	
	
}
