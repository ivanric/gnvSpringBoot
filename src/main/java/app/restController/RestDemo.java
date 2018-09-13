package app.restController;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.manager.ManejadorBeneficiarios;
import app.manager.ManejadorUsuarios;
import app.models.Persona;
import app.models.Usuario;


@RestController
public class RestDemo {
	@Autowired
	ManejadorBeneficiarios manejadorBeneficiarios;
	@Autowired 
	ManejadorUsuarios manejadorUsuarios;
	
	@RequestMapping("/init")
	public ResponseEntity<Map<String, Object>> inicio() {
		Map<String, Object> m=new HashMap<>();
		m.put("Dato","data");
		m.put("Nombre","New");
		m.put("Unive","Catedra");
		m.put("Facultad","Informatica");
		m.put("Yiente","Escuela");
		m.put("Zapatos","Orcle");
		return new ResponseEntity<Map<String,Object>>(m,HttpStatus.OK);
	}
//	@RequestMapping(value="listar")
//	public ResponseEntity<Beneficiario> listar(HttpServletRequest req,HttpServletResponse res){	
//		System.out.println("Entro");
//		Beneficiario data=new Beneficiario();
//		data=manejadorBeneficiarios.obtenerBeneficiario(1);
//		return new ResponseEntity<Beneficiario>(data,HttpStatus.OK);
//	}
	@RequestMapping("hola")
	private ResponseEntity<List<?>> publ() {
		// TODO Auto-generated method stub
		List<?> dato=manejadorBeneficiarios.data();
		return new ResponseEntity<List<?>>(dato,HttpStatus.OK);
	}
	@RequestMapping(value = "/demo", produces = "application/json")
	public Persona helloUser(Principal principal,HttpServletRequest request,Usuario u) {
		System.out.println("UserRecuperado: "+ u.toString());
		HttpSession session=request.getSession(true);
		Persona xusuario=this.manejadorUsuarios.iniciarSession(u.getLogin(),u.getPassword());

//		List<?> l=new ArrayList<>();
		session.setAttribute("username",xusuario);
		Persona p=(Persona) session.getAttribute("username");


		return p;
	}
	
	@RequestMapping(value="/salir",produces = "application/json")
	public Map<String, Object> salir(HttpSession session) {
		Persona person=(Persona) session.getAttribute("username");
		System.out.println("RECUPERANDOOOOO: "+person.toString());
//		session.invalidate();
		String Salio="nada sin session";
		if(person!=null) {
			Salio="si salio:";
		}
		Map<String, Object> m=new HashMap<>();
		m.put("cod:", Salio);
		return m;
		
	}
	@RequestMapping(value="/Jwt")
	public Map<String, Object> d(HttpServletRequest req){
//		HttpSession session=req.getSession();
//		System.out.println("YAUSER: "+session.getAttribute("username"));
//		System.out.println("YAUSER1: "+session.getAttribute("username"));
		Map<String, Object> m=new HashMap<>();
		m.put("1", "Course1");
		m.put("2", "Course2");
		return m;
	}
}
